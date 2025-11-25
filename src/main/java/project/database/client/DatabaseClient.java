package project.database.client;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.config.Config;
import project.utils.SleepUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.EnumMap;
import java.util.Map;

import static project.config.Config.CONFIG;

public enum DatabaseClient {
    DB_CLIENT;
    private static final Logger log = LoggerFactory.getLogger(DatabaseClient.class);
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;
    private static final String url = CONFIG.getCredential("db.host");
    private static final String login = CONFIG.getCredential("db.login");
    private static final String password = CONFIG.getCredential("db.password");


    static {
        config.setJdbcUrl(url);
        config.setUsername(login);
        config.setPassword(password);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(10000);

        dataSource = new HikariDataSource(config);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!dataSource.isClosed()) {
                dataSource.close();
                log.info("HikariCP pool closed.");
            }
        }));
    }

    public void updateConfig() {
        CONFIG.updateConfig(getConfig());
    }

    private Map<Config.ConfigKey, String> getConfig() {
        long sleepTimeSeconds = 1;
        boolean isExit = true;
        Map<Config.ConfigKey, String> config = new EnumMap<>(Config.ConfigKey.class);
        final String query = "SELECT key, value FROM public.configs";

        while (isExit) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    config.put(Config.ConfigKey.fromString(rs.getString("key")), rs.getString("value"));
                }
                isExit = false;
            } catch (Exception e) {
                log.error("getConfig error", e);
                sleepTimeSeconds = sleep(sleepTimeSeconds);
            }
        }
        return config;
    }

    private long sleep(long sleepTime) {
        int sleepTimeLimit = 60;
        if (sleepTime > sleepTimeLimit) {
            SleepUtils.sleepSeconds(sleepTime * 60);
        } else {
            SleepUtils.sleepSeconds(sleepTime * 60);
            long sleepTimeMultiplier = 2;
            sleepTime = sleepTime * sleepTimeMultiplier;
        }
        return sleepTime;
    }
}