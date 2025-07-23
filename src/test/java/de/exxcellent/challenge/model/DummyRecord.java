package de.exxcellent.challenge.model;

/**
 * A simple implementation of the DataRecord interface for testing purposes.
 * This record holds a label and a diff value.
 */
public record DummyRecord(String label, int diff) implements DataRecord {}
