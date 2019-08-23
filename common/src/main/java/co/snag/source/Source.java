package co.snag.source;

import java.util.Properties;

public interface Source {
    Properties getSourceProperties();

    String getName();
}
