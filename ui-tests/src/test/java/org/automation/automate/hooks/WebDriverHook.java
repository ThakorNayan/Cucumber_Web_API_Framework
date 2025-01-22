package org.automation.automate.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.automation.automate.common.constants.Application;
import org.automation.automate.common.utils.LoggerUtil;
import org.automation.automate.driver.factory.LocalDriverFactory;
import org.automation.automate.driver.factory.RemoteDriverFactory;
import org.automation.automate.driver.manager.DriverManager;

import java.util.Base64;

import static org.automation.automate.common.utils.CommonUtil.*;
import static org.automation.automate.common.utils.ScreenshotUtil.takeScreenshotAsBase64;

public class WebDriverHook {

    @Before
    public void setup() {
        String browserName = getBrowserName();
        if (isRemoteRun()) {
            DriverManager.setDriver(new RemoteDriverFactory(browserName));
        } else {
            DriverManager.setDriver(new LocalDriverFactory(browserName));
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            LoggerUtil.logError("Exception occurred while quitting driver", e);
        }
    }

    @AfterStep
    public void onScenarioFailureTakeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName() + getFormattedLocalDateTime(Application.YYYY_MM_DD_HH_MM_SS);
            byte[] screenshotBase64 = Base64.getDecoder().decode(takeScreenshotAsBase64());
            if (screenshotBase64 != null) {
                scenario.attach(screenshotBase64, Application.IMAGE_PNG, screenshotName);
            }
        }
    }
}
