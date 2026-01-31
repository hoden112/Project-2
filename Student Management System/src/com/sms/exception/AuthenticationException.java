package com.sms.exception;

/**
 * AuthenticationException
 *
 * Custom exception thrown when authentication fails.
 */
public class AuthenticationException extends Exception {

    /**
     * Constructor with message.
     *
     * @param message Error message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
     *
     * @param message Error message
     * @param cause   Throwable cause
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

