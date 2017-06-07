package com.klinziuk.screenshot;
import org.openqa.selenium.WebDriver;

public class Main {
	public static void main(String[] args) {
		WebDriver driver = BrowserDriver.CHROME.selectDriver();
		driver.manage().window().maximize();
		driver.get("http://www.protesting.ru/");
		new ScreenShotMaker(driver).makeScreenShot();
		driver.close();
		driver.quit();
		BrowserDriver.CHROME.killDriver();
		new Mail().sendMessage();
	}
}
