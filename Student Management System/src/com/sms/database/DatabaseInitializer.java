package com.sms.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * DatabaseInitializer
 *
 * This class is responsible for:
 * - Creating database tables if they do not exist
 * - Inserting default system data (admin user)
 *
 * It runs once when the application starts.
 */
public class DatabaseInitializer {

    /**
     * Initialize database tables and default data.
     */
    public static void initialize() {

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // USERS TABLE
            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                );
            """;

            // STUDENTS TABLE
            String createStudentsTable = """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id TEXT UNIQUE NOT NULL,
                    full_name TEXT NOT NULL,
                    email TEXT,
                    phone TEXT
                );
            """;

            // COURSES TABLE
            String createCoursesTable = """
                CREATE TABLE IF NOT EXISTS courses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    course_code TEXT UNIQUE NOT NULL,
                    course_name TEXT NOT NULL,
                    credit INTEGER NOT NULL
                );
            """;

            // ENROLLMENTS TABLE
            String createEnrollmentsTable = """
                CREATE TABLE IF NOT EXISTS enrollments (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id INTEGER NOT NULL,
                    course_id INTEGER NOT NULL,
                    status TEXT NOT NULL,
                    FOREIGN KEY (student_id) REFERENCES students(id),
                    FOREIGN KEY (course_id) REFERENCES courses(id)
                );
            """;

            // GRADES TABLE
            String createGradesTable = """
                CREATE TABLE IF NOT EXISTS grades (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    enrollment_id INTEGER NOT NULL,
                    grade_type TEXT NOT NULL,
                    score REAL NOT NULL,
                    FOREIGN KEY (enrollment_id) REFERENCES enrollments(id)
                );
            """;

            // Execute table creation
            statement.execute(createUsersTable);
            statement.execute(createStudentsTable);
            statement.execute(createCoursesTable);
            statement.execute(createEnrollmentsTable);
            statement.execute(createGradesTable);

            // Insert default admin user (only if not exists)
            String insertAdmin = """
                INSERT OR IGNORE INTO users (username, password, role)
                VALUES ('admin', 'admin123', 'ADMIN');
            """;

            statement.execute(insertAdmin);

            System.out.println("[ERROR] Database initialized successfully.");

        } catch (SQLException e) {
            System.err.println("[ERROR] Database initialization failed: " + e.getMessage());
        }
    }
}
