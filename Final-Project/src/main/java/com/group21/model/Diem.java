package com.group21.model;

public class Diem {
    private String maSV;
    private String maMH;
    private Float diemCC1;
    private Float diemCC2;
    private Float diemGK;
    private Float diemCK;

    public Diem(String maSV, String maMH) {
        this.maMH = maMH;
        this.maSV = maSV;
        this.diemCC1 = 0f;
        this.diemCC2 = 0f;
        this.diemGK = 0f;
        this.diemCK = 0f;
    }

    public Diem(String maSV, String maMH, Float diemCC1, Float diemCC2, Float diemGK, Float diemCK) {
        this.maSV = maSV;
        this.maMH = maMH;
        this.diemCC1 = diemCC1;
        this.diemCC2 = diemCC2;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public Float getDiemCC1() {
        return diemCC1;
    }

    public void setDiemCC1(Float diemCC1) {
        this.diemCC1 = diemCC1;
    }

    public Float getDiemCC2() {
        return diemCC2;
    }

    public void setDiemCC2(Float diemCC2) {
        this.diemCC2 = diemCC2;
    }

    public Float getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(Float diemGK) {
        this.diemGK = diemGK;
    }

    public Float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(Float diemCK) {
        this.diemCK = diemCK;
    }

    public double getDiemTongKet() {
        return (diemCC1 * 0.05 + diemCC2 * 0.05 + diemGK * 0.4 + diemCK * 0.5);
    }

}
