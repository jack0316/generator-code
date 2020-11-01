package com.jack.generatorcode.utils.base;

import com.jack.generatorcode.dataModel.database.BaseConfig;
import com.jack.generatorcode.dataModel.database.ColumnModel;
import com.jack.generatorcode.dataModel.database.TableModel;
import com.jack.generatorcode.utils.Tool;

import java.sql.ResultSet;

public class MysqlBase {

    public static TableModel buildTableInfo(BaseConfig config) throws Exception{
        String user = config.getUser();
        String pwd = config.getPassword();
        String url = Tool.format("jdbc:mysql://{0}:{1,number,#}/{2}?serverTimezone=Asia/Shanghai",config.getHost(),config.getPort(),config.getDatabaseName());
        String driver = "com.mysql.cj.jdbc.Driver";
        BaseDao baseDao = new BaseDao(url,user,pwd,driver);
        ResultSet rs = baseDao.query("select * from information_schema.TABLES where TABLE_NAME = ? and TABLE_SCHEMA = ?",config.getTableName(),config.getDatabaseName());
        TableModel table = new TableModel();
        if (rs.next()){
            table.setDatabaseName(rs.getString("table_schema"));
            table.setTableName(rs.getString("table_name"));
            table.setComment(rs.getString("table_comment"));
        }

        rs = (ResultSet) baseDao.execSql("select * from information_schema.COLUMNS where TABLE_NAME = ? and TABLE_SCHEMA = ?",config.getTableName(),config.getDatabaseName());
        while (rs.next()) {
            long scale = rs.getLong("numeric_scale");
            ColumnModel column = new ColumnModel();
            column.setColumnName(rs.getString("column_name"));
            column.setJdbcType(rs.getString("data_type"));
            column.setJavaType("Long");
            if (column.getJdbcType().contains("varchar")){
                column.setJavaType("String");
            }
            if ( scale > 0L){
                column.setJavaType("BigDecimal");
            }
            column.setComment(rs.getString("column_comment"));
            table.addColumn(column);
        }
        return table;
    }

}
