package com.twc.AppiumAutoStart;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

@SuppressWarnings("unused")
public class Capabilities_android extends Driver {
	
	@SuppressWarnings({ "rawtypes" })
	public void dcap() throws InterruptedException, IOException {

	 //  Appium auto start and Killing ADB server
		
		Start_Stop_AppiumServer as = new Start_Stop_AppiumServer();
		
		System.out.println("Stopping the ADB server");
		as.killadb();
		System.out.println("ADB Server is stopped");
		Thread.sleep(9000);
		
		System.out.println("===================================");
		
		System.out.println("Stopping the appium server");
		as.stopAppiumServer();
		System.out.println("Appium server is stopped");
     	
		Thread.sleep(10000);
     	
		System.out.println("Starting the appium server");
		as.startAppiumServer();
		System.out.println("Appium server is started and running");
		Thread.sleep(50000);

		//Read data from PropertyFile
		
		Driver.property();
		PropertyFile.property();
		
        //Setting the Capabilities 
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName",  properties.getProperty("platformName")); 
		capabilities.setCapability("platformVersion", properties.getProperty("platformVersion"));
		capabilities.setCapability("deviceName", properties.getProperty("deviceName")); 
		capabilities.setCapability("app", properties.getProperty("appPath"));
		capabilities.setCapability("appPackage", "com.weather.Weather");
		capabilities.setCapability("appActivity", "com.weather.Weather.app.SplashScreenActivity");
		capabilities.setCapability("autoAcceptAlerts",true);
		capabilities.setCapability("newCommandTimeout",100000);

		Ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Ad.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("Working fine");
	
		//System.out.println("logfile is ::"properties.getProperty("LogFilePath"));

		System.out.println("Capabilities are launched");

	}
}







