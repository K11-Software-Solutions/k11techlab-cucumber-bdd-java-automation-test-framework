package org.k11techlab.bdd.webuitestframework.retryanalyzer;

import org.k11techlab.bdd.webuitestframework.enums.ApplicationProperties;
import org.testng.*;

import java.util.HashSet;
import java.util.Set;

public class RetryTestListenerAdapter extends TestListenerAdapter implements ITestListener{

    public void onTestFailure(ITestResult result) {
        if(!(ApplicationProperties.RETRY_CNT.getIntVal()==0)) {
            if (result.getMethod().getRetryAnalyzer() != null) {
                RetryAnalyzer retryAnalyzer = (RetryAnalyzer)result.getMethod().getRetryAnalyzer();
                if (retryAnalyzer.getRetryCount()>0) {
                    result.setStatus(ITestResult.SKIP);
                } else {
                    result.setStatus(ITestResult.FAILURE);
                }
                Reporter.setCurrentTestResult(result);
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        Set<ITestResult> testsToRemove = new HashSet<>();

        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1 || context.getPassedTests().getResults(method).size() > 0) {
                testsToRemove.add(temp);
            }
        }

        failedTests.removeAll(testsToRemove); // Safely remove all collected tests at once
    }



}
