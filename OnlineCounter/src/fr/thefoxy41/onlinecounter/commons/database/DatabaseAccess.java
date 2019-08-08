package fr.thefoxy41.onlinecounter.commons.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.thefoxy41.onlinecounter.commons.GlobalConfiguration;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseAccess {
    private DatabaseCredentials credentials;
    private HikariDataSource hikariDataSource;

    DatabaseAccess(DatabaseCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * init HikariCP with plugin default parameters
     */
    private void setUpHikariCP() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(credentials.toURI());
        hikariConfig.setUsername(credentials.getUser());
        hikariConfig.setPassword(credentials.getPass());

        hikariConfig.setMaximumPoolSize(8);
        hikariConfig.setMaxLifetime(30_000L);
        hikariConfig.setIdleTimeout(30_000L);
        hikariConfig.setLeakDetectionThreshold(10_000L);
        hikariConfig.setConnectionTimeout(10_000L);
        hikariConfig.setPoolName(GlobalConfiguration.MAIN.getServerName());
        hikariConfig.setMinimumIdle(2);

        hikariConfig.addDataSourceProperty("autoReconnect", true);
        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
        hikariConfig.addDataSourceProperty("cacheResultSetMetadata", true);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public void initPool() {
        setUpHikariCP();
    }

    public void closePool() {
        this.hikariDataSource.close();
    }

    public Connection getConnection() throws SQLException {
        return this.hikariDataSource.getConnection();
    }
}