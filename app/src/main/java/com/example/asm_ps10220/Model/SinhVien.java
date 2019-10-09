package com.example.asm_ps10220.Model;

public class SinhVien {
    String tenSinhVien;
    String ngaySinh;
    String maLop;

    public SinhVien() {

    }

    public SinhVien(String tenSinhVien, String ngaySinh, String maLop) {
        this.tenSinhVien = tenSinhVien;
        this.ngaySinh = ngaySinh;
        this.maLop = maLop;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
}
