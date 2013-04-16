/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.component.factory.master;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.masterdb.AbstractDbMaster;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.db.tool.DbSchemaVersionUtils;

/**
 * Base component factory for all {@link AbstractDbMaster} implementations.
 */
@BeanDefinition
public abstract class AbstractDbMasterComponentFactory extends AbstractComponentFactory {
  
  private static final String SCHEMA_VERSION_PATH = "com/opengamma/masterdb/schema";
  
  private static final Logger s_logger = LoggerFactory.getLogger(DbSecurityMasterComponentFactory.class);
  
  /**
   * The flag determining whether to enforce the schema version.
   */
  @PropertyDefinition
  private boolean _enforceSchemaVersion = true;
  
  //-------------------------------------------------------------------------
  protected void checkSchemaVersion(int actualSchemaVersion, String schemaName) {
    ArgumentChecker.notNull(schemaName, "schemaName");
    Integer expectedSchemaVersion = DbSchemaVersionUtils.readVersion(SCHEMA_VERSION_PATH, schemaName);
    if (expectedSchemaVersion == null) {
      s_logger.info("Unable to find schema version information for {}. The database schema may differ from the required version.", schemaName);
      return;
    }
    if (expectedSchemaVersion.intValue() == actualSchemaVersion) {
      s_logger.debug("Verified " + schemaName + " schema version " + actualSchemaVersion);
      return;
    }
    String relativeDbAge = expectedSchemaVersion.intValue() < actualSchemaVersion ? "new" : "old";
    String message = schemaName + " schema too " + relativeDbAge + ". This build of the OpenGamma Platform works with version " +
        expectedSchemaVersion + " of the " + schemaName + " schema, but the database contains version " + actualSchemaVersion + ".";
    if (isEnforceSchemaVersion()) {
      throw new OpenGammaRuntimeException(message);
    } else {
      s_logger.warn(message);
    }
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AbstractDbMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  public static AbstractDbMasterComponentFactory.Meta meta() {
    return AbstractDbMasterComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(AbstractDbMasterComponentFactory.Meta.INSTANCE);
  }

  @Override
  public AbstractDbMasterComponentFactory.Meta metaBean() {
    return AbstractDbMasterComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2128193333:  // enforceSchemaVersion
        return isEnforceSchemaVersion();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2128193333:  // enforceSchemaVersion
        setEnforceSchemaVersion((Boolean) newValue);
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
      AbstractDbMasterComponentFactory other = (AbstractDbMasterComponentFactory) obj;
      return JodaBeanUtils.equal(isEnforceSchemaVersion(), other.isEnforceSchemaVersion()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(isEnforceSchemaVersion());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the flag determining whether to enforce the schema version.
   * @return the value of the property
   */
  public boolean isEnforceSchemaVersion() {
    return _enforceSchemaVersion;
  }

  /**
   * Sets the flag determining whether to enforce the schema version.
   * @param enforceSchemaVersion  the new value of the property
   */
  public void setEnforceSchemaVersion(boolean enforceSchemaVersion) {
    this._enforceSchemaVersion = enforceSchemaVersion;
  }

  /**
   * Gets the the {@code enforceSchemaVersion} property.
   * @return the property, not null
   */
  public final Property<Boolean> enforceSchemaVersion() {
    return metaBean().enforceSchemaVersion().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AbstractDbMasterComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code enforceSchemaVersion} property.
     */
    private final MetaProperty<Boolean> _enforceSchemaVersion = DirectMetaProperty.ofReadWrite(
        this, "enforceSchemaVersion", AbstractDbMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "enforceSchemaVersion");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 2128193333:  // enforceSchemaVersion
          return _enforceSchemaVersion;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends AbstractDbMasterComponentFactory> builder() {
      throw new UnsupportedOperationException("AbstractDbMasterComponentFactory is an abstract class");
    }

    @Override
    public Class<? extends AbstractDbMasterComponentFactory> beanType() {
      return AbstractDbMasterComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code enforceSchemaVersion} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> enforceSchemaVersion() {
      return _enforceSchemaVersion;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
