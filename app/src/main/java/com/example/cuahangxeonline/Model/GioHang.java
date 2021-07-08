package com.example.cuahangxeonline.Model;

public class GioHang {
    public String IDsp, Tensp;
    public double Giasp;
    public String Hinhsp;
    public int Soluong;

    public String getIDsp() {
        return IDsp;
    }

    public void setIDsp(String IDsp) {
        this.IDsp = IDsp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public double getGiasp() {
        return Giasp;
    }

    public void setGiasp(double giasp) {
        Giasp = giasp;
    }

    public String getHinhsp() {
        return Hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        Hinhsp = hinhsp;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public GioHang(String IDsp, String tensp, double giasp, String hinhsp, int soluong) {
        this.IDsp = IDsp;
        Tensp = tensp;
        Giasp = giasp;
        Hinhsp = hinhsp;
        Soluong = soluong;
    }
}
