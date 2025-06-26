package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By ProductHeader=By.xpath("//div[@class='container']//h1");
	private By productImages=By.cssSelector(".pb-left-column.col-xs-12.col-sm-4.col-md-5 img");
	
	private By productMetaData=By.xpath("(//*[@id='product_reference'] ) | //*[@id='product_condition']");
	//(//*[@id='product_reference'] )/span  | //*[@id="product_condition"]/span
	private By productPriceData=By.xpath("//*[contains(@class,'content_prices')]//p[not(contains(@style,'none'))]");
	//*[contains(@class,'content_prices')]//p[not(contains(@style,'none'))]
	
	private By size=By.cssSelector(".attribute_list #uniform-group_1");
	///
	private By addtoCartBtn =By.cssSelector(".box-cart-bottom .exclusive");
	private By sucessMesg=By.cssSelector(".layer_cart_product.col-xs-12.col-md-6 h2");
	private By selectSize= By.xpath("//select[@name='group_1']");
	
	Map<String,String>productInfoMap;
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
	
	public void setectSizeE() {
		Select s =new Select(driver.findElement(selectSize));
		s.selectByValue("L");
	}
	
	public String getProductHeaderText() {
		return elementUtil.doGetText(ProductHeader);
	}
	public int getProductImagesCount() {
	return	 elementUtil.waitForVisibilityOfElements(productImages,Constants.DEFAULT_TIME_OUT).size();
	}
	public Map<String, String> getProductInfo() {
	productInfoMap=new LinkedHashMap<String,String>();
	//productInfoMap=new HashMap<String,String>();
	//productInfoMap=new TreeMap<String,String>();
		productInfoMap.put("name", getProductHeaderText());
		
		getProductMetaData();
		getProductPriceData();
	return productInfoMap;
	
	}
    
	private void getProductMetaData() {
		List<WebElement>metaDataList=elementUtil.getElements(productMetaData);
		System.out.println("total product meta data:"+metaDataList);
		
		//meta Data
		for (WebElement e :metaDataList) {
			String meta[]=e.getText().split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
			
		}
		
	}
	

	private void getProductPriceData() {
		//price:
		List<WebElement>priceList=elementUtil.getElements(productPriceData);
		System.out.println("total price meta data "+ priceList.size());
	     
	String price=	priceList.get(0).getText().trim();
	String expPrice=priceList.get(1).getText().trim();
	String disPrice =priceList.get(2).getText().trim();
	
	productInfoMap.put("price", price);
	productInfoMap.put("ExpPrice", expPrice);
	productInfoMap.put("Disprice ", disPrice);
	}
	
	


}
