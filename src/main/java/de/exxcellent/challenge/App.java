package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.factory.FootballRecordFactory;
import de.exxcellent.challenge.factory.WeatherRecordFactory;
import de.exxcellent.challenge.io.CsvReader;
import de.exxcellent.challenge.model.FootballDataRecord;
import de.exxcellent.challenge.model.WeatherDataRecord;
import de.exxcellent.challenge.parser.FootballDataParser;
import de.exxcellent.challenge.parser.ParserUtils;
import de.exxcellent.challenge.parser.WeatherDataParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(final String... args) {

        if (args.length != 2) {
            System.out.println("Usage: --weather <weatherFile.csv> or --football <footballFile.csv>");
            return;
        }

        final String mode = args[0];
        final String fileName = args[1];

        LOGGER.info("Application started with mode '{}'.", mode);
        LOGGER.info("Starting process for file '{}'.", fileName);

        switch (mode) {
            case "--weather" -> {
                final ApplicationRunner<WeatherDataRecord> weatherRunner = new ApplicationRunner<>(
                        new CsvReader(),
                        new WeatherDataParser(new ParserUtils(), new WeatherRecordFactory()),
                        new DataAnalyzer<>()
                );
                final String result = weatherRunner.run(fileName);
                LOGGER.info("Weather analysis result: {}", result);
                System.out.printf("Day with smallest temperature spread : %s%n", result);
            }

            case "--football" -> {
                final ApplicationRunner<FootballDataRecord> footballRunner = new ApplicationRunner<>(
                        new CsvReader(),
                        new FootballDataParser(new ParserUtils(), new FootballRecordFactory()),
                        new DataAnalyzer<>()
                );
                final String result = footballRunner.run(fileName);
                LOGGER.info("Football analysis result: {}.", result);
                System.out.printf("Team with smallest goal spread       : %s%n", result);
            }

            default -> {
                LOGGER.error("Unknown mode received: '{}'.", mode);
                System.out.println("Unknown mode!");
                System.out.println("Usage: --weather <weatherFile.csv> or --football <footballFile.csv>");
            }
        }
    }
}