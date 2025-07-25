package de.exxcellent.challenge.errorhandling;

/**
 * Custom exception class for validation errors.
 * This exception is thrown when validation fails for any input.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
