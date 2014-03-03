/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.legalentity;

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

import com.opengamma.master.AbstractMetaDataResult;
import com.opengamma.util.PublicSPI;

/**
 * Result from obtaining meta-data for the legalentity master.
 * <p/>
 * Meta-data is only returned if requested.
 */
@PublicSPI
@BeanDefinition
public class LegalEntityMetaDataResult extends AbstractMetaDataResult {

  /**
   * The database schema version.
   * This is only populated if requested.
   */
  @PropertyDefinition
  private String _schemaVersion;

  /**
   * Creates an instance.
   */
  public LegalEntityMetaDataResult() {
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LegalEntityMetaDataResult}.
   * @return the meta-bean, not null
   */
  public static LegalEntityMetaDataResult.Meta meta() {
    return LegalEntityMetaDataResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LegalEntityMetaDataResult.Meta.INSTANCE);
  }

  @Override
  public LegalEntityMetaDataResult.Meta metaBean() {
    return LegalEntityMetaDataResult.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database schema version.
   * This is only populated if requested.
   * @return the value of the property
   */
  public String getSchemaVersion() {
    return _schemaVersion;
  }

  /**
   * Sets the database schema version.
   * This is only populated if requested.
   * @param schemaVersion  the new value of the property
   */
  public void setSchemaVersion(String schemaVersion) {
    this._schemaVersion = schemaVersion;
  }

  /**
   * Gets the the {@code schemaVersion} property.
   * This is only populated if requested.
   * @return the property, not null
   */
  public final Property<String> schemaVersion() {
    return metaBean().schemaVersion().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public LegalEntityMetaDataResult clone() {
    return (LegalEntityMetaDataResult) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      LegalEntityMetaDataResult other = (LegalEntityMetaDataResult) obj;
      return JodaBeanUtils.equal(getSchemaVersion(), other.getSchemaVersion()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getSchemaVersion());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("LegalEntityMetaDataResult{");
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
    buf.append("schemaVersion").append('=').append(JodaBeanUtils.toString(getSchemaVersion())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LegalEntityMetaDataResult}.
   */
  public static class Meta extends AbstractMetaDataResult.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code schemaVersion} property.
     */
    private final MetaProperty<String> _schemaVersion = DirectMetaProperty.ofReadWrite(
        this, "schemaVersion", LegalEntityMetaDataResult.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "schemaVersion");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -233564169:  // schemaVersion
          return _schemaVersion;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends LegalEntityMetaDataResult> builder() {
      return new DirectBeanBuilder<LegalEntityMetaDataResult>(new LegalEntityMetaDataResult());
    }

    @Override
    public Class<? extends LegalEntityMetaDataResult> beanType() {
      return LegalEntityMetaDataResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code schemaVersion} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> schemaVersion() {
      return _schemaVersion;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -233564169:  // schemaVersion
          return ((LegalEntityMetaDataResult) bean).getSchemaVersion();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -233564169:  // schemaVersion
          ((LegalEntityMetaDataResult) bean).setSchemaVersion((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
