package com.sms.controller;

import com.sms.exception.ValidationException;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CourseController {

    @FXML private TextField courseCodeField;
    @FXML private TextField courseNameField;
    @FXML private TextField creditsField; // Renamed to match FXML fx:id="creditsField"

    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, Integer> idColumn; // Added to match FXML
    @FXML private TableColumn<Course, String> codeColumn;
    @FXML private TableColumn<Course, String> nameColumn;
    @FXML private TableColumn<Course, Integer> creditColumn;

    private final CourseService courseService = new CourseServiceImpl();
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Map table columns
        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        codeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCourseCode()));
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCourseName()));
        creditColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCredit()).asObject());

        loadCourses();

        // Populate fields when selecting a course
        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, selectedCourse) -> {
            if (selectedCourse != null) {
                courseCodeField.setText(selectedCourse.getCourseCode());
                courseNameField.setText(selectedCourse.getCourseName());
                creditsField.setText(String.valueOf(selectedCourse.getCredit()));
            }
        });
    }

    @FXML
    private void handleAddCourse() {
        try {
            Course course = new Course();
            course.setCourseCode(courseCodeField.getText());
            course.setCourseName(courseNameField.getText());
            course.setCredit(Integer.parseInt(creditsField.getText()));

            courseService.addCourse(course);
            AlertUtil.showSuccess("Course added successfully.");
            handleClearFields();
            loadCourses();
        } catch (NumberFormatException e) {
            AlertUtil.showError("Input Error", "Credit must be a number.");
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    @FXML
    private void handleUpdateCourse() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Selection Error", "Select a course to update.");
            return;
        }
        try {
            selected.setCourseCode(courseCodeField.getText());
            selected.setCourseName(courseNameField.getText());
            selected.setCredit(Integer.parseInt(creditsField.getText()));

            courseService.updateCourse(selected);
            AlertUtil.showSuccess("Course updated successfully.");
            handleClearFields();
            loadCourses();
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteCourse() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Selection Error", "Select a course to delete.");
            return;
        }
        try {
            courseService.deleteCourse(selected.getId());
            AlertUtil.showSuccess("Course deleted successfully.");
            handleClearFields();
            loadCourses();
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    // ADDED @FXML so FXML can see this method
    @FXML
    private void handleClearFields() {
        courseCodeField.clear();
        courseNameField.clear();
        creditsField.clear();
        courseTable.getSelectionModel().clearSelection();
    }

    private void loadCourses() {
        courseList.setAll(courseService.getAllCourses());
        courseTable.setItems(courseList);
    }
    @FXML
    private void handleBack() {
        Stage stage = (Stage) courseTable.getScene().getWindow();
        com.sms.util.SceneSwitcher.switchScene(stage, "/view/dashboard/dashboard.fxml", "Dashboard");
    }
}