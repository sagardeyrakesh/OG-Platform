/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.masterdb.security.hibernate.index;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.beans.Bean;
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

import com.opengamma.masterdb.security.hibernate.ExternalIdBean;

/**
 * A Hibernate bean representation of {@link EquityIndexComponentBean}.
 */
@BeanDefinition
public class EquityIndexComponentBean extends DirectBean {
  @PropertyDefinition
  private Long _id;
  @PropertyDefinition
  private Long _position;
  @PropertyDefinition
  private EquityIndexBean _equityIndex;
  @PropertyDefinition
  private Set<ExternalIdBean> _identifiers;
  @PropertyDefinition 
  private BigDecimal _weight;

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof EquityIndexComponentBean)) {
      return false;
    }
    EquityIndexComponentBean index = (EquityIndexComponentBean) other;
    return new EqualsBuilder()
      .append(getId(), index.getId())
      .append(getPosition(), index.getPosition())
      .append(getEquityIndex(), index.getEquityIndex())
      .append(getIdentifiers(), index.getIdentifiers())
      .append(getWeight(), index.getWeight())
      .isEquals();
  }
  
  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(getId())
      .append(getPosition())
      .append(getEquityIndex())
      .append(getIdentifiers())
      .append(getWeight())
      .toHashCode();
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code EquityIndexComponentBean}.
   * @return the meta-bean, not null
   */
  public static EquityIndexComponentBean.Meta meta() {
    return EquityIndexComponentBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(EquityIndexComponentBean.Meta.INSTANCE);
  }

  @Override
  public EquityIndexComponentBean.Meta metaBean() {
    return EquityIndexComponentBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the id.
   * @return the value of the property
   */
  public Long getId() {
    return _id;
  }

  /**
   * Sets the id.
   * @param id  the new value of the property
   */
  public void setId(Long id) {
    this._id = id;
  }

  /**
   * Gets the the {@code id} property.
   * @return the property, not null
   */
  public final Property<Long> id() {
    return metaBean().id().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the position.
   * @return the value of the property
   */
  public Long getPosition() {
    return _position;
  }

  /**
   * Sets the position.
   * @param position  the new value of the property
   */
  public void setPosition(Long position) {
    this._position = position;
  }

  /**
   * Gets the the {@code position} property.
   * @return the property, not null
   */
  public final Property<Long> position() {
    return metaBean().position().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the equityIndex.
   * @return the value of the property
   */
  public EquityIndexBean getEquityIndex() {
    return _equityIndex;
  }

  /**
   * Sets the equityIndex.
   * @param equityIndex  the new value of the property
   */
  public void setEquityIndex(EquityIndexBean equityIndex) {
    this._equityIndex = equityIndex;
  }

  /**
   * Gets the the {@code equityIndex} property.
   * @return the property, not null
   */
  public final Property<EquityIndexBean> equityIndex() {
    return metaBean().equityIndex().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the identifiers.
   * @return the value of the property
   */
  public Set<ExternalIdBean> getIdentifiers() {
    return _identifiers;
  }

  /**
   * Sets the identifiers.
   * @param identifiers  the new value of the property
   */
  public void setIdentifiers(Set<ExternalIdBean> identifiers) {
    this._identifiers = identifiers;
  }

  /**
   * Gets the the {@code identifiers} property.
   * @return the property, not null
   */
  public final Property<Set<ExternalIdBean>> identifiers() {
    return metaBean().identifiers().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the weight.
   * @return the value of the property
   */
  public BigDecimal getWeight() {
    return _weight;
  }

  /**
   * Sets the weight.
   * @param weight  the new value of the property
   */
  public void setWeight(BigDecimal weight) {
    this._weight = weight;
  }

  /**
   * Gets the the {@code weight} property.
   * @return the property, not null
   */
  public final Property<BigDecimal> weight() {
    return metaBean().weight().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public EquityIndexComponentBean clone() {
    BeanBuilder<? extends EquityIndexComponentBean> builder = metaBean().builder();
    for (MetaProperty<?> mp : metaBean().metaPropertyIterable()) {
      if (mp.style().isBuildable()) {
        Object value = mp.get(this);
        if (value instanceof Bean) {
          value = ((Bean) value).clone();
        }
        builder.set(mp.name(), value);
      }
    }
    return builder.build();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("EquityIndexComponentBean{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("id").append('=').append(JodaBeanUtils.toString(getId())).append(',').append(' ');
    buf.append("position").append('=').append(JodaBeanUtils.toString(getPosition())).append(',').append(' ');
    buf.append("equityIndex").append('=').append(JodaBeanUtils.toString(getEquityIndex())).append(',').append(' ');
    buf.append("identifiers").append('=').append(JodaBeanUtils.toString(getIdentifiers())).append(',').append(' ');
    buf.append("weight").append('=').append(JodaBeanUtils.toString(getWeight())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code EquityIndexComponentBean}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code id} property.
     */
    private final MetaProperty<Long> _id = DirectMetaProperty.ofReadWrite(
        this, "id", EquityIndexComponentBean.class, Long.class);
    /**
     * The meta-property for the {@code position} property.
     */
    private final MetaProperty<Long> _position = DirectMetaProperty.ofReadWrite(
        this, "position", EquityIndexComponentBean.class, Long.class);
    /**
     * The meta-property for the {@code equityIndex} property.
     */
    private final MetaProperty<EquityIndexBean> _equityIndex = DirectMetaProperty.ofReadWrite(
        this, "equityIndex", EquityIndexComponentBean.class, EquityIndexBean.class);
    /**
     * The meta-property for the {@code identifiers} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<ExternalIdBean>> _identifiers = DirectMetaProperty.ofReadWrite(
        this, "identifiers", EquityIndexComponentBean.class, (Class) Set.class);
    /**
     * The meta-property for the {@code weight} property.
     */
    private final MetaProperty<BigDecimal> _weight = DirectMetaProperty.ofReadWrite(
        this, "weight", EquityIndexComponentBean.class, BigDecimal.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "id",
        "position",
        "equityIndex",
        "identifiers",
        "weight");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case 747804969:  // position
          return _position;
        case -1135801075:  // equityIndex
          return _equityIndex;
        case 1368189162:  // identifiers
          return _identifiers;
        case -791592328:  // weight
          return _weight;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends EquityIndexComponentBean> builder() {
      return new DirectBeanBuilder<EquityIndexComponentBean>(new EquityIndexComponentBean());
    }

    @Override
    public Class<? extends EquityIndexComponentBean> beanType() {
      return EquityIndexComponentBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code id} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Long> id() {
      return _id;
    }

    /**
     * The meta-property for the {@code position} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Long> position() {
      return _position;
    }

    /**
     * The meta-property for the {@code equityIndex} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<EquityIndexBean> equityIndex() {
      return _equityIndex;
    }

    /**
     * The meta-property for the {@code identifiers} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Set<ExternalIdBean>> identifiers() {
      return _identifiers;
    }

    /**
     * The meta-property for the {@code weight} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BigDecimal> weight() {
      return _weight;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return ((EquityIndexComponentBean) bean).getId();
        case 747804969:  // position
          return ((EquityIndexComponentBean) bean).getPosition();
        case -1135801075:  // equityIndex
          return ((EquityIndexComponentBean) bean).getEquityIndex();
        case 1368189162:  // identifiers
          return ((EquityIndexComponentBean) bean).getIdentifiers();
        case -791592328:  // weight
          return ((EquityIndexComponentBean) bean).getWeight();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          ((EquityIndexComponentBean) bean).setId((Long) newValue);
          return;
        case 747804969:  // position
          ((EquityIndexComponentBean) bean).setPosition((Long) newValue);
          return;
        case -1135801075:  // equityIndex
          ((EquityIndexComponentBean) bean).setEquityIndex((EquityIndexBean) newValue);
          return;
        case 1368189162:  // identifiers
          ((EquityIndexComponentBean) bean).setIdentifiers((Set<ExternalIdBean>) newValue);
          return;
        case -791592328:  // weight
          ((EquityIndexComponentBean) bean).setWeight((BigDecimal) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
