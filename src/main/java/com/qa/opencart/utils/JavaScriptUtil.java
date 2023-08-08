package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	
	 public JavaScriptUtil(WebDriver driver) {
		 this.driver = driver;
	 }
	 
//	 public void flash(WebElement element) {
//		 String bgcolor = element.getCssValue("backgroundColor");
//		 for(int i = 0; i < 10; i++) {
//			 changeColor("rgb(0,200,0", element);
//			 changeColor(bgcolor, element);
//		 }
//	 }
	 
//	 private void changeColor(String color, WebElement element) {
//		 JavascriptExecutor js = ((JavascriptExecutor driver);
//		 
//		 js.executeScript(null, null)
//	 }
//	 
	 
	 
	 public void scrollIntoView(WebElement element) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView(true);", element);//KEEP SCROLLING UNTILL WHAT YOU WANT IS IN VIEW
	 }
	 
	 public void clickElementByJS(WebElement element) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click();", element);//SAYING WHICH ELEMENT YOU WANT TO CLICK ON --RARE USE
	 }
	
	
	
	
	
	
	
	
	
	
	
	
}
