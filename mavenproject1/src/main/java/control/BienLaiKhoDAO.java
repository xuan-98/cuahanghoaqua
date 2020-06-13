/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import static control.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Duong
 */
public class BienLaiKhoDAO extends DAO{
    public BienLaiKhoDAO() {
        super();
    }
    public ArrayList<BienLaiKho> getAllBienLaiKho() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select *  from [CuaHangHoaQua].[dbo].[BienLaiKho]";
        ArrayList<BienLaiKho> bienLaiKhos = new ArrayList<>();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                BienLaiKho bienLaiKho = new BienLaiKho();
                bienLaiKho.setId(rs.getInt(1));
                bienLaiKho.setMaBienLai(rs.getString(2));
                bienLaiKho.setNgayLap(rs.getString(3));
                Kho k=new Kho();
                k.setId(rs.getInt(4));
                bienLaiKho.setKho(k);
                bienLaiKho.setSoLuong(rs.getInt(5));
                bienLaiKho.setTongCong(rs.getInt(6));
                bienLaiKhos.add(bienLaiKho);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex) {
                //
            }
        }
        return bienLaiKhos;
    }
}
