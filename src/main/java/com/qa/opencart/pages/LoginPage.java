package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;


public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil; //***
	
	//1. private By locators:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");													//LOCATORS SHOULD BE PRIVATE
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By newCust = By.xpath("//a[@class='btn btn-primary']");// this on is by me****
	private By registerLink = By.linkText("Register");

	
	
	//2. page constructor:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver); // Bringing in the element util
	}
	
	//3. page actions/methods:
	
	@Step("...getting the login page title....")
	public String getLoginPageTitle() {
		//String title = driver.getTitle();
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE); // BETTER
		System.out.println("Login page title: " + title);
		return title;																				//METHODS SHOULD BE PUBLIC
	}
	
	@Step("...getting the login page url....")
	public String getLoginPageURL() {
		//String url = driver.getCurrentUrl();
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);// BETTER
		System.out.println("Login page url: " + url);
		return url;
	}
	
	@Step("...checking the forgot pwd link....")
	public boolean isForgotPwdLinkExist() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();//  BETTER
	}
	
	
	@Step("login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un, String pwd) {
		//driver.findElement(emailId).sendKeys(un);			//   This is ok
		//driver.findElement(password).sendKeys(pwd);	
		//driver.findElement(loginBtn).click();
		System.out.println("Ap cred are : " + un + ":" + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);	//This is better with the element util
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver); //					************ PAGE CHAINING MODEL *****************
	}
	
	@Step("naveigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
	public void newCustContinue() {
		driver.findElement(newCust).click();// BY ME **********
	}
	
	
	
	
	
}
