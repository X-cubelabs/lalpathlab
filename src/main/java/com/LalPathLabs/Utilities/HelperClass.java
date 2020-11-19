package com.LalPathLabs.Utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class HelperClass extends BaseClass {

	static WebDriver driver;
	static JavascriptExecutor js;
	static Properties param;

	public HelperClass(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = js;
	}

	public static String getBeforeCssRGB(By locator, int timeout) {
		WebElement colorElement = driver.findElement(locator);

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		// waiting until the webelement located with in the specified time @param
		// timeout and assign to element variable
		colorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		// Using the computed style property name to get RGB value for check box / radio
		// button
		String colorRGB = ((JavascriptExecutor) driver)
				.executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('color');",
						colorElement)
				.toString();

		return colorRGB;
	}

	/*
	 * To get the CssValue property name Mostly used for getting color border value
	 * for web elements
	 */

	public static String getElementCssValue(By locator, int timeout, String propertyName) {
		WebElement elementColor = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		// waiting until the webelement located with in the specified time @param
		// timeout and assign to element variable
		elementColor = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		// get the RBG value using CssValue property
		String cssValue = elementColor.findElement(locator).getCssValue(propertyName);

		return cssValue;
	}

	/*
	 * To hit enter button on keyboard automatically while executing
	 */

	public static void hitKeyboardEnter(By locator, int timeout) {
		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// waiting until the webelement located with in the specified time @param
		// timeout and assign to element variable
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		// Hit enter on the located element
		element.findElement(locator).sendKeys(Keys.ENTER);
	}

	/*
	 * To verify if the check box is selected / unselected
	 * 
	 * boolean return "true" if check box is selected
	 * 
	 * return "false" is check box is unselected
	 * 
	 * 
	 */

	public static boolean checkBoxValidationWhenReady(By locator, int timeout) {
		WebElement element = driver.findElement(locator);
		boolean status = element.isSelected();
		return status;
	}

	public static boolean isEnabledStatus(By locator) {
		WebElement element = driver.findElement(locator);
		boolean status = element.isEnabled();
		return status;
	}

	public static void captureScreenShot(ITestResult result, String status) {
		String destDir = "";
		String methodName = result.getMethod().getRealClass().getSimpleName() + "."
				+ result.getMethod().getMethodName();
		System.out.println("Method Name:" + methodName);
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

			// If status = fail then set folder name "screenshots/Failures"
			if (status.equalsIgnoreCase("fail")) {
				destDir = "/Screenshots/Failures";
			}
			// If status = pass then set folder name "screenshots/Success"
			else if (status.equalsIgnoreCase("pass")) {
				destDir = "/Screenshots/Success";
			}

			// To create folder to store screenshots
			new File(destDir).mkdirs();

			// Set file name with combination of test class name + date time.
			String destFile = methodName + " - " + dateFormat.format(new Date()) + ".png";

			FileHandler.copy(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot or copying the files to destination folder:" + ""
					+ e.toString());
		}
	}

	public static void WaitImplicitlyForElement() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static Properties readParam() throws Exception {
		param = new Properties();
		FileInputStream fip = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/com/UpShot/Utilities/Upshot_Param.properties");
		param.load(fip);
		System.out.println("Param.properties file loaded successfully.");
		return param;
	}

	/// Extent reports methods
	public static void SuccessReport(String methodname, String msg) {
		test = extent.startTest(methodname);
		// Assert.assertTrue(true);
		test.log(LogStatus.PASS, msg);
		extent.flush();
	}

	public static void SuccessReport(String msg) {
		// Assert.assertTrue(true);
		test.log(LogStatus.PASS, msg);
		extent.flush();
	}

	public static void FailureReport(String methodname, String msg) {
		test = extent.startTest(methodname);
		// Assert.assertTrue(true);
		test.log(LogStatus.FAIL, msg);
		extent.flush();
	}

	public static void FailureReport(String msg) {

		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		// ExtentTestManager.getTest().log(LogStatus.FAIL,"Test
		// Failed",ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		test.log(LogStatus.FAIL, test.addBase64ScreenShot(base64Screenshot));
		// extent.flush();
		test.log(LogStatus.FAIL, msg);
		extent.flush();

		// //Assert.assertTrue(true);
		// test.log(LogStatus.FAIL, msg);
		// extent.flush();
	}

	public static void logInfo(String msg) {

		// Assert.assertTrue(true);
		test.log(LogStatus.INFO, msg);
		extent.flush();
	}

	public static void logInfo(String methodname, String msg) {

		test = extent.startTest(methodname);

		// Assert.assertTrue(true);
		test.log(LogStatus.INFO, msg);
		extent.flush();
	}
	// extend report methods finish

	public static void checkIfDisplayed(By locator) {
		WebElement elementIsDisplayed = driver.findElement(locator);
		elementIsDisplayed.isDisplayed();
	}

	public static void JSelementScrollIntoView(By locator, int timeout) {
		WebElement elementToScroll = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// waiting until the webelement located with in the specified time @param
		// timeout and assign to element variable
		elementToScroll = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		elementToScroll.findElement(locator);

		// Scroll the browser till element's visibility
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToScroll);
	}

	public static void JSelementScrollTop() {
		// wait till the DOM loads complete
		((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

		// Scroll the browser till top
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-1000)", "");
	}

	public static void JSelementScrollBottom() {
		// wait till the DOM loads complete
		((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

		// Scroll the browser till bottom
		// ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,
		// document.body.scrollHeight)");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)", "");
	}

	public static void JSclickWebelement(By locator) {

		WebElement elementToClick = driver.findElement(locator);

		// Scroll the browser to the element's Y position

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + elementToClick.getLocation().y + ")");

		// Click the element

		elementToClick.click();
	}

	/**
	 * Enter data in the WebElemet when the expected condition is met
	 * 
	 * @param locator
	 * @param timeout
	 */

	public static void enterWhenVisible(By locator, int timeout, String data) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// waiting until the webelement located with in the specified time @param
		// timeout and assign to element variable
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		// to enter the data into text field
		element.sendKeys(data);
		element = null;
	}

	/**
	 * To get the text of the Webelement when the expected is met and return the
	 * text
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public static String getTextWhenVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// waiting until the webelement located with in the specified time @param
		// timeout and assign to element variable

		String LblTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
		return LblTxt;
	}

	/**
	 * Returns the text when element is displayed in the page
	 * 
	 * @param locator
	 * @return
	 */
	public static String getTextVisible(By locator) {
		WebElement element = driver.findElement(locator);
		String LblTxt = "";
		// verify whether Webelement display or not , if element displayed then assign
		// the Text to variable and return
		if (element.isDisplayed()) {
			LblTxt = element.getText();
		}
		return LblTxt;
	}

	// checks for the display of element when expected condition is met
	public static void checkIfDisplayedWhenReady(By locator, int timeout) {
		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.isDisplayed();
		element = null;
	}

	public static void checkIfEnabledWhenReady(By locator, int timeout) {
		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.isEnabled();
		element = null;
	}

	/**
	 * Clicks on the WebElement when the expected condition is met
	 * 
	 * @param locator
	 * @param timeout
	 */

	public static void clickWhenReady(By locator, int timeout) {
		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
		element = null;
	}

	/**
	 * Clicks on the WebElement when the element is visible is met
	 * 
	 * @param locator
	 * @param timeout
	 */
	public static void clickWhenVisible(By locator, int time) {
		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(driver, time);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
	}

	/**
	 * Click on the WebElement when enabled
	 * 
	 * @param locator
	 */
	public static void clickWebelement(By locator) {

		WebElement element = driver.findElement(locator);

		boolean elementIsClickable = element.isEnabled();

		if (elementIsClickable) {

			element.click();

			element = null;
		} else {
			System.out.print(locator.toString() + ":Element not clickable");
		}
	}

	/**
	 * click the Webelement as Located with following @params
	 * 
	 * @param locator1
	 * @param locator2
	 */
	public static void selectDropdownWebElement(By locator1, By locator2) {
		// get the webelement and assign
		WebElement element1 = driver.findElement(locator1);
		element1.click();
		WebElement element2 = driver.findElement(locator2);
		element2.click();
	}

	/**
	 * Select value from dropdown
	 * 
	 * @param locator
	 * @param data
	 */
	public static void selectDropdownWebElement(By locator, String data) {
		WebElement element = driver.findElement(locator);
		Select dropdown = new Select(element);
		try {
			dropdown.selectByVisibleText(data);
		} catch (Exception e1) {
			try {
				dropdown.selectByValue(data);
			} catch (Exception e2) {
				try {
					dropdown.selectByIndex(Integer.valueOf(data));
				} catch (Exception e3) {
				}
			}
		}
	}

	/**
	 * Select value from choose and select dropdown
	 * 
	 * @param locator1
	 * @param locator2
	 * @param locator3
	 * @param data
	 */
	public static void selectChooseandSelectWebElement(By locator1, By locator2, By locator3, String data) {
		WebElement element = driver.findElement(locator1);
		element.click();
		WebElement element2 = driver.findElement(locator2);
		element2.sendKeys(data);
		WebElement element3 = driver.findElement(locator3);
		element3.click();
	}

	/**
	 * 
	 * Enter data in WebElement
	 * 
	 * @param locator
	 * @param data
	 * @throws Exception
	 */

	public static void enterWebElement(By locator, String data) throws Exception {
		driver.findElement(locator).sendKeys(data);

		System.out.println(data + " : entered");

		Thread.sleep(2000);
		// String text = element.getAttribute("value");

		// getting the value from text field and verify whether field is empty or not if
		// empty clear the field

		// if (text.isEmpty()) {
		// //clearing the text webelement
		// element.clear();
		// try {
		// element.clear();
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		//
		// e.printStackTrace();
		// }
		//
		//
		// } else {
		// element.clear();
		// //enter the data into text field
		//
		// element.sendKeys(data);
		// }
		// element = null;
	}

	/**
	 * Upload File
	 * 
	 * @param locator
	 * @param filepath
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public static void uploadFile(By locator, String filepath) throws AWTException, InterruptedException {
		WebElement element = driver.findElement(locator);
		element.click();
		StringSelection ss = new StringSelection(filepath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
	}

	public static void pageDown() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	// public static void uploadFile(By locator, String filepath) throws
	// AWTException, InterruptedException {
	// WebElement element = driver.findElement(locator);
	// element.click();
	//
	// StringSelection ss = new StringSelection(filepath);
	// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	// Robot robot;
	// robot = new Robot();
	// robot.keyPress(KeyEvent.VK_ENTER);
	// robot.keyRelease(KeyEvent.VK_ENTER);
	// robot.delay(2000);
	// robot.keyPress(KeyEvent.VK_CONTROL);
	// Thread.sleep(1000);
	// robot.keyPress(KeyEvent.VK_V);
	// Thread.sleep(1000);
	// robot.keyRelease(KeyEvent.VK_V);
	// robot.keyRelease(KeyEvent.VK_CONTROL);
	// robot.keyPress(KeyEvent.VK_ENTER);
	// robot.keyRelease(KeyEvent.VK_ENTER);
	// Thread.sleep(1000);
	// }

	/**
	 * Method to verify whether page is loaded successfully or not
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean checkPageComplete() throws Exception {
		boolean state = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This loop will rotate for 10 times to check If page Is ready after every 1
		// second.
		for (int i = 0; i < 9; i++) {
			try {
				Thread.sleep(1000);
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					state = true;
					System.out.println("Page loaded successfully");
					break;
				}
			} catch (Exception e) {
				System.out.print("Page cannot be loaded...");
				throw (e);
			}
		}
		return state;
	}

	public static void directSelectChooseandSelectWebElement(By locator1, By locator2) {
		WebElement element1 = driver.findElement(locator1);
		element1.click();
		WebElement element2 = driver.findElement(locator2);
		element2.click();

	}

	/**
	 * Method to direct selecting the dropdown values as one after one
	 * 
	 * @param locator1
	 * @param locator2
	 * @param locator3
	 * @param data
	 * @param timeout
	 */
	public static void enterWhenVisible(By locator1, By locator2, By locator3, String data, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator1));
		element.click();
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator2));
		element.sendKeys(data);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator3));
		element.click();
		element = null;

	}

	/**
	 * Date Selection from date picker
	 * 
	 * @param locator
	 * @param Date
	 * @throws InterruptedException
	 */
	public static void selectDateFromDatePiker(By locator, String Date) throws InterruptedException {
		String[] splitdate = Date.split("-");
		String date = splitdate[0];
		String month = splitdate[1];
		String year = splitdate[2];
		// find the date picker
		WebElement element = driver.findElement(locator);
		element.click();
		// get the month dropdown from date picker
		Select monthDropdown = new Select(driver.findElement(By.cssSelector("select.ui-datepicker-month")));
		monthDropdown.selectByVisibleText(month);
		Thread.sleep(3000);
		// get the year dropdown from date picker
		Select yearDropDown = new Select(driver.findElement(By.cssSelector("select.ui-datepicker-year")));
		yearDropDown.selectByVisibleText(year);
		driver.findElement(By.linkText(date)).click();
	}

	/**
	 * To perform the mouse actions like hover
	 * 
	 * @param locator1
	 * 
	 */

	public static void mouseHover(By locator1) {
		Actions action = new Actions(driver);

		try {
			WebElement element1 = driver.findElement(locator1);
			action.moveToElement(element1).build().perform();
		} catch (Exception e) {
			WebElement element1 = driver.findElement(locator1);
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
		}
	}

	/**
	 * To perform the mouse actions like move and click
	 * 
	 * @param locator1
	 * @param locator2
	 * 
	 */

	public static void mouseHoverThenClick(By locator1, By locator2) {
		Actions action = new Actions(driver);
		// find Webelement 1 with locator1
		WebElement element1 = driver.findElement(locator1);
		action.moveToElement(element1).build().perform();
		// find Webelement 2 with locator2
		try {
			WebElement element2 = driver.findElement(locator2);
			element2.click();
		} catch (Exception e) {
			WebElement element2 = driver.findElement(locator2);
			js.executeScript("arguments[0].scrollIntoView();", element2);
			element2.click();
		}
	}

	/**
	 * Click with moving to the element.
	 * 
	 * @param locator
	 */
	public static void mouseOverClick(By locator) {
		Actions action = new Actions(driver);
		// find Webelement 1 with locator1
		WebElement element1 = driver.findElement(locator);
		action.moveToElement(element1).perform();
		element1.click();
	}

	/**
	 * To get the list of elements from the webpage and return the complete list
	 * 
	 * @param locator1
	 * 
	 * 
	 */

	public static List<WebElement> getElements(By locator) {
		List<WebElement> elementList = null;
		// find the list of same type of elements and assign to the variable
		try {
			elementList = driver.findElements(locator);
		} catch (Exception e) {
			// TODO: handle exception
			return null;

		}
		return elementList;
		// return WebElement;
	}

	public static void pageRefresh() throws InterruptedException {

		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	public static String getPageSource() {

		String content = driver.getPageSource();

		return content;

	}

	@SuppressWarnings("unchecked")
	public static List<WebElement> getElementsWhenVisible(By locator, int timout) {

		List<WebElement> elementList = null;
		// find the list of same type of elements and assign to the variable
		WebDriverWait wait = new WebDriverWait(driver, timout);
		try {
			elementList = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

		return elementList;
		// return WebElement;

	}

	public static String getAttribute(By locator, String attributename) {
		String attribute = "";

		attribute = driver.findElement(locator).getAttribute(attributename);

		return attribute;
	}

	public static WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public static WebElement getElementWhenVisible(By locator, int timout) {
		WebElement element;

		WebDriverWait wait = new WebDriverWait(driver, timout);

		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

		return element;
		// return WebElement;
	}

	public static void Clearelement(By locator) {

		driver.findElement(locator).clear();
	}

	public static void EnterElement(By locator) {

		driver.findElement(locator).sendKeys(Keys.ENTER);
	}

	public static void scrolling(By locator) {
		Actions dragger = new Actions(driver);

		WebElement draggablePartOfScrollbar = driver.findElement(locator);

		// System.out.println(draggablePartOfScrollbar);
		int numberOfPixelsToDragTheScrollbarDown = 5000;
		dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
				.moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release().perform();
		System.out.println("scroling 3");

	}

	public static boolean elementStatus(By locator) {
		boolean status = false;
		try {
			status = driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			// System.out.println("eleemnt not displayed");
		}

		return status;

	}

	public static void CleartextBox(By locator) {

		driver.findElement(locator).clear();

	}

	public static void clearWhenReady(By locator, int timeout) {
		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element = null;
	}

	public void Alertaccept() {

		Alert myalert = driver.switchTo().alert();
		myalert.accept(); // for click OK Button on alert
		driver.switchTo().defaultContent();
	}

	public void dismissAlert() {
		Alert myalert = driver.switchTo().alert();
		myalert.dismiss(); // for click dismiss Button on alert
		driver.switchTo().defaultContent();
	}

	public static void TakeScreenShot(String msg) {
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		// ExtentTestManager.getTest().log(LogStatus.FAIL,"Test
		// Failed",ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		test.log(LogStatus.INFO, msg, test.addBase64ScreenShot(base64Screenshot));

		extent.flush();
	}

	public static WebElement Frame(String FrameID, By locator) {
		// we can include frame Id, Index or value
		System.out.println("line 831 ");
		WebElement Frameswitching = driver.switchTo().frame(FrameID).findElement(locator);
		System.out.println("line 833 ");

		return Frameswitching;

		// WebElement element = null;
		// WebDriverWait wait = new WebDriverWait(driver, timeout);
		// element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void dragSlider(WebElement locator, int xOffset, int yOffset) throws Exception {
		Actions moveSlider = new Actions(driver);
		Action action = moveSlider.clickAndHold(locator).moveByOffset(xOffset, yOffset).release().build();
		action.perform();
		Thread.sleep(500);
	}

	public static void selectVisibleText(String value) throws Exception {
		WebElement ele = null;
		By locator = By.xpath("//*[text()[contains(.,'" + value + "')]]");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		ele.click();
	}

	public static void scrollToElement(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void waitForInvisiblility(By locator, int time) {
		WebElement ele = driver.findElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, time);
		boolean falg = wait.until(ExpectedConditions.invisibilityOf(ele));
		if (!falg) {
			WebDriverWait wait1 = new WebDriverWait(driver, time);
			wait1.until(ExpectedConditions.invisibilityOf(ele));
		}
	}

	public static void dropDownSelect(By locator, String value) {
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public static boolean isVisible(By locator) {
		return driver.findElements(locator).size() > 0 ? true : false;
	}

	public static void clickAvailable(By locator) {
		List<WebElement> list = driver.findElements(locator);
		for (WebElement ele : list) {
			if (ele.isEnabled()) {
				ele.click();
				break;
			}
		}
	}
}
