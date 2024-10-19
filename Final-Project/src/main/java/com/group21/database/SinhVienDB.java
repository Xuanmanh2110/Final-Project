package com.group21.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group21.model.SinhVien;
import com.group21.model.SinhVienDTO;

public class SinhVienDB {

    public static Map<String, SinhVien> sinhViens = new HashMap<>();

    public static void addSinhVien(SinhVien sinhVien) {
        sinhViens.put(sinhVien.getMsv(), sinhVien);
    }

    public static SinhVien searchByMsv(String msv) {
        return sinhViens.get(msv);
    }

    public static void removeSinhVien(String msv) {
        sinhViens.remove(msv);
    }

    public static void updateSinhVien(SinhVien sinhVien) {
        sinhViens.put(sinhVien.getMsv(), sinhVien);
    }

    public static List<SinhVienDTO> getAllSV() {
        List<SinhVienDTO> sinhViensDTO = new ArrayList<>();

        for (SinhVien sinhVien : sinhViens.values()) {
            sinhViensDTO.add(new SinhVienDTO(sinhVien.getMsv(), sinhVien.getTen(), sinhVien.getLop()));
        }

        return sinhViensDTO;
    }
}
