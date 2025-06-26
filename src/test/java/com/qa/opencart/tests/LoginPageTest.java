package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import jdk.jfr.Description;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitelTest() {
		String title=loginPage.getLoginPageTitle();
		System.out.println("Login pahe title is :" +title);
	    Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	
	}
	//@Description("forgotPWDLinkTest..")
	//@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPWDLinkTest() {
		Assert.assertTrue(loginPage.isForgotPWDLinkExist());
	}
     @Test
     public void loginTest() {
    	 loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
     }
     
     @Test
     public void  loginpageFooterLinkTest() {
    	List<String>footerList= loginPage.getFooterLinks();
    	softAssert.assertEquals(footerList.size(), 22);
    	softAssert.assertTrue(footerList.contains("Women"));
    	softAssert.assertAll();
     }
     
//     @Test
//     public void loginTest_negative(String username,String password) {
//    	 loginPage.doLogin(username,password);
//     }
//     
     
     
}
