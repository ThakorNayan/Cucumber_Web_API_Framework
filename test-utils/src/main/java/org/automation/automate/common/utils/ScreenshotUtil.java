package org.automation.automate.common.utils;

import org.automation.automate.driver.manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotUtil {
	private ScreenshotUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static byte[] takeScreenshotAsBytes() {
		try {
			TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
			return takesScreenshot.getScreenshotAs(OutputType.BYTES);
		} catch (Exception e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public static File takeScreenshotAsFile() {
		TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
		return takesScreenshot.getScreenshotAs(OutputType.FILE);
	}

	public static String takeScreenshotAsBase64() {
		TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
		return takesScreenshot.getScreenshotAs(OutputType.BASE64);
	}
}
