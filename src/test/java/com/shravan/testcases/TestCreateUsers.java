package com.shravan.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.shravan.utilities.TestUtil;

public class TestCreateUsers extends LoginAsAdmin {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void testCreateUsers(Hashtable<String, String> data) throws InterruptedException {
		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the testdata as run mode is N");
		}
		click("contentmanager.users");
		click("contentmanager.user.add");
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.id(locators.getProperty("contentmanager.username.id"))))
				.sendKeys(data.get("username"));
		driver.findElement(By.id(locators.getProperty("contentmanager.displayaneme.id")))
				.sendKeys(data.get("displayname"));
		driver.findElement(By.id(locators.getProperty("contentmanager.password"))).sendKeys(data.get("password"));
		driver.findElement(By.id(locators.getProperty("contentmanager.repeat.password")))
				.sendKeys(data.get("password"));
		if (data.get("contentwrite").equals("Y")) {
			click("contentmanager.contentwrite");
		}
		Thread.sleep(1000);
		if (data.get("contentread").equals("Y")) {
			click("contentmanager.contentread");
		}
		Thread.sleep(1000);
		if (data.get("contentsettingsread").equals("Y")) {
			click("contentmanager.contentsettingsread");
		}
		Thread.sleep(1000);
		if (data.get("contentsettingswrite").equals("Y")) {
			click("contentmanager.contentsettingswrite");
		}
		Thread.sleep(1000);
		if (data.get("technicalsettingsread").equals("Y")) {
			click("contentmanager.technicalsettingsread");
		}
		Thread.sleep(1000);
		if (data.get("technicalsettingswrite").equals("Y")) {
			click("contentmanager.technicalsettingswrite");
		}
		Thread.sleep(1000);
		if (data.get("transporter").equals("Y")) {
			click("contentmanager.transporter");
		}
		Thread.sleep(1000);
		if (data.get("automationtesttenant").equals("Y")) {
			click("contentmanager.automationtesttenant");
		}
		Thread.sleep(1000);
		if (data.get("demotenant").equals("Y")) {
			click("contentmanager.demotenant");
		}
		Thread.sleep(1000);
		if (data.get("esignaturetenant").equals("Y")) {
			click("contentmanager.esignaturetenant");
		}
		Thread.sleep(1000);
		if (data.get("initialmandant").equals("Y")) {
			click("contentmanager.initialmandant");
		}
		Thread.sleep(1000);
		if (data.get("sapdemo").equals("Y")) {
			click("contentmanager.sapdemo");
		}
		Thread.sleep(1000);
		if (data.get("testtenant").equals("Y")) {
			click("contentmanager.testtenant");
		}
		Thread.sleep(1000);
		click("contentmanager.user.createbutton");
		Thread.sleep(3000);
	}

}
