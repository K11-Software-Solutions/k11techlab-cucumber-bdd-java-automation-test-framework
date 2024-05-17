package bdd.mobiletests.makemytripapp.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.FlightsPage;
import org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.MenuPage;

import static org.k11techlab.bdd.mobiletestframework.base.appiumengine.AppiumEngine.getDriver;

/**
 * Step Definitions for Flights Page Tests
 *

 */
public class FlightsPageSteps {
    MenuPage menuPage = new MenuPage(getDriver());
    FlightsPage flightsPage = new FlightsPage(getDriver());

    @Then("User clicks on LOB {string}")
    public void userClicksOnLOB(String lobName) {
        menuPage.clickOnLob(lobName);
    }

    @Then("Flights page should be opened")
    public void flightsPageShouldBeOpened() {
        Assert.assertTrue(flightsPage.isFlightsPageHeaderDisplayed());
    }

    @Then("{string} tab is Auto Selected")
    public void tabIsAutoSelected(String flightSearchTab) {
        Assert.assertTrue(flightsPage.isTabSelected(flightSearchTab));
    }

    @Then("{string} input box is visible")
    public void inputBoxIsVisible(String item) {
        Assert.assertTrue(flightsPage.isInputBoxDisplayed(item));
    }

    @Then("Search Flights button should be visible")
    public void searchFlightsButtonShouldBeVisible() {
        Assert.assertTrue(flightsPage.isSearchFlightsButtonDisplayed());
    }

    @Then("User closes the Menu Drawer if opened")
    public void userClosesTheMenuDrawerIfOpened() {
        if (menuPage.isMenuDrawerDisplayed())
            menuPage.closeMenuDrawer();
    }

    @And("Go back to Home Page")
    public void goBackToHomePage() {
        flightsPage.goBackToHome();
    }
}
