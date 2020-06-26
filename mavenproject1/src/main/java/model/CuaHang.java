/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Duong
 */
public class CuaHang {
    private int id;
    private String tenCuaHang;
    private ChiNhanh chiNhanh;
    private String diaChi;

    public CuaHang() {
    }

    public CuaHang(int id) {
        this.id = id;
    }
    
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public ChiNhanh getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(ChiNhanh chiNhanh) {
        this.chiNhanh = chiNhanh;
    }

    @Override
    public String toString() {
        return "CuaHang{" + "id=" + id + ", tenCuaHang=" + tenCuaHang + ", chiNhanh=" + chiNhanh + ", diaChi=" + diaChi + '}';
    }
    
}
