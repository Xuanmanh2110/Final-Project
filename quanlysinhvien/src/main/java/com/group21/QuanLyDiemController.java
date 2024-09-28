package com.group21;

import java.io.IOException;

import com.group21.database.DiemDB;
import com.group21.database.SinhVienDB;
import com.group21.model.Diem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label messageLabel;

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
    private Label messageNhapDiemLabel;

    @FXML
    private TextField maMHFieldSearch;

    @FXML
    private Label messageTimMHLabel;

    @FXML
    public void goToDanhSachSV() throws IOException {
        App.setRoot((Stage) maSVField.getScene().getWindow(), "quanLySV");
    }

    @FXML
    public void timSV() {
        String maSV = maSVField.getText();

        String tenSV = SinhVienDB.searchByMsv(maSV).getTen();

        if (SinhVienDB.searchByMsv(maSV) != null) {
            messageLabel.setText("Đã tìm thấy sinh viên!  " + tenSV);
        } else {
            clearLabels();
            messageLabel.setText("Không tìm thấy sinh viên!");
        }
    }

    @FXML
    public void timMH() {
        String maMH = maMHFieldSearch.getText();

        if (DiemDB.diemList == null || DiemDB.diemList.isEmpty()) {
            clearLabels();
            return;
        }

        Diem studentInfo = DiemDB.searchDiem(maSVField.getText(), maMH);

        if (studentInfo != null) {

            messageTimMHLabel.setText("Đã tìm thấy môn học!");
            updateLabels(studentInfo);
        } else {
            messageTimMHLabel.setText("Không tìm thấy môn học!");
            clearLabels();
        }

    }

    @FXML
    public void nhapDiem() {
        String maSV = maSVField.getText();
        String maMH = maMHField.getText();
        Float cc1 = Float.parseFloat(cc1Field.getText());
        Float cc2 = Float.parseFloat(cc2Field.getText());
        Float gk = Float.parseFloat(gkField.getText());
        Float ck = Float.parseFloat(ckField.getText());

        Diem newScores = new Diem(maSV, maMH, cc1, cc2, gk, ck);

        if (DiemDB.searchDiem(maSV, maMH) != null) {
            DiemDB.updateDiem(newScores);
            messageNhapDiemLabel.setText("Cập nhật điểm thành công!");
            updateLabels(newScores);
        } else {
            DiemDB.addDiem(newScores);
            messageNhapDiemLabel.setText("Thêm mới điểm thành công!");
        }

    }

    private void updateLabels(Diem studentInfo) {
        tenLabel.setText("Tên: " + studentInfo.getMaSV());
        monHocLabel.setText("Môn Học: " + studentInfo.getMaMH());
        diemMoiLabel.setText("Điểm mới: ");
        cc1Label.setText("CC1: " + studentInfo.getDiemCC1());
        cc2Label.setText("CC2: " + studentInfo.getDiemCC2());
        gkLabel.setText("Điểm Giữa Kì: " + studentInfo.getDiemGK());
        ckLabel.setText("Điểm Cuối Kì: " + studentInfo.getDiemCK());
    }

    private void clearLabels() {
        tenLabel.setText("Tên:");
        monHocLabel.setText("Môn Học: ");
        diemMoiLabel.setText("Điểm: ");
        cc1Label.setText("CC1: ");
        cc2Label.setText("CC2: ");
        gkLabel.setText("Điểm Giữa Kì: ");
        ckLabel.setText("Điểm Cuối Kì: ");
    }

    @FXML
    private void gotoHomePage() throws IOException {
        App.setRoot((Stage) maSVField.getScene().getWindow(), "homePage");
    }
}
