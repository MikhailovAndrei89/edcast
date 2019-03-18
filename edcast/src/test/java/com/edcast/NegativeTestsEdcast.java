package com.edcast;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTestsEdcast {

public static void main(String[] args) {}
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod
	protected void setUp(@Optional("chrome") String browser) {
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

	
	@Parameters({"username","password"})
	@Test()
	public void incorrectUserName(String username, String password ) {
		System.out.println("Starting incorrectUserName test");
		
		// open the page
		String url = "https://portnov.edcast.com/log_in";
		driver.get(url);
		//sleep(7000);
		System.out.println("Page is opened");
		// enter username
		WebElement usernameElement = driver.findElement(By.xpath("//input[contains(@id,'undefined-namedomaincom')]"));
		usernameElement.sendKeys(username);
		// enter password
		WebElement passwordElement = driver.findElement(By.xpath("//input[contains(@id,'undefined-undefined-Password')]"));
		passwordElement.sendKeys(password);
		// push log in button
		WebElement logInButton = driver.findElement(By.xpath("//div[@class='login-button']//button[@type='button']"));
		logInButton.click();
		// verifications

		sleep(3000);
		// Succesessful log in message
		WebElement successMessage = driver.findElement(By.xpath("//*[contains(text(),'Invalid')]"));
		String expectedsuccessMessage = "Invalid";
		String actualsuccessMessage = successMessage.getText();
		Assert.assertTrue(actualsuccessMessage.contains(expectedsuccessMessage),
				"bug\nexpectedsuccessMessage:" + expectedsuccessMessage + "\nactualsuccessMessage" + actualsuccessMessage);
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
