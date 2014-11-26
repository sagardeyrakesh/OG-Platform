/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.trade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetTime;

import com.google.common.collect.ImmutableMap;
import com.opengamma.core.position.Counterparty;
import com.opengamma.core.position.Trade;
import com.opengamma.core.security.Security;
import com.opengamma.id.UniqueId;
import com.opengamma.util.money.Currency;

/**
 * Immutable trade bundle to wrap up ImmutableTrade. It is paired with ImmutableTrade, which allows
 * fudge de/serialization. Once fudge is replaced these two classes can be merged.
 */
@BeanDefinition
public final class ImmutableTradeBundle implements ImmutableBean {

  /**
   * The unique identifier of the trade.
   */
  @PropertyDefinition
  private final UniqueId _uniqueId;
  /**
   * The number of units in the trade.
   */
  @PropertyDefinition(validate = "notNull")
  private final BigDecimal _quantity;
  /**
   * The link referencing the security, not null.
   * This may also hold the resolved security.
   */
  @PropertyDefinition(validate = "notNull")
  private final Security _security;
  /**
   * The counterparty.
   */
  @PropertyDefinition
  private final Counterparty _counterparty;
  /**
   * The trade date.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate _tradeDate;
  /**
   * The trade time with offset, null if not known.
   */
  @PropertyDefinition
  private final OffsetTime _tradeTime;
  /**
   * Amount paid for trade at time of purchase, null if not known.
   */
  @PropertyDefinition
  private final Double _premium;
  /**
   * Currency of payment at time of purchase, null if not known.
   */
  @PropertyDefinition
  private final Currency _premiumCurrency;
  /**
   * Date of premium payment, null if not known.
   */
  @PropertyDefinition
  private final LocalDate _premiumDate;
  /**
   * Time of premium payment, null if not known.
   */
  @PropertyDefinition
  private final OffsetTime _premiumTime;
  /**
   * The general purpose trade attributes.
   * These can be used to add arbitrary additional information to the object
   * and for aggregating in portfolios.
   */
  @PropertyDefinition(validate = "notNull")
  private final Map<String, String> _attributes;

  /**
   * Creates a trade bundle needed to create an Immutable trade post serialization
   * @param trade the mutable trade used to create a immutable version, not null
   *
   */
  public static ImmutableTradeBundle of(Trade trade) {
    return new ImmutableTradeBundle(trade.getUniqueId(), trade.getQuantity(), trade.getSecurity(),
                                    trade.getCounterparty(), trade.getTradeDate(), trade.getTradeTime(),
                                    trade.getPremium(), trade.getPremiumCurrency(), trade.getPremiumDate(),
                                    trade.getPremiumTime(), trade.getAttributes());
  }

