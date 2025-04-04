package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By accsHeader = By.xpath("//div[@id='content']/h2");
	private By search = By.xpath("//input[@type='text']");
	private By searchIcon = By.xpath("//div[@id='search']/span/button");
	

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIssAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Acc page title is: " + title);
		return title;
	}
	public String getAccPageURL() {
		String url = eleUtil.waitForUrlContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Acc page title is: " + url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
	return	eleUtil.WaitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchExist() {
		return	eleUtil.WaitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		}
	
	public List<String> getAccountsHeaderList() {
		List<WebElement> accHeaderList = eleUtil.WaitForElementsVisible(accsHeader, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		
	List<String> accHeadersValList = new ArrayList<String>();
	for(WebElement e : accHeaderList) {
		String text = e.getText();
		accHeadersValList.add(text);
	}
	return accHeadersValList;
	}
	
	public 	SearchPage performSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new 	SearchPage(driver);
		}else {
			System.out.println("search field is not present on the page.....");
			return null;
		}
		
	}

	
}
