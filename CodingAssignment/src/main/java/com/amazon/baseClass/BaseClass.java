package com.amazon.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;


public class BaseClass {

	private final static String FilePath = System.getProperty("user.dir")
			+ "/src/main/java/com/amazon/properties/config.Properties";

	static FileInputStream fs = null;

	public static Properties Config = null;

	String BrowserName;

	public static WebDriver driver;
	
    static ATUTestRecorder recorder;
    
	public static void fileSetup() {
		try {
			fs = new FileInputStream(new File(FilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config = new Properties();
		try {
			Config.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeTest
	public void Setup() throws Exception{

		fileSetup();
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		recorder = new ATUTestRecorder(System.getProperty("user.dir"),"TestCart-"+dateFormat.format(date),false);
		recorder.start();
		
		BrowserName = Config.getProperty("BrowserName");

		if (BrowserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			driver = new ChromeDriver(options);

			Reporter.log(BrowserName + " Opened");
			driver.manage().window().maximize();
		} else if (BrowserName.equalsIgnoreCase("Firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver.exe");

			driver = new FirefoxDriver();
			Reporter.log(BrowserName + " Opened");
			// log.info(BrowserName+" Opened");
			driver.manage().window().maximize();
			Reporter.log(BrowserName + " Maximized");

		}  else {
			Reporter.log(BrowserName + " is invalid");

			throw new Exception("Invalid Browser Name");
		}
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("ImplicitWait")),
				TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void TearDown() throws ATUTestRecorderException {
		if (driver != null) {
			driver.quit();
			recorder.stop();
			
		}
	}

}
