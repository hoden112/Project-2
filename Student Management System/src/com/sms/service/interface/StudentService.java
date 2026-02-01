package com.sms.service.interfaces;

import com.sms.model.academic.Student;
import com.sms.exception.DataAccessException;

import java.util.List;

/**
 * StudentService
 *
 * Provides methods to manage students in the system.
 */
public interface StudentService {

    /**
     * Add a new student to the system.
     *
     * @param student The student to add
     * @throws DataAccessException if the operation fails
     */
    void addStudent(Student student) throws DataAccessException;

    /**
     * Update an existing student's information.
     *
     * @param student The student to update
     * @throws DataAccessException if the operation fails
     */
    void updateStudent(Student student) throws DataAccessException;

    /**
     * Delete a student by their ID.
     *
     * @param studentId The ID of the student to delete
     * @throws DataAccessException if the operation fails
     */
    void deleteStudent(int studentId) throws DataAccessException;

    /**
     * Retrieve a student by their ID.
     *
     * @param studentId The ID of the student
     * @return The student object
     * @throws DataAccessException if the operation fails
     */
    Student getStudentById(int studentId) throws DataAccessException;

    /**
     * Get a list of all students.
     *
     * @return List of all students
     * @throws DataAccessException if the operation fails
     */
    List<Student> getAllStudents() throws DataAccessException;
}

