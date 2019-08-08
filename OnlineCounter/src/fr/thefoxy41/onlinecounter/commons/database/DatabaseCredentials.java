package fr.thefoxy41.onlinecounter.commons.database;

import fr.thefoxy41.onlinecounter.commons.FileManager;
import fr.thefoxy41.onlinecounter.commons.database.exceptions.DatabaseConfigurationException;
import org.apache.commons.configuration2.YAMLConfiguration;

import java.io.File;

class DatabaseCredentials {
    private String host;
    private String pass;
    private int port;
    private String user;
    private String dataBaseName;

    // can be better
    DatabaseCredentials(File file) throws DatabaseConfigurationException {
        YAMLConfiguration configuration = FileManager.getConfiguration(file);
        if (configuration == null) {
            throw new DatabaseConfigurationException("Configuration file is not valid");
        }

        if (!configuration.containsKey("host") ||
                !configuration.containsKey("port") ||
                !configuration.containsKey("user") ||
                !configuration.containsKey("password") ||
                !configuration.containsKey("name") ||
                !configuration.containsKey("table")
        ) {
            throw new DatabaseConfigurationException("Parameter forgotten in the configuration file");
        }

        host = configuration.getString("host");
        try {
            port = Integer.parseInt(configuration.getString("port"));
        } catch (NumberFormatException e) {
            throw new DatabaseConfigurationException("Port must be an integer");
        }

        user = configuration.getString("user");
        pass = configuration.getString("password");
        dataBaseName = configuration.getString("name");

        DatabaseConfig.init(configuration);
    }

    String toURI() {
        return "jdbc:mysql://" +
                host +
                ":" +
                port +
                "/" +
                dataBaseName +
                "?useSSL=false";
    }

    String getUser() {
        return user;
    }

    String getPass() {
        return pass;
    }
}
