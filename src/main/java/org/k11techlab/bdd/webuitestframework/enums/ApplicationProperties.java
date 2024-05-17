package org.k11techlab.bdd.webuitestframework.enums;

import org.apache.commons.lang3.StringUtils;
import org.k11techlab.bdd.webuitestframework.configManager.ConfigurationManager;

/**
 * TO get properties key/value While reading value First preference will be
 * System property if set before run.
 */
public enum ApplicationProperties {

    /**
     * Browser Key used to instantiate the selenium browser.
     */
    BROWSER("browser"),
    /**
     * Browser Key used to instantiate a headless chrome browser.
     */
    HEADLESS("headless"),
    /**
     * Test Environment Key.
     */
    TEST_ENVIRONMENT("environment"),
    /**
     * assertions URL of AUT to be used.
     */
    APPLICATION_BASE_URL("baseurl"),
    /**
     * dir to place generated result files.
     */
    REPORT_DIR("test.results.dir"),
    /**
     * dir to place generated result files.
     */
    REPORT_LOG_FILE_NAME("test.log.name"),
    /**
     * directory for reporting results.
     */
    TESTDATA_DIR("test.data.dir"),
    /**
     * dir to place screen-shots.
     */
    SCREENSHOT_DIR("selenium.screenshots.dir"),
    /**
     * Enables or disables screen shots.
     */
    FULL_SCREENSHOT_ENABLE("selenium.fullpage.screenshot.enable"),
    /**
     * Enables or disables email results.
     */
    EMAIL_EXECUTION_REPORT("test.results.emailExceutionReport"),
    /**
     * Email Report Directory.
     */
    EMAIL_EXECUTION_REPORT_DIR("test.results.report.html.dir"),
    /**
     * TODO - what is this.
     */
    DEFAULT_LOCALE("env.default.locale"),
    /**
     * The local character encoding.
     */
    LOCALE_CHAR_ENCODING("locale.char.encoding"),
    /**
     * integer to specify how many times a test action/step should be retried on
     * failure by default.
     */
    DEFAULT_RETRY_ACTIONS_CNT("retry.action.count"),
    /**
     * integer to specify how many times test should be retried on.
     */
    RETRY_CNT("retry.count"),
    /**
     * Saucelabs username.
     */
    SAUCE_USERNAME("sauceLabs.username"),
    /**
     * Saucelabs key.
     */
    SAUCE_KEY("sauceLabs.key"),
    /**
     * Default timeout in seconds.
     */
    DEFAULT_TIMEOUT("DefaultTimeout"),
    /**
     * Default timeout in seconds.
     */
    WAIT_POLL_TIMEOUT("WaitPollTime"),
    /**
     * Set to true to use a grid instance.
     */
    REMOTE("remote"),
    /**
     * The host for the driver to connect to.
     */
    DRIVER_HOST("driverhost"),
    /**
     * The port for the driver to connect to.
     */
    DRIVER_PORT("driverport"),
    /**
     * The platform of the OS.
     */
    PLATFORM("platform"),
    /**
     * The width of the browser.
     */
    BROWSER_WIDTH("browser.width"),
    /**
     * The heigth of the browser.
     */
    BROWSER_HEIGTH("browser.height"),
    /**
     * The web service uri.
     */
    WEB_SERVICE_URI("web.service.uri"),
    /**
     * The web service assertions port.
     */
    WEB_SERVICE_PORT("web.service.port"),
    /**
     * The web service assertions path.
     */
    WEB_SERVICE_PATH("web.service.path"),
    /**
     * The web service timeout.
     */
    WEB_SERVICE_TIMEOUT("web.service.timeout"),
    /**
     * Determines if system should log web service tests.
     */
    WEB_SERVICE_LOG("web.service.log"),
    /**
     * Determines if system should log web service only if test fail.
     */
    WEB_SERVICE_LOG_ON_FAIL_ONLY("web.service.log.on.fail.only");

    /**
     * Key of this enum.
     */
    private String key;

    /**
     * Constructor for enum class.
     *
     * @param appKey The key to set.
     */
    ApplicationProperties(String appKey) {
        this.key = appKey;
    }

    /**
     * Gets the Key.
     *
     * @return returns the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param defaultVal optional
     * @return string value of the key
     */
    public String getStringVal(String... defaultVal) {
        return System.getProperty(key, ConfigurationManager.getBundle().getString(key,
                (null != defaultVal) && (defaultVal.length > 0) ? defaultVal[0] : ""));
    }

    /**
     * @param defaultVal optional
     * @return integer value of the key or 0 if key is not an integer
     */
    public int getIntVal(int... defaultVal) {
        int rc = 0;
        try {
            rc = Integer.parseInt(getStringVal());
        } catch (NumberFormatException e) {
            if (defaultVal != null && (defaultVal.length > 0))
            {
                rc = defaultVal[0];
            }
        }

        return rc;
    }

    /**
     * @param defaultVal optional
     * @return boolean value of the key
     */
    public boolean getBooleanVal(boolean... defaultVal) {
        try {
            String sVal = getStringVal().trim();
            return StringUtils.isNumeric(sVal)
                    ? (Integer.parseInt(sVal) != 0)
                    : Boolean.parseBoolean(sVal);
        } catch (NumberFormatException e) {
            // just ignore
        }
        return (null != defaultVal) && (defaultVal.length > 0) && defaultVal[0];
    }


}
