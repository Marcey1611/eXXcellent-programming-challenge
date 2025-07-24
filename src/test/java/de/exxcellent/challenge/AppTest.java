package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    /**
     * This (smoke) test runs the weather path of the application.
     */
    @Test
    void runWeather() {
        App.main("--weather", "weather.csv");
    }

    /**
     * This (smoke) test runs the football path of the application.
     */
    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }
}