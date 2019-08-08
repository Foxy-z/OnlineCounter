package fr.thefoxy41.onlinecounter.commons;

import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.*;

public class FileManager {

    /**
     * get YAMLConfiguration from file
     * @param file File
     * @return YAMLConfiguration
     */
    public static YAMLConfiguration getConfiguration(File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            YAMLConfiguration configuration = new YAMLConfiguration();
            configuration.read(inputStream);
            return configuration;
        } catch (IOException | ConfigurationException e) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * save YAMLConfiguration to specified file
     * @param configuration YAMLConfiguration
     * @param file File
     */
    public static void save(YAMLConfiguration configuration, File file) {
        Writer writer = null;
        try {
            createIfEmpty(file);

            writer = new FileWriter(file);
            configuration.write(writer);
            writer.flush();
        } catch (ConfigurationException | IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * create file if doesn't exist
     * @param file File
     * @throws IOException if the creation fails
     */
    public static void createIfEmpty(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

}
