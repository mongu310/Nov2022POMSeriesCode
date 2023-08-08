package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password").trim()); // Moving to the accounts page now 
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
	public void accPageHeadersCountTest() {
		List<String> actualAccountPageHeadersList = accPage.getAccountPageHeadersList();
		System.out.println("Acc Page Headers List: " + actualAccountPageHeadersList);
		Assert.assertEquals(actualAccountPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersValueTest() {
		List<String> actualAccountPageHeadersList = accPage.getAccountPageHeadersList();
		System.out.println("Acc Page Headers List: " + actualAccountPageHeadersList);
		System.out.println("Expected Acc Page Headers List: " + AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccountPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][]  {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"},
			{"Naveen"}
		};
	}
	
	
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount() > 0 );
	}
	
	

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][]  {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW"},  
			{"Samsung", "Samsung Galaxy Tab 10.1"},

		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		searchPage = accPage.performSearch(searchKey);
		if(searchPage.getSearchProductsCount()>0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
			
		}
		
	}

	
	
	
	
}
