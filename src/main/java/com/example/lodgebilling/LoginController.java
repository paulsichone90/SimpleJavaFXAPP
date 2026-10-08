package com.example.lodgebilling;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin(ActionEvent event) {

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {

            messageLabel.setText("Please enter username and password.");
            return;
        }

        if (username.equals("admin") && password.equals("1234")) {

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("lodge-billing.fxml")
                );

                Scene scene = new Scene(loader.load());

                Stage stage = (Stage)
                        ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("Lodge Billing System");
                stage.setScene(scene);
                stage.setResizable(true);
                stage.show();

            } catch (Exception e) {

                messageLabel.setText("Unable to open the billing system.");
                e.printStackTrace();
            }

        } else {

            messageLabel.setText("Invalid username or password.");
        }
    }
}