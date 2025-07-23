package de.exxcellent.challenge.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the WeatherDataRecord class.
 * This class tests the functionality of the WeatherDataRecord, including label retrieval and temperature difference calculations.
 */
class WeatherDataRecordTest {

    /**
     * Test that the method getLabel returns the day of the record.
     */
    @Test
    void testGetLabel_returnDay() {
        final WeatherDataRecord record = new WeatherDataRecord("3", 88, 59);
        assertEquals("3", record.getLabel());
    }

    /**
     * Test that the method getDiff returns the difference between max and min temperatures.
     */
    @Test
    void testGetDiff_returnTempDiff() {
        final WeatherDataRecord record = new WeatherDataRecord("3", 88, 59);
        assertEquals(29, record.getDiff());
    }

    /**
     * Test that the method getDiff works correctly with negative temperatures.
     */
    @Test
    void testGetDiff_withNegativeTemperatures() {
        final WeatherDataRecord record = new WeatherDataRecord("1", -5, -20);
        assertEquals(15, record.getDiff());
    }

    /**
     * Test that the method getDiff works correctly with negative and positive temperatures.
     */
    @Test
    void testGetDiff_withNegativeAndPositiveTemperatures() {
        final WeatherDataRecord record = new WeatherDataRecord("1", 5, -20);
        assertEquals(25, record.getDiff());
    }

    /**
     * Test that the constructor throws an exception when the min temperature is greater than the max temperature.
     */
    @Test
    void testConstructor_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new WeatherDataRecord("1", 10, 20));
    }
}
