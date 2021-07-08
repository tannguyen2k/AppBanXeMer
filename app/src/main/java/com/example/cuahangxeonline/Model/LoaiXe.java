package com.example.cuahangxeonline.Model;

public class LoaiXe {
    public String maloai, tenloai, mota, hinhanh;

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public LoaiXe(String maloai, String tenloai, String mota, String hinhanh) {
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.mota = mota;
        this.hinhanh = hinhanh;
    }
}
