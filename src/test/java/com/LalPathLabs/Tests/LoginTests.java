package com.LalPathLabs.Tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.LalPathLabs.Pages.LoginPage;
import com.LalPathLabs.Utilities.BaseClass;
import com.LalPathLabs.Utilities.ExcelData;
import com.LalPathLabs.Utilities.HelperClass;

public class LoginTests {
	
	
	LoginPage login;
	BaseClass base;
	HelperClass hc;
	JavascriptExecutor jsTest;
	WebDriver driverTest;

	@BeforeClass
	public void setUp() {
		base = new BaseClass();
		driverTest = base.driver;
		jsTest = base.js;
		login = new LoginPage(driverTest, jsTest);
		hc=new HelperClass(driverTest, jsTest);
	}

	@Test(priority = 1, dataProvider = "TestData", dataProviderClass = ExcelData.class, enabled = true)
	public void Valid_Creds(String Username, String Password) throws Exception {
		HelperClass.logInfo("Test case name: Valid_Creds", "Test begins....");
		login.Enter_UserName(Username);
		login.Enter_Password(Password);
		login.Click_LoginButton();
	}

}
