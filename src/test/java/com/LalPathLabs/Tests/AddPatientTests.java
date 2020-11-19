package com.LalPathLabs.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.LalPathLabs.Pages.HomePage;
import com.LalPathLabs.Pages.LoginPage;
import com.LalPathLabs.Utilities.BaseClass;
import com.LalPathLabs.Utilities.ExcelData;
import com.LalPathLabs.Utilities.HelperClass;

public class AddPatientTests {

	LoginPage login;
	BaseClass base;
	HelperClass hc;
	HomePage home;
	JavascriptExecutor jsTest;
	WebDriver driverTest;

	@BeforeClass
	public void setUp() {
		base = new BaseClass();
		driverTest = base.driver;
		jsTest = base.js;
		login = new LoginPage(driverTest, jsTest);
		home = new HomePage(driverTest, jsTest);
		hc = new HelperClass(driverTest, jsTest);
	}

	@Test(priority = 1, dataProvider = "TestData", dataProviderClass = ExcelData.class, enabled = false)
	public void Add_Patient(String mobile, String caller, String relation, String patient, String dob, String gender,
			String patientType, String company, String authType, String address, String city, String locality,
			String doctor, String doctorId, String test, String coupon) throws Exception {
		HelperClass.logInfo("Test case name: AddPatient : " + patient, "Test begins....");
		home.Enter_MobileNumber(mobile);
		home.Click_AddPatient();
		home.Enter_CallerName(caller);
		home.Click_CallerRelationShip(relation);
		home.Enter_PatientName(patient);
		home.Enter_DOB(dob);
		home.Select_Gender(gender);
		home.Enter_Address(address);
		home.Select_City(city);
		home.Enter_Locality(locality);
		home.Select_PatientType(patientType);
		home.Select_Company(company);
		home.Select_AuthType(authType);
		home.Choose_Doctor();
		home.Search_Doctor(doctor);
		home.Select_Doctor(doctorId);
		home.Add_Patient();
		home.Test_Booking(test);
		home.Test_Next();
		home.Book_PremiumSlot();
		home.Slot_Next();
		home.Button_Coupon();
		home.Apply_Coupon(coupon);
		home.Proceed_Payment();
		home.Create_Order();
		home.Back_Home();

	}

	@Test(priority = 2, dataProvider = "TestData", dataProviderClass = ExcelData.class, enabled = true)
	public void Update_Order(String mobile, String patient, String caller, String relation, String test, String coupon)
			throws Exception {
		HelperClass.logInfo("Test case name: Update Order", "Test begins....");
		home.Enter_MobileNumber(mobile);
		home.Select_Patient(patient);
		home.Enter_CallerName(caller);
		home.Click_CallerRelationShip(relation);
		home.Click_Next();
		home.Test_Booking(test);
		home.Test_Next();
		home.Book_PremiumSlot();
		home.Slot_Next();
		home.Button_Coupon();
		home.Apply_Coupon(coupon);
		home.Proceed_Payment();
		home.Create_Order();
		home.Back_Home();
	}

	@Test(priority = 4, dataProvider = "TestData", dataProviderClass = ExcelData.class, enabled = false)
	public void Order_Modification(String mobile, String dob, String tests) throws Exception {
		HelperClass.logInfo("Test case name: Order Modification", "Test begins....");
		home.Enter_MobileNumber(mobile);
		home.Tab_Completed();
		home.Edit_Icon();
		home.Enter_DOB(dob);
		home.Click_Next();
		home.NewTest(1);
		home.NewTest(3);
		home.Delete_Test();
		home.Test_Next();
		home.Book_PremiumSlot();
		home.Slot_Next();
		home.Proceed_Payment();
		home.Update_Order();
		home.Back_Home();
	}

}
