/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.master;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.master.orgs.OrganizationMaster;
import com.opengamma.master.orgs.impl.DataOrganizationMasterResource;
import com.opengamma.master.orgs.impl.RemoteOrganizationMaster;
import com.opengamma.masterdb.orgs.DbOrganizationMaster;
import com.opengamma.util.rest.AbstractDataResource;

/**
 * Component factory for the organization master.
 */
@BeanDefinition
public class DbOrganizationMasterComponentFactory extends AbstractDocumentDbMasterComponentFactory<OrganizationMaster, DbOrganizationMaster> {

  
  public DbOrganizationMasterComponentFactory() {
    super("org", OrganizationMaster.class, RemoteOrganizationMaster.class);
  }
  

  @Override
  protected DbOrganizationMaster createDbDocumentMaster() {
    return new DbOrganizationMaster(getDbConnector());
  }
  
  @Override
  protected AbstractDataResource createPublishedResource(DbOrganizationMaster dbMaster, OrganizationMaster postProcessedMaster) {
    return new DataOrganizationMasterResource((OrganizationMaster) postProcessedMaster);
  }
      

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbOrganizationMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  public static DbOrganizationMasterComponentFactory.Meta meta() {
    return DbOrganizationMasterComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(DbOrganizationMasterComponentFactory.Meta.INSTANCE);
  }

  @Override
  public DbOrganizationMasterComponentFactory.Meta metaBean() {
    return DbOrganizationMasterComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public DbOrganizationMasterComponentFactory clone() {
    return (DbOrganizationMasterComponentFactory) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("DbOrganizationMasterComponentFactory{");
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
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbOrganizationMasterComponentFactory}.
   */
  public static class Meta extends AbstractDocumentDbMasterComponentFactory.Meta<OrganizationMaster, DbOrganizationMaster> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends DbOrganizationMasterComponentFactory> builder() {
      return new DirectBeanBuilder<DbOrganizationMasterComponentFactory>(new DbOrganizationMasterComponentFactory());
    }

    @Override
    public Class<? extends DbOrganizationMasterComponentFactory> beanType() {
      return DbOrganizationMasterComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
