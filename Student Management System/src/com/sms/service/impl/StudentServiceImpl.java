package com.sms.service.impl;

import com.sms.dao.interfaces.StudentDAO;
import com.sms.exception.ValidationException;
import com.sms.model.academic.Student;
import com.sms.service.interfaces.StudentService;
import com.sms.dao.impl.StudentDAOImpl;


import java.util.List;

/**
 * StudentServiceImpl
 *
 * Implements StudentService to manage student operations.
 */
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    /**
     * Constructor injection for DAO dependency
     *
     * @param studentDAO StudentDAO implementation
     */
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    public StudentServiceImpl() {
        this.studentDAO = new StudentDAOImpl();
    }

    /**
     * Add a new student after validation.
     *
     * @param student Student object
     * @throws ValidationException if required fields are missing
     */
    @Override
    public void addStudent(Student student) throws ValidationException {
        validateStudent(student);
        studentDAO.addStudent(student);
    }

    /**
     * Update an existing student after validation.
     *
     * @param student Student object
     * @throws ValidationException if required fields are missing
     */
    @Override
    public void updateStudent(Student student) throws ValidationException {
        validateStudent(student);
        studentDAO.updateStudent(student);
    }

    /**
     * Delete a student by ID.
     *
     * @param studentId Student ID
     */
    @Override
    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
    }

    /**
     * Get a student by ID.
     *
     * @param studentId Student ID
     * @return Student object
     */
    @Override
    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    /**
     * Get all students.
     *
     * @return List of students
     */
    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    /**
     * Validate student data before database operations.
     *
     * @param student Student object
     * @throws ValidationException if validation fails
     */
    private void validateStudent(Student student) throws ValidationException {
        if (student.getStudentId() == null || student.getStudentId().isEmpty()) {
            throw new ValidationException("Student ID cannot be empty.");
        }
        if (student.getFullName() == null || student.getFullName().isEmpty()) {
            throw new ValidationException("Student full name cannot be empty.");
        }
        // Optional: email format validation
        if (student.getEmail() != null && !student.getEmail().matches("^\\S+@\\S+\\.\\S+$")) {
            throw new ValidationException("Invalid email format.");
        }
    }
}

