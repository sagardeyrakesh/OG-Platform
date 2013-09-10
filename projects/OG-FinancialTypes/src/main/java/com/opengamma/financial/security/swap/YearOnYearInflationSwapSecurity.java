/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.swap;

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
import org.threeten.bp.ZonedDateTime;

import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * A security for a zero-coupon inflation swap.
 */
@BeanDefinition
public class YearOnYearInflationSwapSecurity extends SwapSecurity {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The security type.
   */
  public static final String SECURITY_TYPE = "YEAR_ON_YEAR_INFLATION_SWAP";

  /**
   * The maturity tenor of the swap.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _maturityTenor;

  /**
   * For the builder
   */
  /* package */YearOnYearInflationSwapSecurity() {
    super(SECURITY_TYPE);
  }

  /**
   * @param tradeDate The trade date, not null
   * @param effectiveDate The effective date, not null
   * @param maturityDate The maturity date, not null
   * @param counterparty The counterparty, not null
   * @param payLeg The pay leg, not null
   * @param receiveLeg The receive leg, not null
   * @param exchangeInitialNotional Whether the initial notional is exchanged
   * @param exchangeFinalNotional Whether the final notional is exchanged
   * @param tenor The tenor, not null
   */
  public YearOnYearInflationSwapSecurity(final ZonedDateTime tradeDate, final ZonedDateTime effectiveDate, final ZonedDateTime maturityDate,
      final String counterparty, final SwapLeg payLeg, final SwapLeg receiveLeg, boolean exchangeInitialNotional, boolean exchangeFinalNotional, final Tenor tenor) {
    super(SECURITY_TYPE, tradeDate, effectiveDate, maturityDate, counterparty, payLeg, receiveLeg);
    setExchangeInitialNotional(exchangeInitialNotional);
    setExchangeFinalNotional(exchangeFinalNotional);
    setMaturityTenor(tenor);
  }

  @Override
  public <T> T accept(final FinancialSecurityVisitor<T> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitYearOnYearInflationSwapSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code YearOnYearInflationSwapSecurity}.
   * @return the meta-bean, not null
   */
  public static YearOnYearInflationSwapSecurity.Meta meta() {
    return YearOnYearInflationSwapSecurity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(YearOnYearInflationSwapSecurity.Meta.INSTANCE);
  }

  @Override
  public YearOnYearInflationSwapSecurity.Meta metaBean() {
    return YearOnYearInflationSwapSecurity.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 45907375:  // maturityTenor
        return getMaturityTenor();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 45907375:  // maturityTenor
        setMaturityTenor((Tenor) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_maturityTenor, "maturityTenor");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      YearOnYearInflationSwapSecurity other = (YearOnYearInflationSwapSecurity) obj;
      return JodaBeanUtils.equal(getMaturityTenor(), other.getMaturityTenor()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getMaturityTenor());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturity tenor of the swap.
   * @return the value of the property, not null
   */
  public Tenor getMaturityTenor() {
    return _maturityTenor;
  }

  /**
   * Sets the maturity tenor of the swap.
   * @param maturityTenor  the new value of the property, not null
   */
  public void setMaturityTenor(Tenor maturityTenor) {
    JodaBeanUtils.notNull(maturityTenor, "maturityTenor");
    this._maturityTenor = maturityTenor;
  }

  /**
   * Gets the the {@code maturityTenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> maturityTenor() {
    return metaBean().maturityTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code YearOnYearInflationSwapSecurity}.
   */
  public static class Meta extends SwapSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code maturityTenor} property.
     */
    private final MetaProperty<Tenor> _maturityTenor = DirectMetaProperty.ofReadWrite(
        this, "maturityTenor", YearOnYearInflationSwapSecurity.class, Tenor.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "maturityTenor");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 45907375:  // maturityTenor
          return _maturityTenor;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends YearOnYearInflationSwapSecurity> builder() {
      return new DirectBeanBuilder<YearOnYearInflationSwapSecurity>(new YearOnYearInflationSwapSecurity());
    }

    @Override
    public Class<? extends YearOnYearInflationSwapSecurity> beanType() {
      return YearOnYearInflationSwapSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code maturityTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> maturityTenor() {
      return _maturityTenor;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
