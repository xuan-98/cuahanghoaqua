/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.BoPhan;
import model.CuaHang;
import model.NhanVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Duong
 */
public class NhanVienDAO extends DAO {
    private final NguoiDAO nguoiDAO;
    private final BoPhanDAO boPhanDAO;
    private final CuaHangDAO cuaHangDAO;

    public NhanVienDAO() {
        super();
        nguoiDAO = new NguoiDAO();
        boPhanDAO = new BoPhanDAO();
        cuaHangDAO = new CuaHangDAO();
    }

    public static void main(String[] args) {

    }

    public ArrayList<NhanVien> getAllNVKho() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = " select * from  [CuaHangHoaQua].[dbo].[NhanVien]  inner join [CuaHangHoaQua].[dbo].[Nguoi] on NhanVien.idNhanVien=Nguoi.idNguoi where vaiTro=N'Quản lí kho'";
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        try {
            stm = getCon().prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = resultSet2NhanVien(rs);
                BoPhan boPhan = new BoPhan();
                CuaHang cuaHang = new CuaHang();
                boPhan.setId(rs.getInt(5));
                cuaHang.setId(rs.getInt(6));
                nhanVien.setBoPhan(boPhan);
                nhanVien.setCuaHang(cuaHang);
                listNhanVien.add(nhanVien);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                getCon().close();
            } catch (SQLException ex) {
                //
            }
        }
        return listNhanVien;

    }

    private NhanVien resultSet2NhanVien(ResultSet rs) throws SQLException {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(rs.getInt(7));
        nhanVien.setEmail(rs.getString(10));
        nhanVien.setNgaySinh(rs.getString(11));
        nhanVien.setGioiTinh(rs.getString(12));
        nhanVien.setHocVan(rs.getString(13));
        nhanVien.setDiaChi(rs.getString(14));
        nhanVien.setHoTen(rs.getString(15));
        nhanVien.setIdNhanVien(rs.getInt(1));
        nhanVien.setVaiTro(rs.getString(2));
        nhanVien.setUserName(rs.getString(3));
        nhanVien.setPassword(rs.getString(4));
        int idBoPhan = rs.getInt(5);
        int idCuaHang = rs.getInt(6);
        BoPhan boPhan = boPhanDAO.getBoPhanById(idBoPhan);
        CuaHang cuaHang = cuaHangDAO.getCuaHangById(idCuaHang);
        nhanVien.setBoPhan(boPhan);
        nhanVien.setCuaHang(cuaHang);
        return nhanVien;
    }

    public ArrayList<NhanVien> getAllNhanVien() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select *  from [CuaHangHoaQua].[dbo].[NhanVien] inner join [CuaHangHoaQua].[dbo].[Nguoi] on NhanVien.idNhanVien=Nguoi.idNguoi";
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getInt(8));
                nhanVien.setEmail(rs.getString(9));
                nhanVien.setNgaySinh(rs.getString(10));
                nhanVien.setGioiTinh(rs.getString(11));
                nhanVien.setHocVan(rs.getString(12));
                nhanVien.setDiaChi(rs.getString(13));
                nhanVien.setHoTen(rs.getString(14));
                nhanVien.setIdNhanVien(rs.getInt(1));
                nhanVien.setVaiTro(rs.getString(2));
                nhanVien.setUserName(rs.getString(3));
                nhanVien.setPassword(rs.getString(4));
                BoPhan boPhan = new BoPhan();
                CuaHang cuaHang = new CuaHang();
                boPhan.setId(rs.getInt(5));
                cuaHang.setId(rs.getInt(6));
                nhanVien.setBoPhan(boPhan);
                nhanVien.setCuaHang(cuaHang);
                listNhanVien.add(nhanVien);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                getCon().close();
            } catch (SQLException ex) {
                //
            }
        }
        return listNhanVien;
    }

    public List<NhanVien> getNhanVienByName(String name) {
        String sql = "select *  from [CuaHangHoaQua].[dbo].[NhanVien] inner join [CuaHangHoaQua].[dbo].[Nguoi] on NhanVien.idNhanVien=Nguoi.idNguoi where Nguoi.hoTen like '%?%'";
        PreparedStatement pre = null;
        ResultSet rs = null;
//        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
//        crs.populate(rs);
        List<NhanVien> listNV = new ArrayList<>();
        try {
            pre.setString(1, name);
            pre = getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = resultSet2NhanVien(rs);
                listNV.add(nhanVien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pre.close();
                rs.close();
                getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listNV;
    }

    public NhanVien getNhanVienById(int idNhanVien) {
        String sql = "select * from  [CuaHangHoaQua].[dbo].[NhanVien]  inner join [CuaHangHoaQua].[dbo].[Nguoi] on NhanVien.idNhanVien=Nguoi.idNguoi where idNhanVien = ?";
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            pre = getCon().prepareStatement(sql);
            pre.setInt(1, idNhanVien);
            rs = pre.executeQuery();
            if (rs.next()) {
                return resultSet2NhanVien(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int insertNV(NhanVien nv) {
        PreparedStatement pre = null;
        try {
            String sql = "insert into [CuaHangHoaQua].[dbo].NhanVien (vaiTro,username,[password],idBoPhan,idCuaHang,idNguoi) "
                    + "values (?,?,?,?,?,?)";
            pre = getCon().prepareStatement(sql);
            pre.setString(1, nv.getVaiTro());
            pre.setString(2, nv.getUserName());
            pre.setString(3, nv.getPassword());
            pre.setInt(4, nv.getBoPhan().getId());
            pre.setInt(5, nv.getCuaHang().getId());
            pre.setInt(6, nv.getId());
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pre.close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return 0;
    }

    public int deleteNV(NhanVien nv) {
        PreparedStatement pre = null;
        int idNV = nv.getIdNhanVien();
        String sql = "delete from [CuaHangHoaQua].[dbo].[NhanVien] WHERE idNhanVien = " + idNV + ";";
        try {
            pre = getCon().prepareStatement(sql);
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pre.close();
                getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public int updateNV(NhanVien nv) {
        PreparedStatement preparedStmt = null;
        try {
            String query = "update users set vaiTro= ? , username=? , [password] = ? , idBoPhan = ? , idCuaHang =? where idNhanVien = ?";
            preparedStmt = getCon().prepareStatement(query);
            preparedStmt.setString(1, nv.getVaiTro());
            preparedStmt.setString(2, nv.getUserName());
            preparedStmt.setString(3, nv.getPassword());
            preparedStmt.setInt(4, nv.getBoPhan().getId());
            preparedStmt.setInt(5, nv.getCuaHang().getId());
            return preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStmt.close();
                getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
}
