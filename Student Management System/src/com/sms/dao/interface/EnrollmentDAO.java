package com.sms.dao.interfaces;

import com.sms.model.academic.Enrollment;
import java.util.List;

/**
 * EnrollmentDAO
 *
 * Interface defining database operations for Enrollment entities.
 */
public interface EnrollmentDAO {

    /**
     * Add a new enrollment to the database.
     *
     * @param enrollment Enrollment object
     */
    void addEnrollment(Enrollment enrollment);

    /**
     * Update an existing enrollment in the database.
     *
     * @param enrollment Enrollment object
     */
    void updateEnrollment(Enrollment enrollment);

    /**
     * Delete an enrollment by its ID.
     *
     * @param enrollmentId Enrollment ID
     */
    void deleteEnrollment(int enrollmentId);

    /**
     * Get an enrollment by its ID.
     *
     * @param enrollmentId Enrollment ID
     * @return Enrollment object or null if not found
     */
    Enrollment getEnrollmentById(int enrollmentId);

    /**
     * Get all enrollments from the database.
     *
     * @return List of Enrollment objects
     */
    List<Enrollment> getAllEnrollments();

    /**
     * Get all enrollments for a specific student.
     *
     * @param studentId Student ID
     * @return List of Enrollment objects
     */
    List<Enrollment> getEnrollmentsByStudentId(int studentId);

    /**
     * Get all enrollments for a specific course.
     *
     * @param courseId Course ID
     * @return List of Enrollment objects
     */
    List<Enrollment> getEnrollmentsByCourseId(int courseId);
}

