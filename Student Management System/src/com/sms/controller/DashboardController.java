package com.sms.controller;

import com.sms.database.SessionManager;
import com.sms.exception.DataAccessException;
import com.sms.exception.ValidationException;
import com.sms.model.user.User;
import com.sms.service.impl.CSVExportServiceImpl;
import com.sms.service.impl.GPAServiceImpl;
import com.sms.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label gpaLabel;

    private final GPAServiceImpl gpaService = new GPAServiceImpl();
    private final CSVExportServiceImpl exportService = new CSVExportServiceImpl();

    @FXML
    public void handleCalculateGPA() {
        try {
            User user = SessionManager.getUser();
            double gpa = gpaService.calculateGPA(user.getUsername());
            gpaLabel.setText("GPA: " + gpa);
        } catch (Exception e) {
            AlertUtil.error("GPA Error", e.getMessage());
        }
    }

    @FXML
    public void handleExport() {
        try {
            User user = SessionManager.getUser();
            exportService.exportGrades(user.getUsername());
            AlertUtil.info("Export Successful", "Grades exported to CSV");
        } catch (Exception e) {
            AlertUtil.error("Export Error", e.getMessage());
        }
    }
}
