package com.qa.opencart.tests;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.Assert;

@Epic("EPIC - 100: design login for open cart app")
@Story("US-Login: 101: design login page features for open cart")
public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.TRIVIAL)
	@Description("....checking the title of the page.... Author: Naveen")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("....checking the url of the page.... Author: Naveen")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("....checking forgot pwd link exist.... Author: Naveen")
	@Test(priority = 3)
	public void forgetPwdLisnkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("....checking user is able to login to app with correct username and password....")
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password").trim()); // Moving to the accounts page now 
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
//	@Test
//	public void continueTest() {
//		loginPage.newCustContinue();//*************** BY ME ******************
//	}
	
	
	
	
	
	
	
	
	
}
