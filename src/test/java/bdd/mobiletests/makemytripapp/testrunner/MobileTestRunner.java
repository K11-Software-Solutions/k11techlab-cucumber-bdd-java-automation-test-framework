package bdd.mobiletests.makemytripapp.testrunner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
/**
 * Runner class to glue features and step definitions
 *
 */
@Suite
@IncludeEngines("cucumber")
@SelectPackages("bdd.mobiletests.makemytripapp.testrunner")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "bdd/mobiletests/makemytripapp/stepdefinitions, bdd/mobiletests/hooks")
@ExcludeTags("ignore")
public class MobileTestRunner {

}

/*@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/mobiletests/makemytrip")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "bdd/mobiletests/makemytripapp/stepdefinitions, bdd/mobiletests/hooks")
@ExcludeTags("ignore")
public class MobileTestRunner {

}*/
