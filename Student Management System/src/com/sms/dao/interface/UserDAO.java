package com.sms.dao.interfaces;

import com.sms.model.user.User;
import com.sms.exception.DataAccessException;
import com.sms.model.user.StudentUser;

import java.util.List;

/**
 * UserDAO
 *
 * Interface defining database operations for User entities.
 */
public interface UserDAO {

    /**
     * Add a new user to the database.
     *
     * @param user User object
     */
    void addUser(User user);

    /**
     * Update an existing user in the database.
     *
     * @param user User object
     */
    void updateUser(User user);

    /**
     * Delete a user by their ID.
     *
     * @param userId User ID
     */
    void deleteUser(int userId);

    /**
     * Get a user by their ID.
     *
     * @param userId User ID
     * @return User object or null if not found
     */
    User getUserById(int userId);

    /**
     * Get all users from the database.
     *
     * @return List of User objects
     */
    List<User> getAllUsers();

    /**
     * Find a user by username.
     *
     * @param username Username
     * @return User object or null if not found
     */
    User findByUsername(String username);
    User getUserByUsername(String username) throws DataAccessException;
    User login(String username, String password) throws DataAccessException;


    /**
     * Validate user login credentials.
     *
     * @param username Username
     * @param password Password
     * @return User object if credentials are correct, otherwise null
     */
    User validateLogin(String username, String password);
}

