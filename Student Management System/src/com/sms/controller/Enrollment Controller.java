package com.sms.model.academic;

import com.sms.model.enums.EnrollmentStatus;

/**
 * Enrollment

 * Represents the enrollment of a student in a course.
 * Stores student ID, course ID, and enrollment status.
 */
public class Enrollment {

    private Student student;
    private int id;             // Auto-generated database ID
    private int studentId;      // Reference to Student.id
    private int courseId;       // Reference to Course.id
    private EnrollmentStatus status;
    private Course course;


    // Constructors

    // Full constructor with DB ID
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }


    public Enrollment(int id, int studentId, int courseId, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    // Constructor without DB ID (for new enrollment before insert)
    public Enrollment(int studentId, int courseId, EnrollmentStatus status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }
    public Enrollment() {
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", status=" + status +
                '}';
    }
}

    // MT 