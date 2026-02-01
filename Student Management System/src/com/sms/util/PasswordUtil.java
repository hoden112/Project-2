package com.sms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * PasswordUtil
 *
 * Utility class for hashing and verifying passwords.
 */
public class PasswordUtil {

    /**
     * Hash a plain-text password using SHA-256.
     *
     * @param password The plain password
     * @return The hashed password as a hex string
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert bytes to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Verify if a plain-text password matches a hashed password.
     *
     * @param password       Plain-text password
     * @param hashedPassword Hashed password stored in DB
     * @return true if match, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hashedInput = hashPassword(password);
        return hashedInput.equals(hashedPassword);
    }
}

