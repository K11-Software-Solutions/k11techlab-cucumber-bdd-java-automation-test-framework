package bdd.webuitests.googlesearch.hooks;

import org.k11techlab.bdd.coreframework.configurationmanager.ConfigReader;
import org.k11techlab.bdd.webuitestframework.driverUtil.WebDriverEngine;
import org.testng.annotations.*;

import io.cucumber.java.Scenario;
import io.cucumber.java.*;
import org.k11techlab.bdd.coreframework.cucumberbddhelper.BDDCommonUtils;
import org.k11techlab.bdd.coreframework.cucumberbddhelper.CucumberUtils;
import org.k11techlab.bdd.coreframework.cucumberbddhelper.ResultManager;
import org.k11techlab.bdd.coreframework.logging.Log;

import java.util.HashSet;
import java.util.Properties;

/**
 * Hooks class containing setup and tear-down methods for test scenarios.
 */
@SuppressWarnings("unused")
public class WebUITestHook{

    static String featureName;
    static HashSet<String> features = new HashSet<>();
    /**
     * Method executed before all tests.
     */
    @BeforeAll
    public static void beforeAll() {
        Properties properties = ConfigReader.getProperties();
        WebDriverEngine.getChromeDriver();
    }

    /**
     * Method executed before each test.
     * 
     * @param scenario The scenario being executed.
     */
    @Before
    public void beforeTest(Scenario scenario) {
        String currentFeatureName = BDDCommonUtils.getFeatureNameFromScenario(scenario, false);
        if (!features.contains(currentFeatureName)) {
            featureName = currentFeatureName;
            features.add(featureName);

            // Initialize Result Map for Current Feature
            ResultManager.initializeResultCollector(featureName);
        }
        CucumberUtils.setCurrentScenario(scenario);
        CucumberUtils.logToReport(scenario.getName() + " Started...");
    }

    /**
     * Method executed after each test.
     * 
     * @param scenario The scenario being executed.
     */
    @After
    public void afterTest(Scenario scenario) {
        // Update Result for Current Scenario
        ResultManager.updateResult(scenario, featureName);
        BDDCommonUtils.attachScreenshotPerConfig(scenario);
    }

    /**
     * Method executed after all tests.
     */
    @AfterAll
    public static void afterAll() {
        ResultManager.printResult();
        WebDriverEngine.quitDriver();

        String home = System.getProperty("user.dir");
        Log.info("------------------------------------------------------");
        Log.info("Reports Generated...");
        Log.info("------------------------------------------------------");
        Log.info("Cucumber: " + home + "\\testReports\\CucumberReport.html");
        Log.info("Cucumber Enhanced: " + home + "\\testReports\\cucumber-html-reports\\overview-features.html");
        Log.info("Time Line: " + home + "\\testReports\\timelineReport\\index.html");
        Log.info("Extent HTML: " + home + "\\testReports\\ExtentReport.html");
        Log.info("Extent PDF: " + home + "\\testReports\\ExtentReport.pdf");
        Log.info("------------------------------------------------------");
    }
}
