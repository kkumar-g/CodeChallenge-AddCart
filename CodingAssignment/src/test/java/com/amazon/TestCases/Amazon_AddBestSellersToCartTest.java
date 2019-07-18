package com.amazon.TestCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.amazon.baseClass.*;
import com.amazon.pages.HomePage;
import com.amazon.pages.ResultsPage;
import com.amazon.util.Amazon_Util;

public class Amazon_AddBestSellersToCartTest extends BaseClass
{
	HomePage homePage;
	ResultsPage resultsPage;
	
	@Test
	public void addtoCart() throws Exception
	{
		homePage= new HomePage(driver);
		resultsPage= new ResultsPage(driver);
		Reporter.log("*********** Test Started ***************");
		System.out.println("*********** Test Started ***************");
		driver.get(Config.getProperty("Testing_URL"));
		Reporter.log("INFO:"+"Navigating the URL:"+Config.getProperty("Testing_URL"));
		homePage.eneterSearchKeywordAndClickSearch("Headphones");
		resultsPage.navigateToEachResultAndAdditToCart();
		homePage.clickLogo();
		homePage.clickCartIcon();
		Amazon_Util.takeScreenShot();
		Reporter.log("INFO:"+"All the products have been added to cart successfully");
		Reporter.log("*********** End of the Test ***************");
		System.out.println("*********** End of the Test ***************");
	}
}
