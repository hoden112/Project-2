package com.sms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection
 *
 * This class is responsible for creating and providing
 * a single database connection instance for the application.
 *
 * Uses Singleton pattern.
 */
public class DatabaseConnection {

    // SQLite database URL (file-based)
    private static final String DB_URL = "jdbc:sqlite:sms.db";

    // Single instance of Connection
    private static Connection connection;

    // Private constructor to prevent object creation
    private DatabaseConnection() {
    }

    /**
     * Returns a single database connection instance.
     *
     * @return Connection
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }

        return connection;
    }
}