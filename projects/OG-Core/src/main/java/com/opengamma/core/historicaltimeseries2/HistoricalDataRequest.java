/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.core.historicaltimeseries2;

import java.util.Map;
import java.util.NoSuchElementException;
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

import com.opengamma.id.ExternalIdBundle;

/** 
 * Fields for a historicalDataRequest
 */
@BeanDefinition
public final class HistoricalDataRequest implements ImmutableBean {

  @PropertyDefinition
  private final ExternalIdBundle _bundle;

  @PropertyDefinition
  private final String _field;
  
  @PropertyDefinition
  private final LocalDate _from;
  
  @PropertyDefinition
  private final boolean _fromInclusive;
  
  @PropertyDefinition
  private final LocalDate _to;
  
  @PropertyDefinition
  private final boolean _toInclusive;
  
  @PropertyDefinition
  private final String _resolver;
 
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code HistoricalDataRequest}.
   * @return the meta-bean, not null
   */
  public static HistoricalDataRequest.Meta meta() {
    return HistoricalDataRequest.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(HistoricalDataRequest.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static HistoricalDataRequest.Builder builder() {
    return new HistoricalDataRequest.Builder();
  }

  private HistoricalDataRequest(
      ExternalIdBundle bundle,
      String field,
      LocalDate from,
      boolean fromInclusive,
      LocalDate to,
      boolean toInclusive,
      String resolver) {
    this._bundle = bundle;
    this._field = field;
    this._from = from;
    this._fromInclusive = fromInclusive;
    this._to = to;
    this._toInclusive = toInclusive;
    this._resolver = resolver;
  }

  @Override
  public HistoricalDataRequest.Meta metaBean() {
    return HistoricalDataRequest.Meta.INSTANCE;
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
   * Gets the bundle.
   * @return the value of the property
   */
  public ExternalIdBundle getBundle() {
    return _bundle;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the field.
   * @return the value of the property
   */
  public String getField() {
    return _field;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the from.
   * @return the value of the property
   */
  public LocalDate getFrom() {
    return _from;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the fromInclusive.
   * @return the value of the property
   */
  public boolean isFromInclusive() {
    return _fromInclusive;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the to.
   * @return the value of the property
   */
  public LocalDate getTo() {
    return _to;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the toInclusive.
   * @return the value of the property
   */
  public boolean isToInclusive() {
    return _toInclusive;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the resolver.
   * @return the value of the property
   */
  public String getResolver() {
    return _resolver;
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
  public HistoricalDataRequest clone() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      HistoricalDataRequest other = (HistoricalDataRequest) obj;
      return JodaBeanUtils.equal(getBundle(), other.getBundle()) &&
          JodaBeanUtils.equal(getField(), other.getField()) &&
          JodaBeanUtils.equal(getFrom(), other.getFrom()) &&
          (isFromInclusive() == other.isFromInclusive()) &&
          JodaBeanUtils.equal(getTo(), other.getTo()) &&
          (isToInclusive() == other.isToInclusive()) &&
          JodaBeanUtils.equal(getResolver(), other.getResolver());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getBundle());
    hash += hash * 31 + JodaBeanUtils.hashCode(getField());
    hash += hash * 31 + JodaBeanUtils.hashCode(getFrom());
    hash += hash * 31 + JodaBeanUtils.hashCode(isFromInclusive());
    hash += hash * 31 + JodaBeanUtils.hashCode(getTo());
    hash += hash * 31 + JodaBeanUtils.hashCode(isToInclusive());
    hash += hash * 31 + JodaBeanUtils.hashCode(getResolver());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(256);
    buf.append("HistoricalDataRequest{");
    buf.append("bundle").append('=').append(getBundle()).append(',').append(' ');
    buf.append("field").append('=').append(getField()).append(',').append(' ');
    buf.append("from").append('=').append(getFrom()).append(',').append(' ');
    buf.append("fromInclusive").append('=').append(isFromInclusive()).append(',').append(' ');
    buf.append("to").append('=').append(getTo()).append(',').append(' ');
    buf.append("toInclusive").append('=').append(isToInclusive()).append(',').append(' ');
    buf.append("resolver").append('=').append(JodaBeanUtils.toString(getResolver()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code HistoricalDataRequest}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code bundle} property.
     */
    private final MetaProperty<ExternalIdBundle> _bundle = DirectMetaProperty.ofImmutable(
        this, "bundle", HistoricalDataRequest.class, ExternalIdBundle.class);
    /**
     * The meta-property for the {@code field} property.
     */
    private final MetaProperty<String> _field = DirectMetaProperty.ofImmutable(
        this, "field", HistoricalDataRequest.class, String.class);
    /**
     * The meta-property for the {@code from} property.
     */
    private final MetaProperty<LocalDate> _from = DirectMetaProperty.ofImmutable(
        this, "from", HistoricalDataRequest.class, LocalDate.class);
    /**
     * The meta-property for the {@code fromInclusive} property.
     */
    private final MetaProperty<Boolean> _fromInclusive = DirectMetaProperty.ofImmutable(
        this, "fromInclusive", HistoricalDataRequest.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code to} property.
     */
    private final MetaProperty<LocalDate> _to = DirectMetaProperty.ofImmutable(
        this, "to", HistoricalDataRequest.class, LocalDate.class);
    /**
     * The meta-property for the {@code toInclusive} property.
     */
    private final MetaProperty<Boolean> _toInclusive = DirectMetaProperty.ofImmutable(
        this, "toInclusive", HistoricalDataRequest.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code resolver} property.
     */
    private final MetaProperty<String> _resolver = DirectMetaProperty.ofImmutable(
        this, "resolver", HistoricalDataRequest.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "bundle",
        "field",
        "from",
        "fromInclusive",
        "to",
        "toInclusive",
        "resolver");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1377881982:  // bundle
          return _bundle;
        case 97427706:  // field
          return _field;
        case 3151786:  // from
          return _from;
        case 637452642:  // fromInclusive
          return _fromInclusive;
        case 3707:  // to
          return _to;
        case -2118589007:  // toInclusive
          return _toInclusive;
        case -341328890:  // resolver
          return _resolver;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public HistoricalDataRequest.Builder builder() {
      return new HistoricalDataRequest.Builder();
    }

    @Override
    public Class<? extends HistoricalDataRequest> beanType() {
      return HistoricalDataRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code bundle} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ExternalIdBundle> bundle() {
      return _bundle;
    }

    /**
     * The meta-property for the {@code field} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> field() {
      return _field;
    }

    /**
     * The meta-property for the {@code from} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> from() {
      return _from;
    }

    /**
     * The meta-property for the {@code fromInclusive} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> fromInclusive() {
      return _fromInclusive;
    }

    /**
     * The meta-property for the {@code to} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> to() {
      return _to;
    }

    /**
     * The meta-property for the {@code toInclusive} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> toInclusive() {
      return _toInclusive;
    }

    /**
     * The meta-property for the {@code resolver} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> resolver() {
      return _resolver;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1377881982:  // bundle
          return ((HistoricalDataRequest) bean).getBundle();
        case 97427706:  // field
          return ((HistoricalDataRequest) bean).getField();
        case 3151786:  // from
          return ((HistoricalDataRequest) bean).getFrom();
        case 637452642:  // fromInclusive
          return ((HistoricalDataRequest) bean).isFromInclusive();
        case 3707:  // to
          return ((HistoricalDataRequest) bean).getTo();
        case -2118589007:  // toInclusive
          return ((HistoricalDataRequest) bean).isToInclusive();
        case -341328890:  // resolver
          return ((HistoricalDataRequest) bean).getResolver();
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
   * The bean-builder for {@code HistoricalDataRequest}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<HistoricalDataRequest> {

    private ExternalIdBundle _bundle;
    private String _field;
    private LocalDate _from;
    private boolean _fromInclusive;
    private LocalDate _to;
    private boolean _toInclusive;
    private String _resolver;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(HistoricalDataRequest beanToCopy) {
      this._bundle = beanToCopy.getBundle();
      this._field = beanToCopy.getField();
      this._from = beanToCopy.getFrom();
      this._fromInclusive = beanToCopy.isFromInclusive();
      this._to = beanToCopy.getTo();
      this._toInclusive = beanToCopy.isToInclusive();
      this._resolver = beanToCopy.getResolver();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1377881982:  // bundle
          return _bundle;
        case 97427706:  // field
          return _field;
        case 3151786:  // from
          return _from;
        case 637452642:  // fromInclusive
          return _fromInclusive;
        case 3707:  // to
          return _to;
        case -2118589007:  // toInclusive
          return _toInclusive;
        case -341328890:  // resolver
          return _resolver;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1377881982:  // bundle
          this._bundle = (ExternalIdBundle) newValue;
          break;
        case 97427706:  // field
          this._field = (String) newValue;
          break;
        case 3151786:  // from
          this._from = (LocalDate) newValue;
          break;
        case 637452642:  // fromInclusive
          this._fromInclusive = (Boolean) newValue;
          break;
        case 3707:  // to
          this._to = (LocalDate) newValue;
          break;
        case -2118589007:  // toInclusive
          this._toInclusive = (Boolean) newValue;
          break;
        case -341328890:  // resolver
          this._resolver = (String) newValue;
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
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public HistoricalDataRequest build() {
      return new HistoricalDataRequest(
          _bundle,
          _field,
          _from,
          _fromInclusive,
          _to,
          _toInclusive,
          _resolver);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code bundle} property in the builder.
     * @param bundle  the new value
     * @return this, for chaining, not null
     */
    public Builder bundle(ExternalIdBundle bundle) {
      this._bundle = bundle;
      return this;
    }

    /**
     * Sets the {@code field} property in the builder.
     * @param field  the new value
     * @return this, for chaining, not null
     */
    public Builder field(String field) {
      this._field = field;
      return this;
    }

    /**
     * Sets the {@code from} property in the builder.
     * @param from  the new value
     * @return this, for chaining, not null
     */
    public Builder from(LocalDate from) {
      this._from = from;
      return this;
    }

    /**
     * Sets the {@code fromInclusive} property in the builder.
     * @param fromInclusive  the new value
     * @return this, for chaining, not null
     */
    public Builder fromInclusive(boolean fromInclusive) {
      this._fromInclusive = fromInclusive;
      return this;
    }

    /**
     * Sets the {@code to} property in the builder.
     * @param to  the new value
     * @return this, for chaining, not null
     */
    public Builder to(LocalDate to) {
      this._to = to;
      return this;
    }

    /**
     * Sets the {@code toInclusive} property in the builder.
     * @param toInclusive  the new value
     * @return this, for chaining, not null
     */
    public Builder toInclusive(boolean toInclusive) {
      this._toInclusive = toInclusive;
      return this;
    }

    /**
     * Sets the {@code resolver} property in the builder.
     * @param resolver  the new value
     * @return this, for chaining, not null
     */
    public Builder resolver(String resolver) {
      this._resolver = resolver;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(256);
      buf.append("HistoricalDataRequest.Builder{");
      buf.append("bundle").append('=').append(JodaBeanUtils.toString(_bundle)).append(',').append(' ');
      buf.append("field").append('=').append(JodaBeanUtils.toString(_field)).append(',').append(' ');
      buf.append("from").append('=').append(JodaBeanUtils.toString(_from)).append(',').append(' ');
      buf.append("fromInclusive").append('=').append(JodaBeanUtils.toString(_fromInclusive)).append(',').append(' ');
      buf.append("to").append('=').append(JodaBeanUtils.toString(_to)).append(',').append(' ');
      buf.append("toInclusive").append('=').append(JodaBeanUtils.toString(_toInclusive)).append(',').append(' ');
      buf.append("resolver").append('=').append(JodaBeanUtils.toString(_resolver));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
  
  
