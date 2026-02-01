package com.sms.database;

import com.sms.model.user.User;

/**
 * SessionManager
 *
 * This class manages the current logged-in user
 * throughout the application.
 *
 * Uses Singleton pattern.
 */
public class SessionManager {

    // Holds the currently logged-in user
    private static User currentUser;

    // Private constructor to prevent instantiation
    private SessionManager() {
    }

    /**
     * Set the currently logged-in user.
     *
     * @param user Logged-in user
     */
    public static void login(User user) {
        currentUser = user;
    }

    /**
     * Get the currently logged-in user.
     *
     * @return User or null if no user is logged in
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Check if a user is logged in.
     *
     * @return true if logged in
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Clear the session (logout).
     */
    public static void logout() {
        currentUser = null;
    }
}
