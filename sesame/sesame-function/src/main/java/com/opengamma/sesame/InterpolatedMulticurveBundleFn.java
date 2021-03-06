/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.threeten.bp.ZonedDateTime;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.index.IndexON;
import com.opengamma.analytics.financial.model.interestrate.curve.DiscountCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldAndDiscountCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldPeriodicCurve;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlock;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderDiscount;
import com.opengamma.analytics.math.curve.InterpolatedDoublesCurve;
import com.opengamma.analytics.math.interpolation.CombinedInterpolatorExtrapolatorFactory;
import com.opengamma.analytics.math.interpolation.Interpolator1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.util.time.TimeCalculator;
import com.opengamma.core.link.ConventionLink;
import com.opengamma.core.link.SecurityLink;
import com.opengamma.core.marketdatasnapshot.SnapshotDataBundle;
import com.opengamma.financial.analytics.curve.AbstractCurveDefinition;
import com.opengamma.financial.analytics.curve.AbstractCurveSpecification;
import com.opengamma.financial.analytics.curve.ConverterUtils;
import com.opengamma.financial.analytics.curve.CurveConstructionConfiguration;
import com.opengamma.financial.analytics.curve.CurveGroupConfiguration;
import com.opengamma.financial.analytics.curve.CurveTypeConfiguration;
import com.opengamma.financial.analytics.curve.DiscountingCurveTypeConfiguration;
import com.opengamma.financial.analytics.curve.IborCurveTypeConfiguration;
import com.opengamma.financial.analytics.curve.InterpolatedCurveSpecification;
import com.opengamma.financial.analytics.curve.OvernightCurveTypeConfiguration;
import com.opengamma.financial.analytics.ircurve.strips.ContinuouslyCompoundedRateNode;
import com.opengamma.financial.analytics.ircurve.strips.CurveNode;
import com.opengamma.financial.analytics.ircurve.strips.CurveNodeWithIdentifier;
import com.opengamma.financial.analytics.ircurve.strips.DiscountFactorNode;
import com.opengamma.financial.analytics.ircurve.strips.PeriodicallyCompoundedRateNode;
import com.opengamma.financial.convention.IborIndexConvention;
import com.opengamma.financial.convention.OvernightIndexConvention;
import com.opengamma.financial.security.index.OvernightIndex;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.money.Currency;
import com.opengamma.util.result.Result;
import com.opengamma.util.result.SuccessStatus;
import com.opengamma.util.time.Tenor;
import com.opengamma.util.tuple.Pair;
import com.opengamma.util.tuple.Pairs;

/**
 * Multicurve bundle function using interpolated (pre-fitted) curves.
 */
public class InterpolatedMulticurveBundleFn implements DiscountingMulticurveBundleFn {
  
  private final CurveSpecificationFn _curveSpecificationProvider;
  private final CurveSpecificationMarketDataFn _curveSpecificationMarketDataProvider;
  
  public InterpolatedMulticurveBundleFn(CurveSpecificationFn curveSpecificationProvider,
                                        CurveSpecificationMarketDataFn curveSpecificationMarketDataProvider) {
    // TODO argument checker
    _curveSpecificationProvider = curveSpecificationProvider;
    _curveSpecificationMarketDataProvider = curveSpecificationMarketDataProvider;
  }

