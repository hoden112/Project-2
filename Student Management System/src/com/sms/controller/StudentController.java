package com.sms.controller;

import com.sms.model.academic.Student;
import com.sms.service.interfaces.StudentService;
import com.sms.service.impl.StudentServiceImpl;
import com.sms.util.AlertUtil;
import com.sms.util.SceneSwitcher; // Import SceneSwitcher
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent; // Import ActionEvent
import javafx.scene.Node; // Import Node
import javafx.stage.Stage; // Import Stage

public class StudentController {

    @FXML private TextField studentIdField;
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField searchField;
    @FXML private Label messageLabel; // Ensure this exists in FXML if you use it

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> studentIdColumn;
    @FXML private TableColumn<Student, String> fullNameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TableColumn<Student, String> phoneColumn;

    private final StudentService studentService = new StudentServiceImpl();
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        studentIdColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStudentId()));
        fullNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFullName()));
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone()));

        loadStudents();

        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, selectedStudent) -> {
            if (selectedStudent != null) {
                studentIdField.setText(selectedStudent.getStudentId());
                fullNameField.setText(selectedStudent.getFullName());
                emailField.setText(selectedStudent.getEmail());
                phoneField.setText(selectedStudent.getPhone());
            }
        });
    }

    @FXML
    private void handleAdd() {
        try {
            Student student = new Student();
            student.setStudentId(studentIdField.getText());
            student.setFullName(fullNameField.getText());
            student.setEmail(emailField.getText());
            student.setPhone(phoneField.getText());

            studentService.addStudent(student);
            AlertUtil.showSuccess("Student added successfully.");
            handleClear();
            loadStudents();
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Error", "Select a student to update.");
            return;
        }
        try {
            selected.setStudentId(studentIdField.getText());
            selected.setFullName(fullNameField.getText());
            selected.setEmail(emailField.getText());
            selected.setPhone(phoneField.getText());

            studentService.updateStudent(selected);
            AlertUtil.showSuccess("Student updated successfully.");
            loadStudents();
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Error", "Select a student to delete.");
            return;
        }
        try {
            studentService.deleteStudent(selected.getId());
            AlertUtil.showSuccess("Student deleted successfully.");
            handleClear();
            loadStudents();
        } catch (Exception e) {
            AlertUtil.showError("Error", e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        studentIdField.clear();
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        studentTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();
        if (keyword.isEmpty()) {
            loadStudents();
        } else {
            ObservableList<Student> filteredList = studentList.filtered(s ->
                s.getFullName().toLowerCase().contains(keyword) ||
                s.getStudentId().toLowerCase().contains(keyword)
            );
            studentTable.setItems(filteredList);
        }
    }

    // ADDED: Method to go back to the dashboard
    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/view/dashboard/dashboard.fxml", "Dashboard");
    }

    private void loadStudents() {
        studentList.setAll(studentService.getAllStudents());
        studentTable.setItems(studentList);
    }
}