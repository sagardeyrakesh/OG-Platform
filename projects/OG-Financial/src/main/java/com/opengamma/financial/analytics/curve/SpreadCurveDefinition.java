/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.config.Config;
import com.opengamma.core.config.ConfigGroups;
import com.opengamma.util.ArgumentChecker;

/**
 * Definition for spread curves. There are two forms supported:
 * <ul>
 *   <li> Two curves and the operation to perform on these curves.
 *   <li> One curve, a constant spread value with units, and the operation.
 * </ul>
 * <p>
 * Note that the operation might not be commutative, so the order of the inputs is important.
 */
@BeanDefinition
@Config(description = "Spread curve definition", group = ConfigGroups.CURVES)
public class SpreadCurveDefinition extends AbstractCurveDefinition {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The first curve.
   */
  @PropertyDefinition(validate = "notNull")
  private String _firstCurve;

  /**
   * The second curve.
   */
  @PropertyDefinition
  private String _secondCurve;

  /**
   * The spread.
   */
  @PropertyDefinition
  private Double _spread;

  /**
   * The spread unit
   */
  @PropertyDefinition
  private String _units;

  /**
   * The operation name.
   */
  @PropertyDefinition(validate = "notNull")
  private String _operationName;

  /**
   * For the builder.
   */
  /* package */ SpreadCurveDefinition() {
    super();
  }

  /**
   * @param name The curve definition name, not null
   * @param firstCurve The first curve, not null
   * @param secondCurve The second curve, not null
   * @param operationName The operation name, not null
   */
  public SpreadCurveDefinition(final String name, final String firstCurve, final String secondCurve,
      final String operationName) {
    super(name);
    ArgumentChecker.notNull(secondCurve, "secondCurve");
    setFirstCurve(firstCurve);
    setSecondCurve(secondCurve);
    setOperationName(operationName);
  }

  /**
   * @param name The curve definition name, not null
   * @param curve The curve, not null
   * @param spread The spread
   * @param units The spread units, not null
   * @param operationName The operation name, not null
   */
  public SpreadCurveDefinition(final String name, final String curve, final double spread, final String units,
      final String operationName) {
    super(name);
    ArgumentChecker.notNull(units, "units");
    setFirstCurve(curve);
    setSpread(spread);
    setUnits(units);
    setOperationName(operationName);
  }

