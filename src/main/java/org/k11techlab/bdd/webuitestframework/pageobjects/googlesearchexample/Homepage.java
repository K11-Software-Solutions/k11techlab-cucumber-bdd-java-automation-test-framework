package org.k11techlab.bdd.webuitestframework.pageobjects.googlesearchexample;

import io.appium.java_client.AppiumDriver;
import org.k11techlab.bdd.webuitestframework.commonUtil.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.k11techlab.bdd.coreframework.SeleniumUtils;
import java.io.IOException;

/**
 * class to interact with page
 *
 * This class has web elements of homepage which are used by HomePageHelper
 */
public class Homepage extends CommonMethods {

	public Homepage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public By searchbox = By.name("q");
	public By searchButton = By.xpath("(//input[@value='Google Search'])[2]");
	public By searchResults = By.xpath("//div[@id='tvcap']//div//div//a");
	public By imagesButton = By.xpath("//a[@data-sc='I']");
	public By imageContainer = By.id("islmp");
	
	

}
