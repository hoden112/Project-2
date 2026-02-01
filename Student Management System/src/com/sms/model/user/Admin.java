package com.sms.model.user;

import com.sms.model.enums.Role;

/**
 * Admin
 *
 * Represents an admin user in the system.
 * Inherits common properties from User.
 */
public class Admin extends User {

    // Additional admin-specific properties can be added here
    // Example: permissionLevel, systemSettings, etc.
    private int permissionLevel;

    // Constructor with ID
    public Admin(int id, String username, String password, int permissionLevel) {
        super(id, username, password, Role.ADMIN);
        this.permissionLevel = permissionLevel;
    }

    // Constructor without ID (for new admin before DB assignment)
    public Admin(String username, String password, int permissionLevel) {
        super(username, password, Role.ADMIN);
        this.permissionLevel = permissionLevel;
    }

    // Getter and Setter
    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    // Admin-specific behavior
    public void resetUserPassword(User user, String newPassword) {
        // Simple logic example, in real app hash password first
        user.setPassword(newPassword);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
