package org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample;

import io.appium.java_client.AppiumDriver;
import org.k11techlab.bdd.coreframework.cucumberbddhelper.BDDCommonUtils;
import org.k11techlab.bdd.coreframework.logging.Log;
import org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.android.FlightsPageAndroid;
import org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.base.FlightsPageBase;
import org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.ios.FlightsPageIOS;
import org.k11techlab.bdd.mobiletestframework.base.appiumengine.AppiumEngine;

import java.time.Duration;

import static org.k11techlab.bdd.coreframework.gobalconstants.constants.APP_DATE_FORMAT;
import static org.k11techlab.bdd.coreframework.gobalconstants.constants.PREFERRED_DATE_FORMAT;

/**
 * Page Object class for Flights page to have modular methods to handle element interactions
 *
 */
@SuppressWarnings("unused")
public class FlightsPage extends HomePage {
    public FlightsPageBase flightsPage;

    /**
     * Constructor to initialize AppiumUtils.
     *
     * @param driver The Appium Driver (AndroidDriver/IOSDriver) instance to use.
     */
    public FlightsPage(AppiumDriver driver) {
        super(driver);
        flightsPage = AppiumEngine.isAndroid() ? new FlightsPageAndroid() : new FlightsPageIOS();
    }

    /**
     * Check if the Flights page header is displayed.
     *
     * @return true if the Flights page header is displayed, false otherwise.
     */
    public boolean isFlightsPageHeaderDisplayed() {
        return waitAndCheckIsVisible(flightsPage.getFlightsHeader(), Duration.ofSeconds(10));
    }

    /**
     * Check if the back button is displayed.
     *
     * @return true if the back button is displayed, false otherwise.
     */
    public boolean isBackButtonDisplayed() {
        return isVisible(flightsPage.getBackButton());
    }

    /**
     * Click on the back button.
     *
     */
    public void clickOnBackButton() {
        click(flightsPage.getBackButton());
    }

    /**
     * Click on a tab with the given name.
     *
     * @param tabName The name of the tab to click.
     */
    public void clickOnTab(String tabName) {
        click(flightsPage.getTab(tabName));
    }

    /**
     * Check if a tab with the given name is selected.
     *
     * @param tabName The name of the tab to check.
     * @return true if the tab is selected, false otherwise.
     */
    public boolean isTabSelected(String tabName) {
        return waitAndFindElement(flightsPage.getTab(tabName)).isSelected();
    }

    /**
     * Check if the departure input is displayed.
     *
     * @return true if the departure input is displayed, false otherwise.
     */
    public boolean isDepartureInputDisplayed() {
        return isVisible(flightsPage.getSearchButton());
    }

    /**
     * Check if the destination input is displayed.
     *
     * @return true if the destination input is displayed, false otherwise.
     */
    public boolean isDestinationInputDisplayed() {
        return isVisible(flightsPage.getSearchButton());
    }

    /**
     * Check if the from-date input is displayed.
     *
     * @return true if the from-date input is displayed, false otherwise.
     */
    public boolean isFromDateDisplayed() {
        return isVisible(flightsPage.getFromDate());
    }

    /**
     * Check if the traveller and class input is displayed.
     *
     * @return true if the traveller and class input is displayed, false otherwise.
    
     */
    public boolean isTravellerAndClassInputDisplayed() {
        return isVisible(flightsPage.getSearchButton());
    }

    /**
     * Check if the search flights button is displayed.
     *
     * @return true if the search flights button is displayed, false otherwise.
     */
    public boolean isSearchFlightsButtonDisplayed() {
        return isVisible(flightsPage.getSearchButton());
    }

    /**
     * Get the selected from city.
     *
     * @return the selected from city.
     */
    public String getSelectedFromCity() {
        return getElementTextContent(flightsPage.getFromSelectedCity());
    }

    /**
     * Get the selected from city code.
     *
     * @return the selected from city code.
     */
    public String getSelectedFromCityCode() {
        return getElementTextContent(flightsPage.getFromSelectedCityCode());
    }

    /**
     * Get the selected from city airport.
     *
     * @return the selected from city airport.
     */
    public String getSelectedFromCityAirport() {
        return getElementTextContent(flightsPage.getFromSelectedAirport());
    }

    /**
     * Get the selected to city.
     *
     * @return the selected to city.
     */
    public String getSelectedToCity() {
        return getElementTextContent(flightsPage.getToSelectedCity());
    }

    /**
     * Get the selected to city code.
     *
     * @return the selected to city code.
     */
    public String getSelectedToCityCode() {
        return getElementTextContent(flightsPage.getToSelectedCityCode());
    }

    /**
     * Get the selected to city airport.
     *
     * @return the selected to city airport.
     */
    public String getSelectedToCityAirport() {
        return getElementTextContent(flightsPage.getToSelectedAirport());
    }

    /**
     * Get the selected date.
     *
     * @return the selected date string.
     */
    public String getSelectedDate() {
        String dateAndMonth = getElementTextContent(flightsPage.getFromDateSelected());
        Log.info("Date and Month from App: " + dateAndMonth);
        String dayAndYear = getElementTextContent(flightsPage.getFromDayAndYearSelected());
        Log.info("Day and Year from App: " + dayAndYear);
        String dateFromApp = dateAndMonth + dayAndYear;
        if (dateFromApp.length() != 16)
            dateFromApp = "0" + dateFromApp;
        Log.info("Date From App: " + dateFromApp);
        return BDDCommonUtils.formatDate(dateFromApp, APP_DATE_FORMAT, PREFERRED_DATE_FORMAT);
    }

    /**
     * Check if the input box is displayed.
     *
     * @param inputBox The input box name.
     * @return true if the input box is displayed, false otherwise.
     */
    public boolean isInputBoxDisplayed(String inputBox) {
        switch (inputBox.toLowerCase()) {
            case "from":
            case "departure":
                return isDepartureInputDisplayed();
            case "to":
            case "destination":
                return isDestinationInputDisplayed();
            case "from date":
            case "departure date":
            case "return date":
                return isFromDateDisplayed();
            case "travellers & class":
            case "travellers and class":
                return isTravellerAndClassInputDisplayed();
            default:
                Log.error("Unexpected Value: " + inputBox);
                return false;
        }
    }
}