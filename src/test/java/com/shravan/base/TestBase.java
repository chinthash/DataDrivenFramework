package com.shravan.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.shravan.utilities.ExcelReader;
import com.shravan.utilities.ExtentManager;
import com.shravan.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();;
	public static Properties locators = new Properties();;
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriverWait wait;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\UserDetails.xlsx");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	@BeforeSuite
	public void setUp() {
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Log file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\locators.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				locators.load(fis);
				log.debug("Locators.properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (System.getenv("BrowserName") != null && !System.getenv("BrowserName").isEmpty()) {
				browser = System.getenv("BrowserName");
			} else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome driver intialized");
			} else if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("firefox driver intialized");
			} else if (config.getProperty("browser").equals("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.debug("IE driver intialized");
			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}
	}

	public void click(String locator) {
		driver.findElement(By.xpath(locators.getProperty(locator))).click();
		test.log(LogStatus.INFO, "Clicking on " + locator);

	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			return false;

		}

	}

	public void verifyEquals(String actual, String expected) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			TestUtil.captureScreenShot();
			Reporter.log("<br>");
			Reporter.log(" < br > " + "Failed with Exception" + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			test.log(LogStatus.FAIL, "Failed with Exception" + t.getMessage() + "<br>");
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}
	}

	public void deleteAllUsers() throws InterruptedException {
		driver.get(config.getProperty("testsiteurl"));
		log.debug("Navigated to " + config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		driver.findElement(By.id(locators.getProperty("login.username.id")))
				.sendKeys(config.getProperty("admin_username"));
		driver.findElement(By.id(locators.getProperty("login.password.id")))
				.sendKeys(config.getProperty("admin_password"));
		driver.findElement(By.id(locators.getProperty("login.submit.id"))).click();
		driver.findElement(By.xpath(locators.getProperty("login.okbutton"))).click();
		Thread.sleep(2000);
		List<WebElement> rows = driver.findElements(By.xpath(locators.getProperty("contentmanager.table.rows")));
		for (int r = 2; r <= rows.size(); r++) {
			Actions actions = new Actions(driver);
			 WebElement target =
			 driver.findElement(By.xpath(locators.getProperty("contentmanager.username.column")));
//			WebElement target = wait.until(ExpectedConditions.visibilityOf(
//					driver.findElement(By.xpath(locators.getProperty("contentmanager.username.column")))));
			actions.moveToElement(target).perform();
			Thread.sleep(2000);
			driver.findElement(By.xpath(locators.getProperty("contentmanager.user.deletebutton"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(locators.getProperty("contentmanager.delete.confirm"))).click();
			Thread.sleep(2000);

		}
	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		deleteAllUsers();
		click("contentmanager.username.img");
		click("contentmanager.logout");
		if (driver != null)
			driver.quit();
		log.debug("Browser exited");
	}

}
