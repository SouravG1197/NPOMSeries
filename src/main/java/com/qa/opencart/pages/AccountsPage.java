package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accSections= By.xpath("//ul[@class='myaccount-link-list']//li");
	private By header= By.cssSelector(".page-heading");
	private By logoutLink =By .cssSelector(".logout");
	private By searchField =By.cssSelector("#search_query_top");
	private By searchButton= By.xpath("//button[@name='submit_search']");
	
	
	
	
	
	
	public AccountsPage(WebDriver driver) {
		this .driver=driver;
		elementUtil = new ElementUtil(this.driver);
		
	}
	
	public String getAccPageTitle() {
		 return elementUtil.getPageTitle(Constants.ACC_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
			}
	
	public boolean getAccPageUrl() {
	return	elementUtil.waitForUrl("my-account", Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
		
	}
	public List<String> getAccSectionList() {
		List<String>accSecValList=new ArrayList<String>();
	List<WebElement>accSeclist
	=elementUtil.waitForVisibilityOfElements(accSections, Constants.DEFAULT_TIME_OUT);
	  for (WebElement e: accSeclist) {
		  accSecValList.add(e.getText());
		  
		  
	  }
	  return accSecValList;
	
	}
	
	public boolean  isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);
		
	}
	public void logout() {
		if(isLogoutLinkExist())
			elementUtil.doClick(logoutLink);
		
	}
			
	
	
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching the product:" +productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}
