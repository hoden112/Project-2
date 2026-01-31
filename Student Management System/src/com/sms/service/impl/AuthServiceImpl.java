package com.sms.service.impl;

import com.sms.database.SessionManager;
import com.sms.dao.interfaces.UserDAO;
import com.sms.dao.impl.UserDAOImpl;
import com.sms.exception.AuthenticationException;
import com.sms.model.user.User;
import com.sms.service.interfaces.AuthService;
import com.sms.util.PasswordUtil;

/**
 * AuthServiceImpl
 *
 * Implements authentication logic for login, registration, and logout.
 */
public class AuthServiceImpl implements AuthService {

    private final UserDAO userDAO;

    public AuthServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    /**
     * Login a user with username and password.
     *
     * @param username Username
     * @param password Password
     * @return Logged-in User
     */
    @Override
    public User login(String username, String password)throws AuthenticationException {
        try{
            User user = userDAO.getUserByUsername(username);

        if (user == null) {
            throw new AuthenticationException("User not found");
        }

        // Check password using PasswordUtil
        if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        // Set session
        SessionManager.login(user);

        return user;
    }catch (Exception e) {
            throw new AuthenticationException("Error during login: " + e.getMessage());
        }
    }

    /**
     * Register a new user.
     *
     * @param user User to register
     */
    @Override
    public void register(User user)throws AuthenticationException {
        try{
            if (userDAO.getUserByUsername(user.getUsername()) != null) {
            throw new AuthenticationException("Username already exists");
        }

        // Hash the password before saving
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        // Save user
        userDAO.addUser(user);
    }catch (Exception e) {
            throw new AuthenticationException("Error during registration: " + e.getMessage());
        }
    }

    /**
     * Logout the currently logged-in user.
     */
    @Override
    public void logout() {
        SessionManager.logout();
    }
}

