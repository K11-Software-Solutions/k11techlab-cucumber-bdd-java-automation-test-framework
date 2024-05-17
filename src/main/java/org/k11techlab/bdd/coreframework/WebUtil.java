package org.k11techlab.bdd.coreframework;

import java.io.File;
import java.time.Duration;
import java.util.List;

/**
 * Abstract class containing utility methods for web automation.
 *

 */
@SuppressWarnings({"unused", "UnusedReturnValue", "SameParameterValue"})
public abstract class WebUtil<T> {

  /**
   * Opens the specified URL in the browser.
   *
   * @param url
   *            The URL to open.
  
   */
  abstract void openPage(String url);

  /**
   * Maximizes the screen.
   *
  
   */
  abstract void maximizeScreen();

  /**
   * Sets the screen size to the specified width and height.
   *
   * @param width
   *            The width of the screen.
   * @param height
   *            The height of the screen.
  
   */
  abstract void setScreenSize(int width, int height);

  /**
   * Sets the default timeout for waiting for elements to appear on the page.
   *
   * @param duration
   *            The default timeout value, in seconds.
  
   */
  abstract void setDefaultTimeOut(Duration duration);

  /**
   * Clicks on the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element to click.
  
   */
  abstract void click(String xpath);

  /**
   * Enters value on to element identified by the specified XPath
   *
   * @param xpath
   *            The XPath of the element
   * @param value
   *            Value to send
  
   */
  abstract void fillText(String xpath, String value);

  /**
   * Retrieves the WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element to retrieve.
   * @return The WebElement found.
  
   */
  abstract T getElement(String xpath);
  abstract T getElementByText(String xpath, String textContent);

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The WebElements found as a list
  
   */
  abstract T getElements(String xpath);

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The WebElements found as a list
  
   */
  abstract T getElements(String xpath, boolean waitForFirstElement);

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The WebElements found as a list
  
   */
  abstract T getElements(String xpath, boolean waitForFirstElement, Duration duration);

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The text content of WebElement
  
   */
  abstract String getElementTextContent(String xpath);

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForElement
   *            true if wait for the element
   * @return The text content of WebElement
  
   */
  abstract String getElementTextContent(String xpath, boolean waitForElement);

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForElement
   *            true if wait for the element
   * @param duration
   *            maximum wait
   * @return The text content of WebElement
  
   */
  abstract String getElementTextContent(String xpath, boolean waitForElement, Duration duration);

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The text content of WebElements found as a list
  
   */
  abstract List<String> getElementsTextContent(String xpath);

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The text content of WebElements found as a list
  
   */
  abstract List<String> getElementsTextContent(String xpath, boolean waitForFirstElement);

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The text content of WebElements found as a list
  
   */
  abstract List<String> getElementsTextContent(String xpath, boolean waitForFirstElement, Duration duration);

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param xpath
   *            The XPath of the element
   * @return The element count
  
   */
  abstract int getElementsCount(String xpath);

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The element count
  
   */
  abstract int getElementsCount(String xpath, boolean waitForFirstElement);

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The element count
  
   */
  abstract int getElementsCount(String xpath, boolean waitForFirstElement, Duration duration);

  /**
   * Clicks on the element identified by the specified XPath using JavaScript.
   *
   * @param xpath
   *            The XPath of the element to click.
  
   */
  abstract void javaScriptClick(String xpath);

  /**
   * Enters value on to element identified by the specified XPath using
   * JavaScript.
   *
   * @param xpath
   *            The XPath of the element
   * @param value
   *            Value to send
  
   */
  abstract void javaScriptFillText(String xpath, String value);

  /**
   * Waits for the element identified by the specified XPath to be clickable and
   * then clicks on it.
   *
   * @param xpath
   *            The XPath of the element to wait for and click.
   * @param duration
   *            The maximum time to wait for the element to be clickable, in
   *            seconds.
  
   */
  abstract void waitAndClick(String xpath, Duration duration);

  /**
   * Waits for the specified number of seconds.
   *
   * @param duration
   *            wait duration.
  
   */
  abstract void waitFor(Duration duration);

  /**
   * Enters the specified text into the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param text
   *            The text to enter.
  
   */
  abstract void enterText(String xpath, String text);

  /**
   * Clears the element identified by the specified XPath and enters the given
   * text into it.
   *
   * @param xpath
   *            The XPath of the element.
   * @param text
   *            The text to enter.
  
   */
  abstract void clearAndEnterText(String xpath, String text);

  /**
   * Retrieves a screenshot of the current page.
   *
   * @return The screenshot file.
  
   */
  abstract File getScreenshot();

  /**
   * Takes a screenshot of the current page and saves it to the specified file
   * path.
   *
   * @param filepath
   *            The file path where the screenshot should be saved.
  
   */
  abstract void takeScreenshot(String filepath);

  /**
   * Retrieves a screenshot of the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The screenshot file.
  
   */
  abstract File getElementScreenshot(String xpath);

  /**
   * Takes a screenshot of the element identified by the specified XPath and saves
   * it to the specified file path.
   *
   * @param xpath
   *            The XPath of the element.
   * @param filepath
   *            The file path where the screenshot should be saved.
  
   */
  abstract void takeElementScreenshot(String xpath, String filepath);

