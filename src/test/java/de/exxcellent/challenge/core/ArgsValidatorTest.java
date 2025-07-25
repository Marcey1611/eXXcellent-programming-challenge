package de.exxcellent.challenge.core;

import de.exxcellent.challenge.errorhandling.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ArgsValidator class.
 * This class tests the validation of command line arguments for weather and football data processing.
 */
class ArgsValidatorTest {

    /**
     * Tests that the ArgsValidator correctly validates valid weather arguments.
     */
    @Test
    void shouldValidateCorrectWeatherArguments() {
        String[] args = {"--weather", "weather.csv"};
        assertDoesNotThrow(() -> ArgsValidator.validate(args));
    }

    /**
     * Tests that the ArgsValidator correctly validates valid football arguments.
     */
    @Test
    void shouldValidateCorrectFootballArguments() {
        String[] args = {"--football", "football.csv"};
        assertDoesNotThrow(() -> ArgsValidator.validate(args));
    }

    /**
     * Tests that the ArgsValidator throws a ValidationException for null as argument.
     */
    @Test
    void shouldThrowForNullArguments() {
        assertThrows(ValidationException.class, () -> ArgsValidator.validate(null));
    }

    /**
     * Tests that the ArgsValidator throws a ValidationException for empty arguments.
     */
    @Test
    void shouldThrowForEmptyArguments() {
        assertThrows(ValidationException.class, () -> ArgsValidator.validate(new String[]{}));
    }

    /**
     * Tests that the ArgsValidator throws a ValidationException because of too many arguments.
     */
    @Test
    void shouldThrowForTooManyArguments() {
        String[] args = {"--weather", "weather.csv", "extra.csv"};
        assertThrows(ValidationException.class, () -> ArgsValidator.validate(args));
    }

    /**
     * Tests that the ArgsValidator throws a ValidationException because of invalid mode.
     */
    @Test
    void shouldThrowForInvalidMode() {
        String[] args = {"--climate", "weather.csv"};
        assertThrows(ValidationException.class, () -> ArgsValidator.validate(args));
    }

    /**
     * Tests that the ArgsValidator throws a ValidationException for invalid file name.
     */
    @Test
    void shouldThrowForInvalidFileName() {
        String[] args = {"--weather", "climate.csv"};
        assertThrows(ValidationException.class, () -> ArgsValidator.validate(args));
    }

    /**
     * Tests that the ArgsValidator throws a ValidationException for invalid file path.
     */
    @Test
    void shouldThrowForTraversalSequence() {
        String[] args = {"--weather", "../weather.csv"};
        assertThrows(ValidationException.class, () -> ArgsValidator.validate(args));
    }
}
