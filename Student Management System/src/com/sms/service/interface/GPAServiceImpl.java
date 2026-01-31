package com.sms.service.impl;

import com.sms.exception.DataAccessException;
import com.sms.model.enums.GradeType;
import com.sms.service.interfaces.GPAService;

import java.util.List;

public class GPAServiceImpl implements GPAService {

    @Override
    public double calculateGPA(String username) {
        
        List<GradeType> grades = List.of(
                GradeType.A, GradeType.B, GradeType.A, GradeType.C
        );

        if (grades.isEmpty()) {
            throw new DataAccessException("No grades found");
        }

        double total = 0;
        for (GradeType grade : grades) {
            total += gradeToPoint(grade);
        }
        return total / grades.size();
    }

    private double gradeToPoint(GradeType grade) {
        return switch (grade) {
            case A -> 4.0;
            case B -> 3.0;
            case C -> 2.0;
            case D -> 1.0;
            case F -> 0.0;
        };
    }
}
