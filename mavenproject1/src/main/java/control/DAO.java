/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Duong
 */
public class DAO {

    protected static Connection con = null;

    public DAO() {
        System.out.println("CALL DAO constructor");
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CuaHangHoaQua";
        String user = "sa";
        String pass = "123";
        try {
//                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                con = DriverManager.getConnection(dbURL, user, pass);
            con = HikariCPDataSource.getConnection();
            // Code here.
        } // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql2 = "select * from taikhoan";
        try {
            con = HikariCPDataSource.getConnection();
            stm = con.prepareStatement(sql2);
            rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println("id:\t" + rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        try {
            if (con.isClosed()) {
                try {
                    con = HikariCPDataSource.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
