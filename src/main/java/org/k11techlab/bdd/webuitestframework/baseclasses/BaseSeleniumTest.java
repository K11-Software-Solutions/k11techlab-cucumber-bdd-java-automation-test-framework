package org.k11techlab.bdd.webuitestframework.baseclasses;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.google.common.base.Preconditions;
import org.k11techlab.bdd.webuitestframework.commonUtil.ScreenShotUtil;
import org.k11techlab.bdd.webuitestframework.driverUtil.*;
import org.k11techlab.bdd.webuitestframework.enums.*;
import org.k11techlab.bdd.coreframework.logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

/**
 * Selenium BaseSeleniumTest class.
 */
public class BaseSeleniumTest implements BaseTestCase {

    /**
     * The webdriver for this class. Default browser is Chrome. It can be replaced with other browser drivers
     */
    public ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() ->
            // Default browser is Chrome. It can be replaced with other browser drivers
            createDriver()
    );

    /**
     * Gets the thread safe web driver.
     * Call this method to get the driver object and launch the browser
     * @return the web driver
     */
    public WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit and remove the web driver
     */
    public void quitAndRemoveDriver() {
        driver.get().quit();
        driver.remove();
    }

    /**
     * Gets the browser driver. Override this to implement custom driver initialization.
     * @return
     */
    protected WebDriver createDriver()
    {
        return DriverManager.getBrowser();
    }

    /**
     * Runs before all tests.
     *
     * @param browser sets the browser property
     * @param platform sets the platform property
     * @param application sets the application property
     * @param includePattern sets the include pattern property
     * @param excludePattern sets the exclude pattern property
     * @param environment sets the environment property
     */
    @Parameters({
        "browser",
        "platform",
        "application",
        "includePattern",
        "excludePattern",
        "environment"})
    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup(@Optional String browser,
            @Optional String platform,
            @Optional String application,
            @Optional String includePattern,
            @Optional String excludePattern,
            @Optional String environment) {

        // data provider filters
        String incPattern = "";
        if (includePattern != null) {
            incPattern = includePattern;
        } else if (System.getProperty("includePattern") == null) {
            incPattern = "NONE";
        }

        System.setProperty("includePattern", incPattern);

        if (excludePattern != null) {
            System.setProperty("excludePattern", excludePattern);
        }

        String webBroswer = "";
        // global variables
        if (browser != null) {
            webBroswer = browser;
        } else if (System.getProperty("browser") == null) {
            webBroswer = "Chrome";
        }

        System.setProperty("browser", webBroswer);

        if (platform != null) {
            System.setProperty("platform", platform);
        }

        if (application != null) {
            System.setProperty("application", application);
        }

        if (environment != null) {
            System.setProperty("environment", environment);
        }
    }

    /**
     * Method executed before methods.
     *
     * @param method The method executed
     */
    @BeforeMethod
    public void beforeMethod(Method method) {
        Log.LOGGER.info("Running the test method: " + method.getName());
     }

    /**
     * Executes after every method. Takes a screen shot if enabled in property
     * files.
     *
     * @param result The test result
     */
    @AfterMethod()
    public void afterMethod(ITestResult result) {
        if (driver != null && result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();

            if (ApplicationProperties.FULL_SCREENSHOT_ENABLE.getStringVal().equals("true")) {
                String fullScreenShotName = ScreenShotUtil.generateScreenshotFileName(methodName);
                String fullScreenShotPath = ScreenShotUtil.captureFullPageScreenshot(getDriver(), fullScreenShotName);

            } else {
                String screenshotName = ScreenShotUtil.captureBase64ScreenShot(getDriver(), methodName);
                Reporter.log("Saved screentshot at: " + screenshotName, true);
              }
        }

        quitAndRemoveDriver();
        Log.LOGGER.info("Quit Driver");
    }

    public void addStepLog(String stepName) {
        ITestResult result = Reporter.getCurrentTestResult();
        Preconditions.checkState(result != null);
        ExtentTest test = (ExtentTest) result.getAttribute("test");
        Preconditions.checkState(test != null);
        test.log(Status.PASS, stepName);
    }

    /**
     * Adds screen shot to extent report.
     *
     * @param stepName the name of the step
     */
    public void addScreenShotStep(String stepName) {
        try {
            String fullScreenShotName = ScreenShotUtil.generateScreenshotFileName(stepName);
            String fullScreenShotPath = ScreenShotUtil.captureFullPageScreenshot(getDriver(), fullScreenShotName);
            fullScreenShotPath = getScreenshotRelativePath(fullScreenShotPath);
            Log.LOGGER.info(MessageFormat.format("Screenshot Step Path = {0}", fullScreenShotPath));
            ITestResult result = Reporter.getCurrentTestResult();
            Preconditions.checkState(result != null);
            ExtentTest test = (ExtentTest) result.getAttribute("test");
            Preconditions.checkState(test != null);

            test.log(Status.PASS, stepName, MediaEntityBuilder.createScreenCaptureFromPath(fullScreenShotPath).build());
            Log.LOGGER.info(MessageFormat.format("Screenshot attached from Path = {0}", fullScreenShotPath));

        } catch (Exception e) {
            Log.LOGGER.info(MessageFormat.format("Unable to attach the screenshot step to the report. Please verify test execution using ExtentTestNgFormatter lister. Exception {0}", e.getMessage()));
        }
    }


    private String getScreenshotRelativePath(String screenshotAbsolutepath) {
        String reportPathStr = System.getProperty("reportPath");
        File reportPath;

        try {
            reportPath = new File(reportPathStr);
        } catch (NullPointerException e) {
            reportPath = new File(TestNG.DEFAULT_OUTPUTDIR);
        }

        Path extentReportPath = Paths.get(reportPath.getAbsolutePath());
        Path ssPath = Paths.get(screenshotAbsolutepath);
        return extentReportPath.relativize(ssPath).toString();
    }
}
