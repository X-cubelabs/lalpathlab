package com.LalPathLabs.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.LalPathLabs.Utilities.HelperClass;

/*
 * 
 * This page will store the locators of Login Page 
 *
 */

public class LoginPage {

	WebDriver driver;
	JavascriptExecutor js;

	By username_text = By.id("user-input");
	By password_text = By.id("pass-input");
	By login_button = By.xpath("//*[@id=\"root\"]/switch/div/main/div[2]/div[2]/form/button/span[1]");

	public LoginPage(WebDriver driver, JavascriptExecutor js) {
		
		this.driver = driver;
		this.js = js;
	}
	
	public void Enter_UserName1(String uname) throws Exception{
		driver.findElement(username_text).sendKeys(uname);
	}
	
	public void Enter_Password1(String pass) throws Exception{
		driver.findElement(password_text).sendKeys(pass);
	}
	
	public void Click_LoginButton1() throws Exception{
		driver.findElement(login_button).click();
	}

	public void Enter_UserName(String uname) throws Exception {
		// username field validation

		try {

			HelperClass.Clearelement(username_text);
			HelperClass.enterWebElement(username_text, uname);
			HelperClass.SuccessReport("Entered username");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(username_text);
				HelperClass.enterWhenVisible(username_text, 5, uname);
				HelperClass.SuccessReport("Entered username");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
					"Timeout...Username field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Enter_Password(String pass) throws Exception {

		// password field validation
		try {
			HelperClass.Clearelement(password_text);
			HelperClass.enterWebElement(password_text, pass);
			HelperClass.SuccessReport("Entered password");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(password_text);
				HelperClass.enterWhenVisible(password_text, 5, pass);
				HelperClass.SuccessReport("Entered password");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Password field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Click_LoginButton() throws Exception {
		// login button validations
		try {
			HelperClass.clickWebelement(login_button);
			HelperClass.logInfo("Clicked on Login button");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(login_button, 5);
				HelperClass.logInfo("Clicked on Login button");
			}

			catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...loginButton cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

}
