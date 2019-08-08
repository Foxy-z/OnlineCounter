package fr.thefoxy41.onlinecounter.commons.database;

import org.apache.commons.configuration2.YAMLConfiguration;

public class DatabaseConfig {
    public static String COUNT_TABLE;

    public static void init(YAMLConfiguration configuration) {
        COUNT_TABLE = configuration.getString("table");
    }
}
