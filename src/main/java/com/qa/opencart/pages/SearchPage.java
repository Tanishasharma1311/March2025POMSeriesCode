package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResults = By.xpath("//div[@id='content']//div//div[@class='image']/a");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public int getSearchProductsCount() {
	int productCount =	eleUtil.WaitForElementsVisible(searchResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	System.out.println("productCount: " + productCount);
	return productCount;
	
	}
	
	public ProductInfoPage selectProduct(String productName) {
	By productLocator =	By.linkText(productName);
	eleUtil.WaitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
	return new ProductInfoPage(driver);
		
	}
}
