package de.exxcellent.challenge.model;

public class FootballDataRecord implements DataRecord {
    private final String teamName;
    private final int goals;
    private final int goalsAllowed;

    public FootballDataRecord(String teamName, int goals, int goalsAllowed) {
        this.teamName = teamName;
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;
    }

    @Override
    public String getLabel() {
        return teamName;
    }

    @Override
    public int getDiff() {
        return Math.abs(goals - goalsAllowed);
    }
}
