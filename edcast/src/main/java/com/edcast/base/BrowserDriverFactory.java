package com.edcast.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserDriverFactory {
	
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	
	public BrowserDriverFactory(String browser) {
		this.browser=browser.toLowerCase();
	}

	public WebDriver createDriver() {
		
		System.out.println("Create driver " + browser);
		switch (browser) {
		case "chrome":
			// Create driver
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;
		case "firefox":
			// Create driver
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
			driver.set(new FirefoxDriver());

		default:
			System.out.println("Do not know how start" + browser + ", starting chrome");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;
		}
		return driver.get();


	}
}

