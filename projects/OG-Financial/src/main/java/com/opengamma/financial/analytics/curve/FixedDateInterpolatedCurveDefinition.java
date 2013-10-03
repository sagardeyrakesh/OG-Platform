/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import java.util.List;
import java.util.Set;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;
import org.threeten.bp.LocalDate;

import com.opengamma.core.config.Config;
import com.opengamma.financial.analytics.ircurve.strips.CurveNode;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Definition for interpolated curves where some of the node dates are fixed.
 */
@BeanDefinition
@Config(description = "Fixed date interpolated curve definition")
public class FixedDateInterpolatedCurveDefinition extends InterpolatedCurveDefinition {

  /** Serialization verison. */
  private static final long serialVersionUID = 1L;

  /**
   * The fixed curve dates.
   */
  @PropertyDefinition(validate = "notNull")
  private List<LocalDate> _fixedDates;

  /**
   * For the builder.
   */
  /* package */ FixedDateInterpolatedCurveDefinition() {
    super();
  }

  /**
   * @param name The name of the curve, not null
   * @param nodes The nodes of the curve, not null
   * @param interpolatorName The interpolator name, not null
   * @param fixedDates The fixed dates, not null
   */
  public FixedDateInterpolatedCurveDefinition(final String name, final Set<CurveNode> nodes, final String interpolatorName, final List<LocalDate> fixedDates) {
    super(name, nodes, interpolatorName);
    setFixedDates(fixedDates);
  }

  /**
   * @param name The name of the curve, not null
   * @param nodes The nodes of the curve, not null
   * @param interpolatorName The interpolator name, not null
   * @param extrapolatorName The name of the left and right extrapolators
   * @param fixedDates  the fixed set of dates
   */
  public FixedDateInterpolatedCurveDefinition(final String name, final Set<CurveNode> nodes, final String interpolatorName, final String extrapolatorName,
      final List<LocalDate> fixedDates) {
    super(name, nodes, interpolatorName, extrapolatorName);
    setFixedDates(fixedDates);
  }

  /**
   * @param name The name of the curve, not null
   * @param nodes The nodes of the curve, not null
   * @param interpolatorName The interpolator name, not null
   * @param rightExtrapolatorName The right extrapolator name
   * @param leftExtrapolatorName The left extrapolator name
   * @param fixedDates  the fixed set of dates
   */
  public FixedDateInterpolatedCurveDefinition(final String name, final Set<CurveNode> nodes, final String interpolatorName, final String rightExtrapolatorName,
      final String leftExtrapolatorName, final List<LocalDate> fixedDates) {
    super(name, nodes, interpolatorName, rightExtrapolatorName, leftExtrapolatorName);
    setFixedDates(fixedDates);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FixedDateInterpolatedCurveDefinition}.
   * @return the meta-bean, not null
   */
  public static FixedDateInterpolatedCurveDefinition.Meta meta() {
    return FixedDateInterpolatedCurveDefinition.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FixedDateInterpolatedCurveDefinition.Meta.INSTANCE);
  }

  @Override
  public FixedDateInterpolatedCurveDefinition.Meta metaBean() {
    return FixedDateInterpolatedCurveDefinition.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1682421617:  // fixedDates
        return getFixedDates();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1682421617:  // fixedDates
        setFixedDates((List<LocalDate>) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_fixedDates, "fixedDates");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FixedDateInterpolatedCurveDefinition other = (FixedDateInterpolatedCurveDefinition) obj;
      return JodaBeanUtils.equal(getFixedDates(), other.getFixedDates()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getFixedDates());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the fixed curve dates.
   * @return the value of the property, not null
   */
  public List<LocalDate> getFixedDates() {
    return _fixedDates;
  }

  /**
   * Sets the fixed curve dates.
   * @param fixedDates  the new value of the property, not null
   */
  public void setFixedDates(List<LocalDate> fixedDates) {
    JodaBeanUtils.notNull(fixedDates, "fixedDates");
    this._fixedDates = fixedDates;
  }

  /**
   * Gets the the {@code fixedDates} property.
   * @return the property, not null
   */
  public final Property<List<LocalDate>> fixedDates() {
    return metaBean().fixedDates().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FixedDateInterpolatedCurveDefinition}.
   */
  public static class Meta extends InterpolatedCurveDefinition.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code fixedDates} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<LocalDate>> _fixedDates = DirectMetaProperty.ofReadWrite(
        this, "fixedDates", FixedDateInterpolatedCurveDefinition.class, (Class) List.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "fixedDates");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1682421617:  // fixedDates
          return _fixedDates;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FixedDateInterpolatedCurveDefinition> builder() {
      return new DirectBeanBuilder<FixedDateInterpolatedCurveDefinition>(new FixedDateInterpolatedCurveDefinition());
    }

    @Override
    public Class<? extends FixedDateInterpolatedCurveDefinition> beanType() {
      return FixedDateInterpolatedCurveDefinition.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code fixedDates} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<LocalDate>> fixedDates() {
      return _fixedDates;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
