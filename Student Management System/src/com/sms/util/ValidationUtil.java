package com.sms.util;

import java.util.regex.Pattern;

/**
 * ValidationUtil
 *
 * Utility class for validating form inputs.
 */
public class ValidationUtil {

    // Regex patterns
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[0-9]{7,15}$");

    private ValidationUtil() {
        // private constructor to prevent instantiation
    }

    /**
     * Checks if a string is not null and not empty.
     *
     * @param value Input string
     * @return true if valid
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Validates email format.
     *
     * @param email Input email
     * @return true if valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validates phone number format.
     *
     * @param phone Input phone
     * @return true if valid
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Validates password strength (example: at least 6 characters).
     *
     * @param password Input password
     * @return true if valid
     */
    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return password.length() >= 6; // simple rule, can add more
    }
}

