package com.sms.model.user;

import com.sms.model.enums.Role;

/**
 * User
 *
 * Base class representing a user in the system.
 * Implements common properties for all user types.
 */
public abstract class User {

    // Unique ID for the user (database primary key)
    private int id;

    // Username for login
    private String username;

    // Password (store hashed in real apps)
    private String password;

    // Role of the user (ADMIN, INSTRUCTOR, STUDENT)
    private Role role;

    // Constructor

    public User(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User() {
    }

    // Overloaded constructor without ID (used before DB assignment)
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    private String fullName;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    // Getters and Setters (Encapsulation)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Common behavior (can be overridden by subclasses)
    public boolean hasRole(Role role) {
        return this.role == role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
