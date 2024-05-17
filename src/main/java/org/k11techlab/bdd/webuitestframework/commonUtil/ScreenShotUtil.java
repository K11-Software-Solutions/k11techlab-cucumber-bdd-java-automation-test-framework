package org.k11techlab.bdd.webuitestframework.commonUtil;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.apache.commons.io.FileUtils;
import org.k11techlab.bdd.webuitestframework.enums.ApplicationProperties;
import org.k11techlab.bdd.coreframework.logging.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Utility class for screenshots.
 */
public final class ScreenShotUtil {

    /**
     * Private constructor.
     */
    private ScreenShotUtil() { }

    /**
     * Default folder path for screen shots.
     */
    static final String FOLDER_PATH = ApplicationProperties.SCREENSHOT_DIR.getStringVal();

    /**
     * Creates a screenshot of the viewport.
     * @param driver the web driver
     * @param screenShotName the name for the screenshot
     * @return the path to the captured screenshot
     */
    public static String createScreenshot(WebDriver driver, String screenShotName) {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = FOLDER_PATH + "/" + screenShotName + ".png";
        File sshot = new File(dest);

        try {
            dest = sshot.getCanonicalPath();
            FileUtils.copyFile(source, sshot);
            Log.LOGGER.info("Saved fullpage screenshot at: " + dest);
        } catch (IOException e) {
            Log.LOGGER.error("Unable to create screenshot Exception = " + e.getMessage() + e.getStackTrace());
        }

        return dest;
    }

    /**
     * Creates a screenshot of the full webpage, including parts of the page not in the current viewport.
     * @param driver the web driver
     * @param screenShotName the name for the screenshot
     * @return the path to the captured screenshot
     */
    public static String captureFullPageScreenshot(WebDriver driver, String screenShotName) {
        ru.yandex.qatools.ashot.Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        String pathToTheScreenshot = "";
        try {
            FileUtil.checkCreateDir(FOLDER_PATH);
            File file = new File(FOLDER_PATH + "/" + screenShotName.trim() + ".png");
            if (file.createNewFile()) {
                Log.LOGGER.info("File created at: " + pathToTheScreenshot);
            }
            pathToTheScreenshot = file.getCanonicalPath();
            ImageIO.write(screenshot.getImage(), "png", file);
        } catch (IOException e) {
            Log.LOGGER.info("IOException creating screenshot. " + e.getMessage() + e.getStackTrace());
        }
        Log.LOGGER.info("Saved fullpage screenshot at: " + pathToTheScreenshot);
        return pathToTheScreenshot;
    }

    /**
     * Captures a base64 screenshot of the displayed page viewport.
     * @param driver The web driver
     * @param screenShotName the screenshot name
     * @return the path to the captured screenshot
     */
    public static String captureBase64ScreenShot(WebDriver driver, String screenShotName) {
        WebDriver d;
        try {
            if (driver instanceof org.openqa.selenium.remote.RemoteWebDriver) {
                d = new Augmenter().augment(driver);
            } else {
                d = driver;
            }
            String base64Image = ((TakesScreenshot) d).getScreenshotAs(OutputType.BASE64);

            return base64ImageToFile(base64Image, screenShotName);

        } catch (Exception e) {
            //Do nothing
        }
        return null;
    }

    /**
     * Saves the web element image into a byte array.
     * @param driver the web driver
     * @param element the web element
     * @return an bytearray of an image of the element
     */
    public static byte[] saveWebElement(WebDriver driver, WebElement element) {
        try {
            BufferedImage image = Shutterbug
                    .shootElement(driver, element)
                    .withName(element.getText())
                    .withTitle(element.getText())
                    .getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            Log.LOGGER.info("IOException creating screenshot. " + e.getMessage() + e.getStackTrace());
        }
        return "Unable to Get WebElement.".getBytes();
    }

    /**
     * Generates a unique screenshot name based on the local date.
     * @param screenShotName The screenshot name to append to the dateTime
     * @return Returns a unique screenshot name
     */
    public static String generateScreenshotFileName(String screenShotName) {
        screenShotName = screenShotName.replaceAll(" ", "_");
        screenShotName = screenShotName.replaceAll("[\\\\/:*?\"<>|]", "");
        String localDateTime = LocalDateTime.now().toString().replaceAll("[^0-9a-zA-Z]", "");
        StringBuilder name = new StringBuilder()
                .append(screenShotName)
                .append("_")
                .append(localDateTime);
        return name.toString();
    }

    /**
     * Saves the base64 image to a file.
     * @param base64Image The base64 image string
     * @param screenShotName the screenshot name
     * @return the path to the screenshot
     */
    private static String base64ImageToFile(String base64Image, String screenShotName) {
        String filename = "";
        try {
            FileUtil.checkCreateDir(FOLDER_PATH);
            filename = FileUtil.saveImageFile(base64Image, screenShotName, FOLDER_PATH);

            Log.LOGGER.debug("Capturing screen shot: " + filename);

        } catch (Exception e) {
            Log.LOGGER.error("Error in capturing screenshot\n" + e.getMessage());
        }
        return filename;
    }
}
