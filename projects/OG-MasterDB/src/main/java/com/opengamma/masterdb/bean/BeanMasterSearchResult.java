/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb.bean;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.master.AbstractDocument;
import com.opengamma.master.AbstractSearchResult;

/**
 * Provides access to the results of searching in a {@code DbBeanMaster}.
 * 
 * @param <D>  the type of the document
 */
@BeanDefinition
public class BeanMasterSearchResult<D extends AbstractDocument> extends AbstractSearchResult<D> {

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BeanMasterSearchResult}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static BeanMasterSearchResult.Meta meta() {
    return BeanMasterSearchResult.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code BeanMasterSearchResult}.
   * @param <R>  the bean's generic type
   * @param cls  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R extends AbstractDocument> BeanMasterSearchResult.Meta<R> metaBeanMasterSearchResult(Class<R> cls) {
    return BeanMasterSearchResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(BeanMasterSearchResult.Meta.INSTANCE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public BeanMasterSearchResult.Meta<D> metaBean() {
    return BeanMasterSearchResult.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public BeanMasterSearchResult<D> clone() {
    return (BeanMasterSearchResult<D>) super.clone();
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
    buf.append("BeanMasterSearchResult{");
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
   * The meta-bean for {@code BeanMasterSearchResult}.
   */
  public static class Meta<D extends AbstractDocument> extends AbstractSearchResult.Meta<D> {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
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
    public BeanBuilder<? extends BeanMasterSearchResult<D>> builder() {
      return new DirectBeanBuilder<BeanMasterSearchResult<D>>(new BeanMasterSearchResult<D>());
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends BeanMasterSearchResult<D>> beanType() {
      return (Class) BeanMasterSearchResult.class;
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
