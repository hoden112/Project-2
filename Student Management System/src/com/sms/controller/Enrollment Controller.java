package com.sms.controller;

import com.sms.model.academic.Course;
import com.sms.model.academic.Enrollment;
import com.sms.model.academic.Student;
import com.sms.model.enums.EnrollmentStatus;
import com.sms.service.interfaces.CourseService;
import com.sms.service.interfaces.EnrollmentService;
import com.sms.service.interfaces.StudentService;
import com.sms.service.impl.CourseServiceImpl;
import com.sms.service.impl.EnrollmentServiceImpl;
import com.sms.service.impl.StudentServiceImpl;
import com.sms.util.AlertUtil;
import com.sms.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EnrollmentController {

    @FXML private ComboBox<Student> studentComboBox;
    @FXML private ComboBox<Course> courseComboBox;
    @FXML private ComboBox<EnrollmentStatus> statusComboBox;

    @FXML private TableView<Enrollment> enrollmentTable;
    @FXML private TableColumn<Enrollment, Integer> idColumn;
    @FXML private TableColumn<Enrollment, String> studentColumn;
    @FXML private TableColumn<Enrollment, String> courseColumn;
    @FXML private TableColumn<Enrollment, String> statusColumn;

    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final EnrollmentService enrollmentService = new EnrollmentServiceImpl();

    private final ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        studentComboBox.setItems(FXCollections.observableArrayList(studentService.getAllStudents()));
        courseComboBox.setItems(FXCollections.observableArrayList(courseService.getAllCourses()));
        statusComboBox.setItems(FXCollections.observableArrayList(EnrollmentStatus.values()));

        idColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());

        studentColumn.setCellValueFactory(d ->
            new SimpleStringProperty(
                d.getValue().getStudent() != null
                    ? d.getValue().getStudent().getFullName()
                    : "N/A"
            )
        );

        courseColumn.setCellValueFactory(d ->
            new SimpleStringProperty(
                d.getValue().getCourse() != null
                    ? d.getValue().getCourse().getCourseName()
                    : "N/A"
            )
        );

        statusColumn.setCellValueFactory(d ->
            new SimpleStringProperty(d.getValue().getStatus().name())
        );

        loadEnrollments();
    }

    @FXML
    private void handleAdd() {
        Student student = studentComboBox.getValue();
        Course course = courseComboBox.getValue();
        EnrollmentStatus status = statusComboBox.getValue();

        if (student == null || course == null || status == null) {
            AlertUtil.showError("Error", "All fields must be selected.");
            return;
        }

        Enrollment e = new Enrollment();
        e.setStudentId(student.getId());
        e.setCourseId(course.getId());
        e.setStatus(status);

        enrollmentService.addEnrollment(e);
        AlertUtil.showSuccess("Enrollment added.");
        handleClear();
        loadEnrollments();
    }

    @FXML
    private void handleDelete() {
        Enrollment e = enrollmentTable.getSelectionModel().getSelectedItem();
        if (e == null) {
            AlertUtil.showError("Error", "Select an enrollment.");
            return;
        }

        enrollmentService.deleteEnrollment(e.getId());
        AlertUtil.showSuccess("Enrollment deleted.");
        loadEnrollments();
    }

    @FXML
    private void handleClear() {
        studentComboBox.setValue(null);
        courseComboBox.setValue(null);
        statusComboBox.setValue(null);
        enrollmentTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) enrollmentTable.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/view/dashboard/dashboard.fxml", "Dashboard");
    }

    private void loadEnrollments() {
        enrollmentList.setAll(enrollmentService.getAllEnrollments());
        enrollmentTable.setItems(enrollmentList);
    }
}
