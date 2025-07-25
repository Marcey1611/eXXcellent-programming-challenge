package de.exxcellent.challenge.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the FootballDataRecord class.
 * This class tests the functionality of the FootballDataRecord.
 */
public class FootballDataRecordTest {

    /**
     * Test that the method getLabel returns the day of the record.
     */
    @Test
    void testGetLabel_returnDay() {
        final FootballDataRecord record = new FootballDataRecord("3", 88, 59);
        assertEquals("3", record.getLabel());
    }

    /**
     * Test that the method getDiff returns the difference between max and min temperatures.
     */
    @Test
    void testGetDiff_returnTempDiff() {
        final FootballDataRecord record = new FootballDataRecord("3", 88, 59);
        assertEquals(29, record.getDiff());
    }

    /**
     * Test that the method getDiff works correctly with negative temperatures.
     */
    @Test
    void testGetDiff_withNegativeTemperatures() {
        final FootballDataRecord record = new FootballDataRecord("1", -5, -20);
        assertEquals(15, record.getDiff());
    }

    /**
     * Test that the method getDiff works correctly with negative and positive temperatures.
     */
    @Test
    void testGetDiff_withNegativeAndPositiveTemperatures() {
        final FootballDataRecord record = new FootballDataRecord("1", 5, -20);
        assertEquals(25, record.getDiff());
    }
}
