package de.exxcellent.challenge.model;

/**
 * An interface representing a data record with a label, a min and a max value.
 * This interface is used to define the structure of records that can be analyzed.
 */
public interface DataRecord {

    /**
     * Gets the label of the data record.
     *
     * @return the label of the record
     */
    String label();

    /**
     * Gets the difference value of the data record.
     * This value is calculated as the difference between the max and min value.
     *
     * @return the difference value of the record
     */
    int diff();
}
