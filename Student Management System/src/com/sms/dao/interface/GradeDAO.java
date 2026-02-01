package com.sms.dao.interfaces;

import com.sms.model.academic.Grade;
import com.sms.exception.DataAccessException;
import java.util.List;

/**
 * GradeDAO
 *
 * Interface defining database operations for Grade entities.
 */
public interface GradeDAO {

    /**
     * Add a new grade to the database.
     *
     * @param grade Grade object
     */
    void addGrade(Grade grade);

    /**
     * Update an existing grade in the database.
     *
     * @param grade Grade object
     */
    void updateGrade(Grade grade);

    /**
     * Delete a grade by its ID.
     *
     * @param gradeId Grade ID
     */
    void deleteGrade(int gradeId);

    /**
     * Get a grade by its ID.
     *
     * @param gradeId Grade ID
     * @return Grade object or null if not found
     */
    Grade getGradeById(int gradeId);

    /**
     * Get all grades from the database.
     *
     * @return List of Grade objects
     */
    List<Grade> getAllGrades() throws DataAccessException;

    /**
     * Get all grades for a specific enrollment.
     *
     * @param enrollmentId Enrollment ID
     * @return List of Grade objects
     */
    List<Grade> getGradesByEnrollmentId(int enrollmentId);
}

