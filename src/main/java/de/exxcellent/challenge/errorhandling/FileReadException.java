package de.exxcellent.challenge.errorhandling;

/**
 * Exception thrown when there is an error reading a file.
 */
public class FileReadException extends RuntimeException {
    public FileReadException(String message) {
        super(message);
    }
}
