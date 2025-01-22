package org.automation.automate.ui.pages;

import org.automation.automate.common.constants.UIWaits;
import org.automation.automate.common.utils.CommonUtil;
import org.automation.automate.common.utils.PropertyUtil;
import org.automation.automate.common.utils.WebDriverUtil;
import org.automation.automate.driver.manager.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.automation.automate.common.utils.LoggerUtil.getLogger;
import static org.automation.automate.common.utils.LoggerUtil.logInfo;

public class StackoverflowPage {
	private final WebDriverUtil webDriverUtil;
	@FindBy(xpath = "//a[text()='Log in']")
	WebElement loginButton;
	@FindBy(xpath = "//input[@name='q']")
	WebElement searchInput;
	@FindBy(tagName = "h1")
	WebElement headerOne;

	private static final String URL = PropertyUtil.getProperty(CommonUtil.getEnvironment(), "app2.url");

	public StackoverflowPage() {
		this.webDriverUtil = new WebDriverUtil(DriverManager.getDriver());
		PageFactory.initElements(DriverManager.getDriver(), this);
		webDriverUtil.waitForPageToLoad(UIWaits.DEFAULT_TIMEOUT);
		webDriverUtil.setTimeouts(UIWaits.DEFAULT_TIMEOUT);
		getLogger(getClass()).info("StackOverflowPage initialized");
	}

	public void open() {
		webDriverUtil.get(URL);
		logInfo("Opened Stackoverflow Home : " + URL);
	}

	public boolean isLoginButtonDisplayed() {
		logInfo("checking is login button displayed");
		return webDriverUtil.isDisplayed(loginButton);
	}

	public void search(String keyword) {
		webDriverUtil.sendKeys(searchInput, keyword);
		webDriverUtil.submit(searchInput);
		webDriverUtil.hardWait(3);
		logInfo("Searching for " + keyword);
	}

	public String getHeaderText() {
		String headerText = webDriverUtil.getText(headerOne);
		logInfo("header text " + headerText);
		return headerText;
	}

}
