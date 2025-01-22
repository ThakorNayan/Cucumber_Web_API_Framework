package org.automation.automate.common.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class Setup implements ITestListener {
    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String fileName = ExtentReportManager.getReportNameWithTimeStamp();
        String filePath = System.getProperty("user.dir") + "/reports/" + fileName;
        extentReports = ExtentReportManager.createInstance(filePath, "Automation execution", "Test Automation Report");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null)
            extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getTestClass().getRealClass().getSimpleName() + " - " + result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",", "<br/>");
        String formattedTrace = "<details>\n" +
                "  <summary>Click here for failure details</summary>\n" +
                "  " + stackTrace + "\n" +
                "</details>";
        ExtentReportManager.logExceptions(formattedTrace);
    }
}
