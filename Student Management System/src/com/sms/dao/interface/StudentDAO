package com.sms.dao.interfaces;

import com.sms.model.academic.Student;
import java.util.List;

/**
 * StudentDAO
 *
 * Interface defining database operations for Student entities.
 */
public interface StudentDAO {

    /**
     * Add a new student to the database.
     *
     * @param student Student object
     */
    void addStudent(Student student);

    /**
     * Update an existing student in the database.
     *
     * @param student Student object
     */
    void updateStudent(Student student);

    /**
     * Delete a student by their ID.
     *
     * @param studentId Student ID
     */
    void deleteStudent(int studentId);

    /**
     * Get a student by their ID.
     *
     * @param studentId Student ID
     * @return Student object or null if not found
     */
    Student getStudentById(int studentId);

    /**
     * Get all students from the database.
     *
     * @return List of Student objects
     */
    List<Student> getAllStudents();

    /**
     * Search students by name or ID.
     *
     * @param keyword Search keyword
     * @return List of matching Student objects
     */
    List<Student> searchStudents(String keyword);
}

