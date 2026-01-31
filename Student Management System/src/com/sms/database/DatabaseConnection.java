package com.sms.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sms",
                        "root",
                        "password"
                );
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed");
        }
    }
}
