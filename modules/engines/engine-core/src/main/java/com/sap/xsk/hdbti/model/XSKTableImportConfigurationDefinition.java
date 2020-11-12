package com.sap.xsk.hdbti.model;

import java.util.Map;
import java.util.Objects;

public class XSKTableImportConfigurationDefinition {

    private Long id;

    private String table;

    private String schema;

    private String file;

    private Boolean header;

    private Boolean useHeaderNames;

    private String delimField;

    private String delimEnclosing;

    private Boolean distinguishEmptyFromNull;

    private Map<String, String> keysAsMap;

    public XSKTableImportConfigurationDefinition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Boolean getHeader() {
        return header;
    }

    public void setHeader(Boolean header) {
        this.header = header;
    }

    public Boolean getUseHeaderNames() {
        return useHeaderNames;
    }

    public void setUseHeaderNames(Boolean useHeaderNames) {
        this.useHeaderNames = useHeaderNames;
    }

    public String getDelimField() {
        return delimField;
    }

    public void setDelimField(String delimField) {
        this.delimField = delimField;
    }

    public String getDelimEnclosing() {
        return delimEnclosing;
    }

    public void setDelimEnclosing(String delimEnclosing) {
        this.delimEnclosing = delimEnclosing;
    }

    public Boolean getDistinguishEmptyFromNull() {
        return distinguishEmptyFromNull;
    }

    public void setDistinguishEmptyFromNull(Boolean distinguishEmptyFromNull) {
        this.distinguishEmptyFromNull = distinguishEmptyFromNull;
    }

    public Map<String, String> getKeysAsMap() {
        return keysAsMap;
    }

    public void setKeysAsMap(Map<String, String> keysAsMap) {
        this.keysAsMap = keysAsMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XSKTableImportConfigurationDefinition that = (XSKTableImportConfigurationDefinition) o;
        return id.equals(that.id) &&
                table.equals(that.table) &&
                schema.equals(that.schema) &&
                file.equals(that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, table, schema, file);
    }
}