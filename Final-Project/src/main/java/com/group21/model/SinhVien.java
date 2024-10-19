package com.group21.model;

import java.time.LocalDate;
import java.util.*;

public class SinhVien {
    private String msv;
    private String ten;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String lop;
    private String sdt;
    private String email;
    private List<String> maMHs = new ArrayList<>();

    public List<String> getMaMHs() {
        return maMHs;
    }

    public void setMaMHs(List<String> maMHs) {
        this.maMHs = maMHs;
    }

    public void addMaMH(String maMH) {
        this.maMHs.add(maMH);
    }

    public SinhVien(String msv, String ten, LocalDate ngaySinh, String gioiTinh, String lop, String sdt, String email) {
        this.msv = msv;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.lop = lop;
        this.sdt = sdt;
        this.email = email;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "Mã Sinh Viên='" + msv + '\'' +
                ", Tên='" + ten + '\'' +
                ", Ngày Sinh=" + ngaySinh +
                ", Giới Tính='" + gioiTinh + '\'' +
                ", Lớp='" + lop + '\'' +
                ", SĐT='" + sdt + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


