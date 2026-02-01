package com.sms.controller;

import com.sms.database.SessionManager;
import com.sms.model.user.User;
import com.sms.model.enums.Role;
import com.sms.util.SceneSwitcher;
import com.sms.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label roleLabel;

    @FXML private Button studentsButton;
    @FXML private Button coursesButton;
    @FXML private Button enrollmentsButton;
    @FXML private Button gradesButton;
    @FXML private Button exportButton;

    @FXML
    public void initialize() {
        User user = SessionManager.getCurrentUser();
        if (user != null) {
            String name = user.getFullName() != null ? user.getFullName() : user.getUsername();
            welcomeLabel.setText("Welcome, " + name);
            roleLabel.setText("Role: " + user.getRole());
            applyRolePermissions(user.getRole());
        }
    }

 
    private void applyRolePermissions(Role role) {
        switch (role) {
            case STUDENT -> {
                hide(studentsButton);
                hide(coursesButton);
                hide(exportButton);
                gradesButton.setText("My Grades");
            }
            case INSTRUCTOR -> {
                hide(studentsButton);
                hide(exportButton);
            }
            case ADMIN -> {}
        }
    }

    private void hide(Button b) {
        b.setVisible(false);
        b.setManaged(false);
    }

    @FXML private void handleStudents(ActionEvent e){ switchScene(e,"/view/student/student.fxml","Students"); }
    @FXML private void handleCourses(ActionEvent e){ switchScene(e,"/view/course/course.fxml","Courses"); }
    @FXML private void handleEnrollments(ActionEvent e){ switchScene(e,"/view/enrollment/enrollment.fxml","Enrollments"); }
    @FXML private void handleGrades(ActionEvent e){ switchScene(e,"/view/grade/grade.fxml","Grades"); }

    @FXML
    private void handleLogout(ActionEvent e) {
        SessionManager.logout();
        switchScene(e,"/view/login/login.fxml","Login");
    }

    private void switchScene(ActionEvent e,String path,String title){
        Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage,path,title);
    }
}
