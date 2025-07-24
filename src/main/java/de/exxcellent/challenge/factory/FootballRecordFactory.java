package de.exxcellent.challenge.factory;


import de.exxcellent.challenge.model.FootballDataRecord;

public class FootballRecordFactory implements DataRecordFactory<FootballDataRecord> {

    @Override
    public FootballDataRecord createFromTokens(final String[] tokens) {
        try {
            final String team = tokens[0];
            final int goals = Integer.parseInt(tokens[1]);
            final int goalsAllowed = Integer.parseInt(tokens[2]);
            return new FootballDataRecord(team, goals, goalsAllowed);
        } catch (final Exception exception) {
            throw new RuntimeException("Invalid football line: " + String.join(" ", tokens));
        }

    }
}
