/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.world.holiday.master;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.util.db.Paging;

/**
 * Result from searching for holidays.
 */
@BeanDefinition
public class HolidaySearchHistoricResult extends DirectBean {

  /**
   * The paging information.
   */
  @PropertyDefinition
  private Paging _paging;
  /**
   * The list of matched holiday documents.
   */
  @PropertyDefinition
  private final List<HolidayDocument> _documents = new ArrayList<HolidayDocument>();

  /**
   * Creates an instance.
   */
  public HolidaySearchHistoricResult() {
  }

  /**
   * Creates an instance.
   * @param coll  the collection of documents to add, not null
   */
  public HolidaySearchHistoricResult(Collection<HolidayDocument> coll) {
    _documents.addAll(coll);
    _paging = Paging.of(coll);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the returned holidays from within the documents.
   * @return the holidays, not null
   */
  public List<ManageableHoliday> getHolidays() {
    List<ManageableHoliday> result = new ArrayList<ManageableHoliday>();
    if (_documents != null) {
      for (HolidayDocument doc : _documents) {
        result.add(doc.getHoliday());
      }
    }
    return result;
  }

  /**
   * Gets the first document, or null if no documents.
   * @return the first document, null if none
   */
  public HolidayDocument getFirstDocument() {
    return getDocuments().size() > 0 ? getDocuments().get(0) : null;
  }

  /**
   * Gets the first holiday, or null if no documents.
   * @return the first holiday, null if none
   */
  public ManageableHoliday getFirstHoliday() {
    return getDocuments().size() > 0 ? getDocuments().get(0).getHoliday() : null;
  }

  /**
   * Gets the single result expected from a query.
   * <p>
   * This throws an exception if more than 1 result is actually available.
   * Thus, this method implies an assumption about uniqueness of the queried holiday.
   * @return the matching holiday, not null
   * @throws IllegalStateException if no holiday was found
   */
  public ManageableHoliday getSingleHoliday() {
    if (_documents.size() > 1) {
      throw new IllegalStateException("Expecting zero or single resulting match, and was " + _documents.size());
    } else {
      return getFirstHoliday();
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code HolidaySearchHistoricResult}.
   * @return the meta-bean, not null
   */
  public static HolidaySearchHistoricResult.Meta meta() {
    return HolidaySearchHistoricResult.Meta.INSTANCE;
  }

  @Override
  public HolidaySearchHistoricResult.Meta metaBean() {
    return HolidaySearchHistoricResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -995747956:  // paging
        return getPaging();
      case 943542968:  // documents
        return getDocuments();
    }
    return super.propertyGet(propertyName);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -995747956:  // paging
        setPaging((Paging) newValue);
        return;
      case 943542968:  // documents
        setDocuments((List<HolidayDocument>) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the paging information.
   * @return the value of the property
   */
  public Paging getPaging() {
    return _paging;
  }

  /**
   * Sets the paging information.
   * @param paging  the new value of the property
   */
  public void setPaging(Paging paging) {
    this._paging = paging;
  }

  /**
   * Gets the the {@code paging} property.
   * @return the property, not null
   */
  public final Property<Paging> paging() {
    return metaBean().paging().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the list of matched holiday documents.
   * @return the value of the property
   */
  public List<HolidayDocument> getDocuments() {
    return _documents;
  }

  /**
   * Sets the list of matched holiday documents.
   * @param documents  the new value of the property
   */
  public void setDocuments(List<HolidayDocument> documents) {
    this._documents.clear();
    this._documents.addAll(documents);
  }

  /**
   * Gets the the {@code documents} property.
   * @return the property, not null
   */
  public final Property<List<HolidayDocument>> documents() {
    return metaBean().documents().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code HolidaySearchHistoricResult}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code paging} property.
     */
    private final MetaProperty<Paging> _paging = DirectMetaProperty.ofReadWrite(this, "paging", Paging.class);
    /**
     * The meta-property for the {@code documents} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<HolidayDocument>> _documents = DirectMetaProperty.ofReadWrite(this, "documents", (Class) List.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("paging", _paging);
      temp.put("documents", _documents);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public HolidaySearchHistoricResult createBean() {
      return new HolidaySearchHistoricResult();
    }

    @Override
    public Class<? extends HolidaySearchHistoricResult> beanType() {
      return HolidaySearchHistoricResult.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code paging} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Paging> paging() {
      return _paging;
    }

    /**
     * The meta-property for the {@code documents} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<HolidayDocument>> documents() {
      return _documents;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
