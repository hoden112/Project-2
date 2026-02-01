package com.sms.dao.impl;

import com.sms.dao.interfaces.EnrollmentDAO;
import com.sms.database.DatabaseConnection;
import com.sms.exception.DataAccessException;
import com.sms.model.academic.Enrollment;
import com.sms.model.enums.EnrollmentStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EnrollmentDAOImpl
 *
 * This class implements EnrollmentDAO interface.
 * Provides CRUD operations for Enrollment data.
 */
public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public void addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_id, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getCourseId());
            stmt.setString(3, enrollment.getStatus().name());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error adding enrollment", e);
        }
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE enrollments SET student_id = ?, course_id = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getCourseId());
            stmt.setString(3, enrollment.getStatus().name());
            stmt.setInt(4, enrollment.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error updating enrollment", e);
        }
    }

    @Override
    public void deleteEnrollment(int enrollmentId) {
        String sql = "DELETE FROM enrollments WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting enrollment", e);
        }
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        String sql = "SELECT * FROM enrollments WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollmentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToEnrollment(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching enrollment", e);
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        String sql = "SELECT * FROM enrollments";
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                enrollments.add(mapResultSetToEnrollment(rs));
            }
            return enrollments;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching enrollments", e);
        }
    }
    @Override
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        String sql = "SELECT * FROM enrollments WHERE student_id = ?";
        List<Enrollment> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToEnrollment(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DataAccessException("Error fetching enrollments by student", e);
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        String sql = "SELECT * FROM enrollments WHERE course_id = ?";
        List<Enrollment> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToEnrollment(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DataAccessException("Error fetching enrollments by course", e);
        }
    }

    /**
     * Helper method to map ResultSet to Enrollment object
     */
    private Enrollment mapResultSetToEnrollment(ResultSet rs) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(rs.getInt("id"));
        enrollment.setStudentId(rs.getInt("student_id"));
        enrollment.setCourseId(rs.getInt("course_id"));
        enrollment.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
        return enrollment;
    }
}

