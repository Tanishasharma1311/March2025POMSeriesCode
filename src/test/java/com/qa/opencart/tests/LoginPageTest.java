package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100: design  login for open cart app")
@Story("US-Login: 101: design login features for open cart app")

public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("checking the title of the page......tester.....Tanisha")
	@Test(priority =1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		
	}
	@Description("checking the url of thr page......tester.....Tanisha")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority =2)
	public void loginPageURLTest() {
		String actualUrl= loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
		
	}
	@Severity(SeverityLevel.CRITICAL)
	@Description("checking the forgot pwd link exist......tester.....Tanisha")
	@Test(priority =3)
	public void forgotPwdLinkExistTest() {
		boolean flag =loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}
	@Severity(SeverityLevel.CRITICAL)
	@Description("checking user ia able to login to app with correct username and password......")
	@Test(priority =4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
