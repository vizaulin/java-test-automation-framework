package project.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static project.database.client.DatabaseClient.DB_CLIENT;

public enum Config {
    CONFIG;

    static {
        DB_CLIENT.updateConfig();
    }

    private final Map<String, String> credentials = new HashMap<>();
    private Map<ConfigKey, String> configs = new EnumMap<>(ConfigKey.class);

    Config() {
        String propertiesFileName = "config.properties";
        String configPath = System.getenv("CONFIG_FILE");

        try (InputStream input = configPath != null
                ? new FileInputStream(configPath)
                : ClassLoader.getSystemResourceAsStream(propertiesFileName)) {
            Properties properties = new Properties();
            properties.load(input);

            if (properties.size() != 3) {
                throw new IOException("Wrong number of loaded parameters");
            }

            properties.forEach((k, v) -> credentials.put(k.toString().toLowerCase(), v.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getCredential(String key) {
        return credentials.get(key.toLowerCase());
    }

    public String getConfig(ConfigKey key) {
        return configs.get(key);
    }

    public void updateConfig(Map<ConfigKey, String> configs) {
        this.configs = configs;
    }

    public enum ConfigKey {
        MAIN_PAGE_TITLE("mainPageTitle"),
        TEMPLATES_PAGE_TITLE("templatesPageTitle"),
        MAIN_PAGE_URL("mainPageUrl"),
        TEMPLATES_PAGE_URL("templatesPageUrl"),
        HOME_PAGE_URL("homePageUrl"),
        LOGIN_PAGE_URL("loginPageUrl"),
        HOME_PAGE_TITLE("homePageTitle"),
        LOGIN_PAGE_TITLE("loginPageTitle"),
        VALID_EMAIL("validEmail"),
        VALID_PASSWORD("validPassword"),
        POSTMAN_API_KEY("postmanApiKey"),
        POSTMAN_MOCK_URL("postmanMockUrl"),
        NONE("none");

        private static final Map<String, ConfigKey> stringToEnum = new HashMap<>();

        static {
            for (ConfigKey key : values())
                stringToEnum.put(key.name, key);
        }

        private final String name;

        ConfigKey(String name) {
            this.name = name;
        }

        public static ConfigKey fromString(String name) {
            return stringToEnum.getOrDefault(name, NONE);
        }
    }
}
