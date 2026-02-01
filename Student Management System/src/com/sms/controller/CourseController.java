package com.sms.controller;

import com.sms.model.academic.Course;
import com.sms.service.interfaces.CourseService;
import com.sms.service.impl.CourseServiceImpl;
import com.sms.util.AlertUtil;
import com.sms.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseController {

    @FXML private TextField courseCodeField;
    @FXML private TextField courseNameField;
    @FXML private TextField creditsField;

    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, Integer> idColumn;
    @FXML private TableColumn<Course, String> codeColumn;
    @FXML private TableColumn<Course, String> nameColumn;
    @FXML private TableColumn<Course, Integer> creditsColumn;

    private final CourseService courseService = new CourseServiceImpl();
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        codeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCourseCode()));
        nameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCourseName()));
        creditsColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getCredit()).asObject());

        loadCourses();

        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, o, c) -> {
            if (c != null) {
                courseCodeField.setText(c.getCourseCode());
                courseNameField.setText(c.getCourseName());
                creditsField.setText(String.valueOf(c.getCredit()));
            }
        });
    }

    @FXML
    private void handleAddCourse() {
        try {
            Course c = new Course();
            c.setCourseCode(courseCodeField.getText());
            c.setCourseName(courseNameField.getText());
            c.setCredit(Integer.parseInt(creditsField.getText()));

            courseService.addCourse(c);
            AlertUtil.showSuccess("Course added successfully.");
            handleClearFields();
            loadCourses();
        } catch (NumberFormatException e) {
            AlertUtil.showError("Input Error", "Credit must be a number.");
        }
    }

    @FXML
    private void handleUpdateCourse() {
        Course c = courseTable.getSelectionModel().getSelectedItem();
        if (c == null) {
            AlertUtil.showError("Error", "Select a course first.");
            return;
        }

        try {
            c.setCourseCode(courseCodeField.getText());
            c.setCourseName(courseNameField.getText());
            c.setCredit(Integer.parseInt(creditsField.getText()));

            courseService.updateCourse(c);
            AlertUtil.showSuccess("Course updated.");
            handleClearFields();
            loadCourses();
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteCourse() {
        Course c = courseTable.getSelectionModel().getSelectedItem();
        if (c == null) {
            AlertUtil.showError("Error", "Select a course to delete.");
            return;
        }

        courseService.deleteCourse(c.getId());
        AlertUtil.showSuccess("Course deleted.");
        handleClearFields();
        loadCourses();
    }

    @FXML
    private void handleClearFields() {
        courseCodeField.clear();
        courseNameField.clear();
        creditsField.clear();
        courseTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) courseTable.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/view/dashboard/dashboard.fxml", "Dashboard");
    }

    private void loadCourses() {
        courseList.setAll(courseService.getAllCourses());
        courseTable.setItems(courseList);
    }
}
