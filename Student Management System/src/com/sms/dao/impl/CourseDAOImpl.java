package com.sms.dao.impl;

import com.sms.dao.interfaces.CourseDAO;
import com.sms.database.DatabaseConnection;
import com.sms.exception.DataAccessException;
import com.sms.model.academic.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (course_code, course_name, credit) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCredit());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error adding course", e);
        }
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_code=?, course_name=?, credit=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCredit());
            stmt.setInt(4, course.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error updating course", e);
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error deleting course", e);
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToCourse(rs);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error fetching course by ID", e);
        }

        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error fetching all courses", e);
        }

        return courses;
    }

    // Helper method to convert ResultSet → Course object
    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setCourseCode(rs.getString("course_code"));
        course.setCourseName(rs.getString("course_name"));
        course.setCredit(rs.getInt("credit"));
        return course;
    }
}

