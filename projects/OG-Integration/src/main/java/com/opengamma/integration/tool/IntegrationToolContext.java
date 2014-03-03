/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opengamma.OpenGammaRuntimeException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.bbg.referencedata.ReferenceDataProvider;
import com.opengamma.bbg.tool.BloombergToolContext;
import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentServer;
import com.opengamma.component.factory.ComponentInfoAttributes;
import com.opengamma.financial.depgraph.provider.DependencyGraphTraceProvider;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.provider.livedata.LiveDataMetaDataProvider;

/**
 * Extended context that is used to provide components to tools.
 * <p>
 * This is populated and passed to tools that need component services.
 * Each component is optional, although typically all are provided.
 */
@BeanDefinition
public class IntegrationToolContext extends ToolContext implements BloombergToolContext {

  private static final Logger s_logger = LoggerFactory.getLogger(IntegrationToolContext.class);
  /** 
   * Link back to the component server providing the implementations.  Useful if you need a specific classifier/instance.
   */
  @PropertyDefinition
  private ComponentServer _componentServer;
  
  /**
   * The Bloomberg reference data provider.
   */
  @PropertyDefinition
  private ReferenceDataProvider _bloombergReferenceDataProvider;

  @PropertyDefinition
  private DependencyGraphTraceProvider _dependencyGraphTraceProvider;
  