  /**
   * Takes a full-page screenshot.
   *
   * @return The screenshot file.
  
   */
  abstract File takeFullPageScreenshot();

  /**
   * Takes a full-page screenshot and saves it to the specified file path.
   *
   * @param filepath
   *            The file path where the screenshot should be saved.
  
   */
  abstract void takeFullPageScreenshot(String filepath);

  /**
   * Retrieves the screenshot of the current page as a Base64 encoded string.
   *
   * @return The Base64 encoded screenshot string.
  
   */
  abstract String getScreenshotAsBase64();

  /**
   * Retrieves the screenshot of the element identified by the specified XPath as
   * a Base64 encoded string.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The Base64 encoded screenshot string.
  
   */
  abstract String getElementScreenshotAsBase64(String xpath);

  /**
   * Retrieves the full-page screenshot as a Base64 encoded string.
   *
   * @return The Base64 encoded screenshot string.
  
   */
  abstract String getFullPageScreenshotAsBase64();

  /**
   * Retrieves the screenshot of the current page as a byte array.
   *
   * @return The screenshot byte array.
  
   */
  abstract byte[] getScreenshotAsByte();

  /**
   * Retrieves the screenshot of the element identified by the specified XPath as
   * a byte array.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The screenshot byte array.
  
   */
  abstract byte[] getElementScreenshotAsByte(String xpath);

  /**
   * Retrieves the full-page screenshot as a byte array.
   *
   * @return The screenshot byte array.
  
   */
  abstract byte[] getFullPageScreenshotAsByte();

  /**
   * Focuses on the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
  
   */
  abstract void focusOnElement(String xpath);

  /**
   * Hovers over the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
  
   */
  abstract void hoverOverElement(String xpath);

  /**
   * Simulates pressing the Tab key.
   *
  
   */
  abstract void pressTab();

  /**
   * Simulates pressing the Enter key.
   *
  
   */
  abstract void pressEnter();

  /**
   * Simulates pressing the Tab key on the element identified by the specified
   * XPath.
   *
   * @param xpath
   *            The XPath of the element.
  
   */
  abstract void pressTabOnElement(String xpath);

  /**
   * Simulates pressing the Enter key on the element identified by the specified
   * XPath.
   *
   * @param xpath
   *            The XPath of the element.
  
   */
  abstract void pressEnterOnElement(String xpath);

  /**
   * Scrolls the element identified by the specified XPath into view.
   *
   * @param xpath
   *            The XPath of the element.
  
   */
  abstract void scrollElementIntoToView(String xpath);

  /**
   * Scrolls the page by the specified percentage.
   *
   * @param percentage
   *            The percentage of the page height to scroll by.
  
   */
  abstract void scrollByPercent(double percentage);

  /**
   * Retrieves the value of the specified attribute of the element identified by
   * the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param attributeName
   *            The name of the attribute.
   * @return The value of the attribute.
  
   */
  abstract String getAttributeValue(String xpath, String attributeName);

  /**
   * Checks if the element identified by XPath is visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @return true if the element is visible, false otherwise
  
   */
  abstract boolean isVisible(String xpath);

  /**
   * Waits for the element identified by XPath to be visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes visible within the duration, false
   *         otherwise
  
   */
  abstract boolean waitAndCheckIsVisible(String xpath, Duration duration);

  /**
   * Waits for the element identified by XPath to be clickable.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
  
   */
  abstract boolean waitAndCheckIsClickable(String xpath, Duration duration);

  /**
   * Waits for the element identified by XPath to become invisible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes invisible within the duration, false
   *         otherwise
  
   */
  abstract boolean waitAndCheckIsInVisible(String xpath, Duration duration);
  /**
   * Waits for the element identified by XPath to become visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
  
   */
  abstract void waitForElementToBeVisible(String xpath, Duration duration);
  /**
   * Waits for the element identified by XPath to become invisible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
  
   */
  abstract void waitForElementToBeInvisible(String xpath, Duration duration);

  /**
   * Waits for the element identified by XPath to become clickable.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
  
   */
  abstract void waitForElementToBeClickable(String xpath, Duration duration);
  /**
   * Returns the current page source
   * 
   * @return String page source
  
   */
  abstract String getPageSource();
  /**
   * Customizes the XPath pattern by replacing placeholder values with the
   * provided values.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value
   *            The value to replace the placeholder.
   * @return The customized XPath.
  
   */
  public static String customizeXpath(String rawXpath, String value) {
    return rawXpath.replace("v1", value);
  }

  /**
   * Customizes the XPath pattern by replacing placeholder values with the
   * provided values.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value1
   *            The first value to replace the first placeholder.
   * @param value2
   *            The second value to replace the second placeholder.
   * @return The customized XPath.
  
   */
  public static String customizeXpath(String rawXpath, String value1, String value2) {
    return rawXpath.replace("v1", value1).replace("v2", value2);
  }

  /**
   * Customizes the XPath pattern by replacing placeholder values with the
   * provided values.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value1
   *            The first value to replace the first placeholder.
   * @param value2
   *            The second value to replace the second placeholder.
   * @param value3
   *            The third value to replace the third placeholder.
   * @return The customized XPath.
  
   */
  public static String customizeXpath(String rawXpath, String value1, String value2, String value3) {
    return rawXpath.replace("v1", value1).replace("v2", value2).replace("v3", value2);
  }
}