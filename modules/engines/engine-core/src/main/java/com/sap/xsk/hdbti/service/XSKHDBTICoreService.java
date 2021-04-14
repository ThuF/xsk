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
package com.sap.xsk.hdbti.service;

import com.google.inject.name.Named;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdbti.api.*;
import com.sap.xsk.hdbti.model.XSKImportedCSVRecordModel;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import com.sap.xsk.utils.XSKUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.csv.CSVRecord;
import org.apache.cxf.common.util.StringUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.repository.api.IRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class XSKHDBTICoreService implements IXSKHDBTICoreService {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDBTICoreService.class);

    @com.google.inject.Inject
    @Named("xskcsvRecordDao")
    private IXSKCSVRecordDao xskcsvRecordDao;
    @com.google.inject.Inject
    @Named("xskImportedCSVRecordDao")
    private IXSKImportedCSVRecordDao xskImportedCSVRecordDao;
    @com.google.inject.Inject
    @Named("xskTableImportArtifactDao")
    private IXSKTableImportArtifactDao xskTableImportArtifactDao;
    @com.google.inject.Inject
    @Named("xskCsvToHdbtiRelationDao")
    private IXSKCsvToHdbtiRelationDao xskCsvToHdbtiRelationDao;
    @Inject
    private IRepository repository;
    @Inject
    private DBMetadataUtil dbMetadataUtil;

    @Override
    public void insertCsvRecords(List<CSVRecord> recordsToInsert, XSKTableImportConfigurationDefinition tableImportConfigurationDefinition) {
        String tableName = convertToActualTableName(tableImportConfigurationDefinition.getTable());

        for (CSVRecord csvRecord : recordsToInsert) {
            try {
                xskcsvRecordDao.save(csvRecord, tableName, tableImportConfigurationDefinition.getDistinguishEmptyFromNull());

                XSKImportedCSVRecordModel importedCSVRecordModel = new XSKImportedCSVRecordModel();
                importedCSVRecordModel.setCsvLocation(tableImportConfigurationDefinition.getFile());
                importedCSVRecordModel.setHash(getCSVRecordHash(csvRecord));
                importedCSVRecordModel.setHdbtiLocation(tableImportConfigurationDefinition.getHdbtiFileName());
                importedCSVRecordModel.setRowId(getPkForCSVRecord(csvRecord, tableName));
                importedCSVRecordModel.setTableName(tableName);
                xskImportedCSVRecordDao.save(importedCSVRecordModel);
            } catch (SQLException e) {
                logger.error(csvRecord.toString());
                logger.error("Error occurred while inserting the csv values in the table pointed by HDBTI file", e);
                logger.error(format("Error occurred while processing %s for table %s at record %d",
                        tableImportConfigurationDefinition.getFile(), tableImportConfigurationDefinition.getTable(),
                        csvRecord.getRecordNumber()), e);
            } catch (Exception e) {
                logger.error(csvRecord.toString());
                logger.error(format("Error occurred while processing %s for table %s at record %d",
                        tableImportConfigurationDefinition.getFile(), tableImportConfigurationDefinition.getTable(),
                        csvRecord.getRecordNumber()), e);
            }
        }
    }

    @Override
    public Map<String, XSKImportedCSVRecordModel> getImportedCSVRecordsByTableAndCSVLocation(String tableName, String csvLocation) throws XSKDataStructuresException {
        return xskImportedCSVRecordDao.getImportedCSVRecordsByTableAndCSVLocation(tableName, csvLocation)
                .stream()
                .collect(Collectors.toMap(XSKImportedCSVRecordModel::getRowId, importModel -> importModel));
    }

    @Override
    public void cleanUpHdbtiRelatedData() throws XSKTableImportException {
        List<XSKTableImportArtifact> tableImportArtifacts = xskTableImportArtifactDao.getTableImportArtifacts();

        for (XSKTableImportArtifact tableImportArtifact : tableImportArtifacts) {
            if (!repository.hasResource(XSKUtils.convertToFullPath(tableImportArtifact.getLocation()))) {
                xskTableImportArtifactDao.removeTableImportArtifact(tableImportArtifact.getLocation());
                xskCsvToHdbtiRelationDao.deleteCsvAndHdbtiRelations(tableImportArtifact.getLocation());
                removeCSVRecordsFromDb(tableImportArtifact.getLocation());
                logger
                        .warn("Cleaned up HDBTI file [{}] from location: {}", tableImportArtifact.getName(),
                                tableImportArtifact.getLocation());
            }
        }
    }

    @Override
    public void updateCsvRecords(List<CSVRecord> csvRecords, List<XSKImportedCSVRecordModel> importedCsvRecordsToUpdate, String tableName, Boolean distinguishEmptyFromNull) {
        for (CSVRecord csvRecord : csvRecords) {
            try {
                xskcsvRecordDao.update(csvRecord, tableName, getPkForCSVRecord(csvRecord, tableName), distinguishEmptyFromNull);
                for (XSKImportedCSVRecordModel importedCSVRecordModel : importedCsvRecordsToUpdate) {
                    xskImportedCSVRecordDao.update(importedCSVRecordModel);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void removeCsvRecords(List<XSKImportedCSVRecordModel> importedCSVRecordModels, String tableName) {
        List<String> idsToRemove = importedCSVRecordModels.stream().map(XSKImportedCSVRecordModel::getRowId).collect(Collectors.toList());

        try {
            xskcsvRecordDao.deleteAll(idsToRemove, tableName);
            xskImportedCSVRecordDao.deleteAll(importedCSVRecordModels);
        } catch (SQLException sqlException) {
            logger.error(String.format("An error occurred while trying to delete removed CSVs with ids: %s from table %s", String.join(",", idsToRemove)));
        }
    }

    @Override
    public void refreshCsvRelations(XSKTableImportArtifact tableImportArtifact) {
        xskCsvToHdbtiRelationDao.deleteCsvAndHdbtiRelations(XSKUtils.convertToFullPath(tableImportArtifact.getLocation()));
        xskCsvToHdbtiRelationDao.persistNewCsvAndHdbtiRelations(tableImportArtifact);
    }

    @Override
    public String getPkForCSVRecord(CSVRecord csvRecord, String tableName) {
        PersistenceTableModel tableMetadata = getTableMetadata(tableName);
        for (int i = 0; i < csvRecord.size(); i++) {
            boolean isColumnPk = tableMetadata.getColumns().get(i).isPrimaryKey();
            if (isColumnPk && !StringUtils.isEmpty(csvRecord.get(i))) {
                return csvRecord.get(i);
            }
        }

        return null;
    }

    @Override
    public String getCSVRecordHash(CSVRecord csvRecord) {
        StringBuilder joinedValues = new StringBuilder();

        for (int i = 0; i < csvRecord.size(); i++) {
            joinedValues.append(csvRecord.get(i));
            if (i != csvRecord.size() - 1) {
                joinedValues.append(",");
            }
        }

        return DigestUtils.md5Hex(joinedValues.toString());
    }

    @Override
    public String convertToActualTableName(String tableName) {
        boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        if (caseSensitive) {
            tableName = "\"" + tableName + "\"";
        }
        return tableName;
    }

    @Override
    public String convertToActualFileName(String fileNamePath) {
        String fileName = fileNamePath.substring(fileNamePath.lastIndexOf(':') + 1);

        return "/registry/public/" + fileNamePath.substring(0, fileNamePath.indexOf(':')).replaceAll("\\.", "/") + "/" + fileName;
    }

    private PersistenceTableModel getTableMetadata(String tableName) {
        try {
            return dbMetadataUtil.getTableMetadata(tableName);
        } catch (SQLException sqlException) {
            logger.error(String.format("Error occurred while trying to read table metadata for table with name: %s", tableName), sqlException);
        }

        return null;
    }

    private void removeCSVRecordsFromDb(String hdbtiLocation) {
        List<XSKImportedCSVRecordModel> csvRecordsToRemove = xskImportedCSVRecordDao.getImportedCSVsByHdbtiLocation(hdbtiLocation);
        csvRecordsToRemove.sort(Comparator.comparing(XSKImportedCSVRecordModel::getCreatedAt, Comparator.reverseOrder()));
        try {
            for (XSKImportedCSVRecordModel csvRecord :
                    csvRecordsToRemove) {
                xskcsvRecordDao.delete(csvRecord.getRowId(), csvRecord.getTableName());
            }

            xskImportedCSVRecordDao.deleteAll(csvRecordsToRemove);
        } catch (SQLException sqlException) {
            logger.error(String.format("Error occurred while trying to remove imported csv records after a deletion of an hdbti file with location: %s", hdbtiLocation), sqlException);
        }
    }
}
