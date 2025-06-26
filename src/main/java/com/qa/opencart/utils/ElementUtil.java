package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
    private JavaScriptUtil jsUtil;
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(this.driver);
	}

	public WebElement getElement(By locator) {
		WebElement element= driver.findElement(locator);

		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public void doSendKeys(By locator, String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public String doGetText(By locator) {
		return getElement(locator).getText().trim();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(e.getText());
			}

		}
		return eleTextList;

	}

	public void printElementsText(By locator) {
		getElementsTextList(locator).stream().forEach(e -> System.out.println(e));
	}

	public void getAttributeList(By locator, String attr) {
		List<WebElement> attrList = getElements(locator);

		for (int i = 0; i < attrList.size(); i++) {
			String srcValue = attrList.get(i).getAttribute(attr);
			System.out.println(srcValue);
		}
	}

	// ***********Select base dropdown*****
	public void doSelectDropdownIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropdownVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectDropdownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectValues(By locator, String value) {

		List<WebElement> optionList = getElements(locator);

		for (WebElement e : optionList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void printDropDownOptions(By locator) {
		getDropDownOptions(locator).stream().forEach(e -> System.out.println(e));

	}

	public List<String> getDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : optionsList) {
			optionsTextList.add(e.getText());
		}

		return optionsTextList;
	}

	public void doSelectValueFromDropdowm(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}

		}
	}
	
	
	
	/**************Actions Utils***************/
	
	
	public void handelTwoLevelMenu(By parentLocator, By ChilLocator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentLocator)).perform();
		//getElement(ChilLocator).click();
		doClick(ChilLocator);

	}

	public void handelThreeLevelMenu(By parentLocator1, By parentLocator2, By ChilLocator) throws InterruptedException {
		Actions act = new Actions(driver);

		act.moveToElement(getElement(parentLocator1)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(parentLocator2)).perform();
		Thread.sleep(2000);
		getElement(ChilLocator).click();

	}
	
	//---------------------------------------/
	public  void doActionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		
	}
	

	public  void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator)).perform();
		
	}
	//---------------Wait Utils--------------------
	
	public List<WebElement> waitForVisibilityOfElements(By locator,int timeOut) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	return	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public void printElementsText(By locator,int timeOut) {
		waitForVisibilityOfElements(locator,timeOut).stream().forEach(e->System.out.println(e.getText()));
	}
	
	
	
	
	
	public WebElement doPresenceOfElement(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
//	public WebElement doPresenceOfElement(By locator,int timeOut int intervalTime) {
//		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeOut));
//		  wait.pollingEvery(Duration.ofMillis(intervalTime));
//		return	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//				
//	}
	
	
	
	public WebElement doVisibilityOfElement(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		
	}
	
	public  WebElement waitForPrsenceOfElementWithFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)

				.withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofMillis(intervalTime))
				.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public  void waitForFrameWithFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)

				.withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofMillis(intervalTime))
				.ignoring(NoSuchFrameException.class, NoSuchElementException.class);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}
	public  Alert waitForAlertWithFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)

				.withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofMillis(intervalTime))
				.ignoring(NoAlertPresentException.class, NoSuchElementException.class);

	return	wait.until(ExpectedConditions.alertIsPresent());

	}   
	
	
	
	
	
	public  Alert waitForAlert(int timeOut) {
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public  void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}
	public  void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}
	public  String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	
	public  boolean waitForUrl(String urlFraction, int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	
	public  boolean waitForUrToBel(String urlValue, int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.urlToBe(urlValue));
	}
	
	
	public String getPageTitle(String titleFraction, int timeOut) {
		waitForTitle(titleFraction,timeOut);
		return driver.getTitle();
	}
	
	
	
	public  boolean waitForTitle(String titleFraction, int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.titleContains(titleFraction));
	}
	
	public  boolean waitForTitleIs(String titleVal, int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.titleIs(titleVal));
	
	}
	
	public  void waitForFrameElement(String IDORNAME,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDORNAME));
		
	}
	
	public  void waitForFrameElement(int frameIndex,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		
	}
     public WebElement waitForElementToBeClickable(By locator,int timeOut) {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
 		return wait.until(ExpectedConditions.elementToBeClickable(locator));
 		 
     }
     public void clickWhenReady(By locator,int timeOut) {
    	 waitForElementToBeClickable(locator,timeOut).click();
     }
	
}
