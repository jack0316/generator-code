package com.jack.generatorcode.dataModel.database;

import java.util.ArrayList;
import java.util.List;

public class TableModel {

    private String tableName;

    private String databaseName;

    private String comment;

    private String primaryKey;

    private final List<ColumnModel> columns = new ArrayList<>();


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public TableModel addColumn(ColumnModel column){
        this.columns.add(column);
        return this;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }
}
