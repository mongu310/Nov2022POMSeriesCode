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
	private ElementUtil eleUtil; // ***
	
	private By logoutLink = By.linkText("Logout");
	private By accsHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");
	
	
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver); // Bringing in the element util
	}
	
	public String getAccPageTitle() {
		//String title = driver.getTitle();
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE); // BETTER
		System.out.println("Acc page title : " + title);
		return title;
	}
	
	public String getAccPageURL() {
		//String url = driver.getCurrentUrl();
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE); // BETTER
		System.out.println("Acc page url : " + url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		//return driver.findElement(logoutLink).isDisplayed();									//encapsulation example
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();  // BETTER

	}
	
	public boolean isSearchExist() {
		//return driver.findElement(search).isDisplayed();										// encapsulation example
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();  // BETTER
	}
	
	public List<String> getAccountPageHeadersList() {
		//List<WebElement> accHeadersList = driver.findElements(accsHeaders);
		List<WebElement> accHeadersList = eleUtil.waitForElementsVisible(accsHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT); // BETTER
		List<String> accHeadersValList = new ArrayList<String>();
		
		for(WebElement e : accHeadersList) { 	//capture the text of every header and store it in a String
			String text = e.getText();
			accHeadersValList.add(text);
		}
		return accHeadersValList;
	}
	
	public SearchPage performSearch(String searchKey) {
		
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
			
		}
		else {
			System.out.println("Search field is not present on the page....");
			return null;
		}
		
	}

		
		
		
	
}
