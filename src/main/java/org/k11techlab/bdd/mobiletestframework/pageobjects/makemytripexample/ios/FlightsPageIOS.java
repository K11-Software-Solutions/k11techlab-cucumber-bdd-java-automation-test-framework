package org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.ios;

import org.k11techlab.bdd.coreframework.WebUtil;
import org.k11techlab.bdd.coreframework.gobalconstants.constants;
import org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.base.FlightsPageBase;
import org.openqa.selenium.By;


/**
 * Class to maintain elements of Flights page for IOS
 *
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class FlightsPageIOS extends FlightsPageBase {
    private final String flightsHeader = constants.EMPTY_STRING;
    private final String tab = constants.EMPTY_STRING;
    private final String from = constants.EMPTY_STRING;
    private final String fromSelectedCity = constants.EMPTY_STRING;
    private final String fromSelectedCityCode = constants.EMPTY_STRING;
    private final String fromSelectedAirport = constants.EMPTY_STRING;
    private final String to = constants.EMPTY_STRING;
    private final String toSelectedCity = constants.EMPTY_STRING;
    private final String toSelectedCityCode = constants.EMPTY_STRING;
    private final String toSelectedAirport = constants.EMPTY_STRING;
    private final String fromDate = constants.EMPTY_STRING;
    private final String fromDateSelected = constants.EMPTY_STRING;
    private final String fromDayAndYearSelected = constants.EMPTY_STRING;
    private final String returnDate = constants.EMPTY_STRING;
    private final String returnSelected = constants.EMPTY_STRING;
    private final String travelersAndClass = constants.EMPTY_STRING;
    private final String travelerSelected = constants.EMPTY_STRING;
    private final String classSelected = constants.EMPTY_STRING;
    private final String searchButton = constants.EMPTY_STRING;
    private final String departureCity = constants.EMPTY_STRING;
    private final String arrivalCity = constants.EMPTY_STRING;
    private final String suggestions = constants.EMPTY_STRING;
    private final String cityCode = constants.EMPTY_STRING;
    private final String cityName = constants.EMPTY_STRING;
    private final String airportName = constants.EMPTY_STRING;
    private final String backButton = constants.EMPTY_STRING;
    @Override
    public By getFlightsHeader() {
        return By.xpath(flightsHeader);
    }

    @Override
    public By getBackButton() {
        return By.xpath(backButton);
    }
    @Override
    public By getTab(String tabName) {
        return By.xpath(WebUtil.customizeXpath(tab, tabName));
    }

    @Override
    public By getFrom() {
        return By.xpath(from);
    }

    @Override
    public By getFromSelectedCity() {
        return By.xpath(fromSelectedCity);
    }

    @Override
    public By getFromSelectedCityCode() {
        return By.xpath(fromSelectedCityCode);
    }

    @Override
    public By getFromSelectedAirport() {
        return By.xpath(fromSelectedAirport);
    }

    @Override
    public By getTo() {
        return By.xpath(to);
    }

    @Override
    public By getToSelectedCity() {
        return By.xpath(toSelectedCity);
    }

    @Override
    public By getToSelectedCityCode() {
        return By.xpath(toSelectedCityCode);
    }

    @Override
    public By getToSelectedAirport() {
        return By.xpath(toSelectedAirport);
    }

    @Override
    public By getFromDate() {
        return By.xpath(fromDate);
    }

    @Override
    public By getFromDateSelected() {
        return By.xpath(fromDateSelected);
    }

    @Override
    public By getFromDayAndYearSelected() {
        return By.id(fromDayAndYearSelected);
    }

    @Override
    public By getReturnDate() {
        return By.xpath(returnDate);
    }

    @Override
    public By getReturnSelected() {
        return By.xpath(returnSelected);
    }

    @Override
    public By getTravelersAndClass() {
        return By.xpath(travelersAndClass);
    }

    @Override
    public By getTravelerCount() {
        return By.xpath(travelerSelected);
    }

    @Override
    public By getClassSelected() {
        return By.xpath(classSelected);
    }

    @Override
    public By getSearchButton() {
        return By.xpath(searchButton);
    }

    @Override
    public By getDepartureCity() {
        return By.xpath(departureCity);
    }

    @Override
    public By getArrivalCity() {
        return By.xpath(arrivalCity);
    }

    @Override
    public By getSuggestions() {
        return By.xpath(suggestions);
    }

    @Override
    public By getCityCode() {
        return By.xpath(cityCode);
    }

    @Override
    public By getCityName() {
        return By.xpath(cityName);
    }

    @Override
    public By getAirportName() {
        return By.xpath(airportName);
    }
}