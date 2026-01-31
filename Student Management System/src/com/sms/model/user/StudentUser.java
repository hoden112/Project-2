package com.sms.model.user;

import com.sms.model.enums.Role;

/**
 * StudentUser
 *
 * Represents a student user in the system.
 * Inherits common properties from User.
 */
public class StudentUser extends User {

    // Additional student-specific properties
    private String fullName;
    private String email;
    private String phone;

    // Constructor with ID
    public StudentUser(int id, String username, String password, String fullName, String email, String phone) {
        super(id, username, password, Role.STUDENT);
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
    public StudentUser() {
        super(); // calls the User constructor
    }


    // Constructor without ID (for new student before DB assignment)
    public StudentUser(String username, String password, String fullName, String email, String phone) {
        super(username, password, Role.STUDENT);
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "StudentUser{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
