/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.BoPhan;
import model.CongTy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Duong
 */
public class BoPhanDAO extends DAO {
    public BoPhanDAO() {
        super();
    }

    public static void main(String[] args) {
        BoPhanDAO boPhanDAO = new BoPhanDAO();
        ArrayList<BoPhan> allBoPhan = boPhanDAO.getAllBoPhan();
        for (BoPhan boPhan : allBoPhan) {
            System.out.println(boPhan);
        }
    }

    public BoPhan getBoPhanById(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select *  from [CuaHangHoaQua].[dbo].[BoPhan] where idBoPhan=" + id + "";
        BoPhan boPhan = new BoPhan();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int idCongTy = rs.getInt(3);
                CongTy congTy = new CongTy();
                congTy.setId(idCongTy);
                boPhan.setId(rs.getInt(1));
                boPhan.setTen(rs.getString(2));
                boPhan.setCongTy(congTy);
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
        return boPhan;

    }

    public ArrayList<BoPhan> getAllBoPhan() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select *  from [CuaHangHoaQua].[dbo].[BoPhan]";
        ArrayList<BoPhan> listBoPhan = new ArrayList<>();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                BoPhan boPhan = new BoPhan();
                int idCongTy = rs.getInt(3);
                CongTy congTy = new CongTy();
                congTy.setId(idCongTy);
                boPhan.setId(rs.getInt(1));
                boPhan.setTen(rs.getString(2));
                boPhan.setCongTy(congTy);
                listBoPhan.add(boPhan);
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
        return listBoPhan;
    }
}
