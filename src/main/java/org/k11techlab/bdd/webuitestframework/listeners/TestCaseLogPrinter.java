package org.k11techlab.bdd.webuitestframework.listeners;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.util.List;

public class TestCaseLogPrinter extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.err.println("Printing the test method logs: " + asString(Reporter.getOutput(tr)));
    }

    private String asString(List<String> output) {
        StringBuilder builder = new StringBuilder();
        for (String each : output) {
            builder.append(each).append(", ");
        }
        //Removing the last ","
        return builder.toString().substring(0, builder.length() - 2);
    }
}
