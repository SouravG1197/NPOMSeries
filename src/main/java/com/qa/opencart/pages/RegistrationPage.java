package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private ElementUtil elementUtil;
	
	private By genderM=By.xpath("//*[@for='id_gender1']");
	private By genderF=By.xpath("//*[@for='id_gender2']");
	private By firstName=By.cssSelector("#customer_firstname");
	private By lastName=By.cssSelector("#customer_lastname");
	private By password=By.cssSelector("#passwd");
	private By dateSelect=By.cssSelector("#days");
	private By monthSelect=By.cssSelector("#uniform-months");
	private By yearSelect=By.cssSelector("#uniform-years");
	
	private By selectCheckBox=By.cssSelector("#newsletter");
	private By registerButton=By.cssSelector("#submitAccount");
	private By sucessMessg=By.xpath("//p[@class='alert alert-success']");
	private By logOut=By.xpath("//a[@title='Log me out']");
	
	//private By logIn=By.xpath("//input[@id='email_create']");
	//private By clickLoginButton=By.xpath("//button[@id='SubmitCreate']");
	
	public RegistrationPage(WebDriver driver) {
		 elementUtil = new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String gender, String firstName,String lastName,
			String password) throws InterruptedException {
		
		
		//elementUtil.doSendKeys(this.logIn, logIn);
		//elementUtil.doClick(clickLoginButton);
		Thread.sleep(5000); 
		  elementUtil.doVisibilityOfElement(this.firstName, 10);
		 
		if (gender.equalsIgnoreCase("Mr")) {
			elementUtil.doClick(genderM);
			}else
		 {
			elementUtil.doActionClick(genderF);
		}
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doClick(selectCheckBox);
		elementUtil.doClick(registerButton);
		
		String mesg=elementUtil.doVisibilityOfElement(sucessMessg, Constants.DEFAULT_TIME_OUT).getText();
		if (mesg.contains(Constants.REGISTER_SUCESS_MESSG)) {
			elementUtil.doClick(logOut);
			
			return true ;
			
		}return false;
		
	}

}
