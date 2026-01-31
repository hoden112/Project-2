package com.sms.service.impl;

import com.sms.dao.interfaces.GradeDAO;
import com.sms.dao.interfaces.EnrollmentDAO;
import com.sms.exception.ValidationException;
import com.sms.exception.DataAccessException;
import com.sms.model.academic.Enrollment;
import com.sms.model.academic.Grade;
import com.sms.service.interfaces.GradeService;

import java.util.List;

/**
 * GradeServiceImpl
 *
 * Implements GradeService to manage grade operations.
 */
public class GradeServiceImpl implements GradeService {

    private final GradeDAO gradeDAO;
    private final EnrollmentDAO enrollmentDAO;

    /**
     * Constructor injection for DAO dependencies.
     *
     * @param gradeDAO      GradeDAO implementation
     * @param enrollmentDAO EnrollmentDAO implementation
     */
    public GradeServiceImpl(GradeDAO gradeDAO, EnrollmentDAO enrollmentDAO) {
        this.gradeDAO = gradeDAO;
        this.enrollmentDAO = enrollmentDAO;
    }
    public GradeServiceImpl() {
        this.gradeDAO = new com.sms.dao.impl.GradeDAOImpl();
        this.enrollmentDAO = new com.sms.dao.impl.EnrollmentDAOImpl();
    }


    /**
     * Add a grade for a student's enrollment.
     *
     * @param grade Grade object
     * @throws ValidationException if grade score is invalid
     */
    @Override
    public void addGrade(Grade grade) throws DataAccessException {
        if (grade.getScore() < 0 || grade.getScore() > 100) {
            throw new ValidationException("Grade score must be between 0 and 100.");
        }

        Enrollment enrollment = enrollmentDAO.getEnrollmentById(grade.getEnrollment().getId());
        if (enrollment == null) {
            throw new ValidationException("Enrollment not found for the grade.");
        }

        gradeDAO.addGrade(grade);
    }

    /**
     * Update an existing grade.
     *
     * @param grade Grade object
     * @throws ValidationException if grade score is invalid
     */
    @Override
    public void updateGrade(Grade grade) throws DataAccessException {
        if (grade.getScore() < 0 || grade.getScore() > 100) {
            throw new ValidationException("Grade score must be between 0 and 100.");
        }

        gradeDAO.updateGrade(grade);
    }

    /**
     * Delete a grade by its ID.
     *
     * @param gradeId Grade ID
     */
    @Override
    public void deleteGrade(int gradeId) throws DataAccessException {
        gradeDAO.deleteGrade(gradeId);
    }

    /**
     * Get a grade by its ID.
     *
     * @param gradeId Grade ID
     * @return Grade object
     */
    @Override
    public Grade getGradeById(int gradeId) throws DataAccessException {
        return gradeDAO.getGradeById(gradeId);
    }

    /**
     * Get all grades for a specific enrollment.
     *
     * @return List of grades
     */
    @Override
    public List<Grade> getGradesByEnrollment(Enrollment enrollment) {
        return gradeDAO.getGradesByEnrollmentId(enrollment.getId());
    }

    /**
     * Calculate final grade for an enrollment using weights.
     *
     * @param enrollmentId Enrollment ID
     * @return final numeric grade
     */
    @Override
    public double calculateFinalGrade(int enrollmentId) {
        List<Grade> grades = gradeDAO.getGradesByEnrollmentId(enrollmentId);
        if (grades.isEmpty()) return 0.0;

        double total = 0.0;
        for (Grade grade : grades) {
            // Example: all grade types equal weight (can customize)
            total += grade.getScore();
        }

        return total / grades.size();
    }
    @Override
    public List<Grade> getAllGrades() throws DataAccessException {
        return gradeDAO.getAllGrades();
    }
    @Override
    public double calculateGPA(String studentId) {
        List<Grade> grades = gradeDAO.getGradesByStudentId(studentId);
        if (grades.isEmpty()) return 0.0;

        double totalPoints = 0;
        int totalCredits = 0;

        for (Grade g : grades) {
            int credit = g.getEnrollment().getCourse().getCredit();
            totalPoints += g.getScore() * credit;
            totalCredits += credit;
        }

        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public List<Grade> getGradesByStudent(String studentId) {
        return gradeDAO.getGradesByStudentId(studentId); // DAO method
    }


}

