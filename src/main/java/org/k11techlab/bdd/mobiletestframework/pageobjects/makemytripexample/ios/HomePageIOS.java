package org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.ios;

import org.k11techlab.bdd.coreframework.WebUtil;
import org.k11techlab.bdd.coreframework.gobalconstants.constants;
import org.k11techlab.bdd.mobiletestframework.pageobjects.makemytripexample.base.HomePageBase;
import org.openqa.selenium.By;

/**
 * Class to maintain elements of Home page for IOS
 *
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class HomePageIOS extends HomePageBase {
    private final String selectYourLanguageLabel = constants.EMPTY_STRING;
    private final String continueButton = constants.EMPTY_STRING;
    private final String languageSkipButton = constants.EMPTY_STRING;
    private final String loginScreen = constants.EMPTY_STRING;
    private final String dismissButton = constants.EMPTY_STRING;
    private final String cta = constants.EMPTY_STRING;
    private final String ctaClose = constants.EMPTY_STRING;
    private final String completeActionUsingDevicePopUp = constants.EMPTY_STRING;
    private final String continueWithEmail = constants.EMPTY_STRING;
    private final String closeLoginAlert = constants.EMPTY_STRING;
    private final String adBar = constants.EMPTY_STRING;
    private final String adBarCloseButton = constants.EMPTY_STRING;
    private final String adText = constants.EMPTY_STRING;
    private final String drawerButton = constants.EMPTY_STRING;
    private final String myCash = constants.EMPTY_STRING;
    private final String myBiz = constants.EMPTY_STRING;
    private final String searchIcon = constants.EMPTY_STRING;
    private final String searchBar = constants.EMPTY_STRING;
    private final String logo = constants.EMPTY_STRING;
    private final String header = constants.EMPTY_STRING;
    private final String subHeader = constants.EMPTY_STRING;
    private final String primaryLob = constants.EMPTY_STRING;
    private final String primaryLobItems = constants.EMPTY_STRING;
    private final String primaryLobItem = constants.EMPTY_STRING;
    private final String secondaryLob = constants.EMPTY_STRING;
    private final String secondaryLobItems = constants.EMPTY_STRING;
    private final String secondaryLobItem = constants.EMPTY_STRING;
    private final String getSecondaryLobExpand = constants.EMPTY_STRING;
    private final String lobItem = constants.EMPTY_STRING;
    private final String HomeTab = constants.EMPTY_STRING;
    private final String BottomTabs = constants.EMPTY_STRING;
    private final String BottomTab = constants.EMPTY_STRING;
    private final String backButton = constants.EMPTY_STRING;

    @Override
    public By getSelectYourLanguageLabel() {
        return By.xpath(selectYourLanguageLabel);
    }

    @Override
    public By getContinueButton() {
        return By.xpath(continueButton);
    }

    @Override
    public By getLanguageSelectionSkipButton() {
        return By.id(languageSkipButton);
    }

    @Override
    public By getLoginScreen() {
        return By.xpath(loginScreen);
    }

    @Override
    public By getDismissButton() {
        return By.id(dismissButton);
    }
    @Override
    public By getCta() {
        return By.xpath(cta);
    }

    @Override
    public By getCtaClose() {
        return By.xpath(ctaClose);
    }
    @Override
    public By getCompleteActionUsingDevicePopUp() {
        return By.xpath(completeActionUsingDevicePopUp);
    }

    @Override
    public By getContinueWithEmail() {
        return By.xpath(continueWithEmail);
    }

    @Override
    public By getCloseLoginAlert() {
        return By.xpath(closeLoginAlert);
    }

    @Override
    public By getAdBar() {
        return By.id(adBar);
    }

    @Override
    public By getAdBarCloseButton() {
        return By.xpath(adBarCloseButton);
    }

    @Override
    public By getAdContent() {
        return By.id(adText);
    }

    @Override
    public By getDrawerButton() {
        return By.xpath(drawerButton);
    }

    @Override
    public By getMyCash() {
        return By.xpath(myCash);
    }

    @Override
    public By getMyBiz() {
        return By.xpath(myBiz);
    }

    @Override
    public By getSearchIcon() {
        return By.xpath(searchIcon);
    }

    @Override
    public By getSearchBar() {
        return By.id(searchBar);
    }

    @Override
    public By getMenuDrawer() {
        return null;
    }

    @Override
    public By getLoginSignUpButton() {
        return null;
    }

    @Override
    public By getPrimaryItemsInMenuDrawer() {
        return null;
    }

    @Override
    public By getMyTripsSection() {
        return null;
    }

    @Override
    public By getMyTripsItems() {
        return null;
    }

    @Override
    public By getRewardsSection() {
        return null;
    }

    @Override
    public By getRewardsItems() {
        return null;
    }

    @Override
    public By getSettingsSection() {
        return null;
    }

    @Override
    public By getSettingsItems() {
        return null;
    }

    @Override
    public By getMenuDrawerBottomLinks(String itemText) {
        return null;
    }

    @Override
    public By getLogo() {
        return By.id(logo);
    }

    @Override
    public By getHeader() {
        return By.id(header);
    }

    @Override
    public By getSubHeader() {
        return By.id(subHeader);
    }

    @Override
    public By getPrimaryLob() {
        return By.xpath(primaryLob);
    }

    @Override
    public By getPrimaryLobItems() {
        return By.xpath(primaryLobItems);
    }

    @Override
    public By getPrimaryLobItem(String lobName) {
        return By.xpath(WebUtil.customizeXpath(primaryLobItem, lobName));
    }

    @Override
    public By getSecondaryLob() {
        return By.xpath(secondaryLob);
    }

    @Override
    public By getSecondaryLobItems() {
        return By.xpath(secondaryLobItems);
    }

    @Override
    public By getSecondaryLobItem(String lobName) {
        return By.xpath(WebUtil.customizeXpath(secondaryLobItem, lobName));
    }

    @Override
    public By getLobItem(String lobName) {
        return By.xpath(WebUtil.customizeXpath(lobItem, lobName));
    }

    @Override
    public By getGetSecondaryLobExpand() {
        return By.xpath(getSecondaryLobExpand);
    }

    @Override
    public By getHomeTab() {
        return By.xpath(HomeTab);
    }

    @Override
    public By getBottomTabs() {
        return By.xpath(BottomTabs);
    }

    @Override
    public By getBottomTab() {
        return By.xpath(BottomTab);
    }

    @Override
    public By getBackButton() {
        return By.xpath(backButton);
    }
}