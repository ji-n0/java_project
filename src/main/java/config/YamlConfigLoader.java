package config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlConfigLoader {
    private final Map<String, Object> config;

    public YamlConfigLoader() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.yml")) {
            if (inputStream == null) {
                throw new RuntimeException("Could not find application.yml in resources folder");
            }
            config = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load application.yml", e);
        }
    }

    public String getDatabaseUrl() {
        return (String) ((Map<String, Object>) config.get("database")).get("url");
    }

    public String getDatabaseUsername() {
        return (String) ((Map<String, Object>) config.get("database")).get("username");
    }

    public String getDatabasePassword() {
        return (String) ((Map<String, Object>) config.get("database")).get("password");
    }
}
