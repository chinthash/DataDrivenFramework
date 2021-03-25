package com.shravan.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import com.shravan.base.TestBase;

public class LoginAsAdmin extends TestBase {
	@BeforeTest
	public void loginAsAdmin() throws InterruptedException {
		driver.findElement(By.id(locators.getProperty("login.username.id")))
				.sendKeys(config.getProperty("admin_username"));
		driver.findElement(By.id(locators.getProperty("login.password.id")))
				.sendKeys(config.getProperty("admin_password"));
		driver.findElement(By.id(locators.getProperty("login.submit.id"))).click();
		driver.findElement(By.xpath(locators.getProperty("login.okbutton"))).click();
		Assert.assertTrue(isElementPresent(By.xpath(locators.getProperty("contentmanager.users"))),
				"Login not successful");
		log.debug("Logged in to application successfully");
	}

	@AfterClass
	public void logout() {
		click("contentmanager.username.img");
		click("contentmanager.logout");

	}

}
