/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import java.io.Serializable;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;
import com.opengamma.id.UniqueId;

/**
 * 
 */
@BeanDefinition
public class CurveConfigurationSpecification extends DirectBean implements Serializable {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The id of the target.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _targetId;

  /**
   * The priority of the this computation configuration specification (lower is higher priority, with zero the highest)
   */
  @PropertyDefinition
  private int _priority;

  /* package */CurveConfigurationSpecification() {
  }

  public CurveConfigurationSpecification(final ExternalId targetId, final int priority) {
    setTargetId(targetId);
    setPriority(priority);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CurveConfigurationSpecification}.
   * @return the meta-bean, not null
   */
  public static CurveConfigurationSpecification.Meta meta() {
    return CurveConfigurationSpecification.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(CurveConfigurationSpecification.Meta.INSTANCE);
  }

  @Override
  public CurveConfigurationSpecification.Meta metaBean() {
    return CurveConfigurationSpecification.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -441951604:  // targetId
        return getTargetId();
      case -1165461084:  // priority
        return getPriority();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -441951604:  // targetId
        setTargetId((ExternalId) newValue);
        return;
      case -1165461084:  // priority
        setPriority((Integer) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_targetId, "targetId");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CurveConfigurationSpecification other = (CurveConfigurationSpecification) obj;
      return JodaBeanUtils.equal(getTargetId(), other.getTargetId()) &&
          JodaBeanUtils.equal(getPriority(), other.getPriority());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getTargetId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPriority());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the id of the target.
   * @return the value of the property, not null
   */
  public ExternalId getTargetId() {
    return _targetId;
  }

  /**
   * Sets the id of the target.
   * @param targetId  the new value of the property, not null
   */
  public void setTargetId(ExternalId targetId) {
    JodaBeanUtils.notNull(targetId, "targetId");
    this._targetId = targetId;
  }

  /**
   * Gets the the {@code targetId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> targetId() {
    return metaBean().targetId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the priority of the this computation configuration specification (lower is higher priority, with zero the highest)
   * @return the value of the property
   */
  public int getPriority() {
    return _priority;
  }

  /**
   * Sets the priority of the this computation configuration specification (lower is higher priority, with zero the highest)
   * @param priority  the new value of the property
   */
  public void setPriority(int priority) {
    this._priority = priority;
  }

  /**
   * Gets the the {@code priority} property.
   * @return the property, not null
   */
  public final Property<Integer> priority() {
    return metaBean().priority().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CurveConfigurationSpecification}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code targetId} property.
     */
    private final MetaProperty<ExternalId> _targetId = DirectMetaProperty.ofReadWrite(
        this, "targetId", CurveConfigurationSpecification.class, ExternalId.class);
    /**
     * The meta-property for the {@code priority} property.
     */
    private final MetaProperty<Integer> _priority = DirectMetaProperty.ofReadWrite(
        this, "priority", CurveConfigurationSpecification.class, Integer.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "targetId",
        "priority");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -441951604:  // targetId
          return _targetId;
        case -1165461084:  // priority
          return _priority;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CurveConfigurationSpecification> builder() {
      return new DirectBeanBuilder<CurveConfigurationSpecification>(new CurveConfigurationSpecification());
    }

    @Override
    public Class<? extends CurveConfigurationSpecification> beanType() {
      return CurveConfigurationSpecification.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code targetId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> targetId() {
      return _targetId;
    }

    /**
     * The meta-property for the {@code priority} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> priority() {
      return _priority;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}