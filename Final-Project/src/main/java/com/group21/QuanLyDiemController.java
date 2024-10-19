package com.group21;

import java.io.IOException;
import java.util.*;

import com.group21.database.*;
import com.group21.model.*;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class QuanLyDiemController {

    @FXML
    private TextField maSVField;

    @FXML
    private TextField tenMHField;

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
    private TextFlow messageNhapDiemTFlow;

    @FXML
    private TextField timTenMHField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button timMHBtn;

    @FXML
    private ListView<MonHocDTO> danhSachMonHoc = new ListView<>();

    private MonHocDTO selectedMonHocDTO;

    private List<MonHocDTO> monHocList = new ArrayList<>();

    private HBox previouslySelectedHBox;

    @FXML
    public void initialize() {
        setupDanhSachMonHoc();
    }

    @FXML
    public void goToDanhSachSV() throws IOException {
        App.setRoot((Stage) maSVField.getScene().getWindow(), "quanLySV");
    }

    @FXML
    public void timSV() {
        // Lấy mã sinh viên
        String maSV = maSVField.getText();

        SinhVien sinhVien = SinhVienDB.searchByMsv(maSV);

        // Kiem tra xem sinh viên co ton tai hay khong
        if (sinhVien != null) {
            // Lấy mã môn học list của sinh viên
            List<String> maMHs = SinhVienDB.sinhViens.get(maSV).getMaMHs();

            // Set lại môn học dto list của sinh viên
            monHocList = getMonHocDTOs(maSV, maMHs);

            timMHBtn.setDisable(false);

            messageLabel.setText("Tìm thấy sinh viên!");

        } else {
            clearLabels();
            monHocList.clear();
            messageLabel.setText("Không tìm thấy sinh viên!");
        }

        danhSachMonHoc.getItems().setAll(monHocList);
    }

    private List<MonHocDTO> getMonHocDTOs(String maSV, List<String> maMHs) {
        List<MonHocDTO> monHocDTOs = new ArrayList<>();

        // Tạo môn học dto từ mã môn học list
        for (String maMH : maMHs) {
            for (MonHoc monHoc : MonHocDB.monHocs) {
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
        // Remove selection behaviour của ListView
        danhSachMonHoc.setSelectionModel(new NoSelectionModel<>());

        danhSachMonHoc.setCellFactory(param -> new ListCell<MonHocDTO>() {
            @Override
            protected void updateItem(MonHocDTO monHoc, boolean empty) {
                super.updateItem(monHoc, empty);
                if (empty || monHoc == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox();

                    // Create button với tên mã môn học
                    Button monHocButton = new Button(monHoc.getTenMH() + " - " + monHoc.getMaMH());

                    monHocButton.setStyle(
                            "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black; -fx-padding: 10;");

                    // Add Label điểm tổnng kết
                    Label diemLabel = new Label(
                            "Điểm Tổng Kết: " + String.format("%.2f", monHoc.getDiem().getDiemTongKet()));
                    hBox.getChildren().addAll(monHocButton, diemLabel);

                    monHocButton.setOnAction(event -> {
                        // Reset style của button vừa ấn
                        if (previouslySelectedHBox != null) {
                            previouslySelectedHBox.setStyle(
                                    "-fx-padding: 0; -fx-background-color: transparent; -fx-border-color: lightgray;");
                        }

                        selectedMonHocDTO = monHoc;
                        maMHField.setText(monHoc.getMaMH());
                        tenMHField.setText(monHoc.getTenMH());
                        updateThongTin();

                        hBox.setStyle("-fx-padding: 0; -fx-background-color: lightblue; -fx-border-color: lightgray;");
                        previouslySelectedHBox = hBox;
                    });

                    hBox.setOnMouseClicked(event -> {
                        monHocButton.fire();
                    });

                    hBox.setFocusTraversable(false);

                    hBox.setSpacing(20);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setStyle("-fx-padding: 0; -fx-border-color: lightgray;");

                    setGraphic(hBox);
                }
            }
        });
    }

    @FXML
    public void timMonHoc() {
        // Loại bỏ khoảng trống và chuyển về chữ thường
        String tenMH = timTenMHField.getText().trim().toLowerCase();

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
        messageNhapDiemTFlow.getChildren().clear();

        // Lấy thông tin từ input
        String maSV = maSVField.getText();
        String maMH = maMHField.getText();
        String tenMH = tenMHField.getText();

        // Check đủ thông tin
        if (maSV.isEmpty() || maMH.isEmpty() || tenMH.isEmpty()) {
            messageNhapDiemTFlow.getChildren()
                    .add(text("Vui lòng điền đầy đủ thông tin! Mã sinh viên, mã môn học, tên môn học"));
            return;
        }

        // Mặc định là 0
        Float cc1 = !cc1Field.getText().isEmpty() ? Float.parseFloat(cc1Field.getText()) : 0;
        Float cc2 = !cc2Field.getText().isEmpty() ? Float.parseFloat(cc2Field.getText()) : 0;
        Float gk = !gkField.getText().isEmpty() ? Float.parseFloat(gkField.getText()) : 0;
        Float ck = !ckField.getText().isEmpty() ? Float.parseFloat(ckField.getText()) : 0;

        // Điểm cần lưu
        Diem diemMoi;

        // Tìm Môn Học từ mã môn học
        MonHoc monHoc = MonHocDB.searchMonHoc(maMH);

        // Môn học không tìm thấy trong MonHocDB
        if (monHoc == null) {
            // Lưu môn học mới vô database
            MonHocDB.monHocs.add(new MonHoc(maMH, tenMH));

            MonHocDTO monHocDTO = new MonHocDTO();
            monHocDTO.setMaMH(maMH);
            monHocDTO.setTenMH(tenMH);
            monHocDTO.setDiem(new Diem(maSV, maMH, cc1, cc2, gk, ck));

            // Lưu môn học đang chọn
            selectedMonHocDTO = monHocDTO;

            DiemDB.addDiem(new Diem(maSV, maMH, cc1, cc2, gk, ck));

            // Lưu mã môn học của sinh viên hiện tại
            SinhVienDB.searchByMsv(maSV).addMaMH(maMH);

            messageNhapDiemTFlow.getChildren().add(text("Thêm mới môn học thành công!"));

        } else {
            // Môn học tìm thấy trong db

            // Check tên môn học thay đổi
            if (monHoc.getMaMH().equals(maMH) && !monHoc.getTenMH().equals(tenMH)) {
                monHoc.setTenMH(tenMH);

                MonHocDB.updateMonHoc(monHoc);

                for (MonHocDTO mHocDTO : monHocList) {
                    if (mHocDTO.getMaMH().equals(maMH)) {
                        mHocDTO.setTenMH(tenMH);
                        messageNhapDiemTFlow.getChildren().add(text("Tên môn học đã được thay đổi"));
                        break;
                    }
                }

            }

            // Check điểm không thay đổi
            Diem diemToCheck = DiemDB.searchDiem(maSV, maMH);

            // Điểm thay đổi
            if (diemToCheck != null) {

                if (cc1.equals(diemToCheck.getDiemCC1()) && cc2.equals(diemToCheck.getDiemCC2()) &&
                        gk.equals(diemToCheck.getDiemGK()) && ck.equals(diemToCheck.getDiemCK())) {
                    timSV();
                    messageNhapDiemTFlow.getChildren().add(text("Không có thay đổi mới về điểm"));
                    return;
                }

                diemMoi = new Diem(maSV, maMH, cc1, cc2, gk, ck);

                DiemDB.updateDiem(diemMoi);

                // Update điểm cho môn học
                for (MonHocDTO mHocDTO : monHocList) {
                    if (mHocDTO.getMaMH().equals(maMH)) {
                        mHocDTO.setDiem(diemMoi);
                        selectedMonHocDTO = mHocDTO;

                        messageNhapDiemTFlow.getChildren().add(text("Cập nhật điểm thành công"));
                        break;
                    }
                }
            } else {
                // Sinh viên chưa có điểm môn học này
                diemMoi = new Diem(maSV, maMH, cc1, cc2, gk, ck);
                DiemDB.addDiem(diemMoi);

                SinhVien sinhVien = SinhVienDB.searchByMsv(maSV);

                if (sinhVien == null) {
                    return;
                }

                // Add mã môn học vô sinh viên
                sinhVien.addMaMH(maMH);
                
                messageNhapDiemTFlow.getChildren().add(text("Thêm điểm mới thành công!"));
            }
        }

        // Load danh sách môn học
        monHocList = getMonHocDTOs(maSV, SinhVienDB.searchByMsv(maSV).getMaMHs());
        danhSachMonHoc.getItems().setAll(monHocList);
        updateThongTin();
        // clearInputFields();
    }

    private Text text(String text) {
        Text newText = new Text(text + "\n");
        newText.setFill(Color.RED);

        return newText;
    }

    private void updateThongTin() {

        if (selectedMonHocDTO == null) {
            clearLabels();
            return;
        }

        if (selectedMonHocDTO.getDiem() == null) {
            clearLabels();
            return;
        }

        Diem diem = selectedMonHocDTO.getDiem();

        SinhVien sVien = SinhVienDB.searchByMsv(diem.getMaSV());

        tenLabel.setText("Tên: " + sVien.getTen());
        monHocLabel.setText("Môn Học: " + selectedMonHocDTO.getTenMH());
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

    // private void clearInputFields() {
    //     cc1Field.clear();
    //     cc2Field.clear();
    //     gkField.clear();
    //     ckField.clear();
    // }

    @FXML
    private void gotoHomePage() throws IOException {
        App.setRoot((Stage) maSVField.getScene().getWindow(), "homePage");
    }
}
