package com.group21;

import java.io.IOException;
import java.util.*;

import com.group21.database.*;
import com.group21.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class QuanLyDiemController {

    @FXML
    private TextField maSVField;

    @FXML
    private TextField maMHField;

    @FXML
    private TextField cc1Field;

    @FXML
    private TextField cc2Field;

    @FXML
    private TextField gkField;

    @FXML
    private TextField ckField;

    @FXML
    private Label monHocListLabel;

    @FXML
    private Label tenLabel;

    @FXML
    private Label monHocLabel;

    @FXML
    private Label diemMoiLabel;
    @FXML
    private Label cc1Label;
    @FXML
    private Label cc2Label;
    @FXML
    private Label gkLabel;
    @FXML
    private Label ckLabel;

    @FXML
    private Label tongKetLabel;

    @FXML
    private Label messageNhapDiemLabel;

    @FXML
    private TextField tenMHField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button timMHBtn;

    @FXML
    private ListView<MonHocDTO> danhSachMonHoc = new ListView<>();

    private MonHocDTO selectedMonHoc;

    private List<MonHocDTO> monHocList = new ArrayList<>();

    @FXML
    public void goToDanhSachSV() throws IOException {
        App.setRoot((Stage) maSVField.getScene().getWindow(), "quanLySV");
    }

    @FXML
    public void timSV() {
        // Lấy mã sinh viên
        String maSV = maSVField.getText();

        SinhVien sinhVien = SinhVienDB.searchByMsv(maSV);

        monHocList = new ArrayList<>();

        // Kiem tra xem sinh viên co ton tai hay khong
        if (sinhVien != null) {
            // Lấy mã môn học list của sinh viên
            List<String> maMHs = SinhVienDB.sinhViens.get(maSV).getMaMHs();

            monHocList = getMonHocDTOs(maSV, maMHs);

            if (!monHocList.isEmpty()) {
                setupDanhSachMonHoc();
            }

            timMHBtn.setDisable(false);

        } else {
            clearLabels();
            messageLabel.setText("Không tìm thấy sinh viên!");
        }

        danhSachMonHoc.getItems().setAll(monHocList);
    }

    private List<MonHocDTO> getMonHocDTOs(String maSV, List<String> maMHs) {
        List<MonHocDTO> monHocDTOs = new ArrayList<>();

        // Tạo môn học dto từ mã môn học list
        for (String maMH : maMHs) {
            for (MonHoc monHoc : MonHocDB.monHocList) {
                if (monHoc.getMaMH().equals(maMH)) {

                    MonHocDTO monHocDTO = new MonHocDTO();
                    monHocDTO.setMaMH(maMH);
                    monHocDTO.setTenMH(monHoc.getTenMH());
                    monHocDTO.setDiem(DiemDB.searchDiem(maSV, maMH));

                    monHocDTOs.add(monHocDTO);
                    break;
                }
            }
        }

        return monHocDTOs;
    }

    private void setupDanhSachMonHoc() {
        danhSachMonHoc.setCellFactory(param -> new ListCell<MonHocDTO>() {
            @Override
            protected void updateItem(MonHocDTO monHoc, boolean empty) {
                super.updateItem(monHoc, empty);
                if (empty || monHoc == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox();
                    Label tenMHLabel = new Label(monHoc.getTenMH());

                    if (monHoc.getDiem() != null) {
                        Label diemLabel = new Label(
                                "Điểm Tổng Kết: " + String.format("%.2f", monHoc.getDiem().getDiemTongKet()));
                        hBox.getChildren().addAll(tenMHLabel, diemLabel);

                    } else {
                        hBox.getChildren().add(tenMHLabel);
                    }
                    // Click 2 lần vô môn học
                    hBox.setOnMouseClicked((MouseEvent event) -> {
                        selectedMonHoc = monHoc;
                        maMHField.setText(monHoc.getMaMH());
                        updateThongTin();
                    });

                    hBox.setSpacing(20);
                    setGraphic(hBox);
                }
            }
        });
    }

    @FXML
    public void timMonHoc() {
        // Loại bỏ khoảng trống và chuyển về chữ thường
        String tenMH = tenMHField.getText().trim().toLowerCase();

        if (DiemDB.diemList == null || DiemDB.diemList.isEmpty()) {
            clearLabels();
            return;
        }

        // Yêu cầu nhập msv trước khi tìm môn học
        if (maSVField.getText().isEmpty()) {
            messageLabel.setText("Vui lòng nhập mã sinh viên cần check");
            clearLabels();
            return;
        }

        // Load danh sách môn học với msv
        timSV();

        // Chứa môn học tìm thấy
        List<MonHocDTO> foundMonHocList = new ArrayList<>();

        for (MonHocDTO monHoc : monHocList) {
            String tenMonHoc = monHoc.getTenMH().trim().toLowerCase();
            if (tenMonHoc.contains(tenMH)) {
                foundMonHocList.add(monHoc);
            }
        }

        if (foundMonHocList.isEmpty()) {
            messageLabel.setText("Không tìm thấy môn học!");
            clearLabels();
            return;
        }

        // Update danh sách môn học với môn học tìm được
        danhSachMonHoc.getItems().setAll(foundMonHocList);
    }

    @FXML
    public void luuDiem() {
        String maSV = maSVField.getText();
        String maMH = selectedMonHoc.getMaMH();
        Float cc1 = Float.parseFloat(cc1Field.getText());
        Float cc2 = Float.parseFloat(cc2Field.getText());
        Float gk = Float.parseFloat(gkField.getText());
        Float ck = Float.parseFloat(ckField.getText());

        Diem diemMoi = new Diem(maSV, maMH, cc1, cc2, gk, ck);

        // Update điểm cho môn học
        for (MonHocDTO monHoc : monHocList) {
            if (monHoc.getMaMH().equals(maMH)) {
                monHoc.setDiem(diemMoi);
            }
        }

        selectedMonHoc = new MonHocDTO(maMH, maMH, diemMoi);

        danhSachMonHoc.getItems().setAll(monHocList);

        if (DiemDB.searchDiem(maSV, maMH) != null) {
            DiemDB.updateDiem(diemMoi);
            messageNhapDiemLabel.setText("Cập nhật điểm thành công!");
        } else {
            DiemDB.addDiem(diemMoi);
            messageNhapDiemLabel.setText("Thêm mới điểm thành công!");
        }

        updateThongTin();
        clearInputFields();
    }

    private void updateThongTin() {

        if (selectedMonHoc.getDiem() == null) {
            clearLabels();
            return;
        }

        Diem diem = selectedMonHoc.getDiem();


        SinhVien sVien = SinhVienDB.searchByMsv(diem.getMaSV());

        tenLabel.setText("Tên: " + sVien.getTen());
        monHocLabel.setText("Môn Học: " + selectedMonHoc.getMaMH());
        cc1Label.setText("CC1: " + diem.getDiemCC1());
        cc2Label.setText("CC2: " + diem.getDiemCC2());
        gkLabel.setText("Điểm Giữa Kì: " + diem.getDiemGK());
        ckLabel.setText("Điểm Cuối Kì: " + diem.getDiemCK());
        tongKetLabel.setText("Điểm Tổng Kết: " + String.format("%.2f", diem.getDiemTongKet()));
    }

    private void clearLabels() {
        tenLabel.setText("Tên:");
        monHocLabel.setText("Môn Học: ");
        cc1Label.setText("CC1: ");
        cc2Label.setText("CC2: ");
        gkLabel.setText("Điểm Giữa Kì: ");
        ckLabel.setText("Điểm Cuối Kì: ");
    }

    private void clearInputFields() {
        cc1Field.clear();
        cc2Field.clear();
        gkField.clear();
        ckField.clear();
    }

    @FXML
    private void gotoHomePage() throws IOException {
        App.setRoot((Stage) maSVField.getScene().getWindow(), "homePage");
    }
}
