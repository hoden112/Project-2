package com.sms.controller;

import com.sms.model.academic.Enrollment;
import com.sms.model.academic.Grade;
import com.sms.model.enums.GradeType;
import com.sms.service.interfaces.EnrollmentService;
import com.sms.service.interfaces.GradeService;
import com.sms.service.impl.EnrollmentServiceImpl;
import com.sms.service.impl.GradeServiceImpl;
import com.sms.util.AlertUtil;
import com.sms.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GradeController {

    @FXML private ComboBox<Enrollment> enrollmentComboBox;
    @FXML private ComboBox<GradeType> gradeTypeComboBox;
    @FXML private TextField scoreField;

    @FXML private TableView<Grade> gradeTable;
    @FXML private TableColumn<Grade, Integer> idColumn;
    @FXML private TableColumn<Grade, String> studentColumn;
    @FXML private TableColumn<Grade, String> courseColumn;
    @FXML private TableColumn<Grade, String> gradeTypeColumn; // Matches fx:id in FXML
    @FXML private TableColumn<Grade, Double> scoreColumn;

    private final GradeService gradeService = new GradeServiceImpl();
    // Ensure EnrollmentServiceImpl matches your constructor requirements
    private final EnrollmentService enrollmentService = new EnrollmentServiceImpl();

    private final ObservableList<Grade> gradeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Load dropdowns
        enrollmentComboBox.setItems(FXCollections.observableArrayList(enrollmentService.getAllEnrollments()));
        gradeTypeComboBox.setItems(FXCollections.observableArrayList(GradeType.values()));

        // Setup table columns
        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        
        studentColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEnrollment().getStudent().getFullName()));

        courseColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEnrollment().getCourse().getCourseName()));

        gradeTypeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getGradeType().name()));

        scoreColumn.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getScore()).asObject());

        loadGrades();
    }

    // RENAMED: Matches onAction="#handleAdd"
    @FXML
    private void handleAdd() {
        try {
            Enrollment enrollment = enrollmentComboBox.getValue();
            GradeType type = gradeTypeComboBox.getValue();
            String scoreText = scoreField.getText();

            if (enrollment == null || type == null || scoreText.isBlank()) {
                AlertUtil.showError("Error", "All fields are required.");
                return;
            }

            double score = Double.parseDouble(scoreText);

            Grade grade = new Grade();
            grade.setEnrollment(enrollment);
            grade.setGradeType(type);
            grade.setScore(score);

            gradeService.addGrade(grade);
            AlertUtil.showSuccess("Grade added successfully.");
            handleClear();
            loadGrades();
        } catch (NumberFormatException e) {
            AlertUtil.showError("Error", "Score must be a number.");
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    // RENAMED: Matches onAction="#handleDelete"
    @FXML
    private void handleDelete() {
        Grade selected = gradeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Selection Error", "Select a grade to delete.");
            return;
        }
        try {
            gradeService.deleteGrade(selected.getId());
            AlertUtil.showSuccess("Grade deleted successfully.");
            loadGrades();
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    // RENAMED: Matches onAction="#handleClear"
    @FXML
    private void handleClear() {
        enrollmentComboBox.setValue(null);
        gradeTypeComboBox.setValue(null);
        scoreField.clear();
        gradeTable.getSelectionModel().clearSelection();
    }

    // ADDED: Matches onAction="#handleUpdate"
    @FXML
    private void handleUpdate() {
        AlertUtil.showSuccess("Update feature not implemented yet.");
    }

    // ADDED: Matches onAction="#handleBack"
    @FXML
    private void handleBack() {
        Stage stage = (Stage) gradeTable.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/view/dashboard/dashboard.fxml", "Dashboard");
    }

    private void loadGrades() {
        gradeList.setAll(gradeService.getAllGrades());
        gradeTable.setItems(gradeList);
    }
    