  public List<LiveDataMetaDataProvider> getLiveDataMetaDataProviders() {
    if (_componentServer == null) {
      throw new OpenGammaRuntimeException("ComponentServer null, tool must be run against a running server, e.g '-c http://localhost:8080'");
    }
    List<ComponentInfo> componentInfos = _componentServer.getComponentInfos(LiveDataMetaDataProvider.class);
    List<LiveDataMetaDataProvider> results = new ArrayList<>();
    for (ComponentInfo info : componentInfos) {
      try {
        String clazzName = info.getAttribute(ComponentInfoAttributes.REMOTE_CLIENT_JAVA);
        if (clazzName == null) {
          continue;
        }
        Class<?> clazz = Class.forName(clazzName);
        LiveDataMetaDataProvider ldMetaDataProvider = (LiveDataMetaDataProvider) clazz.getConstructor(URI.class).newInstance(info.getUri());
        results.add(ldMetaDataProvider);
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException ex) {
        s_logger.error("Couldn't create instance of {}", info.getAttribute(null));
      }
    }
    return results;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IntegrationToolContext}.
   * @return the meta-bean, not null
   */
  public static IntegrationToolContext.Meta meta() {
    return IntegrationToolContext.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IntegrationToolContext.Meta.INSTANCE);
  }

  @Override
  public IntegrationToolContext.Meta metaBean() {
    return IntegrationToolContext.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets link back to the component server providing the implementations.  Useful if you need a specific classifier/instance.
   * @return the value of the property
   */
  public ComponentServer getComponentServer() {
    return _componentServer;
  }

  /**
   * Sets link back to the component server providing the implementations.  Useful if you need a specific classifier/instance.
   * @param componentServer  the new value of the property
   */
  public void setComponentServer(ComponentServer componentServer) {
    this._componentServer = componentServer;
  }

  /**
   * Gets the the {@code componentServer} property.
   * @return the property, not null
   */
  public final Property<ComponentServer> componentServer() {
    return metaBean().componentServer().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Bloomberg reference data provider.
   * @return the value of the property
   */
  public ReferenceDataProvider getBloombergReferenceDataProvider() {
    return _bloombergReferenceDataProvider;
  }

  /**
   * Sets the Bloomberg reference data provider.
   * @param bloombergReferenceDataProvider  the new value of the property
   */
  public void setBloombergReferenceDataProvider(ReferenceDataProvider bloombergReferenceDataProvider) {
    this._bloombergReferenceDataProvider = bloombergReferenceDataProvider;
  }

  /**
   * Gets the the {@code bloombergReferenceDataProvider} property.
   * @return the property, not null
   */
  public final Property<ReferenceDataProvider> bloombergReferenceDataProvider() {
    return metaBean().bloombergReferenceDataProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the dependencyGraphTraceProvider.
   * @return the value of the property
   */
  public DependencyGraphTraceProvider getDependencyGraphTraceProvider() {
    return _dependencyGraphTraceProvider;
  }

  /**
   * Sets the dependencyGraphTraceProvider.
   * @param dependencyGraphTraceProvider  the new value of the property
   */
  public void setDependencyGraphTraceProvider(DependencyGraphTraceProvider dependencyGraphTraceProvider) {
    this._dependencyGraphTraceProvider = dependencyGraphTraceProvider;
  }

  /**
   * Gets the the {@code dependencyGraphTraceProvider} property.
   * @return the property, not null
   */
  public final Property<DependencyGraphTraceProvider> dependencyGraphTraceProvider() {
    return metaBean().dependencyGraphTraceProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public IntegrationToolContext clone() {
    return (IntegrationToolContext) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IntegrationToolContext other = (IntegrationToolContext) obj;
      return JodaBeanUtils.equal(getComponentServer(), other.getComponentServer()) &&
          JodaBeanUtils.equal(getBloombergReferenceDataProvider(), other.getBloombergReferenceDataProvider()) &&
          JodaBeanUtils.equal(getDependencyGraphTraceProvider(), other.getDependencyGraphTraceProvider()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getComponentServer());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBloombergReferenceDataProvider());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDependencyGraphTraceProvider());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("IntegrationToolContext{");
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
    buf.append("componentServer").append('=').append(JodaBeanUtils.toString(getComponentServer())).append(',').append(' ');
    buf.append("bloombergReferenceDataProvider").append('=').append(JodaBeanUtils.toString(getBloombergReferenceDataProvider())).append(',').append(' ');
    buf.append("dependencyGraphTraceProvider").append('=').append(JodaBeanUtils.toString(getDependencyGraphTraceProvider())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IntegrationToolContext}.
   */
  public static class Meta extends ToolContext.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code componentServer} property.
     */
    private final MetaProperty<ComponentServer> _componentServer = DirectMetaProperty.ofReadWrite(
        this, "componentServer", IntegrationToolContext.class, ComponentServer.class);
    /**
     * The meta-property for the {@code bloombergReferenceDataProvider} property.
     */
    private final MetaProperty<ReferenceDataProvider> _bloombergReferenceDataProvider = DirectMetaProperty.ofReadWrite(
        this, "bloombergReferenceDataProvider", IntegrationToolContext.class, ReferenceDataProvider.class);
    /**
     * The meta-property for the {@code dependencyGraphTraceProvider} property.
     */
    private final MetaProperty<DependencyGraphTraceProvider> _dependencyGraphTraceProvider = DirectMetaProperty.ofReadWrite(
        this, "dependencyGraphTraceProvider", IntegrationToolContext.class, DependencyGraphTraceProvider.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "componentServer",
        "bloombergReferenceDataProvider",
        "dependencyGraphTraceProvider");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -826685792:  // componentServer
          return _componentServer;
        case -245204181:  // bloombergReferenceDataProvider
          return _bloombergReferenceDataProvider;
        case 67712595:  // dependencyGraphTraceProvider
          return _dependencyGraphTraceProvider;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IntegrationToolContext> builder() {
      return new DirectBeanBuilder<IntegrationToolContext>(new IntegrationToolContext());
    }

    @Override
    public Class<? extends IntegrationToolContext> beanType() {
      return IntegrationToolContext.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code componentServer} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ComponentServer> componentServer() {
      return _componentServer;
    }

    /**
     * The meta-property for the {@code bloombergReferenceDataProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ReferenceDataProvider> bloombergReferenceDataProvider() {
      return _bloombergReferenceDataProvider;
    }

    /**
     * The meta-property for the {@code dependencyGraphTraceProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DependencyGraphTraceProvider> dependencyGraphTraceProvider() {
      return _dependencyGraphTraceProvider;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -826685792:  // componentServer
          return ((IntegrationToolContext) bean).getComponentServer();
        case -245204181:  // bloombergReferenceDataProvider
          return ((IntegrationToolContext) bean).getBloombergReferenceDataProvider();
        case 67712595:  // dependencyGraphTraceProvider
          return ((IntegrationToolContext) bean).getDependencyGraphTraceProvider();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -826685792:  // componentServer
          ((IntegrationToolContext) bean).setComponentServer((ComponentServer) newValue);
          return;
        case -245204181:  // bloombergReferenceDataProvider
          ((IntegrationToolContext) bean).setBloombergReferenceDataProvider((ReferenceDataProvider) newValue);
          return;
        case 67712595:  // dependencyGraphTraceProvider
          ((IntegrationToolContext) bean).setDependencyGraphTraceProvider((DependencyGraphTraceProvider) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
