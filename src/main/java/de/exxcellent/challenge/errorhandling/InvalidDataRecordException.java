package de.exxcellent.challenge.errorhandling;

/**
 * Exception thrown when a data record is invalid.
 */
public class InvalidDataRecordException extends RuntimeException {
  public InvalidDataRecordException(String message) {
    super(message);
  }
}
