/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.util.timeseries.fudge;

import org.fudgemsg.mapping.FudgeBuilder;

import com.opengamma.util.timeseries.fast.DateTimeNumericEncoding;
import com.opengamma.util.timeseries.fast.longint.FastArrayLongDoubleTimeSeries;

/**
 * 
 *
 * @author jim
 */
public class FastArrayLongDoubleTimeSeriesBuilder extends FastLongDoubleTimeSeriesBuilder<FastArrayLongDoubleTimeSeries> implements
    FudgeBuilder<FastArrayLongDoubleTimeSeries> {
  
  public FastArrayLongDoubleTimeSeries makeSeries(DateTimeNumericEncoding encoding, long[] times, double[] values) {
    return new FastArrayLongDoubleTimeSeries(encoding, times, values);
  }

}
