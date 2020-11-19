package com.LalPathLabs.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.LalPathLabs.Utilities.HelperClass;

public class HomePage {

	WebDriver driver;
	JavascriptExecutor js;

	By mobileNumber_text = By.xpath("//input[@class='MuiInputBase-input']");
	By addPatient_button = By.xpath("//div[3]/div/div/div/div[2]/div[1]/div[1]/div[2]/button");
	By callerName_text = By
			.xpath("//input[@class='MuiInputBase-input MuiInput-input' and @placeholder='Please enter caller name']");
	By callerRelation_drop = By.xpath("//*[@id=\"callerRelation\"]");
	By relationFriend_Option = By.xpath("//*[@id=\"callerRelation\"]/option[5]");
	By title_drop = By.xpath(
			"//*[@id=\"root\"]/div[2]/div[3]/div/div[2]/div[3]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/select");
	By mr_option = By.xpath(
			"//*[@id=\"root\"]/div[2]/div[3]/div/div[2]/div[3]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/select/option[2]");
	By gender_drop = By.xpath(
			"//select[@class='MuiSelect-root MuiSelect-select MuiInputBase-input MuiInput-input' and @name='gender']");
	By male_option = By.xpath(
			"//*[@id='root']/div[2]/div[3]/div/div[2]/div[3]/div[2]/div/div/div/div/div/div[3]/div[1]/div/div/select/option[2]");
	By patientName_text = By
			.xpath("//input[@class='MuiInputBase-input MuiInput-input' and @placeholder='Patient Name']");
	By dob_text = By.xpath(
			"//input[@class='MuiInputBase-input MuiInput-input MuiInputBase-inputAdornedEnd' and @placeholder='dd/mm/yyyy']");
	By address_text = By.xpath("//*[@id=\"standard-multiline-flexible\"]");
	By city_text = By.xpath("//input[@placeholder='Please select a city']");
	By locality_text = By.xpath("//input[@placeholder='Enter Locality']");
	By doctor_radio = By.xpath("//input[@name='referralType' and @value='doctor']");
	By doctorSearch_text = By.xpath("//input[@placeholder='Search by Doctor Name/Doctor ID']");
	By add_button = By
			.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained jss618' and @tabindex='0']");
	By slotAvailable_Button = By.xpath(
			"//div[@class='MuiButtonBase-root MuiListItem-root jss824 MuiListItem-gutters MuiListItem-button' and @aria-disabled='false']");
	By registerNext_button = By.xpath(
			"/button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained jss807 MuiButton-disableElevation']");
	By testSearch_text = By.xpath("//input[@id='autocomplete']");
	By testNext_button = By.xpath("//*[@id='book_test-content']/div/div/div[5]/button");
	By couponCode_text = By.xpath(
			"//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputMarginDense MuiOutlinedInput-inputMarginDense' and @placeholder='Enter Promo Code']");
	By validate_button = By.xpath("//span[@class='MuiButton-label' and contains(text(),'Validate')]");
	By proceedToPayment_button = By.xpath("//div[3]/div[2]/button");
	By createOrder_button = By.xpath("//div[2]/div/div[2]/button");
	By backToHome_button = By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/button[2]");
	By slotBooking_Expand = By.xpath("//span[contains(text(),'Slot Booking')]");
	By next_day = By.xpath("//*[@id='container']/div/div/ul/div[2]");
	By premium_slot = By
			.xpath("//*[@id='slot_book-content']/div/div/div[2]/ul/div[1]/div[2]/div[@aria-disabled='false']");
	By soltNext_button = By.xpath("//*[@id='slot_book-content']/div/div/div[3]/button");
	By applyCoupon_button = By.xpath("//span[contains(text(),'Apply Coupon')]");
	By button_addPatient = By.xpath("//div[@aria-labelledby='patient-details']/div/div/div/button");
	By select_patient = null;/* //div/h3[contains(text(), 'PatientTwo')] */
	By mousePoint = By.xpath("//div[1]/div[2]/div[2]/div/div[2]/div[2]");
	By button_bookTest = By.xpath("//div/div/button/span[contains(text(), 'Book Test')]");
	By button_next = By.xpath("//button/span[contains(text(), 'Next')]");
	By tab_completed = By.xpath("//button[@name='Completed']");
	By icon_modifyOrder = By.xpath("//button[@title='modify order']");// multiple
	By premiumNewSlot = By.xpath("//*[@id='slot_book-content']/div/div/div[2]/ul/div[1]/div[2]/div[2]");
	By radio_test = By.xpath("//input[@aria-label='dont_know_dob' and @type='checkbox']");// 2
	By delete_test = By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root' and @aria-label]");// size-3
	By button_modifyOrder = By.xpath("//button/span[contains(text(), 'modify Order')]");
	By pre_Loader = By.xpath("//*[@id='root']/div[1]/div");
	By input_company = By.xpath("//input[@placeholder='Enter company name']");
	By select_authType = By.id("authType-label-placeholder");
	By select_patientType = By.xpath("//select[@name='typeOfPatient']");
	By icon_couponClose = By.xpath("//h2/button/span");
	By input_Locality = By.xpath("//input[@type='text' and @placeholder='Enter Locality']");

