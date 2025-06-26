package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setUpRegistration() throws InterruptedException {
		registrationPage = loginPage.navigateToRegisterPage();

	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
//
//	@Test
//	public void userRegistrationTest() throws InterruptedException {
//
//		Assert.assertTrue(registrationPage.accountRegistration("Mr", "Dilps", "Sen", "Tim1234"));
//
//	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String gender, String firstName, String lastName, String password)
			throws InterruptedException {

	    if (firstName == null || firstName.trim().isEmpty()) {
	        System.out.println("Skipping empty test data...");
	        return;  // Skip this test iteration
	    }

		 
		Assert.assertTrue(registrationPage.accountRegistration(gender, firstName, lastName, password));
		registrationPage = loginPage.navigateToRegisterPage();
	}

}
