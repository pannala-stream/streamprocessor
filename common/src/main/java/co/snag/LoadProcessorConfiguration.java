package co.snag;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.util.Objects.requireNonNull;

public class LoadProcessorConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(LoadProcessorConfiguration.class);
    private final List<Path> configurationFiles;

    public LoadProcessorConfiguration(final List<Path> configurationFiles) {
        requireNonNull(configurationFiles);

        this.configurationFiles = configurationFiles;
    }

    public LoadProcessorConfiguration addConfigurationFile(final Path configurationFile) {
        requireNonNull(configurationFile);
        return addConfigurationFile(ImmutableList.of(configurationFile));
    }

    private LoadProcessorConfiguration addConfigurationFile(Iterable<Path> configurationFiles) {
        requireNonNull(configurationFiles);

        final List<Path> configurationFile = ImmutableList.<Path>builder()
                .addAll(this.configurationFiles)
                .addAll(configurationFiles)
                .build();
        return new LoadProcessorConfiguration(configurationFile);
    }

    public Properties load() {
       final Properties properties = new Properties();
       configurationFiles.forEach(configurationFile -> {
           try {
               properties.load(Files.newInputStream(configurationFile));
               LOG.info("Sucessfully loaded the configuration file {}", configurationFile);
           } catch (NoSuchFileException e) {
               LOG.error("Failed to load the configuration file {}", configurationFile, e);
               throw new LoadProcessorConfigurationException(configurationFile, e);
           }
           catch (IOException e) {
               throw new RuntimeException(e);
           }
       });
       return properties;
    }

    public static LoadProcessorConfiguration create() {
        return new LoadProcessorConfiguration(Collections.emptyList());
    }
}
