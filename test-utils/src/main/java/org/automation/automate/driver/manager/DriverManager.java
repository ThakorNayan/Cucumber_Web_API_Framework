package org.automation.automate.driver.manager;

import org.automation.automate.common.customexceptions.AutomationException;
import org.automation.automate.driver.factory.DriverFactory;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private DriverManager() {
        throw new IllegalStateException("DriverManager is a utility class");
    }

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new AutomationException("WebDriver instance is null, please check local/remote driver is initialised properly");
        }
        return driver.get();
    }

    public static void setDriver(DriverFactory driverFactory) {
        driver.set(driverFactory.createDriver());
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}

