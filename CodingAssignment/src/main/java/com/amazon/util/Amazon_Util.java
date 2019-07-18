package com.amazon.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.amazon.baseClass.BaseClass;

public class Amazon_Util extends BaseClass {

	static JavascriptExecutor je = (JavascriptExecutor) driver;

	public static void ClickElement(WebElement e) {
		e.click();
	}

	public static void SendKey(WebElement e, String s) {
		e.sendKeys(s);
	}

	public static void JavaScriptClick(WebElement e) {
		je.executeScript("arguments[0].click();", e);
		//Reporter.log("Clicked on element "+e);
	}

	public static void Explicitwait(int timeout, WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public static void JavaScriptEnterValue(String value, WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + value + "';", e);
		Reporter.log("Value entered in element "+e);
	}


	public static String takeScreenShot() throws Exception {
		File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Path=System.getProperty("user.dir")+"/ScreenShot/"+System.currentTimeMillis()+".png";
		
		FileUtils.copyFile(screenshotAs, new File(Path));
		
		return Path;
	}

}
