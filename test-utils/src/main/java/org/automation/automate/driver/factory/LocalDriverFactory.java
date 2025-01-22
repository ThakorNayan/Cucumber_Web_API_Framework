package org.automation.automate.driver.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.automation.automate.common.constants.Browser.*;
import static org.automation.automate.common.constants.Directory.*;

public class LocalDriverFactory implements DriverFactory {
    private final String browser;

    public LocalDriverFactory(String browser) {
        this.browser = browser;
    }

    @Override
    public WebDriver createDriver() {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case CHROME -> {
//                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            }
            case EDGE -> {
//                System.setProperty("webdriver.edge.driver", EDGE_DRIVER);
                driver = new EdgeDriver();
                driver.manage().window().maximize();
            }
            case FIREFOX -> {
//                System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            default ->
                    throw new IllegalArgumentException("Invalid Browser: " + browser + " please use chrome, edge or firefox as browser type");
        }
        return driver;
    }
}
