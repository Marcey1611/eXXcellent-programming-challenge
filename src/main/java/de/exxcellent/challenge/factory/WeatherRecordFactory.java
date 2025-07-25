package de.exxcellent.challenge.factory;

import de.exxcellent.challenge.errorhandling.DataParseException;
import de.exxcellent.challenge.model.WeatherDataRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class for creating WeatherDataRecord instances from an array of tokens.
 * Each token represents a piece of data related to the weather record.
 */
public class WeatherRecordFactory implements DataRecordFactory<WeatherDataRecord>{

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherRecordFactory.class);

    /**
     * Creates a WeatherDataRecord from an array of tokens.
     * The first token is the day, the second is the maximum temperature,
     * and the third is the minimum temperature.
     *
     * @param tokens an array of strings containing the day, max temperature, and min temperature.
     * @return a new WeatherDataRecord instance.
     */
    @Override
    public final WeatherDataRecord createFromTokens(final String[] tokens) {
        try {
            final String day = tokens[0];
            final int max = Integer.parseInt(tokens[1]);
            final int min = Integer.parseInt(tokens[2]);
            return new WeatherDataRecord(day, max, min);
        } catch (final Exception exception) {
            LOGGER.error("Failed to parse weather record: {}", (Object) tokens);
            throw new DataParseException("Invalid weather record input.");
        }
    }
}
