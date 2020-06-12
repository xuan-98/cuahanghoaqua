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
public class BienLaiNhapDAO extends DAO {

    public BienLaiNhapDAO() {
        super();
    }

    public ArrayList<BienLaiNhap> getAllBienLaiNhap() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select *  from [CuaHangHoaQua].[dbo].[BienLaiNhap] inner join [CuaHangHoaQua].[dbo].[BienLaiKho] on BienLaiNhap.idBienLaiKho=BienLaiKho.idBienLaiKho";
        ArrayList<BienLaiNhap> listBienLaiNhap = new ArrayList<>();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                BienLaiNhap bienLaiNhap = new BienLaiNhap();
                bienLaiNhap.setIdBienLaiNhap(rs.getInt(1));
                bienLaiNhap.setId(rs.getInt(2));// set Id bien lai kho
                HopDong dong = new HopDong();
                dong.setId(rs.getInt(3));
                PhieuThuChi chi = new PhieuThuChi();
                chi.setIdPhieuThuChi(rs.getInt(4));
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getInt(5));
                ///////////////
                bienLaiNhap.setHopDong(dong);
                bienLaiNhap.setPhieuThuChi(chi);
                bienLaiNhap.setNhanVien(nhanVien);
                bienLaiNhap.setId(rs.getInt(6));
                bienLaiNhap.setMaBienLai(rs.getString(7));
                bienLaiNhap.setNgayLap(rs.getString(8));
                Kho k = new Kho();
                k.setId(rs.getInt(9));
                bienLaiNhap.setKho(k);
                bienLaiNhap.setSoLuong(rs.getInt(10));
                listBienLaiNhap.add(bienLaiNhap);
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
        return listBienLaiNhap;
    }

    public boolean themBienLaiNhap(BienLaiNhap bienLaiNhap) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String idBienLaiNhap = "'" + bienLaiNhap.getIdBienLaiNhap() + "',";
        String idBienLaiKho = "'" + bienLaiNhap.getId() + "',";//get id bien lai kho
        String idHopDong = "'" + bienLaiNhap.getHopDong().getId() + "',";
        String idPhieuThuChi = "'" + bienLaiNhap.getPhieuThuChi().getIdPhieuThuChi() + "',";
        String idNhanVien = "'" + bienLaiNhap.getNhanVien().getIdNhanVien() + "'";
        String maBienLaiKho = "N'" + bienLaiNhap.getMaBienLai() + "',";
        String ngayLap = "N'" + bienLaiNhap.getNgayLap() + "',";
        String soLuong = "N'" + bienLaiNhap.getSoLuong() + "'";
        String idKho = "N'" + bienLaiNhap.getKho().getId()+ "',";

        String sql = "insert into [CuaHangHoaQua].[dbo].[BienLaiNhap] (idBienLaiKho,idHopDong,idPhieuThuChi,idNhanVien)"
                + " values("  +idBienLaiKho + idHopDong + idPhieuThuChi + idNhanVien + ")";
        String sql2 = "insert into [CuaHangHoaQua].[dbo].[BienLaiKho] (idBienLaiKho,maBienLaiKho,ngayLap,idKho,soLuong)"
                + " values(" + idBienLaiKho + maBienLaiKho + ngayLap + idKho+soLuong + ")";
        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement(sql);
            stm.executeUpdate();
            stm = con.prepareStatement(sql2);
            stm.executeUpdate();
            con.commit();
            con.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("roll back...BienLaiNhapDAO");
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

    public static void main(String[] args) {
        BienLaiNhapDAO aO = new BienLaiNhapDAO();
        HopDong dong = new HopDong();
        NhanVien nhanVien = new NhanVien();
        PhieuThuChi chi = new PhieuThuChi();

        BienLaiNhap bienLaiNhap = new BienLaiNhap();
        bienLaiNhap.setHopDong(dong);
        Kho k=new Kho();
        bienLaiNhap.setKho(k);
        bienLaiNhap.setNhanVien(nhanVien);
        bienLaiNhap.setPhieuThuChi(chi);
        aO.themBienLaiNhap(bienLaiNhap);
    }
}
