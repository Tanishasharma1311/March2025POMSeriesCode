package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductImagesCountTest() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"Macbook", "MacBook Air",4},
			{"iMac", "iMac", 3},	
			
		};
	}
	@Test(dataProvider = "getProductImagesCountTest")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
	Map<String,String> actproductInfoMap = productInfoPage.getProductInfo();
	System.out.println(actproductInfoMap);
	Assert.assertEquals(actproductInfoMap.get("Brand"), "Apple");
		
	}
	@Test
	public void addToCartTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String actCartMesg = productInfoPage.addProductToCart();
		softAssert.assertTrue(actCartMesg.contains("Success"));
		softAssert.assertTrue(actCartMesg.contains("MacBook Pro"));
		
	}

}
