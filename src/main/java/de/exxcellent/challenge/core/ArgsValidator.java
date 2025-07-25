package de.exxcellent.challenge.core;

import de.exxcellent.challenge.errorhandling.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public final class ArgsValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArgsValidator.class);
    private static final List<String> VALID_MODES = List.of("--weather", "--football");
    private static final List<String> VALID_FILES = List.of("weather.csv", "football.csv");

    public static void validate(final String[] args) {
        checkNullAndLength(args);

        final String mode = args[0];
        final String fileName = args[1];

        checkMode(mode);
        checkFileName(fileName);
    }

    private static void checkNullAndLength(final String[] args) {
        if (args == null || args.length != 2) {
            LOGGER.error("Arguments cannot be null or empty");
            throw new ValidationException("Arguments invalid.");
        }
    }

    private static void checkMode(final String mode) {
        if (!VALID_MODES.contains(mode)) {
            LOGGER.error("Unsupported mode: {}", mode);
            throw new ValidationException("Unsupported mode.");
        }
    }

    private static void checkFileName(final String fileName) {
        if (!VALID_FILES.contains(fileName)) {
            LOGGER.error("Unsupported or unsafe filename: {}", fileName);
            throw new ValidationException("Unsupported or unsafe filename.");
        }
    }
}
