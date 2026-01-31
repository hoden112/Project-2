package com.sms.model.academic;
import java.time.LocalDate;
/**
 * Student
 *
 * Represents a student in the academic system.
 * Stores academic-related information like student ID, name, and contact info.
 */
public class Student {

    private int id;             // Auto-generated database ID
    private String studentId;   // Unique student identifier (e.g., roll number)
    private String fullName;
    private String email;
    private String phone;

    private LocalDate dateOfBirth; // add this field

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    // Constructors

    // Full constructor with database ID
    public Student(int id, String studentId, String fullName, String email, String phone) {
        this.id = id;
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
    public Student() {
        // You can leave it empty or set default values
    }

    // Constructor without DB ID (for new student before insert)
    public Student(String studentId, String fullName, String email, String phone, LocalDate dateOfBirth) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

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
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
