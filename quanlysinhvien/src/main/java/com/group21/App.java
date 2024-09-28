package com.group21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        launch();
    }

}