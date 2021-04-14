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
package com.sap.xsk.hdbti;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.api.IXSKHDBTIProcessor;
import com.sap.xsk.hdbti.model.Student;
import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.module.HdbtiTestModule;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class XSKHDBTIProcessorTest {

    private static final String STUDENTS_CSV_LOCATION = "registry/public/university/students/students.csv";

    private Connection connection;
    private IXSKHDBTIProcessor processor;
    private PersistenceManager<Student> studentManager = new PersistenceManager<>();
    private PersistenceManager<XSKImportedCSVRecordModel> importedCsvRecordsManager = new PersistenceManager<>();

    @Before
    public void setUp() throws SQLException {
        Injector injector = Guice.createInjector(new HdbtiTestModule());
        this.connection = injector.getInstance(DataSource.class).getConnection();
        processor = injector.getInstance(Key.get(IXSKHDBTIProcessor.class, Names.named("xskHdbtiProcessor")));
        studentManager.tableCreate(connection, Student.class);
    }

    @After
    public void cleanup() {
        studentManager.tableDrop(connection, Student.class);
        importedCsvRecordsManager.tableDrop(connection, XSKImportedCSVRecordModel.class);
    }

    @Test
    public void testDataImportedCorrectly() throws XSKDataStructuresException, SQLException, IOException {
        writeToFile(STUDENTS_CSV_LOCATION, getFullContent());
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile("university.students:students.csv");
        importConfigurationDefinition.setHdbtiFileName("university.data:Students.hdbti");
        importConfigurationDefinition.setTable("STUDENTS");

        processor.process(importConfigurationDefinition, connection);
        List<Student> students = studentManager.findAll(connection, Student.class);
        assertCorrectDataImported(students);

        List<XSKImportedCSVRecordModel> importedCsvRecords = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
        assertCorrectCsvRecordMetadataImported(importedCsvRecords);
        writeToFile(STUDENTS_CSV_LOCATION, "");
    }

    @Test
    public void testChangeOfDataProperlyReflectedInDb() throws XSKDataStructuresException, SQLException, IOException {
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile("university.students:students.csv");
        importConfigurationDefinition.setHdbtiFileName("university.data:Students.hdbti");
        importConfigurationDefinition.setTable("STUDENTS");

        writeToFile(STUDENTS_CSV_LOCATION, getFullContent());
        processor.process(importConfigurationDefinition, connection);

        writeToFile(STUDENTS_CSV_LOCATION, "");
        writeToFile(STUDENTS_CSV_LOCATION, getUpdatedContent());
        processor.process(importConfigurationDefinition, connection);

        List<Student> students1 = studentManager.findAll(connection, Student.class);
        assertEquals("Changed", students1.get(1).getFirstName());
        assertEquals("Changed", students1.get(1).getLastName());
        assertEquals(Timestamp.valueOf("2018-09-21 14:00:12"), students1.get(1).getSigned());
    }

    @Test
    public void testCsvRecordRemovedFromCsvFileShouldRemoveFromDb() throws XSKDataStructuresException, SQLException, IOException {
        XSKTableImportConfigurationDefinition importConfigurationDefinitionWithRemovedRecord = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinitionWithRemovedRecord.setFile("university.students:students.csv");
        importConfigurationDefinitionWithRemovedRecord.setHdbtiFileName("university.data:Students.hdbti");
        importConfigurationDefinitionWithRemovedRecord.setTable("STUDENTS");

        writeToFile(STUDENTS_CSV_LOCATION, getFullContent());
        processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

        writeToFile(STUDENTS_CSV_LOCATION, "");
        writeToFile(STUDENTS_CSV_LOCATION, getRemovedContent());
        processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

        List<Student> students = studentManager.findAll(connection, Student.class);
        assertEquals(2, students.size());

        List<XSKImportedCSVRecordModel> importedCsvRecords = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
        assertEquals(2, importedCsvRecords.size());

        Student deletedStudent = studentManager.find(connection, Student.class, 1L);
        assertNull(deletedStudent);
        List<XSKImportedCSVRecordModel> importedCSVRecordModel = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
        boolean isCsvRecordRemovedFromMetadataTable = importedCSVRecordModel.stream().anyMatch(i -> i.getRowId().equals("1"));
        assertFalse(isCsvRecordRemovedFromMetadataTable);

        writeToFile(STUDENTS_CSV_LOCATION, "");
    }

    @Test
    public void testCsvRecordAddedShouldReflectDataInDb() throws XSKDataStructuresException, SQLException, IOException {
        XSKTableImportConfigurationDefinition importConfigurationDefinitionWithRemovedRecord = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinitionWithRemovedRecord.setFile("university.students:students.csv");
        importConfigurationDefinitionWithRemovedRecord.setHdbtiFileName("university.data:Students.hdbti");
        importConfigurationDefinitionWithRemovedRecord.setTable("STUDENTS");

        writeToFile(STUDENTS_CSV_LOCATION, getFullContent());
        processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

        writeToFile(STUDENTS_CSV_LOCATION, "");
        writeToFile(STUDENTS_CSV_LOCATION, getAddedContent());

        processor.process(importConfigurationDefinitionWithRemovedRecord, connection);

        List<Student> students = studentManager.findAll(connection, Student.class);
        assertEquals(4, students.size());
        List<XSKImportedCSVRecordModel> importedCSVRecordModel = importedCsvRecordsManager.findAll(connection, XSKImportedCSVRecordModel.class);
        assertEquals(4, importedCSVRecordModel.size());
        writeToFile(STUDENTS_CSV_LOCATION, "");
    }

    @Test
    public void testCsvRecordWithCustomDelimFieldDelimEnclosingAndEscapeCharShouldInsertProperData() throws XSKDataStructuresException, SQLException, IOException {
        XSKTableImportConfigurationDefinition importConfigurationDefinition = new XSKTableImportConfigurationDefinition();
        importConfigurationDefinition.setFile("university.students:students.csv");
        importConfigurationDefinition.setHdbtiFileName("university.data:Students.hdbti");
        importConfigurationDefinition.setTable("STUDENTS");
        importConfigurationDefinition.setDelimEnclosing("'");
        importConfigurationDefinition.setDelimField(";");

        writeToFile(STUDENTS_CSV_LOCATION, getQuoteContent());
        processor.process(importConfigurationDefinition, connection);
        List<Student> students = studentManager.findAll(connection, Student.class);
        assertEquals(1, students.size());
        assertEquals("Georgi, 'Junior'", students.get(0).getFirstName());

        writeToFile(STUDENTS_CSV_LOCATION, "");
    }

    private void assertCorrectDataImported(List<Student> students) {
        int importedStudentsCount = 3;
        assertEquals(importedStudentsCount, students.size());
        assertEquals(1L, students.get(0).getId().longValue());
        assertEquals("Georgi", students.get(0).getFirstName());
        assertEquals("Georgiev", students.get(0).getLastName());
        assertEquals(27L, students.get(0).getAge().longValue());
        assertEquals(Timestamp.valueOf("2017-09-20 14:00:12"), students.get(0).getSigned());

        assertEquals(2L, students.get(1).getId().longValue());
        assertEquals("Sara", students.get(1).getFirstName());
        assertEquals("Toshkova", students.get(1).getLastName());
        assertEquals(21L, students.get(1).getAge().longValue());
        assertEquals(Timestamp.valueOf("2019-09-21 14:00:12"), students.get(1).getSigned());

        assertEquals(3L, students.get(2).getId().longValue());
        assertEquals("Toshko", students.get(2).getFirstName());
        assertEquals("Ivanov", students.get(2).getLastName());
        assertEquals(25L, students.get(2).getAge().longValue());
        assertEquals(Timestamp.valueOf("2018-09-23 14:00:12"), students.get(2).getSigned());
    }

    private void assertCorrectCsvRecordMetadataImported(List<XSKImportedCSVRecordModel> importedCsvRecords) {
        assertEquals(3, importedCsvRecords.size());

        assertEquals("1", importedCsvRecords.get(0).getRowId());
        assertEquals("STUDENTS", importedCsvRecords.get(0).getTableName());
        assertEquals("university.students:students.csv", importedCsvRecords.get(0).getCsvLocation());
        assertEquals("university.data:Students.hdbti", importedCsvRecords.get(0).getHdbtiLocation());

        assertEquals("2", importedCsvRecords.get(1).getRowId());
        assertEquals("STUDENTS", importedCsvRecords.get(1).getTableName());
        assertEquals("university.students:students.csv", importedCsvRecords.get(1).getCsvLocation());
        assertEquals("university.data:Students.hdbti", importedCsvRecords.get(1).getHdbtiLocation());

        assertEquals("3", importedCsvRecords.get(2).getRowId());
        assertEquals("STUDENTS", importedCsvRecords.get(2).getTableName());
        assertEquals("university.students:students.csv", importedCsvRecords.get(2).getCsvLocation());
        assertEquals("university.data:Students.hdbti", importedCsvRecords.get(2).getHdbtiLocation());
    }

    private String getFullContent() {
        return "1,Georgi,Georgiev,27,2017-09-20 14:00:12" + System.lineSeparator()
                + "2,Sara,Toshkova,21,2019-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private String getQuoteContent() {
        return "1;'Georgi, \\'Junior\\'';Georgiev;27;2017-09-20 14:00:12" + System.lineSeparator();
    }

    private String getAddedContent() {
        return "1,Georgi,Georgiev,27,2017-09-20 14:00:12" + System.lineSeparator()
                + "2,Sara,Toshkova,21,2019-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator()
                + "4,Pesho,Peshev,25,2014-09-23 13:10:12" + System.lineSeparator();
    }

    private String getUpdatedContent() {
        return "1,Georgi,Georgiev,27,2017-09-20 14:00:12" + System.lineSeparator()
                + "2,Changed,Changed,21,2018-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private String getRemovedContent() {
        return "2,Sara,Toshkova,21,2019-09-21 14:00:12" + System.lineSeparator()
                + "3,Toshko,Ivanov,25,2018-09-23 14:00:12" + System.lineSeparator();
    }

    private void writeToFile(String path, String content) {
        File fileToBeModified = getFileFromResource(path);
        try (FileWriter fileWriter = new FileWriter(fileToBeModified)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromResource(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
