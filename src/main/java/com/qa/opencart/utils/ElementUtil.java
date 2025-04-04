package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
	this.driver = driver;
	}
	public  WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	public  WebElement getElement(By locator, int timeOut) {
		return WaitForElementVisible(locator,timeOut);
	}
	public  List<WebElement> getElements(By locator ){
		return driver.findElements(locator);
		
	}
	
	
	public void doSendKeys(By locator, String value ) {
	WebElement element = getElement(locator);
	element.clear();
	element.sendKeys(value);
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	public void doActionsSendKeys(By locator, String value) {
		Actions  act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
		
	}
	
	public void doActionsClick(By locator) {
		Actions  act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
		
	}
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	public  String getElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	
	
	public void getElementAttributes(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		for(WebElement e : eleList  ) {
			String attrVal = e.getAttribute(attrName);
			System.out.println(attrVal);
			
		}
		}
		public int getTotalElementsCount(By locator) {
			int eleCount =  getElements(locator).size();
			System.out.println("total elements:" + eleCount);
			return eleCount;
		}
		
		
		//Select based dropdown utils
		public  void doSelectByindex(By locator, int index) {
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
			
		
		}

		public  void doSelectByValue(By locator, String value) {
			Select select = new Select(getElement(locator));
			select.selectByValue(value);
			
		
		}
		
		public  void doSelectByVisibleText(By locator, String text) {
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(text);
			
		
		}
		
		
		public  List<WebElement> getTotalDropdDownOptionsList(By locator) {
			Select select = new Select(getElement(locator));
			return select.getOptions();
		}
		
		public  List<String> getDropDownOptionsTextList(By locator) {
			List<WebElement> optionsList =	getTotalDropdDownOptionsList(locator);
			List<String> optionsTextList = new ArrayList<String>();
			for(WebElement e : optionsList  ) {
				String text = e.getText();
				optionsTextList .add(text);
				
			}
			return optionsTextList;
		}
		public void selectDropDownValue(By locator, String expValue) {
			List<WebElement> optionsList = getTotalDropdDownOptionsList(locator);
			for(WebElement e : optionsList ) {
				String text = e.getText();
				System.out.println(text);
				if(text.equals(expValue)) {
					e.click();
					break;
				}
			}
		}
		
		public int getTotalDropdDownOptions(By locator) {
			int optionsCount = getTotalDropdDownOptionsList(locator).size();
			System.out.println("total options -->" + optionsCount);
			return optionsCount;
			
			
		}
		
		public void doSearch(By suggListLocator, String suggName ) {
			List<WebElement> suggList = getElements(suggListLocator);
		
			System.out.println(suggList.size());
			for(WebElement e : suggList) {
				String text = e.getText();
				System.out.println(text);
				if(text.contains(suggName)) {
				e.click();
				break;
				
			}
				
				
		}
			
		}

	//	****************************************** Wait Utils ******************************************
		public  WebElement WaitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds( timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		public  WebElement WaitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds( timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public  List<WebElement> WaitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds( timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		public  List<WebElement> WaitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds( timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		
		public  Alert WaitForAlertPresence(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
			
		}
		
		public  String getAlertText(int timeOut) {
			return WaitForAlertPresence(timeOut).getText();
			
		}
		
		public void acceptAlert(int timeOut) {
			WaitForAlertPresence(timeOut).accept();
		}
		public void DismissAlert(int timeOut) {
			WaitForAlertPresence(timeOut).dismiss();
		}
		public void alertSendKeys(int timeOut, String Value) {
			WaitForAlertPresence(timeOut).sendKeys(Value);
		}
		
		public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.titleContains(titleFractionValue));
			return driver.getTitle();
		}
		
		public String waitForTitleIssAndFetch(int timeOut, String titleValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.titleIs(titleValue));
			return driver.getTitle();
		}
		public String waitForUrlContainsAndFetch(int timeOut, String urlFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.urlContains(urlFractionValue));
			return driver.getCurrentUrl();
		}
		public String waitForUrlIsAndFetch(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.urlToBe(urlValue));
			return driver.getCurrentUrl();
		}
		public boolean waitForUrlContains(int timeOut, String urlFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.urlContains(urlFractionValue));
			
		}
		public void WaitForSwitchToItByIdorName(int timeOut, String idOrName) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
			
			
		}
		public void WaitForSwitchToItByIndex(int timeOut, int frameIndex) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));	
			
		}
		
		public void WaitForSwitchToItByFrameElement(int timeOut, WebElement frameElement) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));	
			
		}
		public  void WaitForSwitchToItByFrameLocator(int timeOut, WebElement frameLocator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt( frameLocator));	
			
		}
		
		public void clickWhenReady(int timeOut, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		public WebElement waitForElementToBeClickable(int timeOut, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return 	wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		public void doClickWithActionsAndWait(int timeOut, By locator) {
			WebElement ele = waitForElementToBeClickable(timeOut, locator);
			Actions act = new Actions(driver);
			act.click(ele).build().perform();
		}




	}


		
	
	



	
	


