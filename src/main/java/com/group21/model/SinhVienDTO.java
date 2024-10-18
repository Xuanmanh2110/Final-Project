package com.group21.model;

public class SinhVienDTO {
    private String msv;
    private String ten;
    private String lop;

    public SinhVienDTO(String msv, String ten, String lop) {
        this.msv = msv;
        this.ten = ten;
        this.lop = lop;
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

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
