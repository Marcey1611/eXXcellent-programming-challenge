package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.factory.FootballRecordFactory;
import de.exxcellent.challenge.factory.WeatherRecordFactory;
import de.exxcellent.challenge.io.CsvReader;
import de.exxcellent.challenge.model.DataRecord;
import de.exxcellent.challenge.parser.FootballDataParser;
import de.exxcellent.challenge.parser.ParserUtils;
import de.exxcellent.challenge.parser.WeatherDataParser;

/**
 * Factory class to create instances of ApplicationRunner based on the provided mode.
 */
public class ApplicationRunnerFactory {

    /**
     * Creates an ApplicationRunner based on the specified mode.
     *
     * @param mode the mode to determine which ApplicationRunner to create.
     * @return an instance of ApplicationRunner configured for the specified mode.
     * @throws IllegalArgumentException if the mode is not supported.
     */
    public static ApplicationRunner<? extends DataRecord> createRunner(String mode) {
        return switch (mode) {
            case "--weather" -> new ApplicationRunner<>(
                    new CsvReader(),
                    new WeatherDataParser(new ParserUtils(), new WeatherRecordFactory()),
                    new DataAnalyzer<>()
            );
            case "--football" -> new ApplicationRunner<>(
                    new CsvReader(),
                    new FootballDataParser(new ParserUtils(), new FootballRecordFactory()),
                    new DataAnalyzer<>()
            );
            default -> throw new IllegalArgumentException("Unsupported mode!");
        };
    }
}

