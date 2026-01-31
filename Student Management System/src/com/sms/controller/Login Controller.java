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

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private final AuthService authService = new AuthServiceImpl();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            AlertUtil.showError("Error", "Username and password cannot be empty.");
            return;
        }

        try {
            User user = authService.login(username, password);

            if (user != null) {
                SessionManager.login(user);
                Stage stage = (Stage) usernameField.getScene().getWindow();

               
                String dashboardPath = "/view/dashboard/dashboard.fxml";
                
                String title = "";
                switch (user.getRole()) {
                    case ADMIN: title = "Admin Dashboard"; break;
                    case INSTRUCTOR: title = "Instructor Dashboard"; break;
                    case STUDENT: title = "Student Dashboard"; break;
                }

                SceneSwitcher.switchScene(stage, dashboardPath, title);
                AlertUtil.showSuccess("Login successful. Welcome, " + user.getUsername() + "!");

            } else {
                AlertUtil.showError("Error", "Invalid username or password.");
            }

        } catch (AuthenticationException e) {
            AlertUtil.showError("Auth Error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.showError("Unexpected Error", "An error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void handleRegister() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        // Path must match folder structure exactly
        SceneSwitcher.switchScene(stage, "/view/register/register.fxml", "User Registration");
    }
}