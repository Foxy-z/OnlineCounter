package fr.thefoxy41.onlinecounter.commons;

import fr.thefoxy41.onlinecounter.commons.interfaces.Plugin;
import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.*;

public class GlobalConfiguration {
    public static Plugin MAIN;

    public static String DB_FILE_PATH;
    public static String DB_FILE_NAME = "database.yml";

    public static void init(Plugin plugin) {
        MAIN = plugin;
        DB_FILE_PATH = plugin.getPluginFolder() + "/config";

        try {
            checkDefaultConfig(plugin);
        } catch (IOException | ConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * copy default configuration file from plugin source to plugin folder
     * @param plugin MainClass
     * @throws IOException if the creation fails
     * @throws ConfigurationException if there is an error with the configuration file
     */
    private static void checkDefaultConfig(Plugin plugin) throws IOException, ConfigurationException {
        File configFile = new File(DB_FILE_PATH, DB_FILE_NAME);
        if (!configFile.exists()) {
            plugin.log("Default configuration file has been moved to " + DB_FILE_PATH + "/" + DB_FILE_NAME);

            InputStream stream = GlobalConfiguration.class.getClassLoader().getResourceAsStream(DB_FILE_NAME);
            YAMLConfiguration configuration = new YAMLConfiguration();

            if (stream != null) {
                configuration.read(stream);
                stream.close();
            }

            FileManager.save(configuration, configFile);
        }
    }
}
