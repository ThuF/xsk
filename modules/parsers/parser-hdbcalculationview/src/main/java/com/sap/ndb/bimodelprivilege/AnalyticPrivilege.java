/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.26 at 10:54:28 AM EET 
//


package com.sap.ndb.bimodelprivilege;

import com.sap.ndb.basemodelbase.PrivilegeType;
import com.sap.ndb.bimodeldatafoundation.BIResource;
import com.sap.ndb.repositorymodelresource.RepositoryObject;
import com.sap.ndb.sqlcoremodelaccesscontrol.ValueFilter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Analytic privileges define multi-dimensional authorizations on information models
 * If an analytical privilege applies to more than one Information model the restrictions for
 * dimensions and attributes which are not contained in a cube are ignored at runtime.
 * An analytic privilege having zero restrictions in interpreted as "everything is allowed"
 * Constraints
 * 3. For privileges no excluding sign is allowed as of now - i.e.
 * Prililege.Restriction.Filter.including = true for all restrictions and all filters
 *
 *
 * <p>Java class for AnalyticPrivilege complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AnalyticPrivilege"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.sap.com/ndb/BiModelDataFoundation.ecore}BIResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="securedModels" type="{http://www.sap.com/ndb/BiModelPrivilege.ecore}SecuredModels" minOccurs="0"/&gt;
 *         &lt;element name="validity" type="{http://www.sap.com/ndb/SQLCoreModelAccessControl.ecore}ValueFilter" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="restriction" type="{http://www.sap.com/ndb/BiModelPrivilege.ecore}Restriction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;element name="conditionProcedureName" type="{http://www.sap.com/ndb/RepositoryModelResource.ecore}FQName"/&gt;
 *           &lt;element name="whereSql" type="{http://www.sap.com/ndb/BaseModelBase.ecore}SqlFragment"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="requiredEntity" type="{http://www.sap.com/ndb/RepositoryModelResource.ecore}RepositoryObject" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="privilegeType" type="{http://www.sap.com/ndb/BaseModelBase.ecore}PrivilegeType" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalyticPrivilege", propOrder = {
    "securedModels",
    "validity",
    "restriction",
    "conditionProcedureName",
    "whereSql",
    "requiredEntity"
})
public class AnalyticPrivilege
    extends BIResource {

  protected SecuredModels securedModels;
  protected List<ValueFilter> validity;
  protected List<Restriction> restriction;
  protected String conditionProcedureName;
  protected String whereSql;
  protected List<RepositoryObject> requiredEntity;
  @XmlAttribute(name = "privilegeType")
  protected PrivilegeType privilegeType;

  /**
   * Gets the value of the securedModels property.
   *
   * @return possible object is
   * {@link SecuredModels }
   */
  public SecuredModels getSecuredModels() {
    return securedModels;
  }

  /**
   * Sets the value of the securedModels property.
   *
   * @param value allowed object is
   *              {@link SecuredModels }
   */
  public void setSecuredModels(SecuredModels value) {
    this.securedModels = value;
  }

  /**
   * Gets the value of the validity property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the validity property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getValidity().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link ValueFilter }
   */
  public List<ValueFilter> getValidity() {
    if (validity == null) {
      validity = new ArrayList<ValueFilter>();
    }
    return this.validity;
  }

  /**
   * Gets the value of the restriction property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the restriction property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRestriction().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Restriction }
   */
  public List<Restriction> getRestriction() {
    if (restriction == null) {
      restriction = new ArrayList<Restriction>();
    }
    return this.restriction;
  }

  /**
   * Gets the value of the conditionProcedureName property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getConditionProcedureName() {
    return conditionProcedureName;
  }

  /**
   * Sets the value of the conditionProcedureName property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setConditionProcedureName(String value) {
    this.conditionProcedureName = value;
  }

  /**
   * Gets the value of the whereSql property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getWhereSql() {
    return whereSql;
  }

  /**
   * Sets the value of the whereSql property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setWhereSql(String value) {
    this.whereSql = value;
  }

  /**
   * Gets the value of the requiredEntity property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the requiredEntity property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRequiredEntity().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link RepositoryObject }
   */
  public List<RepositoryObject> getRequiredEntity() {
    if (requiredEntity == null) {
      requiredEntity = new ArrayList<RepositoryObject>();
    }
    return this.requiredEntity;
  }

  /**
   * Gets the value of the privilegeType property.
   *
   * @return possible object is
   * {@link PrivilegeType }
   */
  public PrivilegeType getPrivilegeType() {
    return privilegeType;
  }

  /**
   * Sets the value of the privilegeType property.
   *
   * @param value allowed object is
   *              {@link PrivilegeType }
   */
  public void setPrivilegeType(PrivilegeType value) {
    this.privilegeType = value;
  }

}