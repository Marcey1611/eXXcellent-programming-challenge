package de.exxcellent.challenge.factory;

import de.exxcellent.challenge.errorhandling.DataParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.exxcellent.challenge.model.FootballDataRecord;

/**
 * Factory class for creating FootballDataRecord instances from an array of tokens.
 * Each token represents a piece of data related to the football record.
 */
public class FootballRecordFactory implements DataRecordFactory<FootballDataRecord> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FootballRecordFactory.class);

    /**
     * Creates a FootballDataRecord from an array of tokens.
     *
     * @param tokens an array of strings containing the team, goals, and goals allowed.
     * @return a new WeatherDataRecord instance.
     */
    @Override
    public FootballDataRecord createFromTokens(final String[] tokens) {
        try {
            final String team = tokens[0];
            final int goals = Integer.parseInt(tokens[1]);
            final int goalsAllowed = Integer.parseInt(tokens[2]);
            return new FootballDataRecord(team, goals, goalsAllowed);
        } catch (final Exception exception) {
            LOGGER.error("Failed to parse weather record: {}", (Object) tokens);
            throw new DataParseException("Invalid football record input.");
        }

    }
}
