package org.k11techlab.bdd.webuitestframework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGITestListener implements ITestListener {

    private static ExtentReports extent = initExtentReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports initExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/extent.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Results");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setEncoding("utf-8");
        
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Case " + result.getMethod().getMethodName() + " started");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                                                  result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        System.out.println("Test Case " + result.getMethod().getMethodName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        System.out.println("Test Case " + result.getMethod().getMethodName() + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
        System.out.println("Test Case " + result.getMethod().getMethodName() + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.get().warning("Test failed but within success percentage");
    }
}
