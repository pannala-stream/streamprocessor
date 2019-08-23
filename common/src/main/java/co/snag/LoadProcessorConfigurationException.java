package co.snag;

import java.nio.file.Path;

public class LoadProcessorConfigurationException extends RuntimeException {

    public LoadProcessorConfigurationException(final Path configurationFile, final Throwable cause) {
        super(String.format("Cannot load the configuration file: %s", configurationFile), cause);
    }
}