  /**
   * Temporary replacement for JodaBeans equals method due to bug in hashCode:
   * https://github.com/JodaOrg/joda-beans/issues/89 - this can be removed
   * and the Bean regenerated when this is fixed.
   *
   * Note that this needs to be updated if new properties are added.
   *
   * @param obj object to compare
   * @return true if the objects are equals
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ImmutableTradeBundle other = (ImmutableTradeBundle) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getQuantity(), other.getQuantity()) &&
          JodaBeanUtils.equal(getSecurity(), other.getSecurity()) &&
          JodaBeanUtils.equal(getCounterparty(), other.getCounterparty()) &&
          JodaBeanUtils.equal(getTradeDate(), other.getTradeDate()) &&
          JodaBeanUtils.equal(getTradeTime(), other.getTradeTime()) &&
          JodaBeanUtils.equal(getPremium(), other.getPremium()) &&
          JodaBeanUtils.equal(getPremiumCurrency(), other.getPremiumCurrency()) &&
          JodaBeanUtils.equal(getPremiumDate(), other.getPremiumDate()) &&
          JodaBeanUtils.equal(getPremiumTime(), other.getPremiumTime()) &&
          JodaBeanUtils.equal(getAttributes(), other.getAttributes());
    }
    return false;
  }

  /**
   * Temporary replacement for JodaBeans hashCode method which has a bug:
   * https://github.com/JodaOrg/joda-beans/issues/89 - this can be removed
   * and the Bean regenerated when this is fixed.
   *
   * Note that this needs to be updated if new properties are added.
   *
   * @return the hashCode for the object
   */
  @Override
  public int hashCode() {
    // As none of the properties are arrays, using Objects.hash is
    // a reasonable approach
    return Objects.hash(
        getClass(),
        getUniqueId(),
        getQuantity(),
        getSecurity(),
        getCounterparty(),
        getTradeDate(),
        getTradeTime(),
        getPremium(),
        getPremiumCurrency(),
        getPremiumDate(),
        getPremiumTime(),
        getAttributes());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ImmutableTradeBundle}.
   * @return the meta-bean, not null
   */
  public static ImmutableTradeBundle.Meta meta() {
    return ImmutableTradeBundle.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ImmutableTradeBundle.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ImmutableTradeBundle.Builder builder() {
    return new ImmutableTradeBundle.Builder();
  }

  private ImmutableTradeBundle(
      UniqueId uniqueId,
      BigDecimal quantity,
      Security security,
      Counterparty counterparty,
      LocalDate tradeDate,
      OffsetTime tradeTime,
      Double premium,
      Currency premiumCurrency,
      LocalDate premiumDate,
      OffsetTime premiumTime,
      Map<String, String> attributes) {
    JodaBeanUtils.notNull(quantity, "quantity");
    JodaBeanUtils.notNull(security, "security");
    JodaBeanUtils.notNull(tradeDate, "tradeDate");
    JodaBeanUtils.notNull(attributes, "attributes");
    this._uniqueId = uniqueId;
    this._quantity = quantity;
    this._security = security;
    this._counterparty = counterparty;
    this._tradeDate = tradeDate;
    this._tradeTime = tradeTime;
    this._premium = premium;
    this._premiumCurrency = premiumCurrency;
    this._premiumDate = premiumDate;
    this._premiumTime = premiumTime;
    this._attributes = ImmutableMap.copyOf(attributes);
  }

  @Override
  public ImmutableTradeBundle.Meta metaBean() {
    return ImmutableTradeBundle.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the trade.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the number of units in the trade.
   * @return the value of the property, not null
   */
  public BigDecimal getQuantity() {
    return _quantity;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the link referencing the security, not null.
   * This may also hold the resolved security.
   * @return the value of the property, not null
   */
  public Security getSecurity() {
    return _security;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the counterparty.
   * @return the value of the property
   */
  public Counterparty getCounterparty() {
    return _counterparty;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the trade date.
   * @return the value of the property, not null
   */
  public LocalDate getTradeDate() {
    return _tradeDate;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the trade time with offset, null if not known.
   * @return the value of the property
   */
  public OffsetTime getTradeTime() {
    return _tradeTime;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets amount paid for trade at time of purchase, null if not known.
   * @return the value of the property
   */
  public Double getPremium() {
    return _premium;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets currency of payment at time of purchase, null if not known.
   * @return the value of the property
   */
  public Currency getPremiumCurrency() {
    return _premiumCurrency;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets date of premium payment, null if not known.
   * @return the value of the property
   */
  public LocalDate getPremiumDate() {
    return _premiumDate;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets time of premium payment, null if not known.
   * @return the value of the property
   */
  public OffsetTime getPremiumTime() {
    return _premiumTime;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the general purpose trade attributes.
   * These can be used to add arbitrary additional information to the object
   * and for aggregating in portfolios.
   * @return the value of the property, not null
   */
  public Map<String, String> getAttributes() {
    return _attributes;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(384);
    buf.append("ImmutableTradeBundle{");
    buf.append("uniqueId").append('=').append(getUniqueId()).append(',').append(' ');
    buf.append("quantity").append('=').append(getQuantity()).append(',').append(' ');
    buf.append("security").append('=').append(getSecurity()).append(',').append(' ');
    buf.append("counterparty").append('=').append(getCounterparty()).append(',').append(' ');
    buf.append("tradeDate").append('=').append(getTradeDate()).append(',').append(' ');
    buf.append("tradeTime").append('=').append(getTradeTime()).append(',').append(' ');
    buf.append("premium").append('=').append(getPremium()).append(',').append(' ');
    buf.append("premiumCurrency").append('=').append(getPremiumCurrency()).append(',').append(' ');
    buf.append("premiumDate").append('=').append(getPremiumDate()).append(',').append(' ');
    buf.append("premiumTime").append('=').append(getPremiumTime()).append(',').append(' ');
    buf.append("attributes").append('=').append(JodaBeanUtils.toString(getAttributes()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ImmutableTradeBundle}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofImmutable(
        this, "uniqueId", ImmutableTradeBundle.class, UniqueId.class);
    /**
     * The meta-property for the {@code quantity} property.
     */
    private final MetaProperty<BigDecimal> _quantity = DirectMetaProperty.ofImmutable(
        this, "quantity", ImmutableTradeBundle.class, BigDecimal.class);
    /**
     * The meta-property for the {@code security} property.
     */
    private final MetaProperty<Security> _security = DirectMetaProperty.ofImmutable(
        this, "security", ImmutableTradeBundle.class, Security.class);
    /**
     * The meta-property for the {@code counterparty} property.
     */
    private final MetaProperty<Counterparty> _counterparty = DirectMetaProperty.ofImmutable(
        this, "counterparty", ImmutableTradeBundle.class, Counterparty.class);
    /**
     * The meta-property for the {@code tradeDate} property.
     */
    private final MetaProperty<LocalDate> _tradeDate = DirectMetaProperty.ofImmutable(
        this, "tradeDate", ImmutableTradeBundle.class, LocalDate.class);
    /**
     * The meta-property for the {@code tradeTime} property.
     */
    private final MetaProperty<OffsetTime> _tradeTime = DirectMetaProperty.ofImmutable(
        this, "tradeTime", ImmutableTradeBundle.class, OffsetTime.class);
    /**
     * The meta-property for the {@code premium} property.
     */
    private final MetaProperty<Double> _premium = DirectMetaProperty.ofImmutable(
        this, "premium", ImmutableTradeBundle.class, Double.class);
    /**
     * The meta-property for the {@code premiumCurrency} property.
     */
    private final MetaProperty<Currency> _premiumCurrency = DirectMetaProperty.ofImmutable(
        this, "premiumCurrency", ImmutableTradeBundle.class, Currency.class);
    /**
     * The meta-property for the {@code premiumDate} property.
     */
    private final MetaProperty<LocalDate> _premiumDate = DirectMetaProperty.ofImmutable(
        this, "premiumDate", ImmutableTradeBundle.class, LocalDate.class);
    /**
     * The meta-property for the {@code premiumTime} property.
     */
    private final MetaProperty<OffsetTime> _premiumTime = DirectMetaProperty.ofImmutable(
        this, "premiumTime", ImmutableTradeBundle.class, OffsetTime.class);
    /**
     * The meta-property for the {@code attributes} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<String, String>> _attributes = DirectMetaProperty.ofImmutable(
        this, "attributes", ImmutableTradeBundle.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "quantity",
        "security",
        "counterparty",
        "tradeDate",
        "tradeTime",
        "premium",
        "premiumCurrency",
        "premiumDate",
        "premiumTime",
        "attributes");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case -1285004149:  // quantity
          return _quantity;
        case 949122880:  // security
          return _security;
        case -1651301782:  // counterparty
          return _counterparty;
        case 752419634:  // tradeDate
          return _tradeDate;
        case 752903761:  // tradeTime
          return _tradeTime;
        case -318452137:  // premium
          return _premium;
        case 1136581512:  // premiumCurrency
          return _premiumCurrency;
        case 651701925:  // premiumDate
          return _premiumDate;
        case 652186052:  // premiumTime
          return _premiumTime;
        case 405645655:  // attributes
          return _attributes;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ImmutableTradeBundle.Builder builder() {
      return new ImmutableTradeBundle.Builder();
    }

    @Override
    public Class<? extends ImmutableTradeBundle> beanType() {
      return ImmutableTradeBundle.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code quantity} property.
     * @return the meta-property, not null
     */
    public MetaProperty<BigDecimal> quantity() {
      return _quantity;
    }

    /**
     * The meta-property for the {@code security} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Security> security() {
      return _security;
    }

    /**
     * The meta-property for the {@code counterparty} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Counterparty> counterparty() {
      return _counterparty;
    }

    /**
     * The meta-property for the {@code tradeDate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> tradeDate() {
      return _tradeDate;
    }

    /**
     * The meta-property for the {@code tradeTime} property.
     * @return the meta-property, not null
     */
    public MetaProperty<OffsetTime> tradeTime() {
      return _tradeTime;
    }

    /**
     * The meta-property for the {@code premium} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> premium() {
      return _premium;
    }

    /**
     * The meta-property for the {@code premiumCurrency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> premiumCurrency() {
      return _premiumCurrency;
    }

    /**
     * The meta-property for the {@code premiumDate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> premiumDate() {
      return _premiumDate;
    }

    /**
     * The meta-property for the {@code premiumTime} property.
     * @return the meta-property, not null
     */
    public MetaProperty<OffsetTime> premiumTime() {
      return _premiumTime;
    }

    /**
     * The meta-property for the {@code attributes} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Map<String, String>> attributes() {
      return _attributes;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((ImmutableTradeBundle) bean).getUniqueId();
        case -1285004149:  // quantity
          return ((ImmutableTradeBundle) bean).getQuantity();
        case 949122880:  // security
          return ((ImmutableTradeBundle) bean).getSecurity();
        case -1651301782:  // counterparty
          return ((ImmutableTradeBundle) bean).getCounterparty();
        case 752419634:  // tradeDate
          return ((ImmutableTradeBundle) bean).getTradeDate();
        case 752903761:  // tradeTime
          return ((ImmutableTradeBundle) bean).getTradeTime();
        case -318452137:  // premium
          return ((ImmutableTradeBundle) bean).getPremium();
        case 1136581512:  // premiumCurrency
          return ((ImmutableTradeBundle) bean).getPremiumCurrency();
        case 651701925:  // premiumDate
          return ((ImmutableTradeBundle) bean).getPremiumDate();
        case 652186052:  // premiumTime
          return ((ImmutableTradeBundle) bean).getPremiumTime();
        case 405645655:  // attributes
          return ((ImmutableTradeBundle) bean).getAttributes();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ImmutableTradeBundle}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ImmutableTradeBundle> {

    private UniqueId _uniqueId;
    private BigDecimal _quantity;
    private Security _security;
    private Counterparty _counterparty;
    private LocalDate _tradeDate;
    private OffsetTime _tradeTime;
    private Double _premium;
    private Currency _premiumCurrency;
    private LocalDate _premiumDate;
    private OffsetTime _premiumTime;
    private Map<String, String> _attributes = new HashMap<String, String>();

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ImmutableTradeBundle beanToCopy) {
      this._uniqueId = beanToCopy.getUniqueId();
      this._quantity = beanToCopy.getQuantity();
      this._security = beanToCopy.getSecurity();
      this._counterparty = beanToCopy.getCounterparty();
      this._tradeDate = beanToCopy.getTradeDate();
      this._tradeTime = beanToCopy.getTradeTime();
      this._premium = beanToCopy.getPremium();
      this._premiumCurrency = beanToCopy.getPremiumCurrency();
      this._premiumDate = beanToCopy.getPremiumDate();
      this._premiumTime = beanToCopy.getPremiumTime();
      this._attributes = new HashMap<String, String>(beanToCopy.getAttributes());
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case -1285004149:  // quantity
          return _quantity;
        case 949122880:  // security
          return _security;
        case -1651301782:  // counterparty
          return _counterparty;
        case 752419634:  // tradeDate
          return _tradeDate;
        case 752903761:  // tradeTime
          return _tradeTime;
        case -318452137:  // premium
          return _premium;
        case 1136581512:  // premiumCurrency
          return _premiumCurrency;
        case 651701925:  // premiumDate
          return _premiumDate;
        case 652186052:  // premiumTime
          return _premiumTime;
        case 405645655:  // attributes
          return _attributes;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          this._uniqueId = (UniqueId) newValue;
          break;
        case -1285004149:  // quantity
          this._quantity = (BigDecimal) newValue;
          break;
        case 949122880:  // security
          this._security = (Security) newValue;
          break;
        case -1651301782:  // counterparty
          this._counterparty = (Counterparty) newValue;
          break;
        case 752419634:  // tradeDate
          this._tradeDate = (LocalDate) newValue;
          break;
        case 752903761:  // tradeTime
          this._tradeTime = (OffsetTime) newValue;
          break;
        case -318452137:  // premium
          this._premium = (Double) newValue;
          break;
        case 1136581512:  // premiumCurrency
          this._premiumCurrency = (Currency) newValue;
          break;
        case 651701925:  // premiumDate
          this._premiumDate = (LocalDate) newValue;
          break;
        case 652186052:  // premiumTime
          this._premiumTime = (OffsetTime) newValue;
          break;
        case 405645655:  // attributes
          this._attributes = (Map<String, String>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public ImmutableTradeBundle build() {
      return new ImmutableTradeBundle(
          _uniqueId,
          _quantity,
          _security,
          _counterparty,
          _tradeDate,
          _tradeTime,
          _premium,
          _premiumCurrency,
          _premiumDate,
          _premiumTime,
          _attributes);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code uniqueId} property in the builder.
     * @param uniqueId  the new value
     * @return this, for chaining, not null
     */
    public Builder uniqueId(UniqueId uniqueId) {
      this._uniqueId = uniqueId;
      return this;
    }

    /**
     * Sets the {@code quantity} property in the builder.
     * @param quantity  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder quantity(BigDecimal quantity) {
      JodaBeanUtils.notNull(quantity, "quantity");
      this._quantity = quantity;
      return this;
    }

    /**
     * Sets the {@code security} property in the builder.
     * @param security  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder security(Security security) {
      JodaBeanUtils.notNull(security, "security");
      this._security = security;
      return this;
    }

    /**
     * Sets the {@code counterparty} property in the builder.
     * @param counterparty  the new value
     * @return this, for chaining, not null
     */
    public Builder counterparty(Counterparty counterparty) {
      this._counterparty = counterparty;
      return this;
    }

    /**
     * Sets the {@code tradeDate} property in the builder.
     * @param tradeDate  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder tradeDate(LocalDate tradeDate) {
      JodaBeanUtils.notNull(tradeDate, "tradeDate");
      this._tradeDate = tradeDate;
      return this;
    }

    /**
     * Sets the {@code tradeTime} property in the builder.
     * @param tradeTime  the new value
     * @return this, for chaining, not null
     */
    public Builder tradeTime(OffsetTime tradeTime) {
      this._tradeTime = tradeTime;
      return this;
    }

    /**
     * Sets the {@code premium} property in the builder.
     * @param premium  the new value
     * @return this, for chaining, not null
     */
    public Builder premium(Double premium) {
      this._premium = premium;
      return this;
    }

    /**
     * Sets the {@code premiumCurrency} property in the builder.
     * @param premiumCurrency  the new value
     * @return this, for chaining, not null
     */
    public Builder premiumCurrency(Currency premiumCurrency) {
      this._premiumCurrency = premiumCurrency;
      return this;
    }

    /**
     * Sets the {@code premiumDate} property in the builder.
     * @param premiumDate  the new value
     * @return this, for chaining, not null
     */
    public Builder premiumDate(LocalDate premiumDate) {
      this._premiumDate = premiumDate;
      return this;
    }

    /**
     * Sets the {@code premiumTime} property in the builder.
     * @param premiumTime  the new value
     * @return this, for chaining, not null
     */
    public Builder premiumTime(OffsetTime premiumTime) {
      this._premiumTime = premiumTime;
      return this;
    }

    /**
     * Sets the {@code attributes} property in the builder.
     * @param attributes  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder attributes(Map<String, String> attributes) {
      JodaBeanUtils.notNull(attributes, "attributes");
      this._attributes = attributes;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(384);
      buf.append("ImmutableTradeBundle.Builder{");
      buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(_uniqueId)).append(',').append(' ');
      buf.append("quantity").append('=').append(JodaBeanUtils.toString(_quantity)).append(',').append(' ');
      buf.append("security").append('=').append(JodaBeanUtils.toString(_security)).append(',').append(' ');
      buf.append("counterparty").append('=').append(JodaBeanUtils.toString(_counterparty)).append(',').append(' ');
      buf.append("tradeDate").append('=').append(JodaBeanUtils.toString(_tradeDate)).append(',').append(' ');
      buf.append("tradeTime").append('=').append(JodaBeanUtils.toString(_tradeTime)).append(',').append(' ');
      buf.append("premium").append('=').append(JodaBeanUtils.toString(_premium)).append(',').append(' ');
      buf.append("premiumCurrency").append('=').append(JodaBeanUtils.toString(_premiumCurrency)).append(',').append(' ');
      buf.append("premiumDate").append('=').append(JodaBeanUtils.toString(_premiumDate)).append(',').append(' ');
      buf.append("premiumTime").append('=').append(JodaBeanUtils.toString(_premiumTime)).append(',').append(' ');
      buf.append("attributes").append('=').append(JodaBeanUtils.toString(_attributes));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
