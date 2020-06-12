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
public class SanPham extends MatHang {

    private int idSanPham;
    private int gia;
    private String hanSuDung;
    private BienLaiKho BienLaiKho;

    public SanPham(MatHang mh) {
        this.setMaMatHang(mh.getMaMatHang());
        this.setDonViTinh(mh.getDonViTinh());
        this.setMoTa(mh.getMoTa());
        this.setTenMatHang(mh.getTenMatHang());
    }

    public SanPham() {
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(String hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

  
    public BienLaiKho getBienLaiKho() {
        return BienLaiKho;
    }

    public void setBienLaiKho(BienLaiKho BienLaiKho) {
        this.BienLaiKho = BienLaiKho;
    }

}
