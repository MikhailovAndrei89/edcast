package com.edcast.loginpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.edcast.base.TestUtilites;

public class PositiveTestsEdcastLogIn extends TestUtilites {
	
	
	@Parameters({"username","password"})
	@Test()
	public void correctUserName(String username, String password )  {
		System.out.println("Starting correctUserName test");
		
		// open the page
		String url = "https://portnov.edcast.com/log_in";
		driver.get(url);
		//sleep(5000);
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
//		// Succesessful log in message
//		WebElement successMessage = driver.findElement(By.xpath("//*[contains(text(),'Invalid')]"));
//		String expectedsuccessMessage = "Invalid";
//		String actualsuccessMessage = successMessage.getText();
//		Assert.assertTrue(actualsuccessMessage.contains(expectedsuccessMessage),
//				"bug\nexpectedsuccessMessage:" + expectedsuccessMessage + "\nactualsuccessMessage" + actualsuccessMessage);
 }

}
