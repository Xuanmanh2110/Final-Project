package com.group21.database;

import java.util.ArrayList;
import java.util.List;

import com.group21.model.Diem;

public class DiemDB {

    public static List<Diem> diemList = new ArrayList<>();

    public DiemDB() {
    }

    public static void addDiem(Diem diem) {
        diemList.add(diem);
    }

    public static Diem searchDiem(String maSV, String maMH) {

        for (Diem diem : diemList) {
            if (diem.getMaSV().equals(maSV) && diem.getMaMH().equals(maMH)) {
                return diem;
            }
        }
        return null;
    }

    public static boolean updateDiem(Diem updatedDiem) {
        for (int i = 0; i < diemList.size(); i++) {
            Diem diem = diemList.get(i);
            if (diem.getMaSV().equals(updatedDiem.getMaSV()) && diem.getMaMH().equals(updatedDiem.getMaMH())) {
                diemList.set(i, updatedDiem);
                return true;
            }
        }
        return false;
    }

    public static boolean removeDiem(String maSV, String maMH) {
        return diemList.removeIf(diem -> diem.getMaSV().equals(maSV) && diem.getMaMH().equals(maMH));
    }

    public static List<Diem> getAllDiems() {
        return new ArrayList<>(diemList);
    }
}
