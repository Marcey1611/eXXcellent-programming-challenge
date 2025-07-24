package de.exxcellent.challenge.factory;

import de.exxcellent.challenge.model.FootballDataRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the FootballRecordFactory class.
 * Tests the creation of FootballDataRecord objects from string tokens.
 */
public class FootballRecordFactoryTest {

    private final FootballRecordFactory factory = new FootballRecordFactory();

    /**
     * Tests the creation of a FootballDataRecord from valid tokens.
     */
    @Test
    void createFromTokens_validTokens_shouldCreateCorrectRecord() {
        final String[] tokens = {"12", "88", "59"};

        final FootballDataRecord record = factory.createFromTokens(tokens);

        assertEquals("12", record.getLabel());
        assertEquals(29, record.getDiff());
    }

    /**
     * Tests the creation of a FootballDataRecord from tokens with a non-numeric label.
     */
    @Test
    void createFromTokens_nonNumericMax_shouldThrowException() {
        final String[] tokens = {"12", "eighty", "59"};

        assertThrows(RuntimeException.class, () -> factory.createFromTokens(tokens));
    }

    /**
     * Tests the creation of a FootballDataRecord from tokens with a non-numeric diff.
     */
    @Test
    void createFromTokens_tooFewTokens_shouldThrowException() {
        final String[] tokens = {"12", "88"};

        assertThrows(RuntimeException.class, () -> factory.createFromTokens(tokens));
    }

    /**
     * Tests the creation of a FootballDataRecord from null tokens.
     */
    @Test
    void createFromTokens_nullTokens_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> factory.createFromTokens(null));
    }
}
