package com.jack.generatorcode.utils.base;

import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BaseTool extends BaseDao {


    public BaseTool() {
        super("jdbc:mysql://localhost:3306/shanghaiag?serverTimezone=Asia/Shanghai","root","root","com.mysql");
    }



    public String resultJson(String sql, Object... obj) {
        StringBuilder json = new StringBuilder();
        ResultSet rs = super.query(sql, obj);
        assert rs != null;
        try {
            boolean flag = true;
            json.append("[");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                flag = false;
                json.append("{");
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    json.append("\"").append(columnName).append("\":");
                    if (value instanceof Number) json.append(value);
                    else json.append("\"").append(value).append("\"");
                    if (i < columnCount) json.append(",");
                }
                json.append("},");
            }
            if (flag) return "";
            String str = json.toString();
            int index = str.lastIndexOf(",");
            return str.substring(0, index) + "]";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                super.closeConn(rs.getStatement().getConnection(), rs.getStatement(), rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "[]";
    }

}
