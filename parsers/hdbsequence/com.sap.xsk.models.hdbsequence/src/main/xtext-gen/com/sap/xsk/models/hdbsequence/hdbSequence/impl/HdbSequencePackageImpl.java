/**
 * generated by Xtext 2.18.0
 */
package com.sap.xsk.models.hdbsequence.hdbSequence.impl;

import com.sap.xsk.models.hdbsequence.hdbSequence.HdbSequenceElements;
import com.sap.xsk.models.hdbsequence.hdbSequence.HdbSequenceFactory;
import com.sap.xsk.models.hdbsequence.hdbSequence.HdbSequenceModel;
import com.sap.xsk.models.hdbsequence.hdbSequence.HdbSequencePackage;
import com.sap.xsk.models.hdbsequence.hdbSequence.ListString;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HdbSequencePackageImpl extends EPackageImpl implements HdbSequencePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass hdbSequenceModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass listStringEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass hdbSequenceElementsEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see com.sap.xsk.models.hdbsequence.hdbSequence.HdbSequencePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private HdbSequencePackageImpl()
  {
    super(eNS_URI, HdbSequenceFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link HdbSequencePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static HdbSequencePackage init()
  {
    if (isInited) return (HdbSequencePackage)EPackage.Registry.INSTANCE.getEPackage(HdbSequencePackage.eNS_URI);

    // Obtain or create and register package
    HdbSequencePackageImpl theHdbSequencePackage = (HdbSequencePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof HdbSequencePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new HdbSequencePackageImpl());

    isInited = true;

    // Create package meta-data objects
    theHdbSequencePackage.createPackageContents();

    // Initialize created meta-data
    theHdbSequencePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theHdbSequencePackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(HdbSequencePackage.eNS_URI, theHdbSequencePackage);
    return theHdbSequencePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getHdbSequenceModel()
  {
    return hdbSequenceModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getHdbSequenceModel_Elements()
  {
    return (EReference)hdbSequenceModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getListString()
  {
    return listStringEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getListString_Values()
  {
    return (EAttribute)listStringEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getHdbSequenceElements()
  {
    return hdbSequenceElementsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_Schema()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_SchemaValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_IncrementBy()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_IncrementByValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_StartWith()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_StartWithValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_MaxValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_MaxValueValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_NoMaxValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_NoMaxValueValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_MinValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_MinVlaueValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_NoMinValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_NoMinValueValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_Cycles()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_CyclesValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_Public()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_PublicValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_DependsOnTable()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_DependsOnTableValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_DependsOnView()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHdbSequenceElements_DependsOnViewValue()
  {
    return (EAttribute)hdbSequenceElementsEClass.getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getHdbSequenceElements_Value()
  {
    return (EReference)hdbSequenceElementsEClass.getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HdbSequenceFactory getHdbSequenceFactory()
  {
    return (HdbSequenceFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    hdbSequenceModelEClass = createEClass(HDB_SEQUENCE_MODEL);
    createEReference(hdbSequenceModelEClass, HDB_SEQUENCE_MODEL__ELEMENTS);

    listStringEClass = createEClass(LIST_STRING);
    createEAttribute(listStringEClass, LIST_STRING__VALUES);

    hdbSequenceElementsEClass = createEClass(HDB_SEQUENCE_ELEMENTS);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__SCHEMA);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__SCHEMA_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__INCREMENT_BY);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__INCREMENT_BY_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__START_WITH);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__START_WITH_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__MAX_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__MAX_VALUE_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__NO_MAX_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__NO_MAX_VALUE_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__MIN_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__MIN_VLAUE_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__NO_MIN_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__NO_MIN_VALUE_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__CYCLES);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__CYCLES_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__PUBLIC);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__PUBLIC_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__DEPENDS_ON_TABLE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__DEPENDS_ON_TABLE_VALUE);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__DEPENDS_ON_VIEW);
    createEAttribute(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__DEPENDS_ON_VIEW_VALUE);
    createEReference(hdbSequenceElementsEClass, HDB_SEQUENCE_ELEMENTS__VALUE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(hdbSequenceModelEClass, HdbSequenceModel.class, "HdbSequenceModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getHdbSequenceModel_Elements(), this.getHdbSequenceElements(), null, "elements", null, 0, -1, HdbSequenceModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(listStringEClass, ListString.class, "ListString", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getListString_Values(), ecorePackage.getEString(), "values", null, 0, -1, ListString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(hdbSequenceElementsEClass, HdbSequenceElements.class, "HdbSequenceElements", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getHdbSequenceElements_Schema(), ecorePackage.getEString(), "schema", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_SchemaValue(), ecorePackage.getEString(), "schemaValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_IncrementBy(), ecorePackage.getEString(), "incrementBy", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_IncrementByValue(), ecorePackage.getEInt(), "incrementByValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_StartWith(), ecorePackage.getEString(), "startWith", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_StartWithValue(), ecorePackage.getEInt(), "startWithValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_MaxValue(), ecorePackage.getEString(), "maxValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_MaxValueValue(), ecorePackage.getEInt(), "maxValueValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_NoMaxValue(), ecorePackage.getEString(), "noMaxValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_NoMaxValueValue(), ecorePackage.getEString(), "noMaxValueValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_MinValue(), ecorePackage.getEString(), "minValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_MinVlaueValue(), ecorePackage.getEInt(), "minVlaueValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_NoMinValue(), ecorePackage.getEString(), "noMinValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_NoMinValueValue(), ecorePackage.getEString(), "noMinValueValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_Cycles(), ecorePackage.getEString(), "cycles", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_CyclesValue(), ecorePackage.getEString(), "cyclesValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_Public(), ecorePackage.getEString(), "public", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_PublicValue(), ecorePackage.getEString(), "publicValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_DependsOnTable(), ecorePackage.getEString(), "dependsOnTable", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_DependsOnTableValue(), ecorePackage.getEString(), "dependsOnTableValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_DependsOnView(), ecorePackage.getEString(), "dependsOnView", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getHdbSequenceElements_DependsOnViewValue(), ecorePackage.getEString(), "dependsOnViewValue", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getHdbSequenceElements_Value(), this.getListString(), null, "value", null, 0, 1, HdbSequenceElements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //HdbSequencePackageImpl