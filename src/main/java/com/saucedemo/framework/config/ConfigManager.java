package com.saucedemo.framework.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ConfigManager {
    private static final String CONFIG_RESOURCE = "config/config.yaml";
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());
    private static final Map<String, String> CONFIG = loadConfig();

    private ConfigManager() {
    }

    public static String get(String key) {
        return get(key, null);
    }

    public static String get(String key, String defaultValue) {
        String systemValue = normalize(System.getProperty(key));
        if (systemValue != null) {
            return systemValue;
        }

        String configuredValue = normalize(CONFIG.get(key));
        return configuredValue != null ? configuredValue : defaultValue;
    }

    public static String getBaseUrl() {
        return get("baseUrl", "https://www.saucedemo.com/");
    }

    public static String getBrowser() {
        return get("browser", "chrome").trim().toLowerCase();
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless", "false"));
    }

    public static Duration getExplicitTimeout() {
        String timeoutSeconds = get("timeoutSeconds", "15");
        try {
            return Duration.ofSeconds(Long.parseLong(timeoutSeconds));
        } catch (NumberFormatException exception) {
            throw new IllegalStateException("Invalid timeoutSeconds value: " + timeoutSeconds, exception);
        }
    }

    private static Map<String, String> loadConfig() {
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_RESOURCE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Config resource not found: " + CONFIG_RESOURCE);
            }

            Map<String, Object> rawConfig = YAML_MAPPER.readValue(inputStream, new TypeReference<Map<String, Object>>() {
            });
            if (rawConfig == null || rawConfig.isEmpty()) {
                return Map.of();
            }

            Map<String, String> normalizedConfig = new HashMap<>();
            rawConfig.forEach((key, value) -> {
                if (key != null && value != null) {
                    normalizedConfig.put(key, String.valueOf(value).trim());
                }
            });

            return Collections.unmodifiableMap(normalizedConfig);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load " + CONFIG_RESOURCE, exception);
        }
    }

    private static String normalize(String value) {
        if (value == null) {
            return null;
        }

        String trimmedValue = value.trim();
        return trimmedValue.isEmpty() ? null : trimmedValue;
    }
}

