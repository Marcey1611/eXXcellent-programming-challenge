package de.exxcellent.challenge.errorhandling;

/**
 * Exception thrown when column headers from the csv file cannot be found.
 */
public class InvalidHeaderException extends RuntimeException {
    public InvalidHeaderException(String message) {
        super(message);
    }
}
