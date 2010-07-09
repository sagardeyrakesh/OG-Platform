/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.model.option.pricing.tree;

import static org.junit.Assert.assertEquals;

import javax.time.calendar.ZonedDateTime;

import org.junit.Test;

import com.opengamma.financial.model.interestrate.curve.ConstantYieldCurve;
import com.opengamma.financial.model.option.definition.BoyleTrinomialOptionModelDefinition;
import com.opengamma.financial.model.option.definition.EuropeanVanillaOptionDefinition;
import com.opengamma.financial.model.option.definition.OptionDefinition;
import com.opengamma.financial.model.option.definition.StandardOptionDataBundle;
import com.opengamma.financial.model.option.definition.TrinomialOptionModelDefinition;
import com.opengamma.financial.model.tree.RecombiningTrinomialTree;
import com.opengamma.financial.model.volatility.surface.ConstantVolatilitySurface;
import com.opengamma.math.function.Function1D;
import com.opengamma.util.time.DateUtil;
import com.opengamma.util.time.Expiry;
import com.opengamma.util.tuple.DoublesPair;

/**
 * 
 */
public class TrinomialOptionModelTest {
  private static final ZonedDateTime DATE = DateUtil.getUTCDate(2009, 1, 1);
  private static final Expiry EXPIRY = new Expiry(DateUtil.getDateOffsetWithYearFraction(DATE, 1));
  private static final OptionDefinition CALL = new EuropeanVanillaOptionDefinition(100, EXPIRY, true);
  private static final StandardOptionDataBundle DATA =
      new StandardOptionDataBundle(new ConstantYieldCurve(0.06), 0.03, new ConstantVolatilitySurface(0.2), 100., DATE);
  private static final TrinomialOptionModelDefinition<OptionDefinition, StandardOptionDataBundle> TRINOMIAL = new MyTrinomialOptionModelDefinition();

  @Test
  public void test() {
    final TrinomialOptionModel<StandardOptionDataBundle> model = new TrinomialOptionModel<StandardOptionDataBundle>(3, TRINOMIAL);
    final Function1D<StandardOptionDataBundle, RecombiningTrinomialTree<DoublesPair>> f = model.getTreeGeneratingFunction(CALL);
    final DoublesPair[][] tree = f.evaluate(DATA).getTree();
    final DoublesPair[][] expected = new DoublesPair[4][7];
    final double df1 = Math.exp(-0.02);
    final double df2 = Math.exp(-0.04);
    final double df3 = Math.exp(-0.06);
    expected[0][0] = DoublesPair.of(df3 * 100., 8.4253);
    expected[1][0] = DoublesPair.of(df2 * 81.87, 0.6525);
    expected[1][1] = DoublesPair.of(df2 * 100., 6.4148);
    expected[1][2] = DoublesPair.of(df2 * 122.14, 24.0802);
    expected[2][0] = DoublesPair.of(df1 * 67.03, 0.);
    expected[2][1] = DoublesPair.of(df1 * 81.87, 0.);
    expected[2][2] = DoublesPair.of(df1 * 100., 3.8008);
    expected[2][3] = DoublesPair.of(df1 * 122.14, 22.9051);
    expected[2][4] = DoublesPair.of(df1 * 149.18, 49.6782);
    expected[3][0] = DoublesPair.of(54.88, 0.);
    expected[3][1] = DoublesPair.of(67.03, 0.);
    expected[3][2] = DoublesPair.of(81.87, 0.);
    expected[3][3] = DoublesPair.of(100., 0.);
    expected[3][4] = DoublesPair.of(122.14, 22.1403);
    expected[3][5] = DoublesPair.of(149.18, 49.1825);
    expected[3][6] = DoublesPair.of(182.21, 82.2119);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < RecombiningTrinomialTree.NODES.evaluate(i); j++) {
        assertEquals(tree[i][j].first, expected[i][j].first, 1e-2);
        assertEquals(tree[i][j].second, expected[i][j].second, 1e-4);
      }
    }
  }

  protected static class MyTrinomialOptionModelDefinition extends BoyleTrinomialOptionModelDefinition {

    @Override
    public double getDX(final OptionDefinition option, final StandardOptionDataBundle data, final int n, final int j) {
      return 0.2;
    }
  }
}
