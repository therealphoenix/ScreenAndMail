package com.klinziuk.screenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotMaker {
	WebDriver driver;
	String timeOfScreenshot;

	public ScreenShotMaker(WebDriver driver) {
		this.driver = driver;
	}

	public void makeScreenShot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timeOfScreenshot = String.valueOf(LocalDateTime.now().getNano());
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			FileUtils.copyFile(src, new File("D:/Selenium/" + timeOfScreenshot + "screenshot.jpeg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
