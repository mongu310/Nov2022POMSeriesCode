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
	
	public ElementUtil(WebDriver driver) {   //**************
		this.driver = driver;
	}
	
	
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);// 	WITHOUT WAIT
	}
	
	public WebElement getElement(By locator, int timeOut) {
		return waitForElementVisible(locator, timeOut);// WITH WAIT
	}
	
	
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	//************************************************************************************
	
	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}
			
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	
	//***************************************************************************************
	
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String getElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	
	public void getElementAttributes(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		for(WebElement e : eleList) {
			String attrVal = e.getAttribute(attrName);
			System.out.println(attrVal);
		}
	}
	
	//FIND TOTAL NUMBER OF ANY ELEMENT*************
	public int getTotalElementsCOunt(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("total elements: " +locator + "--->" + eleCount);
		return eleCount;
	}
	
	
	//*******************************************SELECT BASED DROP DOWN UTILS****************************************** 
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select =  new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectDropDownByValue(By locator, String value) {
		Select select =  new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select select =  new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	
	//THIS IS SAME BUT A LITTLE SLOWER************************
	public List<WebElement> getDropDwnOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}
	
	public List<String> getDropDownOptionsTextList(By locator) {
		List<WebElement> optionsList = getDropDwnOptionsList(locator);
		List<String> optionsTextList = new ArrayList<String>();
		
	for(WebElement e : optionsList) {
		String text = e.getText();
		optionsTextList.add(text);
	}
	return optionsTextList;
	}
	
	public void selectDropDownValue(By locaotr, String expValue) {
		List<WebElement> optionsList = getDropDwnOptionsList(locaotr);
		for(WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
				if(text.equals(expValue)) {
					e.click();
					break;
				}
		}
	}
	
	
	public int getTotalDropDownOptions(By locator) {
		int optionsCount = getDropDwnOptionsList(locator).size();
		System.out.println("total options ==> " + optionsCount);
		return optionsCount;
	}
	//****************************************************
	
	
	//TO SEARCH AND PICK SOMETHING
	public void doSearch(By suggListLocator, String suggName) {
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
	
	//check box and name
	
	// //a[text()='Kevin Froster']/parent::td/preceding-sibling::td/input[@type='checkbox']
		public void selectUser(String userName) {
			driver.findElement(By.xpath("//a[text()='"+userName+"']/parent::td/preceding-sibling::td/input[@type='checkbox']")).click();

		}
		
		//a[text()='Ali khan']/parent::td/following-sibling::td/a[@_clid='29593980']
		public String getUserCompanyName(String userName) {
			return driver.findElement(By.xpath("//a[text()='"+userName+"']/parent::td/following-sibling::td/a[@context='company']")).getText();
		}
		
		
		
		//********************************************  WAIT UTILS  ******************************************************************
		//****************************************************************************************************************************
		//****************************************************************************************************************************
		
		
		
		/**An expectation for checking that an element is present on the DOM of a page. 
		 * This does not necessarily mean that the element is visible.
		 * @param locator
		 * @param timeOut
		 * @return
		 */

		public WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));// METHOD WITH WAIT 
		}
		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));// METHOD WITH WAIT 
		}
		
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locator are visible. 
		 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		/**
		 * An expectation for checking that there is at least one element present on a web page.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		
		
		
//		_________________________________________________________________________________________
		
		public Alert waitForAlertPresence(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		public String getAlertText(int timeOut) {
			return waitForAlertPresence(timeOut).getText();
		}
		
		public void acceptAlert(int timeOut) {
			waitForAlertPresence(timeOut).accept();
		}
		
		public void dismissAlert(int timeOut) {
			waitForAlertPresence(timeOut).dismiss();
		}
		
		public void alertSendKeys(int timeOut, String value) {
			waitForAlertPresence(timeOut).sendKeys(value);
		}
		
		//*********************************FOR TITLES*****************************************
		
		public String waitForTitleContains(int timeOut, String titleFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));// WHEN THERE IS A HUGE TITME AND YOU WANT TO SEE 
			wait.until(ExpectedConditions.titleContains(titleFractionValue));			//IF ITS THERE OR NOT
			return driver.getTitle();
		}
		
		public String waitForTitleIsAndFetch(int timeOut, String titleValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.titleIs(titleValue));
			return driver.getTitle();
		}
		
		//****************************************FOR URLS*******************************************
		
		public String waitForURLContainsAndFetch(int timeOut, String urlFractionValue ) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));// FOR ONLY SOME OF THE URL
			wait.until(ExpectedConditions.urlContains(urlFractionValue));			
			return driver.getCurrentUrl();
		}
		
		public String waitForURLIsAndFetch(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));// FOR THE EXACT URL
			wait.until(ExpectedConditions.urlToBe(urlValue));			
			return driver.getCurrentUrl();
		}
		
		public boolean waitForURLContains(int timeOut, String urlFractionValue ) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.urlContains(urlFractionValue));			
		}
		
		//__________________________________________FOR FRAMES____________________________________________________________________
		
		public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("idOrName"));
		}
		
		public void waitForFrameAndSwitchToItByIndex(int timeOut, String frameIndex) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frameIndex"));
		}
		
		public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement) {  //-- this is better than the locator one
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}
		
		public void waitForFrameAndSwitchToItByFrameLocator(int timeOut, By frameLocator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frameLocator"));
		}
		
		/**
		 * An expectation for checking an element is visible and enabled such that you can click it.
		 * @param timeOut
		 * @param locator
		 */
		public void clickWhenReady(int timeOut, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		
		public WebElement waitForElementToBeClickable(int timeOut, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		public void doClickWithActionsAndWait(int timeOut, By locator) {
			WebElement ele = waitForElementToBeClickable(timeOut, locator);
			Actions act = new Actions(driver);
			act.click(ele).build().perform();
		}
	
	
}
