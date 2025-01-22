package org.automation.automate.driver.factory;

import org.automation.automate.common.customexceptions.AutomationException;
import org.automation.automate.common.utils.CommonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.automation.automate.common.constants.Browser.*;

public class RemoteDriverFactory implements DriverFactory {
    private String browser;

    public RemoteDriverFactory(String browser) {
        this.browser = browser;
    }

    @Override
    public WebDriver createDriver() {
        WebDriver driver;
        try {
            driver = new RemoteWebDriver(new URL(CommonUtil.getRemoteURL()), getBrowserOptions());
        } catch (MalformedURLException e) {
            throw new AutomationException("Remote Driver did not load, Malformed URL", e);
        } catch (Exception e) {
            throw new AutomationException("Remote Driver did not load, check if the remote server is up", e);
        }
        driver.manage().window().maximize();
        return driver;
    }

    private AbstractDriverOptions<?> getBrowserOptions() {
        switch (browser.toLowerCase()) {
            case CHROME -> {
                return CommonUtil.getChromeOptions();
            }
            case EDGE -> {
                browser = MICROSOFT_EDGE;
                return CommonUtil.getEdgeOptions();
            }
            case FIREFOX -> {
                return CommonUtil.getFirefoxOptions();
            }
            default ->
                    throw new IllegalArgumentException("Invalid Browser: " + browser + " please use chrome, edge or firefox as browser type");
        }
    }
}