  @Override
  public Result<MulticurveBundle> generateBundle(
     Environment env, CurveConstructionConfiguration curveConfig,
     Map<CurveConstructionConfiguration, Result<MulticurveBundle>> requiredCurves) {

    // Interim result object used to build up the complete set of
    // failures rather than exiting early
    Result<Boolean> result = Result.success(true);
    
    ZonedDateTime now = env.getValuationTime();
    MulticurveProviderDiscount curveBundle = new MulticurveProviderDiscount(new FXMatrix());
    LinkedHashMap<String, Pair<CurveBuildingBlock, DoubleMatrix2D>> unitBundles = new LinkedHashMap<>();
    
    /* For each group of curves */
    for (CurveGroupConfiguration group: curveConfig.getCurveGroups()) {
      
      /* For each curve definition */
      for (Entry<AbstractCurveDefinition, List<? extends CurveTypeConfiguration>> entry: group.resolveTypesForCurves().entrySet()) {

        LinkedHashMap<String, Pair<Integer, Integer>> unitMap = new LinkedHashMap<>();
        int totalNodes = 0;
        AbstractCurveDefinition curve = entry.getKey();        
        Result<AbstractCurveSpecification> curveSpecResult = _curveSpecificationProvider.getCurveSpecification(env, curve);
        
        if (curveSpecResult.isSuccess()) {

          InterpolatedCurveSpecification specification = (InterpolatedCurveSpecification) curveSpecResult.getValue();
          
          Result<Map<ExternalIdBundle, Double>> marketDataResult =
              _curveSpecificationMarketDataProvider.requestData(env, specification);
          
          if (marketDataResult.getStatus() == SuccessStatus.SUCCESS) {
            
            // todo this is temporary to allow us to get up and running fast
            SnapshotDataBundle snapshot = createSnapshotDataBundle(marketDataResult.getValue());
            
            Set<CurveNodeWithIdentifier> nodes = specification.getNodes();
            int n = nodes.size();
            double[] ttm = new double[n];
            double[] yields = new double[n];
            double[][] jacobian = new double[n][n];
            
            int i = 0;
            int compoundPeriodsPerYear = 0;
            int nNodesForCurve = specification.getNodes().size();
            boolean isYield = false;
            for (CurveNodeWithIdentifier node: nodes) {
              CurveNode curveNode = node.getCurveNode();
              if (curveNode instanceof ContinuouslyCompoundedRateNode) {
                if (i == 0) {
                  // First node - set expectation that all nodes are ContinuouslyCompoundedRateNodes
                  isYield = true;
                } else {
                  if (!isYield) {
                    throw new OpenGammaRuntimeException("Expected only continuously-compounded rate nodes, found " + curveNode);
                  }
                }
              } else if (curveNode instanceof DiscountFactorNode) {
                if (i == 0) {
                  // First node - set expectation that all nodes are DiscountFactorNodes
                  isYield = false;
                } else {
                  if (isYield) {
                    throw new OpenGammaRuntimeException("Expected only discount factor nodes, found " + curveNode);
                  }
                }
              } else if (curveNode instanceof PeriodicallyCompoundedRateNode) {
                if (i == 0) {
                  compoundPeriodsPerYear = ((PeriodicallyCompoundedRateNode) curveNode).getCompoundingPeriodsPerYear();
                  isYield = true;
                } else {
                  if (!isYield) {
                    throw new OpenGammaRuntimeException("Was expecting only periodically compounded nodes; have " + curveNode);
                  }
                }
              } else {
                throw new OpenGammaRuntimeException("Can only handle discount factor or continuously-compounded rate nodes; have " + curveNode);
              }
              
              // ttm
              Tenor maturity = curveNode.getResolvedMaturity();
              ttm[i] = TimeCalculator.getTimeBetween(now, now.plus(maturity.getPeriod()));
              
              // yield
              Double yield = snapshot.getDataPoint(node.getIdentifier());
              if (yield == null) {
                throw new OpenGammaRuntimeException("Could not get market data value for " + node);
              }
              yields[i] = yield;
              jacobian[i][i] = 1;
              i++;
            }

            Interpolator1D interpolator = 
                CombinedInterpolatorExtrapolatorFactory.getInterpolator(specification.getInterpolatorName(),
                                                                        specification.getLeftExtrapolatorName(),
                                                                        specification.getRightExtrapolatorName());
            String curveName = curve.getName();
            InterpolatedDoublesCurve rawCurve = InterpolatedDoublesCurve.from(ttm, yields, interpolator, curveName);
            YieldAndDiscountCurve discountCurve;
            if (compoundPeriodsPerYear != 0 && isYield) {
              discountCurve = YieldPeriodicCurve.from(compoundPeriodsPerYear, rawCurve);
            } else if (isYield) {
              discountCurve = new YieldCurve(curveName, rawCurve);
            } else {
              discountCurve = new DiscountCurve(curveName, rawCurve);
            }
                        
            for (CurveTypeConfiguration type: entry.getValue()) {
              if (type instanceof DiscountingCurveTypeConfiguration) {
                Currency currency = Currency.parse(((DiscountingCurveTypeConfiguration) type).getReference());
                curveBundle.setCurve(currency, discountCurve);
              } else if (type instanceof IborCurveTypeConfiguration) {
                curveBundle.setCurve(createIborIndex((IborCurveTypeConfiguration) type), discountCurve);
              } else if (type instanceof OvernightCurveTypeConfiguration) {
                curveBundle.setCurve(createIndexON((OvernightCurveTypeConfiguration) type), discountCurve);
              } else {
                throw new OpenGammaRuntimeException("Unsupported curve type configuration " + type);
              }
            }

            unitMap.put(curveName, Pairs.of(totalNodes, nNodesForCurve));
            unitBundles.put(curveName, Pairs.of(new CurveBuildingBlock(unitMap), new DoubleMatrix2D(jacobian)));
            totalNodes += nNodesForCurve;
          } else {
            result = Result.failure(result, marketDataResult);
          }
        } else {
          result = Result.failure(result, curveSpecResult);
        }
        
      }
    }
    
    if (result.isSuccess()) {
      return Result.success(new MulticurveBundle(curveBundle, new CurveBuildingBlockBundle(unitBundles)));
    } else {
      return Result.failure(result);
    }
  }

  private static SnapshotDataBundle createSnapshotDataBundle(Map<ExternalIdBundle, Double> marketData) {
    SnapshotDataBundle snapshotDataBundle = new SnapshotDataBundle();

    for (Map.Entry<ExternalIdBundle, Double> entry : marketData.entrySet()) {
      snapshotDataBundle.setDataPoint(entry.getKey(), entry.getValue());
    }
    return snapshotDataBundle;
  }

  private IndexON createIndexON(OvernightCurveTypeConfiguration type) {
    OvernightIndex security = SecurityLink.resolvable(type.getConvention(), OvernightIndex.class).resolve();
    OvernightIndexConvention indexConvention =
        ConventionLink.resolvable(security.getConventionId(), OvernightIndexConvention.class).resolve();
    return ConverterUtils.indexON(security.getName(), indexConvention);
  }

  private IborIndex createIborIndex(IborCurveTypeConfiguration type) {
    com.opengamma.financial.security.index.IborIndex security =
        SecurityLink.resolvable(type.getConvention(), com.opengamma.financial.security.index.IborIndex.class).resolve();
    IborIndexConvention indexConvention =
        ConventionLink.resolvable(security.getConventionId(), IborIndexConvention.class).resolve();
    return ConverterUtils.indexIbor(security.getName(), indexConvention, security.getTenor());
  }

  @Override
  public Result<ImpliedDepositCurveData> extractImpliedDepositCurveData(
      Environment env, CurveConstructionConfiguration curveConfig,
      Map<CurveConstructionConfiguration, Result<MulticurveBundle>> builtCurves) {
    throw new UnsupportedOperationException();
  }

}
