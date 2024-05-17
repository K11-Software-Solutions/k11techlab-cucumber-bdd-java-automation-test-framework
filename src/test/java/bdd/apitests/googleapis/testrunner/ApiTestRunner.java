package bdd.apitests.googleapis.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/java/bdd/apitests/googleapis/features",
		glue = {"bdd.apitests.googleapis.stepdefinitions"},
		plugin = {"pretty", "html:target/cucumber-reports"}
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
}

