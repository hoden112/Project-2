package com.sms.model.user;

import com.sms.model.enums.Role;

/**
 * Instructor
 *
 * Represents an instructor user in the system.
 * Inherits common properties from User.
 */
public class Instructor extends User {

    // Additional instructor-specific properties
    private String department;

    // Constructor with ID
    public Instructor(int id, String username, String password, String department) {
        super(id, username, password, Role.INSTRUCTOR);
        this.department = department;
    }

    // Constructor without ID (for new instructor before DB assignment)
    public Instructor(String username, String password, String department) {
        super(username, password, Role.INSTRUCTOR);
        this.department = department;
    }

    // Getter and Setter
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Instructor-specific behavior
    public void assignGrade(StudentUser student, double score) {
        // This is a placeholder logic; real logic will use GradeService
        System.out.println("Assigned grade " + score + " to student " + student.getUsername());
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
