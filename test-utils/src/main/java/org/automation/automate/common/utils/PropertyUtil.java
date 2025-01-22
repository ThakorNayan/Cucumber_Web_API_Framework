package org.automation.automate.common.utils;

import org.automation.automate.common.constants.Directory;
import org.automation.automate.common.customexceptions.AutomationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyUtil {
    private static final String PROPERTIES_EXTENSION = ".properties";
    private static final ThreadLocal<Properties> propertiesThreadLocal = new ThreadLocal<>();

    private PropertyUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized Properties getProperties(String propertyFileName) {
        String propertyFile = Directory.CONFIG + propertyFileName + PROPERTIES_EXTENSION;
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(propertyFile))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new AutomationException("Failed to load properties from file : " + propertyFile, e);
        }
        set(properties);
        return get();
    }

    public static synchronized Properties getProperties(String directory, String propertyFileName) {
        String propertyFile = directory + propertyFileName + PROPERTIES_EXTENSION;
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(propertyFile))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new AutomationException("Failed to load properties from file : " + propertyFile, e);
        }
        set(properties);
        return get();
    }

    private static Properties get() {
        Properties properties = propertiesThreadLocal.get();
        if (properties != null) {
            return properties;
        }
        throw new AutomationException("Properties object is null");
    }

    private static void set(Properties properties) {
        propertiesThreadLocal.set(properties);
    }

    private static void remove() {
        propertiesThreadLocal.remove();
    }

    public static String getProperty(String propertyFileName, String key) {
        return getProperties(propertyFileName).getProperty(key);
    }

    public static String getProperty(String propertyDirectory, String propertyFileName, String key) {
        return getProperties(propertyDirectory, propertyFileName).getProperty(key);
    }

    public static void setProperty(String propertyFileName, String key, String value) {
        Properties properties = getProperties(propertyFileName);
        properties.setProperty(key, value);
        String propertyFile = Directory.CONFIG + propertyFileName + PROPERTIES_EXTENSION;
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(propertyFile))) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            throw new AutomationException("Failed to load properties from file : " + propertyFile, e);
        }
    }
}
