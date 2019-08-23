package co.snag;

import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.Assert.*;

public class LoadProcessorConfigurationTest {

    private LoadProcessorConfiguration configurationLoader;

    @Before
    public void setup() throws URISyntaxException {
        this.configurationLoader = LoadProcessorConfiguration.create()
                .addConfigurationFile(Path.of(ClassLoader.getSystemResource("dev.properties").toURI()))
                .addConfigurationFile(Path.of(ClassLoader.getSystemResource("up.properties").toURI()));
    }

    @Test
    public void loadTest() {
        // When
        final Properties properties = configurationLoader.load();

        // Then
        assertEquals("bar", properties.getProperty("foo"));
        assertEquals(Integer.valueOf(9092), Integer.valueOf(properties.getProperty("zk-default-port")));

        // check for overridden value
        assertEquals("snag.com", properties.getProperty("snag-domain"));
    }

    @Test(expected = LoadProcessorConfigurationException.class)
    public void loadProcessorConfigurationExceptionTest() {
        configurationLoader.addConfigurationFile(Paths.get("lol.properties"))
                .load();
    }
}