package org.automation.automate.common.utils;

import org.automation.automate.common.constants.UIWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class WebDriverUtil {
    private final WebDriver driver;
    private WebDriverWait wait;

    public WebDriverUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void setImplicitWait(long seconds) {
        driver.manage().timeouts().implicitlyWait(waitForDuration(seconds));
    }

    public Duration waitForDuration(long seconds) {
        return Duration.ofSeconds(seconds);
    }

    public void waitForPageToLoad(long seconds) {
        wait = new WebDriverWait(driver, waitForDuration(seconds));
        ExpectedCondition<Boolean> expectedCondition = webDriver -> getJavascriptExecutor(webDriver).executeScript("return document.readyState").equals("complete");
        wait.until(expectedCondition);
    }

    public JavascriptExecutor getJavascriptExecutor(WebDriver driver) {
        return ((JavascriptExecutor) driver);
    }

    public void get(String url) {
        driver.get(url);
    }

    public WebElement waitForElementToBePresent(By locator, long timeoutInSeconds) {
        wait = new WebDriverWait(driver, waitForDuration(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementToBePresent(By locator) {
        wait = new WebDriverWait(driver, waitForDuration(UIWaits.DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElement(WebElement element) {
        wait = new WebDriverWait(driver, waitForDuration(UIWaits.DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElement(WebElement element, long timeoutInSeconds) {
        wait = new WebDriverWait(driver, waitForDuration(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElement(By locator) {
        wait = new WebDriverWait(driver, waitForDuration(UIWaits.DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElement(By locator, long timeoutInSeconds) {
        wait = new WebDriverWait(driver, waitForDuration(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForElements(By locator) {
        wait = new WebDriverWait(driver, waitForDuration(UIWaits.DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitForElements(By locator, long timeoutInSeconds) {
        wait = new WebDriverWait(driver, waitForDuration(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitForElements(WebElement element) {
        wait = new WebDriverWait(driver, waitForDuration(UIWaits.DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public List<WebElement> waitForElements(WebElement element, long timeoutInSeconds) {
        wait = new WebDriverWait(driver, waitForDuration(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public void executeJS(String script, Object... args) {
        getJavascriptExecutor(driver).executeScript(script, args);
    }

    public void setTimeouts(long seconds) {
        setImplicitWait(seconds);
        setPageTimeout(seconds);
    }

    public void setPageTimeout(long seconds) {
        driver.manage().timeouts().pageLoadTimeout(waitForDuration(seconds));
    }

    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void setWindowPosition(int x, int y) {
        driver.manage().window().setPosition(new Point(x, y));
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void fullscreenWindow() {
        driver.manage().window().fullscreen();
    }

    public void minimizeWindow() {
        driver.manage().window().minimize();
    }

    public void sendKeys(WebElement element, String value) {
        waitForElement(element).sendKeys(value);
    }

    public void sendKeys(By by, String value) {
        waitForElement(by).sendKeys(value);
    }

    public void clear(WebElement element) {
        waitForElement(element).clear();
    }

    public void clear(By by) {
        waitForElement(by).clear();
    }

    public void clearAndSendKeys(By by, String value) {
        clear(by);
        sendKeys(by, value);
    }

    public void clearAndSendKeys(WebElement element, String value) {
        clear(element);
        sendKeys(element, value);
    }

    public void click(By by) {
        waitForElement(by).click();
    }

    public void click(WebElement element) {
        waitForElement(element).click();
    }

    public void click(By by, long timeoutInSeconds) {
        waitForElement(by, timeoutInSeconds).click();
    }

    public void click(WebElement element, long timeoutInSeconds) {
        waitForElement(element, timeoutInSeconds).click();
    }

    public void submit(WebElement element) {
        waitForElement(element).submit();
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayed(WebElement element, long timeoutInSeconds) {
        try {
            return waitForElement(element, timeoutInSeconds).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayed(By by, long timeoutInSeconds) {
        try {
            return waitForElement(by, timeoutInSeconds).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(WebElement element, long timeoutInSeconds) {
        try {
            return waitForElement(element, timeoutInSeconds).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(By by, long timeoutInSeconds) {
        try {
            return waitForElement(by, timeoutInSeconds).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelected(WebElement element, long timeoutInSeconds) {
        try {
            return waitForElement(element, timeoutInSeconds).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelected(By by, long timeoutInSeconds) {
        try {
            return waitForElement(by, timeoutInSeconds).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public void hardWait(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LoggerUtil.logError(e.getMessage(), e);
        }
    }

    public String getAttribute(WebElement element, String attributeName) {
        return waitForElement(element).getAttribute(attributeName);
    }

    public String getAttribute(By by, String attributeName) {
        return waitForElement(by).getAttribute(attributeName);
    }

    public void mouseHover(By by) {
        new Actions(driver).moveToElement(waitForElement(by)).build().perform();
    }

    public void mouseHover(WebElement element) {
        new Actions(driver).moveToElement(waitForElement(element)).build().perform();
    }

    public void scrollToElement(WebElement element) {
        getJavascriptExecutor(driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void jsClick(WebElement element) {
        getJavascriptExecutor(driver).executeScript("arguments[0].click();", element);
    }

    public void actionsClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void selectByVisibleText(WebElement element, String visibleText) {
        Select select = getSelect(waitForElement(element));
        select.selectByVisibleText(visibleText);
    }

    public void selectByVisibleText(By by, String visibleText) {
        Select select = getSelect(waitForElement(by));
        select.selectByVisibleText(visibleText);
    }

    public void selectByValue(WebElement element, String value) {
        Select select = getSelect(waitForElement(element));
        select.selectByValue(value);
    }

    public void selectByValue(By by, String value) {
        Select select = getSelect(waitForElement(by));
        select.selectByValue(value);
    }

    public void selectByIndex(WebElement element, int index) {
        Select select = getSelect(waitForElement(element));
        select.selectByIndex(index);
    }

    public void selectByIndex(By by, int index) {
        Select select = getSelect(waitForElement(by));
        select.selectByIndex(index);
    }

    public void deselectByVisibleText(WebElement element, String visibleText) {
        Select select = getSelect(waitForElement(element));
        select.deselectByVisibleText(visibleText);
    }

    public void deselectByVisibleText(By by, String visibleText) {
        Select select = getSelect(waitForElement(by));
        select.deselectByVisibleText(visibleText);
    }

    public void deselectByValue(WebElement element, String value) {
        Select select = getSelect(waitForElement(element));
        select.deselectByValue(value);
    }

    public void deselectByValue(By by, String value) {
        Select select = getSelect(waitForElement(by));
        select.deselectByValue(value);
    }

    public void deselectByIndex(WebElement element, int index) {
        Select select = getSelect(waitForElement(element));
        select.deselectByIndex(index);
    }

    public void deselectByIndex(By by, int index) {
        Select select = getSelect(waitForElement(by));
        select.deselectByIndex(index);
    }

    private Select getSelect(WebElement element) {
        return new Select(element);
    }

    public List<String> getOptions(WebElement element) {
        Select select = getSelect(waitForElement(element));
        List<WebElement> options = select.getOptions();
        return options.stream().map(WebElement::getText).toList();
    }

    public List<String> getOptions(By by) {
        Select select = getSelect(waitForElement(by));
        List<WebElement> options = select.getOptions();
        return options.stream().map(WebElement::getText).toList();
    }

    public List<String> getSelectedOptions(WebElement element) {
        Select select = getSelect(waitForElement(element));
        List<WebElement> options = select.getAllSelectedOptions();
        return options.stream().map(WebElement::getText).toList();
    }

    public List<String> getSelectedOptions(By by) {
        Select select = getSelect(waitForElement(by));
        List<WebElement> options = select.getAllSelectedOptions();
        return options.stream().map(WebElement::getText).toList();
    }

    public String getFirstSelectedOption(WebElement element) {
        Select select = getSelect(waitForElement(element));
        return select.getFirstSelectedOption().getText();
    }

    public String getFirstSelectedOption(By by) {
        Select select = getSelect(waitForElement(by));
        return select.getFirstSelectedOption().getText();
    }

    public String getText(WebElement element) {
        return waitForElement(element).getText();
    }

    public String getText(WebElement element, long timeoutInSeconds) {
        return waitForElement(element, timeoutInSeconds).getText();
    }

    public String getText(By by) {
        return waitForElement(by).getText();
    }

    public String getText(By by, long timeoutInSeconds) {
        return waitForElement(by, timeoutInSeconds).getText();
    }
}
