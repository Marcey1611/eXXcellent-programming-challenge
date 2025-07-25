package de.exxcellent.challenge.io;

import de.exxcellent.challenge.errorhandling.FileReadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * CsvReader is responsible for reading CSV files.
 * It provides a method to read the contents of a file and return it as a list of strings.
 */
public class CsvReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvReader.class);
    private static final String FILE_NAME_EXTENSION = "de/exxcellent/challenge/%s";

    /**
     * Reads the contents of a CSV file and returns it as a list of strings.
     *
     * @param fileName the name of the file to read.
     * @return a list of strings representing the lines in the file.
     * @throws RuntimeException if there is an error reading the file.
     */
    public List<String> readFile(final String fileName) {
        final String extendedFileName = String.format(FILE_NAME_EXTENSION, fileName);

        try {
            final URL resource = getClass().getClassLoader().getResource(extendedFileName);

            if (resource == null) {
                LOGGER.error("Resource not found for file: {}", extendedFileName);
                throw new FileReadException("File not found.");
            }

            final Path filePath = Paths.get(resource.toURI());
            final List<String> lines = Files.readAllLines(filePath);

            if (lines.isEmpty()) {
                LOGGER.error("File is empty: {}", fileName);
                throw new FileReadException("Provided file is empty.");
            }

            return lines;

        } catch (URISyntaxException | IOException e) {
            LOGGER.error("Error while reading file: {}", fileName, e);
            throw new FileReadException("Could not read provided file.");
        }
    }
}
