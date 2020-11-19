package com.LalPathLabs.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.LalPathLabs.ExtentReports.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass extends ExtentManager {
	
	
	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static ExtentReports extent;
	public static ExtentTest test; 
	public static Properties Param;

	@BeforeTest
	public static void loadBrowser() throws Exception {
		System.out.print("started");
		try {
			System.out.println("param1");
			Param = readParam();
		} catch (FileNotFoundException fnd) {
			System.out.println("Param.properties does not exist in the path");
			throw (fnd);
		} catch (IOException io) {
			System.out.println("Unable to read Param.properties file");
			throw (io);
		}

		if (Param.getProperty("browser").equalsIgnoreCase("Chrome")) {
			try {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/BrowserDrivers/"
						+ Param.getProperty("windows") + "/chromedriver.exe");

				ChromeOptions options = new ChromeOptions();
				// options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(ChromeOptions.CAPABILITY, options);

				// set performance logger
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
				cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
				cap.setAcceptInsecureCerts(true);
				driver = new ChromeDriver();
				System.out.println("Chrome Driver Instance loaded successfully.");
			} catch (WebDriverException wde) {
				System.out.println("Chrome driver cannot be instantiated" + wde.toString());
				throw (wde);
			} catch (Exception ce) {
				System.out.println("Chrome driver instance failed" + ce.toString());
				throw (ce);
			}
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Param.getProperty("siteURL"));
	}

	public static Properties readParam() throws Exception {

		Param = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")
				+ "/src/main/java/com/LalPathLabs/Utilities/LalPathLabs_Param.properties");
		Param.load(fip);
		System.out.println("Param.properties file loaded successfully.");
		return Param;
	}

	@BeforeSuite
	public void begin() {

		extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReportResults.html", true);

	}

	// @AfterSuite
	public void tearDown() {
		driver.close();
		driver = null;
		Param = null;

	}
}
