package fr.thefoxy41.onlinecounter.commons.database.objects;

import fr.thefoxy41.onlinecounter.commons.database.DatabaseConfig;
import fr.thefoxy41.onlinecounter.commons.database.DatabaseManager;
import fr.thefoxy41.onlinecounter.commons.database.exceptions.DatabaseConnectionException;
import fr.thefoxy41.onlinecounter.commons.database.exceptions.DatabaseQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    private Connection connection;
    private PreparedStatement statement;

    public Query() throws DatabaseConnectionException {
        try {
            this.connection = DatabaseManager.getDataBase().getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("An error occurred while connecting to main database");
        }
    }

    public Query(Connection connection) throws DatabaseConnectionException {
        try {
            if (connection == null || connection.isClosed()) {
                throw new DatabaseConnectionException("An error occurred while connecting to database: connection is null or closed");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("An error occurred while connecting to main database");
        }

        this.connection = connection;
    }

    public void insert(String... args) throws DatabaseQueryException {
        try {
            String arguments = String.join(", ", args);

            statement = connection.prepareStatement("INSERT INTO " + DatabaseConfig.COUNT_TABLE + " VALUES (" + arguments + ")");
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while insert query in main database");
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertOrUpdate(String query) throws DatabaseQueryException {
        try {
            statement = connection.prepareStatement("INSERT INTO " + DatabaseConfig.COUNT_TABLE + " " + query);
        } catch (SQLException e) {
            throw new DatabaseQueryException("Error occurred while insert query in main database");
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
