package com.klinziuk.screenshot;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum BrowserDriver {
	
    CHROME(1);

    public static final String BASEURL = "http://www.protesting.ru/";
    private int index;
    BrowserDriver(int browserIndex) {
        this.index = browserIndex;
    }

    public  WebDriver selectDriver() {
        switch (index) {
            case 1: {
            	ChromeOptions ops = new ChromeOptions();
            	Map<String, Object> prefs = new HashMap<String, Object>();
            	prefs.put("credentials_enable_service", false);
            	prefs.put("password_manager_enabled", false); 
            	ops.setExperimentalOption("prefs", prefs);
                ops.addArguments("--disable-notifications");
                System.setProperty("webdriver.chrome.driver", "D:/Java/Chrome/chromedriver.exe");
                return new ChromeDriver(ops);
            }
            default: {
                System.setProperty("webdriver.chrome.driver", "D:/Java/Chrome/chromedriver.exe");
                return new ChromeDriver();
            }
        }
    }

    public void killDriver() {
        try {
            switch (index) {
                case 1: {
                    Runtime.getRuntime().exec("taskkill /f /IM chromedriver.exe");
                }
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}