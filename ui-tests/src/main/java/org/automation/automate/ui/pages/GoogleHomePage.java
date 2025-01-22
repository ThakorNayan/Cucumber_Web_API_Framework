package org.automation.automate.ui.pages;

import org.automation.automate.common.constants.UIWaits;
import org.automation.automate.common.utils.CommonUtil;
import org.automation.automate.common.utils.PropertyUtil;
import org.automation.automate.common.utils.WebDriverUtil;
import org.automation.automate.driver.manager.DriverManager;
import org.automation.automate.ui.testdata.Credentials;
import org.automation.automate.ui.testdata.UserRoles;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.automation.automate.common.utils.LoggerUtil.getLogger;
import static org.automation.automate.common.utils.LoggerUtil.logInfo;

public class GoogleHomePage {
	private final WebDriverUtil webDriverUtil;
	private final Credentials credentials;
	@FindBy(name = "q")
	WebElement searchBox;

	private static final String URL = PropertyUtil.getProperty(CommonUtil.getEnvironment(), "app.url");

	public GoogleHomePage() {
		this.webDriverUtil = new WebDriverUtil(DriverManager.getDriver());
		credentials = new Credentials();
		PageFactory.initElements(DriverManager.getDriver(), this);
		webDriverUtil.waitForPageToLoad(UIWaits.DEFAULT_TIMEOUT);
		webDriverUtil.setTimeouts(UIWaits.DEFAULT_TIMEOUT);
		getLogger(getClass()).info("GoogleHomepage initialized");
	}

	public void open() {
		webDriverUtil.get(URL);
		logInfo("Opened Google Home : " + URL);
	}

	public void searchFor(String keyword) {
		webDriverUtil.sendKeys(searchBox, keyword);
		webDriverUtil.submit(searchBox);
		logInfo("Searched for: " + keyword);
	}

	public void testUserLogin() {
		logInfo("test user " + credentials.getUsername(UserRoles.TEST_USER.value));
		logInfo("test password " + credentials.getPassword(UserRoles.TEST_USER.value));
	}

	public void supervisorLogin() {
		logInfo("supervisor user " + credentials.getUsername(UserRoles.SUPERVISOR.value));
		logInfo("supervisor password " + credentials.getPassword(UserRoles.SUPERVISOR.value));
	}
}

