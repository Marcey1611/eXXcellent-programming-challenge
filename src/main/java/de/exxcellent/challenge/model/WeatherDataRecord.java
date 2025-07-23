package de.exxcellent.challenge.model;

/**
 * WeatherDataRecord class that represents a record of weather data for a specific day.
 * It implements the DataRecord interface, providing methods to get the label (day) and the temperature difference.
 */
public class WeatherDataRecord implements DataRecord{

    private final String day;
    private final int maxTemp;
    private final int minTemp;

    /**
     * Constructor for WeatherDataRecord.
     *
     * @param day the day of the record
     * @param maxTemp the maximum temperature for the day
     * @param minTemp the minimum temperature for the day
     */
    public WeatherDataRecord(final String day, final int maxTemp, final int minTemp) {
        if (minTemp > maxTemp) {
            //TODO: Implement custom error handling
            throw new IllegalArgumentException("minTemp cannot be greater than maxTemp");
        }
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    /**
     * Gets the day of the record.
     *
     * @return the day of the record
     */
    @Override
    final public String getLabel() {
        return this.day;
    }

    /**
     * Gets the temperature difference for the record.
     *
     * @return the difference between maxTemp and minTemp
     */
    @Override
    final public int getDiff() {
        return this.maxTemp - this.minTemp;
    }
}
