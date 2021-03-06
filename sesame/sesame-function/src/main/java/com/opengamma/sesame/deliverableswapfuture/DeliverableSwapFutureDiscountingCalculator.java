/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.deliverableswapfuture;

import java.util.Map;

import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitor;
import com.opengamma.analytics.financial.interestrate.future.calculator.FuturesPriceMulticurveCalculator;
import com.opengamma.analytics.financial.provider.calculator.discounting.PV01CurveParametersCalculator;
import com.opengamma.analytics.financial.provider.calculator.discounting.PresentValueCurveSensitivityDiscountingCalculator;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.analytics.financial.provider.description.interestrate.ParameterProviderInterface;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyMulticurveSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyParameterSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.parameter.ParameterSensitivityParameterCalculator;
import com.opengamma.analytics.util.amount.ReferenceAmount;
import com.opengamma.financial.analytics.conversion.DeliverableSwapFutureTradeConverter;
import com.opengamma.financial.analytics.conversion.FixedIncomeConverterDataProvider;
import com.opengamma.financial.analytics.model.fixedincome.BucketedCurveSensitivities;
import com.opengamma.financial.analytics.timeseries.HistoricalTimeSeriesBundle;
import com.opengamma.sesame.CurveMatrixLabeller;
import com.opengamma.sesame.ZeroIRDeltaBucketingUtils;
import com.opengamma.sesame.trade.DeliverableSwapFutureTrade;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;
import com.opengamma.util.result.Result;
import com.opengamma.util.tuple.Pair;

/**
 * Calculator for deliverable swap futures using the discounting method.
 */
public class DeliverableSwapFutureDiscountingCalculator implements DeliverableSwapFutureCalculator {
  
  /**
   * Calculator for the DSF price.
   */  
  private static final FuturesPriceMulticurveCalculator FPMC = FuturesPriceMulticurveCalculator.getInstance();
  
  /**
   * Calculator for the PV01.
   */  
  private static final PV01CurveParametersCalculator<ParameterProviderInterface> PV01C =
      new PV01CurveParametersCalculator<>(PresentValueCurveSensitivityDiscountingCalculator.getInstance());
  
  /** 
   * The curve sensitivity calculator 
   */
  private static final InstrumentDerivativeVisitor<ParameterProviderInterface, MultipleCurrencyMulticurveSensitivity> 
  PVCSDC = PresentValueCurveSensitivityDiscountingCalculator.getInstance();
  
  /** 
   * The parameter sensitivity calculator 
   */
  private static final ParameterSensitivityParameterCalculator<ParameterProviderInterface> PSC =
      new ParameterSensitivityParameterCalculator<>(PVCSDC);
            
  /**
   * Derivative form of the security.
   */
  private final InstrumentDerivative _derivative;
  
  /**
   * The multicurve bundle.
   */  
  private final MulticurveProviderInterface _multicurveBundle;

  /**
   * The curve labellers.
   */
  private final Map<String, CurveMatrixLabeller> _curveLabellers;
  
  /**
   * Contract-specific scaling for the futures lot size.
   */
  
  private final double _unitAmount;
  
  /**
   * Provides scaling to/from basis points.
   */
  private static final double BP_FACTOR = 1.0E-4;
  
  /**
   * Provides scaling to Futures equivalence - Analytics representations use 1 as opposed to 100.
   */
  private static final double FUTURES_EQUIV_FACTOR = 100;
  
  /**
   * Constructs a calculator using the discounting method.
   * @param trade the trade to calculate results on.
   * @param multicurveBundle the multicurve bundle used to calculate results from.
   * @param converter the converter used to create the definition form of the trade.
   * @param valDateTime the valuation time.
   * @param definitionToDerivativeConverter the converter used to create the derivative form of the trade.
   * @param tsBundle the time series bundle containing the last margin price of the future.
   * @param curveLabellers a map comprising of the curve names and labellers.
   */  
  public DeliverableSwapFutureDiscountingCalculator(DeliverableSwapFutureTrade trade,
                                                    MulticurveProviderInterface multicurveBundle,
                                                    DeliverableSwapFutureTradeConverter converter,
                                                    ZonedDateTime valDateTime,
                                                    FixedIncomeConverterDataProvider definitionToDerivativeConverter,
                                                    HistoricalTimeSeriesBundle tsBundle,
                                                    Map<String, CurveMatrixLabeller> curveLabellers) {
    _derivative = createInstrumentDerivative(trade, converter, valDateTime, definitionToDerivativeConverter, tsBundle);
    _multicurveBundle = ArgumentChecker.notNull(multicurveBundle, "multicurveBundle");
    _curveLabellers = ArgumentChecker.notNull(curveLabellers, "curveLabellers");
    _unitAmount = trade.getSecurity().getUnitAmount();
  } 
  
  @Override
  public Result<Double> calculateSecurityModelPrice() {
    return Result.success(_derivative.accept(FPMC, _multicurveBundle));
  }

  @Override
  public Result<ReferenceAmount<Pair<String, Currency>>> calculatePV01() {
    return Result.success(_derivative.accept(PV01C, _multicurveBundle)
                          .multiplyBy(FUTURES_EQUIV_FACTOR)
                          .multiplyBy(_unitAmount));
  }
  
  @Override
  public Result<BucketedCurveSensitivities> calculateBucketedZeroIRDelta() {
    
    MultipleCurrencyParameterSensitivity sensitivities = 
        PSC.calculateSensitivity(_derivative, _multicurveBundle)
                                .multipliedBy(BP_FACTOR)
                                .multipliedBy(FUTURES_EQUIV_FACTOR)
                                .multipliedBy(_unitAmount);
    
    BucketedCurveSensitivities bucketedCurveSensitivities = 
        ZeroIRDeltaBucketingUtils.getBucketedCurveSensitivities(sensitivities, _curveLabellers);
    
    return Result.success(bucketedCurveSensitivities);
  }
  
  /**
   * Create the OG-Analytics derivative representation of a deliverable swap future from the given trade.
   * @param deliverableSwapFutureTrade the trade to convert to an OG-Analytics derivative.
   * @param converter the converter used to create the deliverable swap future definition.
   * @param valuationTime the valuation time at which to create the derivative.
   * @param definitionToDerivConverter the converter used to convert from the definition to derivative.
   * @param tsBundle the time series bundle containing the last margin price of the future.
   * @return the derivative representation of the deliverable swap future trade.
   */
  private InstrumentDerivative createInstrumentDerivative(DeliverableSwapFutureTrade deliverableSwapFutureTrade,
                                                          DeliverableSwapFutureTradeConverter converter,
                                                          ZonedDateTime valuationTime,
                                                          FixedIncomeConverterDataProvider definitionToDerivConverter,
                                                          HistoricalTimeSeriesBundle tsBundle) {
    InstrumentDefinition<?> definition = converter.convert(deliverableSwapFutureTrade.getTrade());
    return definitionToDerivConverter.convert(deliverableSwapFutureTrade.getSecurity(), 
                                                   definition, 
                                                   valuationTime, 
                                                   tsBundle);
  }
}
