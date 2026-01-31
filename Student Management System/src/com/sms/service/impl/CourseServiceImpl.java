package com.sms.service.impl;

import com.sms.dao.interfaces.CourseDAO;
import com.sms.dao.impl.CourseDAOImpl;
import com.sms.exception.ValidationException;
import com.sms.model.academic.Course;
import com.sms.service.interfaces.CourseService;

import java.util.List;

/**
 * CourseServiceImpl
 *
 * Contains business logic, validation, and acts as a bridge
 * between Controller and DAO layers.
 */
public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;

    public CourseServiceImpl() {
        this.courseDAO = new CourseDAOImpl(); // Dependency Injection (manual)
    }

    @Override
    public void addCourse(Course course) {
        validateCourse(course);
        courseDAO.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        if (course.getId() <= 0) {
            throw new ValidationException("Invalid course ID.");
        }
        validateCourse(course);
        courseDAO.updateCourse(course);
    }

    @Override
    public void deleteCourse(int courseId) {
        if (courseId <= 0) {
            throw new ValidationException("Invalid course ID.");
        }
        courseDAO.deleteCourse(courseId);
    }

    @Override
    public Course getCourseById(int courseId) {
        if (courseId <= 0) {
            throw new ValidationException("Invalid course ID.");
        }
        return courseDAO.getCourseById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    /**
     * Business validation rules for courses
     */
    private void validateCourse(Course course) {
        if (course == null) {
            throw new ValidationException("Course cannot be null.");
        }
        if (course.getCourseCode() == null || course.getCourseCode().isBlank()) {
            throw new ValidationException("Course code is required.");
        }
        if (course.getCourseName() == null || course.getCourseName().isBlank()) {
            throw new ValidationException("Course name is required.");
        }
        if (course.getCredit() <= 0) {
            throw new ValidationException("Credit must be greater than zero.");
        }
    }
}
