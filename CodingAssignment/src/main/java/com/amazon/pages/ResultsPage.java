package com.amazon.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

public class ResultsPage 
{
	WebDriver driver;
	ProductDetailsPage prodDetails;
	
	//Constructor
	public ResultsPage(WebDriver driver)
	{
		this.driver = driver;
        AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);

    }
	
	//WebElements
	@FindBy(xpath="//div[@class='a-row a-badge-region']//span[text()='Best Seller']//following::div[1]//h2/a")
	List<WebElement> bestSellers;
	
	
	//Functions
	//This function captures the product results based on the 'Best seller' badge and navigates to each product and adds it to the cart
	public void navigateToEachResultAndAdditToCart()
	{
		prodDetails= new ProductDetailsPage(driver);
		Iterator<WebElement> iter = bestSellers.iterator();
		String url;
		List<String> urlList= new ArrayList<String>();
		boolean addFlag=false;
		//Storing all the links of the products in a list
		while(iter.hasNext())
		{
			WebElement element= iter.next();
			url=element.getAttribute("href");
			urlList.add(url);
		}
		
		System.out.println("NoOf results:"+urlList.size());
		Set<String> uniqueLinks= new HashSet<String>();
		//Looping through each products link and add the product to the cart
		for(int i=0;i<urlList.size();i++)
		{
			System.out.println(urlList.get(i));
			if(!uniqueLinks.contains(urlList.get(i)))
			{
				uniqueLinks.add(urlList.get(i));
				Reporter.log("INFO:"+"Navigating to the product details page of the product with URL:"+urlList.get(i));
				driver.get(urlList.get(i));
				Reporter.log("INFO:"+"Adding the item cart");
				addFlag=prodDetails.clickAddToCart();
				if(addFlag)
				{
					Reporter.log("INFO:"+"Item has been added to cart successfully");
					Reporter.log("INFO:"+"Navigating back to results page");
					driver.navigate().back();
				}
				else
				{
					Reporter.log("ERROR:"+"Item has not been added to cart. Trying to add it again..");
					i=i-1;
				}
			}
			else
			{
				System.out.println("Product has already been to the cart"+ urlList.get(i));
			}

			
		}
		
	}
	
}
