package com.sms.dao.impl;

import com.sms.dao.interfaces.UserDAO;
import com.sms.database.DatabaseConnection;
import com.sms.exception.DataAccessException;
import com.sms.model.user.StudentUser;
import com.sms.model.user.User;
import com.sms.model.enums.Role;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import java.sql.*;

/**
 * UserDAOImpl
 *
 * Implements UserDAO interface.
 * Handles database operations for User entities.
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(User user) throws DataAccessException {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().name());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error adding user", e);
        }
    }

    @Override
    public void updateUser(User user) throws DataAccessException{
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().name());
            stmt.setInt(4, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error updating user", e);
        }
    }

    @Override
    public void deleteUser(int userId) throws DataAccessException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting user", e);
        }
    }

    @Override
    public User getUserById(int userId) throws DataAccessException{
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching user", e);
        }
    }

    @Override
    public User getUserByUsername(String username) throws DataAccessException{
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching user by username", e);
        }
    }
    @Override
    public User findByUsername(String username) throws DataAccessException {
        return getUserByUsername(username);
    }

    /**
     * Verify login credentials
     *
     * @param username String
     * @param password String
     * @return User if credentials match, null otherwise
     */
    @Override

    public User login(String username, String password) throws DataAccessException{
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    @Override
    public List<User> getAllUsers() throws DataAccessException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching all users", e);
        }

    }
    @Override
    public User validateLogin(String username, String password) throws DataAccessException {
        return login(username, password);
    }



    /**
     * Helper method to map ResultSet to User object
     */
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new StudentUser();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    }
}

