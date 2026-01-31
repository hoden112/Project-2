package com.sms.service.interfaces;

import com.sms.model.academic.Course;
import java.util.List;

/**
 * CourseService
 *
 * Interface for managing courses.
 * Defines operations that can be performed on courses.
 */
public interface CourseService {

    /**
     * Add a new course.
     *
     * @param course Course to add
     */
    void addCourse(Course course) ;

    /**
     * Update an existing course.
     *
     * @param course Updated course
     */
    void updateCourse(Course course);

    /**
     * Delete a course by its ID.
     *
     * @param courseId ID of the course to delete
     */
    void deleteCourse(int courseId);

    /**
     * Get a course by its ID.
     *
     * @param courseId ID of the course
     * @return Course object if found, null otherwise
     */
    Course getCourseById(int courseId);

    /**
     * Get all courses.
     *
     * @return List of all courses
     */
    List<Course> getAllCourses();
}
