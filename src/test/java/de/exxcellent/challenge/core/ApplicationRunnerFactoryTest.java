package de.exxcellent.challenge.core;

import de.exxcellent.challenge.model.DataRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ApplicationRunnerFactory.
 * This class tests the creation of application runners based on the provided mode.
 */
class ApplicationRunnerFactoryTest {

    /**
     * Tests the creation of an ApplicationRunner based on the provided mode.
     */
    @Test
    void shouldReturnWeatherApplicationRunnerWhenModeIsWeather() {
        final ApplicationRunner<? extends DataRecord> runner = ApplicationRunnerFactory.createRunner("--weather");

        assertNotNull(runner);
    }

    /**
     * Tests the creation of an ApplicationRunner based on the provided mode.
     */
    @Test
    void shouldReturnFootballApplicationRunnerWhenModeIsFootball() {
        final ApplicationRunner<? extends DataRecord> runner = ApplicationRunnerFactory.createRunner("--football");

        assertNotNull(runner);
    }

    /**
     * Tests if an exception is thrown when an invalid mode is provided.
     */
    @Test
    void shouldThrowExceptionForInvalidMode() {
        assertThrows(IllegalArgumentException.class,
                () -> ApplicationRunnerFactory.createRunner("--invalid")
        );
    }
}
