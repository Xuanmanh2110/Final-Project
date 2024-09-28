package com.group21;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomePageController {
    
    @FXML
    private AnchorPane homePage;

    @FXML
    public void goToQuanLySV() throws IOException {
        App.setRoot((Stage) homePage.getScene().getWindow(), "quanLySV");
    }

    @FXML
    public void goToQuanLyDiem() throws IOException {
        App.setRoot((Stage) homePage.getScene().getWindow(),"quanLyDiem");
    }

    @FXML
    public void quit() throws IOException {
        App.setRoot((Stage) homePage.getScene().getWindow(),"login");
    }
}
