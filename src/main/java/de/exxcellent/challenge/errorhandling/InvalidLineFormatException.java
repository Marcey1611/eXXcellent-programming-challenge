package de.exxcellent.challenge.errorhandling;

/**
 * Exception thrown when a line does not conform to the expected format.
 */
public class InvalidLineFormatException extends RuntimeException {
    public InvalidLineFormatException(String message) {
        super(message);
    }
}
