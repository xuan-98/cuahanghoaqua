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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import model.*;

/**
 *
 * @author Duong
 */
public class XuatHangDAO extends DAO {

    public XuatHangDAO() {
        super();
    }

//    public BienLaiNhap loadBienLaiNhapTheoKho(SanPham sp) {
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        String sql = "  select bln.idBienLaiNhap,bln.idBienLaiKho, bln.idHopDong, bln.idNhanVien from [CuaHangHoaQua].[dbo].[BienLaiNhap] bln inner join [CuaHangHoaQua].[dbo].[BienLaiKho] blk on bln.idBienLaiKho=blk.idBienLaiKho inner join [CuaHangHoaQua].[dbo].[SanPham] sp on sp.idBienLaiKho=bln.idBienLaiKho where idSanPham="+sp.getIdSanPham();
//        BienLaiNhap bln=new BienLaiNhap();
//        try {
//            stm = con.prepareStatement(sql);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                sp.setIdSanPham(rs.getInt("idSanPham"));
//                sp.setTenMatHang(rs.getString("tenMatHang"));
//                sp.setMaMatHang(rs.getString("maMatHang"));
//                sp.setGia(rs.getInt("gia"));
//                sp.setHanSuDung(rs.getString("hanSuDung"));
//                sp.setIdMatHang(rs.getInt("idMatHang"));
//                BienLaiKho blk=new BienLaiKho();
//                blk.setId(rs.getInt("idBienLaiKho"));
//                sp.setBienLaiKho(blk);
//                int soLuong=rs.getInt("soLuong");
//                sp.setDonViTinh(rs.getString("donVi"));
//                listMHTrongKho.put(sp, new Integer(soLuong));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                stm.close();
//                con.close();
//            } catch (SQLException ex) {
//                //
//            }
//        }
//        return listMHTrongKho;
//    }
    public ArrayList<RecordSanPham> loadMatHangTrongKhoTheoKho(Kho kho) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "  select sp.maSp, sp.idSanPham,mh.tenMatHang,mh.maMatHang, sp.gia,sp.hanSuDung,sp.idMatHang,blk.idBienLaiKho,blk.soLuong,mh.donVi from [CuaHangHoaQua].[dbo].[SanPham] sp inner join   [CuaHangHoaQua].[dbo].[BienLaiKho] blk on sp.idBienLaiKho=blk.idBienLaiKho \n"
                + "  inner join  [CuaHangHoaQua].[dbo].[BienLaiNhap] bln on sp.idBienLaiKho=bln.idBienLaiKho inner join [CuaHangHoaQua].[dbo].[MatHang] mh on sp.idMatHang=mh.idMatHang where idKho=" + kho.getId();
        System.out.println(sql);
        ArrayList<RecordSanPham> listMHTrongKho = new ArrayList<RecordSanPham>();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSp(rs.getString("maSp"));
                sp.setIdSanPham(rs.getInt("idSanPham"));
                sp.setTenMatHang(rs.getString("tenMatHang"));
                sp.setMaMatHang(rs.getString("maMatHang"));
                sp.setGia(rs.getInt("gia"));
                sp.setHanSuDung(rs.getString("hanSuDung"));
                sp.setIdMatHang(rs.getInt("idMatHang"));
                BienLaiKho blk = new BienLaiKho();
                blk.setId(rs.getInt("idBienLaiKho"));
                sp.setBienLaiKho(blk);
                int soLuong = rs.getInt("soLuong");
                sp.setDonViTinh(rs.getString("donVi"));
                RecordSanPham recordSanPham = new RecordSanPham();
                recordSanPham.setPham(sp);
                recordSanPham.setSoLuong(soLuong);
                listMHTrongKho.add(recordSanPham);
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
        return listMHTrongKho;
    }
}
