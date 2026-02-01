package com.sms.dao.impl;

import com.sms.dao.interfaces.GradeDAO;
import com.sms.database.DatabaseConnection;
import com.sms.exception.DataAccessException;
import com.sms.model.academic.Grade;
import com.sms.model.enums.GradeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GradeDAOImpl
 *
 * This class implements GradeDAO interface.
 * Provides CRUD operations for Grade data.
 */
public class GradeDAOImpl implements GradeDAO {

    @Override
    public void addGrade(Grade grade) {
        String sql = "INSERT INTO grades (enrollment_id, grade_type, score) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, grade.getEnrollmentId());
            stmt.setString(2, grade.getGradeType().name());
            stmt.setDouble(3, grade.getScore());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error adding grade", e);
        }
    }

    @Override
    public void updateGrade(Grade grade) {
        String sql = "UPDATE grades SET enrollment_id = ?, grade_type = ?, score = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, grade.getEnrollmentId());
            stmt.setString(2, grade.getGradeType().name());
            stmt.setDouble(3, grade.getScore());
            stmt.setInt(4, grade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error updating grade", e);
        }
    }

    @Override
    public void deleteGrade(int gradeId) {
        String sql = "DELETE FROM grades WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gradeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting grade", e);
        }
    }

    @Override
    public Grade getGradeById(int gradeId) {
        String sql = "SELECT * FROM grades WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gradeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToGrade(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching grade", e);
        }
    }

    @Override
    public List<Grade> getAllGrades() {
        String sql = "SELECT * FROM grades";
        List<Grade> grades = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                grades.add(mapResultSetToGrade(rs));
            }
            return grades;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching grades", e);
        }
    }
    @Override
    public List<Grade> getGradesByEnrollmentId(int enrollmentId) {
        String sql = "SELECT * FROM grades WHERE enrollment_id = ?";
        List<Grade> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollmentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToGrade(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DataAccessException("Error fetching grades", e);
        }
    }

    /**
     * Helper method to map ResultSet to Grade object
     */
    private Grade mapResultSetToGrade(ResultSet rs) throws SQLException {
        Grade grade = new Grade();
        grade.setId(rs.getInt("id"));
        grade.setEnrollmentId(rs.getInt("enrollment_id"));
        grade.setGradeType(GradeType.valueOf(rs.getString("grade_type")));
        grade.setScore(rs.getDouble("score"));
        return grade;
    }
}

