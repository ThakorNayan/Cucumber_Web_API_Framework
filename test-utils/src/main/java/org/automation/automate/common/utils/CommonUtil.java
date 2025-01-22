package org.automation.automate.common.utils;

import org.automation.automate.common.constants.Application;
import org.automation.automate.common.constants.Browser;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {


    private CommonUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getValueForSystemVariable(String variableName) {
        return System.getProperty(variableName) != null ? System.getProperty(variableName) : System.getenv(variableName);
    }

    public static String getBrowserName() {
        String browserName = getValueForSystemVariable(Browser.BROWSER_KEY);
        return ifEmptyReadFromLocalProperty(browserName, Browser.BROWSER_KEY);
    }

    public static String getEnvironment() {
        String environment = getValueForSystemVariable(Application.ENVIRONMENT_KEY);
        return ifEmptyReadFromLocalProperty(environment, Application.ENVIRONMENT_KEY);
    }

    public static boolean isRemoteRun() {
        String remoteRun = getValueForSystemVariable(Application.REMOTE_RUN_KEY);
        remoteRun = ifEmptyReadFromLocalProperty(remoteRun, Application.REMOTE_RUN_KEY);
        return remoteRun.equalsIgnoreCase(Application.TRUE);

    }

    public static String ifEmptyReadFromLocalProperty(String value, String key) {
        if (value == null || value.isEmpty()) {
            value = PropertyUtil.getProperty(Application.LOCAL_PROPERTY_FILE, key);
        }
        return value;
    }

    public static String ifEmptyReadFromRemoteProperty(String value, String key) {
        if (value == null || value.isEmpty()) {
            value = PropertyUtil.getProperty(Application.REMOTE_PROPERTY_FILE, key);
        }
        return value;
    }

    public static String getFormattedLocalDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getRemoteURL() {
        String browserName = getValueForSystemVariable(Application.URL_KEY);
        return ifEmptyReadFromRemoteProperty(browserName, Application.URL_KEY);
    }

    public static EdgeOptions getEdgeOptions() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        EdgeOptions edgeOptions = new EdgeOptions();
        capabilities.setBrowserName(Browser.EDGE);
        if (isRemoteRun()) {
            edgeOptions.setCapability("se:recordVideo", true);
        }
        edgeOptions.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
        return edgeOptions;
    }

    public static ChromeOptions getChromeOptions() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        capabilities.setBrowserName(Browser.CHROME);
        if (isRemoteRun()) {
            chromeOptions.setCapability("se:recordVideo", true);
        }
        chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        capabilities.setBrowserName(Browser.FIREFOX);
        if (isRemoteRun()) {
            firefoxOptions.setCapability("se:recordVideo", true);
        }
        firefoxOptions.setCapability(EdgeOptions.CAPABILITY, firefoxOptions);
        return firefoxOptions;
    }

}
