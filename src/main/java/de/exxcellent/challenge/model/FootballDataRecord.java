package de.exxcellent.challenge.model;

/**
 * FootballDataRecord class that represents a record of data for a specific team.
 * It implements the DataRecord interface, providing methods to get the label (day) and the temperature difference.
 */
public class FootballDataRecord implements DataRecord {
    private final String teamName;
    private final int goals;
    private final int goalsAllowed;

    /**
     * Constructor for FootballDataRecord.
     *
     * @param teamName the day of the record
     * @param goals the maximum temperature for the day
     * @param goalsAllowed the minimum temperature for the day
     */
    public FootballDataRecord(final String teamName, int goals, int goalsAllowed) {
        this.teamName = teamName;
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;
    }

    /**
     * Gets the team of the record.
     *
     * @return the team of the record
     */
    @Override
    public String getLabel() {
        return teamName;
    }

    /**
     * Gets the goal difference for the record.
     *
     * @return the goal between maxTemp and minTemp
     */
    @Override
    public int getDiff() {
        return Math.abs(goals - goalsAllowed);
    }
}
