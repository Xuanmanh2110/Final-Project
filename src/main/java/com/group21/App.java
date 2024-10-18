package com.group21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import com.group21.database.DiemDB;
import com.group21.database.MonHocDB;
import com.group21.database.SinhVienDB;
import com.group21.model.Diem;
import com.group21.model.MonHoc;
import com.group21.model.SinhVien;


public class App extends Application {

    private static Scene scene;

    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(Stage stage, String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

        switch (fxml) {
            case "homePage":
                stage.setHeight(600);
                stage.setWidth(800);
                break;
            case "quanLyDiem":
                stage.setHeight(600);
                stage.setWidth(1000);
                break;
            default:
                stage.setHeight(600);
                stage.setWidth(800);
                break;
        }

        if (stage.isShowing()) {
            stage.centerOnScreen();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // Initialize in-memory database
        SinhVien sinhVien = new SinhVien("2301", "Thành Thế Thảo", LocalDate.now(), "Nam", "CNTT 1", "0987654321", "2301@example.com");
        SinhVien sinhVien2 = new SinhVien("2302", "Nguyễn Hoàn Thế Phương", LocalDate.now(), "Nữ", "CNTT 2", "0987654321", "2302@example.com");

        Diem diem = new Diem("2301", "MH01", 9.5f, 7.0f, 8.5f, 9.0f);
        Diem diem2 = new Diem("2301", "MH02", 9.5f, 7.0f, 8.5f, 8.0f);
        Diem diem3 = new Diem("2301", "MH03", 9.5f, 7.0f, 8.5f, 7.0f);



        MonHoc monHoc = new MonHoc("MH01", "Cơ sở dữ liệu");
        MonHoc monHoc2 = new MonHoc("MH02", "Cấu trúc dữ liệu & giải thuật");
        MonHoc monHoc3 = new MonHoc("MH03", "Lập trình web");

        MonHocDB.monHocList.add(monHoc);
        MonHocDB.monHocList.add(monHoc2);
        MonHocDB.monHocList.add(monHoc3);

        sinhVien.setMaMHs(Arrays.asList(monHoc.getMaMH(), monHoc2.getMaMH(), monHoc3.getMaMH()));

        sinhVien2.setMaMHs(Arrays.asList(monHoc.getMaMH(), monHoc2.getMaMH()));

        DiemDB.addDiem(diem);
        DiemDB.addDiem(diem2);
        DiemDB.addDiem(diem3);
        SinhVienDB.addSinhVien(sinhVien);
        SinhVienDB.addSinhVien(sinhVien2);

        launch();
    }

}