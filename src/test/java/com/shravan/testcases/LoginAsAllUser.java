package com.shravan.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.shravan.base.TestBase;
import com.shravan.utilities.TestUtil;

public class LoginAsAllUser extends TestBase {
	public String expectedWindowName = "Content Manager";

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void loginAsAllUser(Hashtable<String, String> data) throws IOException {
		driver.findElement(By.id(locators.getProperty("login.username.id"))).sendKeys(data.get("username"));
		driver.findElement(By.id(locators.getProperty("login.password.id"))).sendKeys(data.get("password"));
		driver.findElement(By.id(locators.getProperty("login.submit.id"))).click();
		String actualWindowName = driver.getTitle();
		verifyEquals(actualWindowName, expectedWindowName);
		Assert.assertTrue(isElementPresent(By.xpath(locators.getProperty("contentmanager.templates.buton"))),
				"Login not successful");
		log.debug("Logged in to application as All User successfully");

	}

	@AfterClass
	public void logout() {
		click("contentmanager.username.img");
		click("contentmanager.logout");

	}

}
