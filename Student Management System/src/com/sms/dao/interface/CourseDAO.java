package com.sms.dao.interfaces;

import com.sms.model.academic.Course;
import java.util.List;

/**
 * CourseDAO
 *
 * Interface defining database operations for Course entities.
 */
public interface CourseDAO {

    /**
     * Add a new course to the database.
     *
     * @param course Course object
     */
    void addCourse(Course course);

    /**
     * Update an existing course in the database.
     *
     * @param course Course object
     */
    void updateCourse(Course course);

    /**
     * Delete a course by its ID.
     *
     * @param courseId Course ID
     */
    void deleteCourse(int courseId);

    /**
     * Get a course by its ID.
     *
     * @param courseId Course ID
     * @return Course object or null if not found
     */
    Course getCourseById(int courseId);

    /**
     * Get all courses from the database.
     *
     * @return List of Course objects
     */
    List<Course> getAllCourses();
