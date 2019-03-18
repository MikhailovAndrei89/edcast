package com.edcast;

import org.openqa.selenium.By;
//import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ForgotPasswordEdcast {

	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod
	protected void setUp(@Optional("firefox") String browser) {
		System.out.println("Create driver " + browser);

		switch (browser) {
		case "chrome":
			// Create driver
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
			driver = new ChromeDriver();
			break;
		case "firefox":
			// Create driver
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Do not know how start" + browser + ", starting chrome");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		sleep(2000);

	}

	
	
	@Parameters({"username"})
	@Test ()
	public void logInTest(String username) {
		System.out.println("Starting Forgot password test");
		
		// open the page
		String url = "https://portnov.edcast.com/log_in";
		driver.get(url);
		// sleep(5000);
		System.out.println("Page is opened");
		// push forgot button
		WebElement ForgotWord = driver.findElement(By.xpath("//*[contains(text(),'Forgot Password?')]"));
		ForgotWord.click();
		// new url
		sleep(2000);
		String expectedUrl = "https://portnov.edcast.com/forgot_password";
		String actualUrlString = driver.getCurrentUrl();
		Assert.assertEquals(actualUrlString, expectedUrl);
		// enter username
		WebElement usernameElement = driver.findElement(By.xpath("//input[contains(@type,'email')]"));
		usernameElement.sendKeys(username);
		// push log in button
		WebElement SubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
		SubmitButton.click();
		// verifications

		// new url
		sleep(2000);
		// log out button is visible
		// Succesessful log in message
		 WebElement successMessage = driver.findElement(By.xpath("//div[@class='forgot-heading']"));
		 String expectedsuccessMessage = "Email Sent!";
		 String actualsuccessMessage = successMessage.getText();
		 Assert.assertTrue(actualsuccessMessage.contains(expectedsuccessMessage),
		 "bug\nexpectedsuccessMessage:" + expectedsuccessMessage +
		 "\nactualsuccessMessage" + actualsuccessMessage);

	}
	@AfterMethod
	protected void tearDown() {
		System.out.println("Close driver");
		driver.quit();

	}
	/**
	 * Static Sleep
	 */
	private void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}