package com.group21.database;

import java.util.*;

import com.group21.model.MonHoc;

public class MonHocDB {
    public static List<MonHoc> monHocs = new ArrayList<>();

    public static MonHoc searchMonHoc(String maMH) {
        for (MonHoc monHoc : monHocs) {
            if (monHoc.getMaMH().equals(maMH)) {
                return monHoc;
            }
        }
        return null;
    }

    public static void updateMonHoc(MonHoc monHoc) {
        for (MonHoc mHoc2 : monHocs) {
            if (mHoc2.getMaMH().equals(monHoc.getMaMH())) {
                mHoc2.setTenMH(monHoc.getTenMH());
                break;
            }
        }
    }
}
