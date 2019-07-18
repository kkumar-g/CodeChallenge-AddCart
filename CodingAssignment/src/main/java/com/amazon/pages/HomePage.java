package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

public class HomePage 
{

	WebDriver driver;
	
	//Constructor
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
        AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);

    }
	
	//WebElements declararion
	
	@FindBy(xpath="//input[@name='field-keywords']")
	WebElement searchTxtBox;
	
	@FindBy(xpath="//input[@value='Go']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//span[contains(@class,'nav-sprite nav-logo-base')]")
	WebElement logo;
	
	@FindBy(xpath="//div[@class='nav-right']//span[contains(@class,'nav-cart-icon nav-sprite')]")
	WebElement cartIcon;
	
	//Functions related to page actions
	//This function enters the search keyword in the search textbox and perform search operation
	public void eneterSearchKeywordAndClickSearch(String keywordToSearchFor)
	{
		Reporter.log("INFO:"+"Entering the Keyword:"+keywordToSearchFor+" to search");
		searchTxtBox.sendKeys(keywordToSearchFor);
		searchBtn.click();
	}
	//Clicking on Amazon logo
	public void clickLogo()
	{
		logo.click();
	}
	
	//Clicking on Cart Icon in the home page
	public void clickCartIcon()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(cartIcon).click().perform();
	}
}
