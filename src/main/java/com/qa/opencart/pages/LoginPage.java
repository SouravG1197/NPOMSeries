package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	//1.By Locators:
private	By emaiId=By.cssSelector("#email");
private	By password= By.cssSelector("#passwd");
private	By loginButton =By.cssSelector("#SubmitLogin");
private	By forgotPwdLink= By.linkText("Forgot your password?");
private	By footerLinks=By.xpath("//footer//div[@class='row']//a");
	
private By logIn=By.xpath("//input[@id='email_create']");
private By clickLoginButton=By.xpath("//button[@id='SubmitCreate']");

	
	//2.constructor:
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil =new ElementUtil(this.driver);
		
	}
  //3. page actions:
	public String getLoginPageTitle() {
		 return elementUtil.getPageTitle(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPWDLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String un,String pwd) {
		elementUtil.doPresenceOfElement(emaiId,Constants.DEFAULT_TIME_OUT).sendKeys(un);
		elementUtil.doActionSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
		
	}
	
	public List<String> getFooterLinks() {
		 List<WebElement> footerList=elementUtil.getElements(footerLinks);
		 List<String> footerTextList=new ArrayList<String>();
		 for (WebElement e :footerList) {
			 footerTextList.add(e.getText());
			 
		 }
		 return footerTextList;
	}
	
	/**
	 * navigate to Registration page 
	 * @return 
	 * @throws InterruptedException 
	 */
	public RegistrationPage navigateToRegisterPage() throws InterruptedException {
		 String email = "testuser" + System.currentTimeMillis() + "@yopmail.com";
		
		elementUtil.doSendKeys(logIn, email);
		 elementUtil.doClick(clickLoginButton);
		 elementUtil.waitForVisibilityOfElements(clickLoginButton, 10);
		 return new RegistrationPage(driver);
	}
	
}
