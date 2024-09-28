package com.group21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import com.group21.database.DiemDB;
import com.group21.database.SinhVienDB;
import com.group21.model.Diem;
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
                stage.setHeight(300);
                stage.setWidth(200);
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

        SinhVien sinhVien = new SinhVien("2301", "Thanh Nhung", LocalDate.now(), "Nam", "Linh Trung", "0987654321", "o0nGf@example.com");
        SinhVien sinhVien2 = new SinhVien("2302", "Nguyễn Thế Thảo", LocalDate.now(), "Nữ", "Linh Trung", "0987654321", "o0nGf@example.com");

        Diem diem2 = new Diem("2302", "Toan", 9.5f, 7.0f, 8.5f, 9.0f);
        Diem diem = new Diem("2301", "Toan", 9.5f, 7.0f, 8.5f, 9.0f);
        
        DiemDB.addDiem(diem);
        DiemDB.addDiem(diem2);
        SinhVienDB.addSinhVien(sinhVien);
        SinhVienDB.addSinhVien(sinhVien2);

        launch();
    }

}