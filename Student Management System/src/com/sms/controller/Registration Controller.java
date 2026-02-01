package com.sms.controller;

import com.sms.model.user.User;
import com.sms.model.user.StudentUser;
import com.sms.model.enums.Role;
import com.sms.service.impl.AuthServiceImpl;
import com.sms.service.interfaces.AuthService;
import com.sms.util.AlertUtil;
import com.sms.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField fullNameField; // Linked to FXML
    @FXML private TextField emailField;    // Linked to FXML
    @FXML private TextField phoneField;    // Linked to FXML
    @FXML private ComboBox<Role> roleComboBox;

    private final AuthService authService = new AuthServiceImpl();

    @FXML
    private void initialize() {
        roleComboBox.getItems().addAll(Role.ADMIN, Role.INSTRUCTOR, Role.STUDENT);
        roleComboBox.setValue(Role.STUDENT);
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirm = confirmPasswordField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        Role role = roleComboBox.getValue();

        if (username.isEmpty()  password.isEmpty()  fullName.isEmpty()) {
            AlertUtil.showError("Validation Error", "Username, Password, and Full Name are required.");
            return;
        }

        if (!password.equals(confirm)) {
            AlertUtil.showError("Error", "Passwords do not match.");
            return;
        }

        
        User user = new StudentUser(username, password, fullName, email, phone);
        user.setRole(role);
        
        try {
            authService.register(user);
            AlertUtil.showSuccess("Registration successful!");
            handleBack(); 
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/view/login/login.fxml", "Login");
    }
}