  /**
   * Returns true if the spread curve is constructed using a curve
   * and a constant value.
   * @return True if the spread curve is represented as a curve, operation and
   * constant value.
   */
  public boolean isNumericalSpread() {
    return _units != null;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SpreadCurveDefinition}.
   * @return the meta-bean, not null
   */
  public static SpreadCurveDefinition.Meta meta() {
    return SpreadCurveDefinition.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SpreadCurveDefinition.Meta.INSTANCE);
  }

  @Override
  public SpreadCurveDefinition.Meta metaBean() {
    return SpreadCurveDefinition.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the first curve.
   * @return the value of the property, not null
   */
  public String getFirstCurve() {
    return _firstCurve;
  }

  /**
   * Sets the first curve.
   * @param firstCurve  the new value of the property, not null
   */
  public void setFirstCurve(String firstCurve) {
    JodaBeanUtils.notNull(firstCurve, "firstCurve");
    this._firstCurve = firstCurve;
  }

  /**
   * Gets the the {@code firstCurve} property.
   * @return the property, not null
   */
  public final Property<String> firstCurve() {
    return metaBean().firstCurve().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the second curve.
   * @return the value of the property
   */
  public String getSecondCurve() {
    return _secondCurve;
  }

  /**
   * Sets the second curve.
   * @param secondCurve  the new value of the property
   */
  public void setSecondCurve(String secondCurve) {
    this._secondCurve = secondCurve;
  }

  /**
   * Gets the the {@code secondCurve} property.
   * @return the property, not null
   */
  public final Property<String> secondCurve() {
    return metaBean().secondCurve().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spread.
   * @return the value of the property
   */
  public Double getSpread() {
    return _spread;
  }

  /**
   * Sets the spread.
   * @param spread  the new value of the property
   */
  public void setSpread(Double spread) {
    this._spread = spread;
  }

  /**
   * Gets the the {@code spread} property.
   * @return the property, not null
   */
  public final Property<Double> spread() {
    return metaBean().spread().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spread unit
   * @return the value of the property
   */
  public String getUnits() {
    return _units;
  }

  /**
   * Sets the spread unit
   * @param units  the new value of the property
   */
  public void setUnits(String units) {
    this._units = units;
  }

  /**
   * Gets the the {@code units} property.
   * @return the property, not null
   */
  public final Property<String> units() {
    return metaBean().units().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the operation name.
   * @return the value of the property, not null
   */
  public String getOperationName() {
    return _operationName;
  }

  /**
   * Sets the operation name.
   * @param operationName  the new value of the property, not null
   */
  public void setOperationName(String operationName) {
    JodaBeanUtils.notNull(operationName, "operationName");
    this._operationName = operationName;
  }

  /**
   * Gets the the {@code operationName} property.
   * @return the property, not null
   */
  public final Property<String> operationName() {
    return metaBean().operationName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public SpreadCurveDefinition clone() {
    return (SpreadCurveDefinition) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SpreadCurveDefinition other = (SpreadCurveDefinition) obj;
      return JodaBeanUtils.equal(getFirstCurve(), other.getFirstCurve()) &&
          JodaBeanUtils.equal(getSecondCurve(), other.getSecondCurve()) &&
          JodaBeanUtils.equal(getSpread(), other.getSpread()) &&
          JodaBeanUtils.equal(getUnits(), other.getUnits()) &&
          JodaBeanUtils.equal(getOperationName(), other.getOperationName()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getFirstCurve());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSecondCurve());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpread());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnits());
    hash += hash * 31 + JodaBeanUtils.hashCode(getOperationName());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("SpreadCurveDefinition{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
    buf.append("firstCurve").append('=').append(JodaBeanUtils.toString(getFirstCurve())).append(',').append(' ');
    buf.append("secondCurve").append('=').append(JodaBeanUtils.toString(getSecondCurve())).append(',').append(' ');
    buf.append("spread").append('=').append(JodaBeanUtils.toString(getSpread())).append(',').append(' ');
    buf.append("units").append('=').append(JodaBeanUtils.toString(getUnits())).append(',').append(' ');
    buf.append("operationName").append('=').append(JodaBeanUtils.toString(getOperationName())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SpreadCurveDefinition}.
   */
  public static class Meta extends AbstractCurveDefinition.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code firstCurve} property.
     */
    private final MetaProperty<String> _firstCurve = DirectMetaProperty.ofReadWrite(
        this, "firstCurve", SpreadCurveDefinition.class, String.class);
    /**
     * The meta-property for the {@code secondCurve} property.
     */
    private final MetaProperty<String> _secondCurve = DirectMetaProperty.ofReadWrite(
        this, "secondCurve", SpreadCurveDefinition.class, String.class);
    /**
     * The meta-property for the {@code spread} property.
     */
    private final MetaProperty<Double> _spread = DirectMetaProperty.ofReadWrite(
        this, "spread", SpreadCurveDefinition.class, Double.class);
    /**
     * The meta-property for the {@code units} property.
     */
    private final MetaProperty<String> _units = DirectMetaProperty.ofReadWrite(
        this, "units", SpreadCurveDefinition.class, String.class);
    /**
     * The meta-property for the {@code operationName} property.
     */
    private final MetaProperty<String> _operationName = DirectMetaProperty.ofReadWrite(
        this, "operationName", SpreadCurveDefinition.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "firstCurve",
        "secondCurve",
        "spread",
        "units",
        "operationName");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -186618849:  // firstCurve
          return _firstCurve;
        case 239629531:  // secondCurve
          return _secondCurve;
        case -895684237:  // spread
          return _spread;
        case 111433583:  // units
          return _units;
        case 91797650:  // operationName
          return _operationName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SpreadCurveDefinition> builder() {
      return new DirectBeanBuilder<SpreadCurveDefinition>(new SpreadCurveDefinition());
    }

    @Override
    public Class<? extends SpreadCurveDefinition> beanType() {
      return SpreadCurveDefinition.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code firstCurve} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> firstCurve() {
      return _firstCurve;
    }

    /**
     * The meta-property for the {@code secondCurve} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> secondCurve() {
      return _secondCurve;
    }

    /**
     * The meta-property for the {@code spread} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> spread() {
      return _spread;
    }

    /**
     * The meta-property for the {@code units} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> units() {
      return _units;
    }

    /**
     * The meta-property for the {@code operationName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> operationName() {
      return _operationName;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -186618849:  // firstCurve
          return ((SpreadCurveDefinition) bean).getFirstCurve();
        case 239629531:  // secondCurve
          return ((SpreadCurveDefinition) bean).getSecondCurve();
        case -895684237:  // spread
          return ((SpreadCurveDefinition) bean).getSpread();
        case 111433583:  // units
          return ((SpreadCurveDefinition) bean).getUnits();
        case 91797650:  // operationName
          return ((SpreadCurveDefinition) bean).getOperationName();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -186618849:  // firstCurve
          ((SpreadCurveDefinition) bean).setFirstCurve((String) newValue);
          return;
        case 239629531:  // secondCurve
          ((SpreadCurveDefinition) bean).setSecondCurve((String) newValue);
          return;
        case -895684237:  // spread
          ((SpreadCurveDefinition) bean).setSpread((Double) newValue);
          return;
        case 111433583:  // units
          ((SpreadCurveDefinition) bean).setUnits((String) newValue);
          return;
        case 91797650:  // operationName
          ((SpreadCurveDefinition) bean).setOperationName((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((SpreadCurveDefinition) bean)._firstCurve, "firstCurve");
      JodaBeanUtils.notNull(((SpreadCurveDefinition) bean)._operationName, "operationName");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
