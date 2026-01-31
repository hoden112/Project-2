package com.sms.service.impl;

import com.sms.exception.DataAccessException;
import com.sms.service.interfaces.ExportService;

import java.io.FileWriter;
import java.io.IOException;

public class CSVExportServiceImpl implements ExportService {

    @Override
    public void exportGrades(String username) {
        try (FileWriter writer = new FileWriter("grades_" + username + ".csv")) {
            writer.write("Course,Grade\n");
            writer.write("Math,A\n");
            writer.write("Physics,B\n");
        } catch (IOException e) {
            throw new DataAccessException("Failed to export CSV");
        }
    }
}
