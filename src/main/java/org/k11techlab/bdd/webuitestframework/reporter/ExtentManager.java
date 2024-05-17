package org.k11techlab.bdd.webuitestframework.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    // Singleton method to get an instance of ExtentReports
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("test-output/extent.html");
        }
        return extent;
    }

    // Method to create an instance of ExtentReports
    public static ExtentReports createInstance(String fileName) {
        // Create and configure an instance of ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle(fileName);
        sparkReporter.config().setReportName(fileName);
        sparkReporter.config().setEncoding("utf-8");

        // The configuration for 'protocol' as 'https' might be necessary depending on your project setup.
        sparkReporter.config().setProtocol(com.aventstack.extentreports.reporter.configuration.Protocol.HTTPS);

        // Create an instance of ExtentReports and attach the configured reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        return extent;
    }
}
