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

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    private static final String WEATHER_FILE_NAME = "weather.csv";
    private static final String FOOTBALL_FILE_NAME = "football.csv";

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …

        final ApplicationRunner<WeatherDataRecord> weatherRunner = new ApplicationRunner<>(
                new CsvReader(),
                new WeatherDataParser(new ParserUtils(), new WeatherRecordFactory()),
                new DataAnalyzer<>()
        );

        final ApplicationRunner<FootballDataRecord> footballRunner = new ApplicationRunner<>(
                new CsvReader(),
                new FootballDataParser(new ParserUtils(), new FootballRecordFactory()),
                new DataAnalyzer<>()
        );

        final String dayWithSmallestTempSpread = weatherRunner.run(WEATHER_FILE_NAME);     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        final String teamWithSmallestGoalSpread = footballRunner.run(FOOTBALL_FILE_NAME); // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
