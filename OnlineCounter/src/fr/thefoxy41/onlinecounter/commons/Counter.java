package fr.thefoxy41.onlinecounter.commons;

import fr.thefoxy41.onlinecounter.commons.database.objects.Query;
import fr.thefoxy41.onlinecounter.commons.database.exceptions.DatabaseConnectionException;

import java.sql.SQLException;

public class Counter {
    public static void update(int players) {
        String serverName = GlobalConfiguration.MAIN.getServerName();
        try {
            Query query = new Query();
            query.insertOrUpdate("(name, value) VALUES('" + serverName + "', " + players + ") ON DUPLICATE KEY UPDATE name='" + serverName + "', value=" + players);

            GlobalConfiguration.MAIN.log("Updated online count for " + serverName + ": " + players + " players online");
        } catch (DatabaseConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }
}
