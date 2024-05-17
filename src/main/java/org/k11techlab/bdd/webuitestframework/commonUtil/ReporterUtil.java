package org.k11techlab.bdd.webuitestframework.commonUtil;

import org.testng.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ReporterUtil {

    /* Build test suite summary data. */
    public static Map<String, String> getTestSuiteSummary(ISuite suite) {
        Map<String, String> testExecTimeMap = new HashMap<>();
        testExecTimeMap.put("TestSuiteName", suite.getName());

        Map<String, ISuiteResult> testResults = suite.getResults();
        for (ISuiteResult result : testResults.values()) {
            ITestContext testObj = result.getTestContext();
            testExecTimeMap.put("TestName", testObj.getName());
            Date startDate = testObj.getStartDate();
            testExecTimeMap.put("TestStartTime", getDateInStringFormat(startDate));
            Date endDate = testObj.getEndDate();
            testExecTimeMap.put("TestEndTime", getDateInStringFormat(endDate));
            long deltaTime = endDate.getTime() - startDate.getTime();
            String deltaTimeStr = convertDeltaTimeToString(deltaTime);
            testExecTimeMap.put("TestExecutionTime", deltaTimeStr);
        }
        return testExecTimeMap;
    }

    /* Get test method summary info. */
    public static String getFailedTestMehodSummary(ISuite suite, String... expectedMessage) {
        StringBuffer retBuf = new StringBuffer();
        String failedTestMethodInfo;
        retBuf.append("<tr><td colspan=7><center><b>" + "Failed Test Cases Summary" + "</b></center></td></tr>");
        retBuf.append("<tr><td colspan=7><center><b>" + "Test Suite: " + suite.getName() + "</b></center></td></tr>");
        Map<String, ISuiteResult> testResults = suite.getResults();

        for (ISuiteResult result : testResults.values()) {
            ITestContext testObj = result.getTestContext();
            String testName = testObj.getName();

            /* Get failed test method related data. */
            IResultMap testFailedResult = testObj.getFailedTests();

            failedTestMethodInfo = getTestMethodReport(testName, testFailedResult);
            retBuf.append(failedTestMethodInfo);
        }
        return retBuf.toString();
    }

    /* Get date string format value. */
    private static String getDateInStringFormat(Date date) {
        StringBuffer retBuf = new StringBuffer();
        if (date == null) {
            date = new Date();
        }
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        retBuf.append(df.format(date));
        return retBuf.toString();
    }

    /* Convert long type deltaTime to string with format hh:mm:ss. */
    private static String convertDeltaTimeToString(long millis) {
        return String.format("%02dh:%02dm:%02ds", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

    /* Get failed, passed or skipped test methods report. */
    private static String getTestMethodReport(String testName, IResultMap testResultMap) {
        StringBuilder retStrBuf = new StringBuilder();

        String resultTitle = testName;
        retStrBuf.append("<tr><td colspan=7><center><b>" + "Test Name: " + resultTitle + "</b></center></td></tr>");

        Set<ITestResult> testResultSet = testResultMap.getAllResults();

        for (ITestResult testResult : testResultSet) {
            String paramStr = "";
            String exceptionMessage = "";

            //Get parameter list.
            Object paramObjArr[] = testResult.getParameters();
            for (Object paramObj : paramObjArr) {
                paramStr += paramObj.toString();
                paramStr += " ";
            }

            exceptionMessage = testResult.getThrowable().getMessage();

            /* Add parameter. */
            retStrBuf.append("<tr><td <b>" + "Test Parameters: " + "</b>");
            retStrBuf.append(paramStr.substring(paramStr.indexOf("{") + 1, paramStr.indexOf("}")));
            retStrBuf.append("</tr></td>");
            retStrBuf.append(System.lineSeparator());


            /* Add exception message. */
            retStrBuf.append("<tr><td <b>" + "Exception Message: " + "</b>");
            retStrBuf.append(exceptionMessage);
            retStrBuf.append("</tr></td>");

            retStrBuf.append(System.lineSeparator());

        }
        return retStrBuf.toString();
    }
}
