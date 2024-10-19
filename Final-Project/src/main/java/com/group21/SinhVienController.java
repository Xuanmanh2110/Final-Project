package com.group21;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;

import com.group21.database.SinhVienDB;
import com.group21.model.SinhVien;

public class SinhVienController {

    @FXML
    private TextField tenField;
    @FXML
    private TextField maSVField;
    @FXML
    private DatePicker ngaySinhPicker;
    @FXML
    private ChoiceBox<String> gioiTinhBox;
    @FXML
    private TextField lopField;
    @FXML
    private TextField sdtField;
    @FXML
    private TextField emailField;
    @FXML
    private Button luuButton;

    @FXML
    private Label messageLabel;

    @FXML
    private Label displayTenLabel;
    @FXML
    private Label displayMaSVLabel;
    @FXML
    private Label displayNgaySinhLabel;
    @FXML
    private Label displayGioiTinhLabel;
    @FXML
    private Label displayLopLabel;
    @FXML
    private Label displaySdtLabel;
    @FXML
    private Label displayEmailLabel;

    @FXML
    public void initialize() {
        gioiTinhBox.getItems().addAll("Nam", "Nữ", "Khác");

    }

    @FXML
    private void goToUpdateScoreView() throws IOException {
        App.setRoot((Stage) messageLabel.getScene().getWindow(), "quanLyDiem");
    }

    @FXML
    public void goToDanhSachSV() throws IOException {
        App.setRoot((Stage) maSVField.getScene().getWindow(), "quanLySV");
    }

    @FXML
    private void handleLuuButtonAction() {
        String ten = tenField.getText();
        String maSV = maSVField.getText();
        LocalDate ngaySinh = ngaySinhPicker.getValue();
        String gioiTinh = gioiTinhBox.getValue();
        String lop = lopField.getText();
        String sdt = sdtField.getText();
        String email = emailField.getText();

        if (ten.isEmpty() || maSV.isEmpty() || lop.isEmpty() || gioiTinh == null || sdt.isEmpty() || email.isEmpty() || ngaySinh == null) {
            messageLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        SinhVien newSinhVien = new SinhVien(maSV, ten, ngaySinh, gioiTinh, lop, sdt, email);

        SinhVienDB.addSinhVien(newSinhVien);

        messageLabel.setText("Lưu Thông Tin Thành Công!");

        displayTenLabel.setText(ten);
        displayMaSVLabel.setText(maSV);
        displayNgaySinhLabel.setText(ngaySinh.toString());
        displayGioiTinhLabel.setText(gioiTinh);
        displayLopLabel.setText(lop);
        displaySdtLabel.setText(sdt);
        displayEmailLabel.setText(email);

        tenField.clear();
        maSVField.clear();
        lopField.clear();
        sdtField.getText();
        emailField.clear();
        sdtField.clear();
        ngaySinhPicker.setValue(null);
        gioiTinhBox.setValue(null);
    }

    @FXML
    private void gotoHomePage() throws IOException {
        App.setRoot((Stage) messageLabel.getScene().getWindow(), "homePage");
    }
}
