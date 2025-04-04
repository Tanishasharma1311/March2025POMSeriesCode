package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ProductInfoPage;

public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());

	}

	@Test
	public void accPageHeadersTest() {
		List<String> actualAccPageHeadersList = accPage.getAccountsHeaderList();
		System.out.println("Acc page headers list:" + actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);

	}

	@Test
	public void accPageHeadersValueTest() {
		List<String> actualAccPageHeadersList = accPage.getAccountsHeaderList();
		System.out.println("Acc page headers list:" + actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
			
			
		};
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount() > 0);
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},	
			
		};
	}
	@Test(dataProvider = "getProductTestData")
	public void selectProductTest(String searchKey, String productName) {
		searchPage = accPage.performSearch(searchKey);
		if(searchPage.getSearchProductsCount()>0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}
		
	}

}
