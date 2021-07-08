package com.example.cuahangxeonline.Model;

import java.io.Serializable;

public class XeMoi implements Serializable {
    public String idxe, tenxe;
    public double gia;
    public int soluongton;
    public String  maloai, ngay, hinhanh, mota;
    public String getIdxe() {
        return idxe;
    }

    public void setIdxe(String idxe) {
        this.idxe = idxe;
    }

    public String getTenxe() {
        return tenxe;
    }

    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public XeMoi(String idxe, String tenxe, double gia ,int soluongton, String maloai, String ngay, String hinhanh, String mota) {
        this.idxe = idxe;
        this.tenxe = tenxe;
        this.gia = gia;
        this.soluongton = soluongton;
        this.maloai = maloai;
        this.ngay = ngay;
        this.hinhanh = hinhanh;
        this.mota = mota;


    }
}
