package de.exxcellent.challenge.errorhandling;

/**
 * Exception thrown when there is an error parsing data.
 */
public class DataParseException extends RuntimeException {
    public DataParseException(String message) {
        super(message);
    }
}

