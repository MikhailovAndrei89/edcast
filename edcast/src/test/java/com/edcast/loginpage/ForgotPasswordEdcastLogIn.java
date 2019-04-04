package com.edcast.loginpage;

import org.openqa.selenium.By;
//import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.edcast.base.TestUtilites;

public class ForgotPasswordEdcastLogIn extends TestUtilites {

		
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


}