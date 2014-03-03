/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.master.historicaltimeseries.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.LocalDate;

import com.opengamma.core.historicaltimeseries.impl.NonVersionedRedisHistoricalTimeSeriesSource;
import com.opengamma.core.value.MarketDataRequirementNames;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.ExternalIdBundleWithDates;
import com.opengamma.id.ObjectId;
import com.opengamma.id.UniqueId;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolutionResult;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolver;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolverWithBasicChangeManager;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.util.ArgumentChecker;

/**
 * Implements {@link HistoricalTimeSeriesResolver} on top of any Redis HTS source.
 */
public class RedisSimulationSeriesResolver extends HistoricalTimeSeriesResolverWithBasicChangeManager {

  private static final Logger s_logger = LoggerFactory.getLogger(RedisSimulationSeriesResolver.class);
  private final NonVersionedRedisHistoricalTimeSeriesSource[] _redisSources;

  public RedisSimulationSeriesResolver(NonVersionedRedisHistoricalTimeSeriesSource... redisSources) {
    ArgumentChecker.notNull(redisSources, "sources");
    ArgumentChecker.notNegativeOrZero(redisSources.length, "redisSources must not be empty");
    _redisSources = redisSources;
  }
  
  @Override
  public HistoricalTimeSeriesResolutionResult resolve(ExternalIdBundle identifierBundle, LocalDate identifierValidityDate, String dataSource, String dataProvider, String dataField,
                                                      String resolutionKey) {
    if (identifierBundle.isEmpty()) {
      return null; // is this the correct action?
    } else if (identifierBundle.size() > 1) {
      s_logger.warn("Attempted to call RedisSimulationSeriesSource with bundle {}. Calls with more than 1 entry in ID bundle are probably misuse of this class.", identifierBundle);
    }
    ExternalId externalId = identifierBundle.getExternalIds().iterator().next();
    if (!MarketDataRequirementNames.MARKET_VALUE.equals(dataField)) {
      //s_logger.warn("Redis simulation asked for {} for {}, can only handle market value.", dataField, externalId);
      return null;
    }
    final UniqueId uniqueId = UniqueId.of(externalId.getScheme().getName(), externalId.getValue());
    
    boolean oneMatches = false;
    for (NonVersionedRedisHistoricalTimeSeriesSource source : _redisSources) {
      if (source.exists(uniqueId)) {
        oneMatches = true;
        break;
      }
    }
    
    if (!oneMatches) {
      return null;
    }
    
    ManageableHistoricalTimeSeriesInfo htsInfo = new ManageableHistoricalTimeSeriesInfo() {
      private static final long serialVersionUID = 1L;

      @Override
      public UniqueId getUniqueId() {
        return uniqueId;
      }

      @Override
      public ExternalIdBundleWithDates getExternalIdBundle() {
        throw new UnsupportedOperationException("Unsupported operation.");
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException("Unsupported operation.");
      }

      @Override
      public String getDataField() {
        throw new UnsupportedOperationException("Unsupported operation.");
      }

      @Override
      public String getDataSource() {
        throw new UnsupportedOperationException("Unsupported operation.");
      }

      @Override
      public String getDataProvider() {
        throw new UnsupportedOperationException("Unsupported operation.");
      }

      @Override
      public String getObservationTime() {
        throw new UnsupportedOperationException("Unsupported operation.");
      }

      @Override
      public ObjectId getTimeSeriesObjectId() {
        throw new UnsupportedOperationException("Unsupported operation.");
      }

    };
    HistoricalTimeSeriesResolutionResult result = new HistoricalTimeSeriesResolutionResult(htsInfo);
    return result;
  }
}
