package com.sms.dao.impl;

import com.sms.dao.interfaces.StudentDAO;
import com.sms.database.DatabaseConnection;
import com.sms.exception.DataAccessException;
import com.sms.model.academic.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAOImpl
 *
 * Implements StudentDAO interface.
 * Provides CRUD operations for Student data.
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, full_name, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getPhone());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error adding student", e);
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET student_id = ?, full_name = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getPhone());
            stmt.setInt(5, student.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error updating student", e);
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting student", e);
        }
    }

    @Override
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching student", e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
            return students;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching students", e);
        }
    }
    @Override
    public List<Student> searchStudents(String keyword) {
        String sql = "SELECT * FROM students WHERE full_name LIKE ? OR student_id LIKE ?";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
            return students;

        } catch (SQLException e) {
            throw new DataAccessException("Error searching students", e);
        }
    }

    /**
     * Helper method to map ResultSet to Student object
     */
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setStudentId(rs.getString("student_id"));
        student.setFullName(rs.getString("full_name"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        return student;
    }
}
