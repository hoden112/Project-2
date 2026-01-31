package com.sms.controller;

import com.sms.exception.ValidationException;
import com.sms.model.user.User;
import com.sms.model.enums.Role;
import com.sms.service.interfaces.AuthService;
import com.sms.service.impl.AuthServiceImpl;
import com.sms.util.AlertUtil;
import com.sms.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox; // Use ComboBox
import com.sms.model.user.StudentUser;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ComboBox<Role> roleComboBox; // Name must match fx:id in FXML

    private final AuthService authService = new AuthServiceImpl();

    @FXML
    private void initialize() {
        if (roleComboBox != null) {
            roleComboBox.getItems().addAll(Role.ADMIN, Role.INSTRUCTOR, Role.STUDENT);
            roleComboBox.setValue(Role.STUDENT);
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        Role role = roleComboBox.getValue();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            AlertUtil.showError("Validation Error", "All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            AlertUtil.showError("Error", "Passwords do not match.");
            return;
        }

        User user = new StudentUser(username, password, "", "", "");
        Stage stage = (Stage) usernameField.getScene().getWindow();

        try {
            authService.register(user);
            AlertUtil.showSuccess("User registered successfully!");
            SceneSwitcher.switchScene(stage, "/view/login/login.fxml", "Login");
        } catch (ValidationException e) {
            AlertUtil.showError("Validation Error", e.getMessage());
        } catch (Exception e) {
            AlertUtil.showError("Unexpected Error", "An error occurred: " + e.getMessage());
        }
    }

    // Name must match onAction="#handleBack" in FXML
    @FXML
    private void handleBack() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/view/login/login.fxml", "Login");
    }
}