	public HomePage(WebDriver driver, JavascriptExecutor js) {

		this.driver = driver;
		this.js = js;

	}

	public void waitForLoader() throws Exception {
		try {
			HelperClass.waitForInvisiblility(pre_Loader, 10);
		} catch (Exception e) {
			try {
				HelperClass.waitForInvisiblility(pre_Loader, 15);
			} catch (Exception e1) {
				try {
					HelperClass.waitForInvisiblility(pre_Loader, 25);
				} catch (Exception e2) {
					HelperClass.FailureReport("Test failed");
					HelperClass
							.logInfo("Timeout... Loader Wait failed even after waiting for 30 sec.." + e2.toString());
					throw (e2);
				}
			}
		}
	}

	public void Enter_MobileNumber(String num) throws Exception {
		waitForLoader();
		try {
			HelperClass.Clearelement(mobileNumber_text);
			HelperClass.enterWebElement(mobileNumber_text, num);
			HelperClass.SuccessReport("Entered Mobile Number..!");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(mobileNumber_text);
				HelperClass.enterWhenVisible(mobileNumber_text, 5, num);
				HelperClass.SuccessReport("Entered Mobile Number..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Mobile Number field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

	public void Click_AddPatient() throws Exception {
		waitForLoader();
		try {
			HelperClass.clickWebelement(addPatient_button);
			HelperClass.SuccessReport("Add Patient Button Clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(addPatient_button, 10);
				HelperClass.SuccessReport("Add Patient Button Clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout... Add Patient Button cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Enter_CallerName(String name) throws Exception {
		try {
			HelperClass.Clearelement(callerName_text);
			HelperClass.enterWebElement(callerName_text, name);
			HelperClass.SuccessReport("Entered Caller Name..!");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(callerName_text);
				HelperClass.enterWhenVisible(callerName_text, 5, name);
				HelperClass.SuccessReport("Entered Caller Name..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Caller Name field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

	public void Click_CallerRelationShip(String relation) throws Exception {

		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.dropDownSelect(callerRelation_drop, relation);
			HelperClass.SuccessReport("Relationship Clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.WaitImplicitlyForElement();
				HelperClass.dropDownSelect(callerRelation_drop, relation);
				HelperClass.SuccessReport("Relationship Clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Relationship field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

	public void Select_CallerRelationShip() throws Exception {
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.clickWebelement(relationFriend_Option);
			HelperClass.SuccessReport("Relationship Option Selected..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenVisible(relationFriend_Option, 5);
				HelperClass.SuccessReport("Relationship Option Selected..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo("Timeout...Relationship Option field cannot be found even after waiting for 5 sec.."
						+ e2.toString());
				throw (e2);
			}
		}
	}

	public void Enter_PatientName(String patient) throws Exception {
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.Clearelement(patientName_text);
			HelperClass.enterWebElement(patientName_text, patient);
			HelperClass.SuccessReport("Entered Patient Name");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(patientName_text);
				HelperClass.enterWhenVisible(patientName_text, 5, patient);
				HelperClass.SuccessReport("Entered Patient Name");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Username field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

	public void Enter_DOB(String dob) throws Exception {
		try {
			HelperClass.Clearelement(dob_text);
			HelperClass.enterWebElement(dob_text, dob);
			HelperClass.SuccessReport("Entered Date Of Birth");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(dob_text);
				HelperClass.enterWhenVisible(dob_text, 5, dob);
				HelperClass.SuccessReport("Entered Date Of Birth");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Date of birth field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
		HelperClass.hitKeyboardEnter(dob_text, 5);

	}

	public void Select_Gender(String gender) throws Exception {
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.dropDownSelect(gender_drop, gender);
			HelperClass.SuccessReport("Selected Male Option");
		} catch (Exception e1) {
			try {
				waitForLoader();
				HelperClass.dropDownSelect(gender_drop, gender);
				HelperClass.logInfo("Selected Male Option");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Username field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Enter_Address(String addr) throws Exception {

		try {

			HelperClass.Clearelement(address_text);
			HelperClass.enterWebElement(address_text, addr);
			HelperClass.SuccessReport("Entered Address");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(address_text);
				HelperClass.enterWhenVisible(address_text, 5, addr);
				HelperClass.SuccessReport("Entered Address");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Username field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

	public void Select_City(String city) throws Exception {

		try {

			HelperClass.Clearelement(city_text);
			HelperClass.enterWebElement(city_text, city);
			HelperClass.SuccessReport("Entered City Name");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(city_text);
				HelperClass.enterWhenVisible(city_text, 5, city);
				HelperClass.SuccessReport("Entered City Name");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Username field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
		HelperClass.hitKeyboardEnter(city_text, 5);
	}

	public void Enter_Locality(String locality) throws Exception {
		waitForLoader();
		try {
			HelperClass.Clearelement(locality_text);
			HelperClass.enterWebElement(locality_text, locality);
			waitForLoader();
			HelperClass.selectVisibleText(locality);
			HelperClass.SuccessReport("Localitys selected");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(locality_text);
				HelperClass.enterWebElement(locality_text, locality);
				waitForLoader();
				HelperClass.selectVisibleText(locality);
				HelperClass.SuccessReport("Localitys selected");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass
						.logInfo("Timeout...Locality cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Choose_Doctor() throws Exception {
		try {
			HelperClass.clickWebelement(doctor_radio);
			HelperClass.SuccessReport("Selected Doctor Radio");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenVisible(doctor_radio, 5);
				HelperClass.logInfo("Selected Doctor Radio");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Username field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

	public void Search_Doctor(String docname) throws Exception {
		try {
			HelperClass.Clearelement(doctorSearch_text);
			HelperClass.enterWebElement(doctorSearch_text, docname);
			HelperClass.SuccessReport("Entered Doctor Name");
		} catch (Exception e1) {
			try {
				HelperClass.Clearelement(doctorSearch_text);
				HelperClass.enterWhenVisible(doctorSearch_text, 5, docname);
				HelperClass.SuccessReport("Entered Doctor Name");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Doctor field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Select_Doctor(String doctorId) throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.selectVisibleText(doctorId);
			HelperClass.SuccessReport("Doctor selected..!");
		} catch (Exception e) {
			HelperClass.FailureReport("Test failed");
			HelperClass.logInfo("Timeout...DoctorId cannot be found even after waiting for 5 sec.." + e.toString());
			throw (e);
		}
		HelperClass.WaitImplicitlyForElement();
		driver.findElement(doctorSearch_text).sendKeys(Keys.TAB);
	}

	public void Add_Patient() throws Exception {
		HelperClass.WaitImplicitlyForElement();
		try {
			HelperClass.clickWebelement(button_addPatient);
			HelperClass.SuccessReport("Patient Added..!");
		} catch (Exception e) {
			try {
				HelperClass.clickWhenVisible(button_addPatient, 10);
				HelperClass.SuccessReport("Patient Added..!");
			} catch (Exception e1) {
				HelperClass.FailureReport("Patient added failed..!");
				HelperClass.logInfo(
						"Timeout...Button Patient cannot be found even after waiting for 5 sec.." + e1.toString());
				throw (e1);
			}
		}
	}

	public boolean selectPremiumSlot() throws Exception {
		List<WebElement> list = driver.findElements(premium_slot);
		boolean flag = false;
		if (list != null && list.size() > 0) {
			list.get(0).click();
			flag = true;
		}
		return flag;
	}

	public void Book_PremiumSlot() throws Exception {
		waitForLoader();
		int count = 2;
		while (!selectPremiumSlot()) {
			next_day = By.xpath("//*[@id='container']/div/div/ul/div[" + count + "]");
			System.out.println("Next day selected...!");
			count++;
			try {
				waitForLoader();
				HelperClass.clickWebelement(next_day);
				HelperClass.SuccessReport("Booked Premium Slot");
			} catch (Exception e1) {
				try {
					waitForLoader();
					HelperClass.clickWhenReady(next_day, 5);
					HelperClass.SuccessReport("Booked Premium Slot");
				} catch (Exception e2) {
					HelperClass.FailureReport("Test failed");
					HelperClass.logInfo("Timeout...Premium Slot field cannot be found even after waiting for 5 sec.."
							+ e2.toString());
					throw (e2);
				}
			}
		}
	}

	public void Slot_Next() throws Exception {
		waitForLoader();
		try {
			HelperClass.pageDown();
			HelperClass.WaitImplicitlyForElement();
			HelperClass.mouseOverClick(soltNext_button);
			HelperClass.SuccessReport("Slot Next Button clicked...!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenVisible(soltNext_button, 10);
				HelperClass.SuccessReport("Slot Next Button clicked...!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo("Timeout...Slot Next Button field cannot be found even after waiting for 5 sec.."
						+ e2.toString());
				throw (e2);
			}
		}
	}

	public void Test_Booking(String tests) throws Exception {
		String[] strArr = tests.split(",");
		for (String test : strArr) {
			waitForLoader();
			try {
				HelperClass.Clearelement(testSearch_text);
				HelperClass.enterWebElement(testSearch_text, test);
				HelperClass.SuccessReport("Test Search String Entered");
			} catch (Exception e1) {
				try {
					HelperClass.Clearelement(testSearch_text);
					HelperClass.enterWhenVisible(testSearch_text, 5, test);
					HelperClass.SuccessReport("Test Search String Entered");
				} catch (Exception e2) {
					HelperClass.FailureReport("Test failed");
					HelperClass.logInfo(
							"Timeout...Test Booking cannot be found even after waiting for 5 sec.." + e2.toString());
					throw (e2);
				}
			}
			HelperClass.WaitImplicitlyForElement();
			driver.findElement(By.xpath("//*[text()[contains(.,'" + test + "')]]")).click();
			HelperClass.logInfo("Test Selection Success!");
			HelperClass.WaitImplicitlyForElement();
		}
	}

	public void Button_Coupon() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.clickWhenReady(applyCoupon_button, 5);
			HelperClass.SuccessReport("Coupon Button clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenVisible(applyCoupon_button, 10);
				HelperClass.SuccessReport("Coupon Button clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Coupon Button cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Apply_Coupon(String coupon) throws Exception {
		try {

			HelperClass.WaitImplicitlyForElement();
			HelperClass.Clearelement(couponCode_text);
			HelperClass.enterWebElement(couponCode_text, coupon);
			HelperClass.SuccessReport("Coupon Code Entered Entered");
			HelperClass.clickWhenReady(validate_button, 5);
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(validate_button, 5);
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Coupon Apply field cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
		closeCouponPopup();
	}

	public void Create_Order() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.clickWebelement(createOrder_button);
			HelperClass.SuccessReport("Created Ticket Successfully!");
		} catch (Exception e1) {
			try {
				HelperClass.WaitImplicitlyForElement();
				HelperClass.clickWhenVisible(createOrder_button, 10);
				HelperClass.SuccessReport("Created Ticket Successfully!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Create Order Button cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}

	}

	public void Select_Patient(String patent) throws Exception {
		waitForLoader();
		Thread.sleep(5000);
		select_patient = By.xpath("//div[2]/div[1]/div[2]/div[1]/div/div[1]/div[2]");
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.mouseOverClick(select_patient);
			HelperClass.SuccessReport("Selected Patient Successfully!");
		} catch (Exception e) {
			try {
				HelperClass.mouseOverClick(select_patient);
				HelperClass.SuccessReport("Selected Patient Successfully!");
			} catch (Exception e1) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo("Timeout...Patient cannot be found even after waiting for 5 sec.." + e1.toString());
				throw (e1);
			}
		}
	}

	public void Click_Next() throws Exception {
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.JSelementScrollIntoView(button_next, 5);
			HelperClass.clickWebelement(button_next);
			HelperClass.SuccessReport("Button Next clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenVisible(button_next, 10);
				HelperClass.SuccessReport("Button Next clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Button Next cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Tab_Completed() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.mouseOverClick(tab_completed);
			HelperClass.SuccessReport("Tab Completed clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenVisible(tab_completed, 10);
				HelperClass.SuccessReport("Tab Completed clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Tab Completed cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Edit_Icon() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.clickWebelement(icon_modifyOrder);
			HelperClass.SuccessReport("Edit Icon clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(icon_modifyOrder, 5);
				HelperClass.SuccessReport("Edit Icon clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass
						.logInfo("Timeout...Edit Icon cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void premiumNewSlot() throws Exception {
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.clickWebelement(premiumNewSlot);
			HelperClass.clickWebelement(soltNext_button);
			HelperClass.SuccessReport("Premium new slot selected..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(premiumNewSlot, 5);
				HelperClass.clickWebelement(soltNext_button);
				HelperClass.SuccessReport("Premium new slot selected..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...PremiumNewSlot cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void NewTest(int test) throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			driver.findElements(radio_test).get(test).click();
			HelperClass.SuccessReport("New Test selected..!");
		} catch (Exception e1) {
			try {
				HelperClass.WaitImplicitlyForElement();
				driver.findElements(radio_test).get(test).click();
				HelperClass.SuccessReport("New Test selected..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass
						.logInfo("Timeout...New Test cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Delete_Test() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.mouseOverClick(delete_test);
			HelperClass.SuccessReport("Test Deleted Sucessfully..!");
		} catch (Exception e1) {
			try {
				HelperClass.WaitImplicitlyForElement();
				HelperClass.clickWhenVisible(delete_test, 10);
				HelperClass.SuccessReport("Test Deleted Sucessfully..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Delete Test cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Update_Order() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.clickWebelement(button_modifyOrder);
			HelperClass.SuccessReport("Button ModifyOrder Sucessfully..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(button_modifyOrder, 5);
				HelperClass.SuccessReport("Button ModifyOrder Sucessfully..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo(
						"Timeout...Button ModifyOrder cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Back_Home() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.clickWebelement(backToHome_button);
			HelperClass.SuccessReport("back To Home Sucessfully..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(backToHome_button, 5);
				HelperClass.SuccessReport("back To Home Sucessfully..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass
						.logInfo("Timeout...Back Home cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Test_Next() throws Exception {
		waitForLoader();
		try {
			HelperClass.mouseOverClick(testNext_button);
			HelperClass.SuccessReport("Test Next clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.clickWhenReady(testNext_button, 10);
				HelperClass.SuccessReport("Test Next clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass
						.logInfo("Timeout...Test Next cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Proceed_Payment() throws Exception {
		waitForLoader();
		try {
			HelperClass.WaitImplicitlyForElement();
			HelperClass.JSelementScrollIntoView(proceedToPayment_button, 10);
			HelperClass.mouseOverClick(proceedToPayment_button);
			HelperClass.SuccessReport("Payment clicked..!");
		} catch (Exception e1) {
			try {
				HelperClass.JSelementScrollIntoView(proceedToPayment_button, 10);
				HelperClass.clickWhenVisible(proceedToPayment_button, 5);
				HelperClass.SuccessReport("Payment clicked..!");
			} catch (Exception e2) {
				HelperClass.FailureReport("Test failed");
				HelperClass.logInfo("Timeout...Payment cannot be found even after waiting for 5 sec.." + e2.toString());
				throw (e2);
			}
		}
	}

	public void Select_Company(String company) throws Exception {
		if (company != null && company != "") {
			try {
				HelperClass.Clearelement(input_company);
				HelperClass.enterWebElement(input_company, company);
				HelperClass.selectVisibleText(company);
				HelperClass.SuccessReport("Entered Company Name...!");
			} catch (Exception e1) {
				try {
					HelperClass.Clearelement(input_company);
					HelperClass.enterWhenVisible(input_company, 5, company);
					HelperClass.selectVisibleText(company);
					HelperClass.SuccessReport("Entered Company Name...!");
				} catch (Exception e2) {
					HelperClass.FailureReport("Test failed");
					HelperClass.logInfo("Timeout...Company Name field cannot be found even after waiting for 5 sec.."
							+ e2.toString());
					throw (e2);
				}
			}
		}
	}

	public void Select_AuthType(String type) throws Exception {
		if (type != null && type != "") {
			Thread.sleep(5000);
			try {
				HelperClass.WaitImplicitlyForElement();
				HelperClass.dropDownSelect(select_authType, type);
				HelperClass.SuccessReport("Authentication Type Selected...!");
			} catch (Exception e1) {
				try {
					HelperClass.WaitImplicitlyForElement();
					HelperClass.dropDownSelect(select_authType, type);
					HelperClass.SuccessReport("Authentication Type Selected...!");
				} catch (Exception e2) {
					HelperClass.FailureReport("Test failed");
					HelperClass.logInfo(
							"Timeout...Authentication Type field cannot be found even after waiting for 5 sec.."
									+ e2.toString());
					throw (e2);
				}
			}
		}
	}

	public void Select_PatientType(String type) throws Exception {
		waitForLoader();
		if (type != "") {
			try {
				HelperClass.WaitImplicitlyForElement();
				HelperClass.dropDownSelect(select_patientType, type);
				HelperClass.SuccessReport("Patient Type Selected...!");
			} catch (Exception e1) {
				try {
					HelperClass.WaitImplicitlyForElement();
					HelperClass.dropDownSelect(select_patientType, type);
					HelperClass.SuccessReport("Patient Type Selected...!");
				} catch (Exception e2) {
					HelperClass.FailureReport("Test failed");
					HelperClass.logInfo("Timeout...Patient Type field cannot be found even after waiting for 5 sec.."
							+ e2.toString());
					throw (e2);
				}
			}
		}
	}

	public void closeCouponPopup() throws Exception {
		waitForLoader();
		if (isAvailable(icon_couponClose)) {
			try {
				HelperClass.WaitImplicitlyForElement();
				HelperClass.clickWebelement(icon_couponClose);
				HelperClass.SuccessReport("Close coupon popup...!");
			} catch (Exception e1) {
				try {
					HelperClass.WaitImplicitlyForElement();
					HelperClass.clickWhenReady(icon_couponClose, 10);
					HelperClass.SuccessReport("Close coupon popup...!");
				} catch (Exception e2) {
					HelperClass.FailureReport("Test failed");
					HelperClass.logInfo(
							"Timeout...Coupon Popup close button cannot be found even after waiting for 5 sec.."
									+ e2.toString());
					throw (e2);
				}
			}
		}
	}

	private boolean isAvailable(By locator) {
		return HelperClass.isVisible(locator);
	}
}
