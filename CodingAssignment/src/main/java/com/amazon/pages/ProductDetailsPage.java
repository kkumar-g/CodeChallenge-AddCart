package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductDetailsPage 
{
	WebDriver driver;

	//Constructor
	public ProductDetailsPage(WebDriver driver)
	{
		this.driver = driver;
        AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);
    }
	
	//WebElements
	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	WebElement addToCartBtn;
	
	@FindBy(xpath = "//div[contains(@class,'added-to-cart') or contains(@class,'color-success')]//*[contains(tdext(),'Added to Cart')]")
	List<WebElement> addedToCartMsg;
	
	//Functions
	//This function adds products to cart. In case of any popups, it close the popup
	public boolean clickAddToCart()
	{
		boolean addedSuccessfully=false;
		addToCartBtn.click();
		//Checking for popup
		List<WebElement> popup= driver.findElements(By.xpath("//div[@class='a-popover a-popover-modal a-declarative']//button[@data-action='a-popover-close']"));
		if(popup.size()>0)
		{
			System.out.println("Popup found. Clicking on continue it..");
			//driver.findElement(By.xpath("//div[@class='a-popover a-popover-modal a-declarative']//button[@data-action='a-popover-close']")).click();
			driver.findElement(By.xpath("//div[contains(@class,'a-popover-modal')]//input[@class='a-button-input']")).click();
		}
		
		By parthForSuccessMsg= By.xpath("//div[contains(@class,'added-to-cart') or contains(@class,'color-success')]//*[contains(text(),'Added to Cart')]");
		
		if(driver.findElements(parthForSuccessMsg).size()>0)
			addedSuccessfully=true;
		
		return addedSuccessfully;
	}
}
