package com.group21;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.group21.database.SinhVienDB;
import com.group21.model.SinhVien;
import com.group21.model.SinhVienDTO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class QuanLySVController {

    @FXML
    private ListView<SinhVienDTO> studentListView = new ListView<>();

    @FXML
    private TextField tenField;

    @FXML
    private TextField maSVField;

    @FXML
    private TextField lopField;

    @FXML
    private DatePicker ngaySinhPicker;

    @FXML
    private ChoiceBox<String> gioiTinhBox;

    @FXML
    private TextField sdtField;

    @FXML
    private TextField emailField;

    @FXML
    public void initialize() {

        gioiTinhBox.getItems().addAll("Nam", "Nữ", "Khác");

        studentListView.setCellFactory(param -> new ListCell<SinhVienDTO>() {
            @Override
            protected void updateItem(SinhVienDTO sv, boolean empty) {
                super.updateItem(sv, empty);
                if (empty || sv == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox();
                    Label nameLabel = new Label(sv.getTen());
                    Label maSVLabel = new Label(sv.getMsv());
                    Label lopLabel = new Label(sv.getLop());
                    Button editButton = new Button("Sửa");

                    editButton.setOnAction(e -> {
                        tenField.setText(sv.getTen());
                        maSVField.setText(sv.getMsv());
                        lopField.setText(sv.getLop());

                        SinhVien sinhVien = SinhVienDB.searchByMsv(sv.getMsv());

                        ngaySinhPicker.setValue(sinhVien.getNgaySinh());
                        gioiTinhBox.setValue(sinhVien.getGioiTinh());
                        sdtField.setText(sinhVien.getSdt());
                        emailField.setText(sinhVien.getEmail());
                    });

                    hBox.getChildren().addAll(nameLabel, maSVLabel, lopLabel, editButton);
                    hBox.setSpacing(20);
                    setGraphic(hBox);
                }
            }
        });

        loadStudentData();
    }

    private void loadStudentData() {
        List<SinhVienDTO> sinhViensDTO = SinhVienDB.getAllSV();

        studentListView.getItems().addAll(sinhViensDTO);

    }

    @FXML
    private void handleLuuButtonAction() {

        String ten = tenField.getText();
        String maSV = maSVField.getText();
        String lop = lopField.getText();
        LocalDate ngaySinh = ngaySinhPicker.getValue();
        String gioiTinh = gioiTinhBox.getValue();
        String sdt = sdtField.getText();
        String email = emailField.getText();

        SinhVien sinhVien = SinhVienDB.searchByMsv(maSV);

        if (sinhVien != null) {
            sinhVien.setTen(ten);
            sinhVien.setLop(lop);
            sinhVien.setNgaySinh(ngaySinh);
            sinhVien.setGioiTinh(gioiTinh);
            sinhVien.setSdt(sdt);
            sinhVien.setEmail(email);
            SinhVienDB.updateSinhVien(sinhVien);
        }

        tenField.clear();
        maSVField.clear();
        lopField.clear();
        ngaySinhPicker.setValue(null);
        gioiTinhBox.setValue(null);
        sdtField.clear();
        emailField.clear();

        refreshStudentList();
    }

    @FXML
    private void handleXoaButtonAction() {
        SinhVienDB.removeSinhVien(maSVField.getText());
        refreshStudentList();
    }

    @FXML
    private void gotoAddStudentView() throws IOException {
        App.setRoot((Stage) studentListView.getScene().getWindow(), "nhapThongTin");
    }

    private void refreshStudentList() {
        studentListView.getItems().clear();
        loadStudentData();
    }

    @FXML
    private void gotoHomePage() throws IOException {
        App.setRoot((Stage) studentListView.getScene().getWindow(), "homePage");
    }

    @FXML
    public void goToQuanLyDiem() throws IOException {
        App.setRoot((Stage) studentListView.getScene().getWindow(),"quanLyDiem");
    }
}
