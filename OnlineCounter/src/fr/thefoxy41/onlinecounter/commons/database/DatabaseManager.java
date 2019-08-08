package fr.thefoxy41.onlinecounter.commons.database;

import fr.thefoxy41.onlinecounter.commons.GlobalConfiguration;
import fr.thefoxy41.onlinecounter.commons.database.exceptions.DatabaseConfigurationException;

import java.io.File;

public class DatabaseManager {
    private static DatabaseAccess dataBase;

    public static DatabaseAccess getDataBase() {
        return dataBase;
    }

    public static void initDataBaseConnection() throws DatabaseConfigurationException {
        dataBase = new DatabaseAccess(new DatabaseCredentials(new File(GlobalConfiguration.DB_FILE_PATH, GlobalConfiguration.DB_FILE_NAME)));
        dataBase.initPool();
    }

    public static void closeDataBaseConnection() {
        if (dataBase != null) dataBase.closePool();
    }
}