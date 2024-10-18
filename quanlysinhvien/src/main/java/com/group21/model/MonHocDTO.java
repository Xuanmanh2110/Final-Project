package com.group21.model;

public class MonHocDTO {
    private String maMH;
    private String tenMH;
    private Diem diem;

    public MonHocDTO() {
    }

    public MonHocDTO(String maMH, String tenMH, Diem diem) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.diem = diem;
    }

    public String getMaMH() {
        return maMH;
    }
    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }
    public String getTenMH() {
        return tenMH;
    }
    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }
    public Diem getDiem() {
        return diem;
    }
    public void setDiem(Diem diem) {
        this.diem = diem;
    }
}
