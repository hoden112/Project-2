package com.sms.controller;

import com.sms.database.SessionManager;
import com.sms.exception.AuthenticationException;
import com.sms.model.user.User;
import com.sms.service.interfaces.AuthService;
import com.sms.service.impl.AuthServiceImpl;
import com.sms.util.AlertUtil;
import com.sms.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final AuthService authService = new AuthServiceImpl();

   
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            AlertUtil.showError("Validation Error", "Username and password cannot be empty.");
            return;
        }

        try {
            User user = authService.login(username, password);

            if (user == null) {
                AlertUtil.showError("Login Failed", "Invalid username or password.");
                return;
            }

            SessionManager.login(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            SceneSwitcher.switchScene(
                    stage,
                    "/view/dashboard/dashboard.fxml",
                    user.getRole().name() + " Dashboard"
            );

            AlertUtil.showSuccess("Welcome, " + user.getUsername() + "!");

        } catch (AuthenticationException e) {
            AlertUtil.showError("Authentication Error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.showError("System Error", "Something went wrong. Please try again.");
        }
    }

    
    @FXML
    private void handleRegister() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        SceneSwitcher.switchScene(
                stage,
                "/view/register/register.fxml",
                "User Registration"
        );
    }

   
    @FXML
    private void handleReset() {
        usernameField.clear();
        passwordField.clear();
    }
}
