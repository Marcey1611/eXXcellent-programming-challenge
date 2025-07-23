package de.exxcellent.challenge.dummies;

import de.exxcellent.challenge.model.DataRecord;

/**
 * A simple implementation of the DataRecord interface for testing purposes.
 * This record holds a label and a diff value.
 */
public record DummyRecord(String label, int diff) implements DataRecord {

    /**
     * Returns the label of the label.
     *
     * @return the label of the record
     */
    @Override
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the diff value of the record.
     *
     * @return the diff value of the record
     */
    @Override
    public int getDiff() {
        return this.diff;
    }
}
