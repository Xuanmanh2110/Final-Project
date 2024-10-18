package com.group21;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    private final String VALID_USERNAME = "user1";
    private final String VALID_PASSWORD = "pass1";

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateLogin(username, password)) {
            messageLabel.setText("Login successful!");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                try {
                    App.setRoot((Stage) loginButton.getScene().getWindow(), "homePage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            pause.play();
            // App.setRoot("homePage");
        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }

    private boolean validateLogin(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }
}
