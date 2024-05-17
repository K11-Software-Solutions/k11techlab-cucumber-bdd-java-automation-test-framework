package org.k11techlab.bdd.webuitestframework.driverUtil;


import org.k11techlab.bdd.coreframework.WaitUtil;
import org.k11techlab.bdd.coreframework.configurationmanager.ConfigReader;
import org.k11techlab.bdd.coreframework.logging.Log;
import org.k11techlab.bdd.webuitestframework.enums.ApplicationProperties;
import org.k11techlab.bdd.webuitestframework.enums.Browsers;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class to handle WebDriver Initialization, termination and other driver management utils.
 *
 */
@SuppressWarnings("unused")
public class WebDriverEngine {

    static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final Logger LOG = Logger.getLogger(WebDriverEngine.class.getName());

    private static final String platform; //The platform of the execution machine.
    private static final boolean headless;//Headless execution switch
    private static final boolean remote;//Remote driver switch.
    private static final String seleniumHost; //The selenium host
    private static final String seleniumPort; //The selenium port
    private static final String seleniumRemoteUrl; // The selenium report url
    private static final Dimension broswerWindowSize; // The selenium browser window dimension.
    private static final Integer browserWindowWidth; // The browser window width.
    private static final Integer broswerWindowHeigth; //The browser window height.

    static {
        seleniumHost = ApplicationProperties.DRIVER_HOST.getStringVal();
        seleniumPort = ApplicationProperties.DRIVER_PORT.getStringVal();
        platform = ApplicationProperties.PLATFORM.getStringVal();

        headless = ApplicationProperties.HEADLESS.getBooleanVal(false);
        remote = ApplicationProperties.REMOTE.getBooleanVal(false);
        //CHECKSTYLE:OFF
        browserWindowWidth = ApplicationProperties.BROWSER_HEIGTH.getStringVal().isEmpty() ? 1024 : Integer.parseInt(ApplicationProperties.BROWSER_HEIGTH.getStringVal());
        broswerWindowHeigth = ApplicationProperties.BROWSER_WIDTH.getStringVal().isEmpty() ? 1280 : Integer.parseInt(ApplicationProperties.BROWSER_WIDTH.getStringVal());
        //CHECKSTYLE:ON

        broswerWindowSize = new Dimension(browserWindowWidth, broswerWindowHeigth);
        //Applicable For Selenium Grid
        seleniumRemoteUrl = "http://" + seleniumHost + ":" + seleniumPort + "/wd/hub";
    }

    /**
     * creates the browser driver specified in the system property "browser" or
     * the configuration parameter 'browser' specified in the
     * 'test-config.properties' file. if no property is set then a chrome
     * browser driver is created. The allowed properties are CHROME, IE. e.g to
     * run with IE, pass in the option -Dbrowser=IE at runtime
     *
     * TODO consider - what if this is running on the Linux server which has no IE support?
     *
     * @return WebDriver
     */
    public static WebDriver getBrowser() {
        Browsers browser;
        WebDriver dr;

        if (System.getProperty("browser") == null) {
            Log.LOGGER.info("Browser is Null, setting Browser");
            //LoadEnvironmentProperties prop = new LoadEnvironmentProperties();
            String browsername = ApplicationProperties.BROWSER.getStringVal("browser", "Chrome");
            browser = Browsers.browserForName(browsername);
            Log.LOGGER.info("Browser is set using test-config.properties: " + browsername);
        } else {
            browser = Browsers.browserForName(System.getProperty("browser"));
            Log.LOGGER.info("Browser is set using System.Property: " + System.getProperty("browser"));
        }

        switch (browser) {

            case CHROME:
                dr = getChromeDriver();
                break;
          /*  case FIREFOX:
                dr = createFireFoxDriver();
                break;*/

            default:
                dr = getChromeDriver();
                break;
        }
        return dr;
    }

    /**
     * Creates a chrome driver.
     *
     * @return Returns a chrome driver instance.
     */
    public static WebDriver getChromeDriver() {
        //below code lets you switch between a local driver and the grid:

        String isRemoteString = System.getProperty("remote");

        boolean isRemote = isRemoteString != null && !isRemoteString.isEmpty() && isRemoteString.equalsIgnoreCase("true") ? true : remote;

        if (isRemote) {
            WebDriver remoteWebDriver = null;
            try {
                Log.LOGGER.info(MessageFormat.format("Running on remote Grid instance. on {0}", seleniumRemoteUrl));
                remoteWebDriver = new RemoteWebDriver(new URL(seleniumRemoteUrl), getChromeOptions());
            } catch (MalformedURLException e) {
                LOG.info(seleniumRemoteUrl + " Error " + e.getMessage());
                throw new RuntimeException(e);  // just give up
            }
            Log.info("Driver Started....");
            tlDriver.set(remoteWebDriver);
            return remoteWebDriver;

        } else {

            //WebDriverManager.chromedriver().arch32().setup();
            // Set the path to the ChromeDriver executable
            WebDriver driver= launchChromeBrowser();
            Log.info("Driver Started....");
            tlDriver.set(driver);
            return driver;
        }
    }

    public static WebDriver launchChromeBrowser() {
        // Set the path to the ChromeDriver executable
        System.setProperty(
                ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe"
        );

        // Set the path for the ChromeDriver log file
        System.setProperty(
                ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                System.getProperty("user.dir") + File.separator + "target" + File.separator + "chromedriver.log"
        );

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        // Enable browser logging
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        //chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);

        // Initialize ChromeDriver with ChromeOptions
        WebDriver driver= new ChromeDriver();

        return driver;
    }


    /**
     * Gets the chrome options.
     *
     * @return Returns chrome options.
     */
    private static ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--ignore-certificate-errors");
           //Support for Headless browser testing
        if ((System.getProperty("headless") != null && System.getProperty("headless").equals("true")) || headless) {

            Log.LOGGER.info("Running Chrome Headless");
            options.addArguments("--headless");
        }

        return options;
    }


    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        tlDriver.get().quit();
        tlDriver.remove();
        Log.info("Driver instance Terminated...");
    }



}
