package com.jack.generatorcode.utils.base;


import java.sql.*;

public class BaseDao {


    private String URL;
    private String USER;
    private String PASSWORD;
    private String driverName;

   public BaseDao(String URL, String USER, String PASSWORD,String driverName) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.driverName = driverName;
    }


    public Connection getConn() {
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConn(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql, Object... obj) {
        Connection conn = this.getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int exec(String sql, Object... obj) {
        Connection conn = this.getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConn(conn, ps, null);
        }
        return -1;
    }

    public Object execSql(String sql, Object... obj){
        Connection conn = this.getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            boolean execute = ps.execute();
            if (execute) return  ps.getResultSet() ;
            return ps.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
