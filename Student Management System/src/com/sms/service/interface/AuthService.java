package com.sms.service.interfaces;

import com.sms.exception.AuthenticationException;
import com.sms.model.user.User;

/**
 * AuthService
 *
 * Provides authentication and user session management operations.
 */
public interface AuthService {

    /**
     * Log in a user with username and password.
     *
     * @param username User's username
     * @param password User's password
     * @return Logged-in User object
     * @throws AuthenticationException if credentials are invalid
     */
    User login(String username, String password) throws AuthenticationException;

    /**
     * Register a new user.
     *
     * @param user User object to register
     * @throws AuthenticationException if registration fails (e.g., duplicate username)
     */
    void register(User user) throws AuthenticationException;

    /**
     * Log out the current user.
     */
    void logout();
}

