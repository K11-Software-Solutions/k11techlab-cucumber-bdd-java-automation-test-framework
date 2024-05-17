package bdd.webuitests.googlesearch.testrunner;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/java/bdd/webuitests/googlesearch/features",
		glue = {"bdd.webuitests.googlesearch.stepdefinitions", "bdd.webuitests.googlesearch.hooks"},
		plugin = {"pretty", "html:target/cucumber-reports"}
)
public class WebUITestRunner extends AbstractTestNGCucumberTests {
}

