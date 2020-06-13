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
import model.MatHang;
import model.*;

/**
 *
 * @author Duong
 */
public class CongNoDAO extends DAO{

    public CongNoDAO() {
        super();
    }
    public ArrayList<CongNo> getAllCongNo() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select *  from [CuaHangHoaQua].[dbo].[CongNo]";
        ArrayList<CongNo> listCongNo = new ArrayList<>();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                CongNo cn = new CongNo();
                cn.setIdCongNo(rs.getInt(1));
                cn.setMaSoThue(rs.getString(2));
                cn.setSoTienNo(rs.getInt(3));
                NhaCungCap cap=new NhaCungCap();
                cap.setId((rs.getInt(4)));
                PhieuThuChi chi=new PhieuThuChi();
                chi.setIdPhieuThuChi(rs.getInt(5));
                cn.setPhieuThuChi(chi);
                cn.setCap(cap);
                listCongNo.add(cn);
            }
            con.close();
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
        return listCongNo;
    }
    public boolean themCongNo(CongNo cn) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String maSoThue = "N'" + cn.getMaSoThue()+ "',";
        String soTienNo = "N'" + cn.getSoTienNo()+ "',";
        String idNhaCungCap = "N'" + cn.getCap().getId()+ "'";
        String sql = "insert into [CuaHangHoaQua].[dbo].[CongNo] (maSoThue,soTienNo,idNhaCungCap)"
                + " values(" + maSoThue + soTienNo + idNhaCungCap + ")";
        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement(sql);
            stm.executeUpdate();
            con.commit();
            con.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("roll back...CongNoDAO");
                }
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            return false;
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex3) {
                //
                ex3.printStackTrace();
            }
        }
        return true;
    }
}
