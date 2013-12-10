/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.fxforward;

import static com.opengamma.sesame.FunctionResultGenerator.failure;
import static com.opengamma.sesame.FunctionResultGenerator.propagateFailure;
import static com.opengamma.sesame.FunctionResultGenerator.success;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderDiscount;
import com.opengamma.analytics.financial.provider.description.interestrate.ProviderUtils;
import com.opengamma.financial.security.fx.FXForwardSecurity;
import com.opengamma.sesame.DiscountingMulticurveBundleFn;
import com.opengamma.sesame.FXMatrixFn;
import com.opengamma.sesame.FailureStatus;
import com.opengamma.sesame.FunctionResult;
import com.opengamma.sesame.MarketExposureSelector;
import com.opengamma.sesame.MarketExposureSelectorFn;
import com.opengamma.util.money.Currency;
import com.opengamma.util.tuple.Pair;

public class FXForwardDiscountingCalculatorFn implements FXForwardCalculatorFn {

  private final FXForwardCalculatorFactory _factory;

  private final MarketExposureSelectorFn _marketExposureSelectorFn;
  private final FXMatrixFn _fxMatrixProvider;
  private final DiscountingMulticurveBundleFn _multicurveBundleProviderFunction;

  public FXForwardDiscountingCalculatorFn(FXForwardCalculatorFactory factory,
                                          MarketExposureSelectorFn marketExposureSelectorFn,
                                          FXMatrixFn fxMatrixProvider,
                                          DiscountingMulticurveBundleFn multicurveBundleProviderFunction) {
    _factory = factory;
    _marketExposureSelectorFn = marketExposureSelectorFn;
    _fxMatrixProvider = fxMatrixProvider;
    _multicurveBundleProviderFunction = multicurveBundleProviderFunction;
  }

  @Override
  public FunctionResult<FXForwardCalculator> generateCalculator(FXForwardSecurity security) {

    // get currencies from security, probably should use visitor/utils
    Set<Currency> currencies = ImmutableSet.of(security.getPayCurrency(), security.getReceiveCurrency());

    // Even if we can't get a matrix we want to get as far as we can to
    // ensure market data population, so ignore the result for now
    FunctionResult<FXMatrix> fxmResult = _fxMatrixProvider.getFXMatrix(currencies);

    FunctionResult<MarketExposureSelector> mesResult = _marketExposureSelectorFn.getMarketExposureSelector();

    if (mesResult.isResultAvailable()) {

      Set<String> incompleteBundles = new HashSet<>();
      Set<MulticurveProviderDiscount> bundles = new HashSet<>();
      CurveBuildingBlockBundle mergedJacobianBundle = new CurveBuildingBlockBundle();

      MarketExposureSelector selector = mesResult.getResult();
      Set<String> curveConfigNames = selector.determineCurveConfigurationsForSecurity(security);

      for (String name : curveConfigNames) {

        FunctionResult<Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle>> bundle =
            _multicurveBundleProviderFunction.generateBundle(name);

        if (bundle.isResultAvailable()) {
          Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> result = bundle.getResult();
          bundles.add(result.getFirst());
          mergedJacobianBundle.addAll(result.getSecond());
        } else {
          incompleteBundles.add(name);
        }
      }

      if (!curveConfigNames.isEmpty() && incompleteBundles.isEmpty() && fxmResult.isResultAvailable()) {

        MulticurveProviderDiscount bundle = mergeBundlesAndMatrix(bundles, fxmResult.getResult());
        return success(_factory.createCalculator(security, bundle, mergedJacobianBundle));

      } else if (curveConfigNames.isEmpty()) {
        return failure(FailureStatus.MISSING_DATA, "No matching curves found for security: {}", security);
      } else if (!incompleteBundles.isEmpty()) {
        return failure(FailureStatus.MISSING_DATA, "Missing complete curve bundles(s) for: {}", incompleteBundles);
      } else {
        return propagateFailure(fxmResult);
      }
    } else {
      return propagateFailure(mesResult);
    }
  }

  private MulticurveProviderDiscount mergeBundlesAndMatrix(Collection<MulticurveProviderDiscount> providers,
                                                           FXMatrix fxMatrix) {
    // Don't merge when we only have a single provider bundle
    return providers.size() > 1 ?
        ProviderUtils.mergeDiscountingProviders(mergeBundles(providers), fxMatrix) :
        providers.iterator().next();
  }

  private MulticurveProviderDiscount mergeBundles(Collection<MulticurveProviderDiscount> providers) {
    return ProviderUtils.mergeDiscountingProviders(providers);
  }
}
