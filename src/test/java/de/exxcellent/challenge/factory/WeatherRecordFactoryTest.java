package de.exxcellent.challenge.factory;

import de.exxcellent.challenge.errorhandling.DataParseException;
import de.exxcellent.challenge.model.WeatherDataRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the WeatherRecordFactory class.
 * Tests the creation of WeatherDataRecord objects from string tokens.
 */
class WeatherRecordFactoryTest {

    private final WeatherRecordFactory factory = new WeatherRecordFactory();

    /**
     * Tests the creation of a WeatherDataRecord from valid tokens.
     */
    @Test
    void createFromTokens_validTokens_shouldCreateCorrectRecord() {
        final String[] tokens = {"12", "88", "59"};

        final WeatherDataRecord record = factory.createFromTokens(tokens);

        assertEquals("12", record.getLabel());
        assertEquals(29, record.getDiff());
    }

    /**
     * Tests the creation of a WeatherDataRecord from tokens with a non-numeric label.
     */
    @Test
    void createFromTokens_nonNumericMax_shouldThrowException() {
        final String[] tokens = {"12", "eighty", "59"};

        assertThrows(DataParseException.class, () -> factory.createFromTokens(tokens));
    }

    /**
     * Tests the creation of a WeatherDataRecord from tokens with a non-numeric diff.
     */
    @Test
    void createFromTokens_tooFewTokens_shouldThrowException() {
        final String[] tokens = {"12", "88"};

        assertThrows(DataParseException.class, () -> factory.createFromTokens(tokens));
    }

    /**
     * Tests the creation of a WeatherDataRecord from null tokens.
     */
    @Test
    void createFromTokens_nullTokens_shouldThrowException() {
        assertThrows(DataParseException.class, () -> factory.createFromTokens(null));
    }
}
