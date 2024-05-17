package org.k11techlab.bdd.coreframework.cucumberbddhelper;

import io.cucumber.java.Scenario;
import org.k11techlab.bdd.coreframework.logging.Log;
import org.k11techlab.bdd.coreframework.configurationmanager.ConfigReader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.k11techlab.bdd.mobiletestframework.base.appiumengine.AppiumEngine;

/**
 * Class to handle coreframework utilities
 */
public class BDDCommonUtils {
    /**
     * Extracts the feature name from the given Scenario object.
     *
     * @param scenario        The Scenario object from Cucumber.
     * @param withPackageName A boolean indicating whether to include package name along with feature name.
     * @return The feature name extracted from the Scenario.
     */
    public static String getFeatureNameFromScenario(Scenario scenario, boolean withPackageName) {
        String uri = scenario.getUri().toString();
        String[] uriParts = uri.split("/");

        // Extracting the feature name
        String featureName = "";
        if (uriParts.length > 1) {
            featureName = uriParts[uriParts.length - 1].split("\\.")[0];
        }

        // Extracting the package name if needed
        String packageName = "";
        if (withPackageName && uriParts.length > 0) {
            String[] packageParts = uriParts[0].split(":");
            if (packageParts.length > 1) {
                packageName = packageParts[1];
            }
        }

        Log.info("Feature: " + featureName);
        Log.info("Package: " + packageName);

        if (withPackageName && !packageName.isEmpty()) {
            return packageName + " - " + featureName;
        } else {
            return featureName;
        }
    }
    /**
     * Enum representing the status of a test scenario.
     *
     */
    public enum STATUS {
        PASS("PASS"), FAIL("FAIL");
        public final String status;

        STATUS(String status) {
            this.status = status;
        }
    }

    /**
     * Attaches screenshot to the Cucumber report based on the configuration.
     *
     */
    public static void attachScreenshotPerConfig(Scenario scenario) {
        Properties properties = ConfigReader.getProperties();
        if (properties.getProperty("screenshot").equals("only.fail")) {
            if (scenario.isFailed()) {
                CucumberUtils.logFailureToReport(scenario.getName() + " Failed...");
                CucumberUtils.attachScreenshot(AppiumEngine.getDriver());
            }
        } else if (properties.getProperty("screenshot").equals("only.pass")) {
            if (!scenario.isFailed()) {
                CucumberUtils.logSuccessToReport(scenario.getName() + " Passed...");
                CucumberUtils.attachScreenshot(AppiumEngine.getDriver());
            }
        } else {
            Log.info("Taking Screenshot.");
            CucumberUtils.attachScreenshot(AppiumEngine.getDriver());
        }
    }

    /**
     * Format a date string from one format to another.
     *
     * @param inputDateStr     The input date string.
     * @param inputDateFormat  The input date format.
     * @param outputDateFormat The output date format.
     * @return the formatted date string.
     */
    public static String formatDate(String inputDateStr, String inputDateFormat, String outputDateFormat) {
        DateFormat inputFormatter = new SimpleDateFormat(inputDateFormat);
        DateFormat outputFormatter = new SimpleDateFormat(outputDateFormat);
        try {
            Date inputDate = inputFormatter.parse(inputDateStr);
            return outputFormatter.format(inputDate);
        } catch (ParseException e) {
            Log.error("Parse Exception...", e);
            return null;
        }
    }
}
