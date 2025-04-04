package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//a");
	private By productMetaData = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[position()=2]/li");
	private Map<String,String> productInfoMap;
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMessg = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderValue() {
	String productHeaderVal =	eleUtil.doElementGetText(productHeader);
	System.out.println("product header value is: " + productHeaderVal);
	return productHeaderVal;
	}
	
	public int getProductImagesCount() {
	int imagesCount =	eleUtil.WaitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	System.out.println("product images count:" + imagesCount);
		return imagesCount;
	}
	
	public void enterQuantity(int qty) {
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
		System.out.println("product quantity: " + qty);
		
		
	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMessg = eleUtil.WaitForElementVisible(cartSuccessMessg, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		System.out.println("Cart Success Mesg: " + successMessg);
		return successMessg;
		
	}
	public Map<String,String> getProductInfo() {
		 productInfoMap = new HashMap<String,String>();
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
		
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList =	eleUtil.getElements(productMetaData);
		for(WebElement e : metaList) {
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
		String key = metaInfo[0].trim();
		String value =metaInfo[1].trim();
		productInfoMap.put(key, value);
		
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String exTaxValue = exTax.split(":")[1].trim();
		productInfoMap.put("productprice", price);
		productInfoMap.put(exTax, exTaxValue);
		
	}

}
