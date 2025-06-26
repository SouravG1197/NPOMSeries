package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductinfoPageTest  extends BaseTest{
	

	@BeforeClass
	public void  productInfoSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoHeaderTest() {
		searchResultsPage=accPage.doSearch("Printed Dress");
		productInfoPage=searchResultsPage.selectProduct("Printed Chiffon Dress");
		Assert.assertEquals(productInfoPage.getProductHeaderText(),"Printed Chiffon Dress");
		
	}
	
	
	@Test
	public void productImagestest() {
		searchResultsPage=accPage.doSearch("Printed Dress");
		productInfoPage=searchResultsPage.selectProduct("Printed Chiffon Dress");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.PRODUCT_IMAGE_COUNT);
	
		
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage=accPage.doSearch("Printed Dress");
		productInfoPage=searchResultsPage.selectProduct("Printed Chiffon Dress");
		Map<String,String>actProductInfoMap=productInfoPage.getProductInfo();
		
		actProductInfoMap.forEach((k,v)-> System.out.println(k + " : " +v));
		
		softAssert.assertEquals(actProductInfoMap.get("name"), "Printed Chiffon Dress");
		softAssert.assertEquals(actProductInfoMap.get("Condition"), "New product");
		softAssert.assertTrue(actProductInfoMap.get("price").contains("16"));
		softAssert.assertEquals(actProductInfoMap.get("Reference"), "demo_7");
		
		softAssert.assertAll();
	}
  
	
	
	
	

}
