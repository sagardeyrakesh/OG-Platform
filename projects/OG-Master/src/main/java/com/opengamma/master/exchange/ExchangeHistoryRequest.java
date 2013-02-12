/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.exchange;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.Instant;

import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.master.AbstractHistoryRequest;
import com.opengamma.util.PublicSPI;

/**
 * Request for the history of an exchange.
 * <p>
 * A full exchange master implements historical storage of data.
 * History can be stored in two dimensions and this request provides searching.
 * <p>
 * The first historic dimension is the classic series of versions.
 * Each new version is stored in such a manor that previous versions can be accessed.
 * <p>
 * The second historic dimension is corrections.
 * A correction occurs when it is realized that the original data stored was incorrect.
 * A simple exchange master might simply replace the original version with the corrected value.
 * A full implementation will store the correction in such a manner that it is still possible
 * to obtain the value before the correction was made.
 * <p>
 * For example, an exchange added on Monday and updated on Thursday has two versions.
 * If it is realized on Friday that the version stored on Monday was incorrect, then a
 * correction may be applied. There are now two versions, the first of which has one correction.
 * This may continue, with multiple corrections allowed for each version.
 * <p>
 * Versions and corrections are represented by instants in the search.
 */
@PublicSPI
@BeanDefinition
public class ExchangeHistoryRequest extends AbstractHistoryRequest {

  /**
   * The depth of exchange data to return.
   * False will only return the basic information held in the {@code Exchange} class.
   * True will load the full exchange data, such as calendar entries.
   * By default this is false to save space in the response.
   */
  @PropertyDefinition
  private boolean _fullDetail;

  /**
   * Creates an instance.
   * The object identifier must be added before searching.
   */
  public ExchangeHistoryRequest() {
    super();
  }

  /**
   * Creates an instance with object identifier.
   * This will retrieve all versions and corrections unless the relevant fields are set.
   * 
   * @param objectId  the object identifier, not null
   */
  public ExchangeHistoryRequest(final ObjectIdentifiable objectId) {
    super(objectId);
  }

  /**
   * Creates an instance with object identifier and optional version and correction.
   * 
   * @param objectId  the object identifier, not null
   * @param versionInstant  the version instant to retrieve, null for all versions
   * @param correctedToInstant  the instant that the data should be corrected to, null for all corrections
   */
  public ExchangeHistoryRequest(final ObjectIdentifiable objectId, Instant versionInstant, Instant correctedToInstant) {
    super(objectId, versionInstant, correctedToInstant);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExchangeHistoryRequest}.
   * @return the meta-bean, not null
   */
  public static ExchangeHistoryRequest.Meta meta() {
    return ExchangeHistoryRequest.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(ExchangeHistoryRequest.Meta.INSTANCE);
  }

  @Override
  public ExchangeHistoryRequest.Meta metaBean() {
    return ExchangeHistoryRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1233600576:  // fullDetail
        return isFullDetail();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1233600576:  // fullDetail
        setFullDetail((Boolean) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExchangeHistoryRequest other = (ExchangeHistoryRequest) obj;
      return JodaBeanUtils.equal(isFullDetail(), other.isFullDetail()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(isFullDetail());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the depth of exchange data to return.
   * False will only return the basic information held in the {@code Exchange} class.
   * True will load the full exchange data, such as calendar entries.
   * By default this is false to save space in the response.
   * @return the value of the property
   */
  public boolean isFullDetail() {
    return _fullDetail;
  }

  /**
   * Sets the depth of exchange data to return.
   * False will only return the basic information held in the {@code Exchange} class.
   * True will load the full exchange data, such as calendar entries.
   * By default this is false to save space in the response.
   * @param fullDetail  the new value of the property
   */
  public void setFullDetail(boolean fullDetail) {
    this._fullDetail = fullDetail;
  }

  /**
   * Gets the the {@code fullDetail} property.
   * False will only return the basic information held in the {@code Exchange} class.
   * True will load the full exchange data, such as calendar entries.
   * By default this is false to save space in the response.
   * @return the property, not null
   */
  public final Property<Boolean> fullDetail() {
    return metaBean().fullDetail().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExchangeHistoryRequest}.
   */
  public static class Meta extends AbstractHistoryRequest.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code fullDetail} property.
     */
    private final MetaProperty<Boolean> _fullDetail = DirectMetaProperty.ofReadWrite(
        this, "fullDetail", ExchangeHistoryRequest.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "fullDetail");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1233600576:  // fullDetail
          return _fullDetail;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExchangeHistoryRequest> builder() {
      return new DirectBeanBuilder<ExchangeHistoryRequest>(new ExchangeHistoryRequest());
    }

    @Override
    public Class<? extends ExchangeHistoryRequest> beanType() {
      return ExchangeHistoryRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code fullDetail} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> fullDetail() {
      return _fullDetail;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
