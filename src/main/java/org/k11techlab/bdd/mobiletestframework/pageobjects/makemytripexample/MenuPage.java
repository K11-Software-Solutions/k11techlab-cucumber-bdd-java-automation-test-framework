package org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample;

import io.appium.java_client.AppiumDriver;

import java.util.List;

/**
 * Page Object class for Menu Drawer page to have modular methods to handle element interactions
 *
 */
@SuppressWarnings("unused")
public class MenuPage extends HomePage {
    /**
     * Constructor to initialize AppiumUtils.
     *
     * @param driver The Appium Driver (AndroidDriver/IOSDriver) instance to use.
     */
    public MenuPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Click on the hamburger menu icon.
     */
    public void clickOnHamburgerMenu() {
        click(homePage.getDrawerButton());
    }

    /**
     * Check if the menu drawer is displayed.
     *
     * @return true if the menu drawer is displayed, false otherwise.
     */
    public boolean isMenuDrawerDisplayed() {
        return isVisible(homePage.getMenuDrawer());
    }

    /**
     * Close the menu drawer by swiping left.
     *
     */
    public void closeMenuDrawer() {
        swipeLeft(homePage.getMenuDrawer());
    }

    /**
     * Check if the login/sign-up button is displayed in the menu drawer.
     *
     * @return true if the login/sign-up button is displayed, false otherwise.
     */
    public boolean isLoginSignUpButtonDisplayed() {
        return isVisible(homePage.getLoginSignUpButton());
    }

    /**
     * Get the text of primary items in the menu drawer.
     *
     * @return a list containing the text of primary items in the menu drawer.
     */
    public List<String> drawerPrimaryItems() {
        return getElementsTextContent(homePage.getPrimaryItemsInMenuDrawer(), true);
    }

    /**
     * Check if a section is displayed on the menu drawer.
     *
     * @param section The name of the section to check.
     * @return true if the section is displayed, false otherwise.
     * @throws IllegalArgumentException if the section name is invalid.
     */
    public boolean isSectionDisplayedOnMenuDrawer(String section) {
        switch (section.toLowerCase()) {
            case "my trips":
                return isVisible(homePage.getMyTripsSection());
            case "rewards":
                return isVisible(homePage.getRewardsSection());
            case "settings":
                return isVisible(homePage.getSettingsSection());
            default:
                throw new IllegalArgumentException("Invalid section: " + section);
        }
    }

    /**
     * Get the text of items under a section in the menu drawer.
     *
     * @param sectionName The name of the section.
     * @return a list containing the text of items under the section.
     * @throws IllegalArgumentException if the section name is invalid.
     */
    public List<String> getItemsUnderSection(String sectionName) {
        switch (sectionName.toLowerCase()) {
            case "my trips":
                return getElementsTextContent(homePage.getMyTripsItems());
            case "rewards":
                return getElementsTextContent(homePage.getRewardsItems());
            case "settings":
                return getElementsTextContent(homePage.getSettingsItems());
            default:
                throw new IllegalArgumentException("Invalid section: " + sectionName);
        }
    }

    /**
     * Verify if a specific item is displayed at the bottom of the menu drawer.
     *
     * @param item The name of the item to verify.
     * @return true if the item is displayed, false otherwise.
     * @throws IllegalArgumentException if the item name is invalid.
     */
    public boolean verifyMenuDrawerBottom(String item) {
        switch (item.toLowerCase()) {
            case "rate us link":
                return isVisible(homePage.getMenuDrawerBottomLinks("Rate Us"));
            case "privacy policy link":
                return isVisible(homePage.getMenuDrawerBottomLinks("Privacy Policy"));
            case "app version":
                return isVisible(homePage.getMenuDrawerBottomLinks("App Version"));
            default:
                throw new IllegalArgumentException("Invalid Item: " + item);
        }
    }

    /**
     * Get the app version displayed at the bottom of the menu drawer.
     *
     * @return the app version.
     */
    public String getAppVersion() {
        return getElement(homePage.getMenuDrawerBottomLinks("App Version"))
                .getText().split("Version ")[1];
    }
}