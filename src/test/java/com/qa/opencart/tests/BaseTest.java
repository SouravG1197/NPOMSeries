package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;


//@Listeners(ExtentReportListener.class)
public class BaseTest {

	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accPage;
	public SearchResultsPage searchResultsPage;
	public ProductInfoPage productInfoPage;
	public RegistrationPage registrationPage;
	
	public SoftAssert softAssert;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		softAssert=new SoftAssert();
		df=new DriverFactory();
		prop=df.init_prop();
		if (browserName !=null) {
			prop.setProperty("browser", browserName);
		}
	driver=	df.init_driver(prop);
	loginPage=new LoginPage(driver);
	//accPage=loginPage.doLogin(un, pwd);
	}
	
   @AfterTest
	public void tearDown() {
	driver.quit();
	}
	
  
	
	
	
}
