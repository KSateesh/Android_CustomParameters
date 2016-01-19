package com.twc.General;

import java.io.IOException;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;


@SuppressWarnings("unused")
public class setAddress_Location extends Driver {
	
	public void setLocation() throws InterruptedException, IOException{
		
		String originalContext = Ad.getContext();
		Ad.context("NATIVE_APP");
		
		//reading file from Property file
		 Driver.property();
		 PropertyFile.property();
		 
		 System.out.println("Setting Address on the APP");

		 Thread.sleep(4000);
		 
		 //Getting the present location on the APP
//		 String presentLocation = Ad.findElement(By.id("com.weather.Weather:id/location_name")).getText();
		 String presentLocation = Ad.findElement(By.id("com.weather.Weather:id/location_actionbar_name")).getText();
	     System.out.println("Present Location on App is :: "+presentLocation);
	     
	     Thread.sleep(2000);

	     //Clicking on Search icon to enter the ZIP Code
	     
	     Ad.findElementByAccessibilityId("Search").click();
//	     Ad.findElementById("com.weather.Weather:id/search").click();

	     System.out.println("Entering a ZipCode");
	     
	     Ad.findElementById("com.weather.Weather:id/search_src_text").sendKeys("10035");
//	     Ad.findElementByName("Address/City/Zip").sendKeys("10035");
	      
//	     Thread.sleep(2000);
	     
	     String deviceName = properties.getProperty("deviceName");
	     System.out.println("deviceName is : "+deviceName);
	     Thread.sleep(2000);
	 try{   
	     //Selecting the Address by swipe method   
	     if(deviceName.contains("S6")){
	     Ad.swipe(280,380,1170,380,2000); //for s6
	     }
	     else if (deviceName.contains("Nexus")){
	    	 Ad.swipe(180,174,580,174,1000); //for nexus 
	     }
	    }catch(Exception e){
		  System.out.println("Device Name not matched with S6/Nexus");
		  } 
	     
//	     Ad.swipe(180,290,690,290,1000); // for nexus-1
	     
	     System.out.println("Selected the Address");
	     
	     Thread.sleep(1000);

		try {
			WebElement localAlert = Ad.findElementByName("Get Local Alerts");
			// Ad.findElementById("com.weather.Weather:id/tvTitle");
			if (localAlert != null && localAlert.isDisplayed()) {
				System.out.println("\"Get Local Alert\""+" is present and tap on Later button");
				Ad.findElementByName("Later").click();
				// Ad.findElementById("android:id/button2").click();

			} else {
				System.out.println("\"Get Local Alert\""+" is NOT displayed");
			}

		} catch (Exception e) {
			System.out.println("\"Get Local Alert\""+" is NOT displayed");
		}

		// Getting the updated location after selecting the entered ZipCode/Address
		String updatedLocation = Ad.findElement(By.id("com.weather.Weather:id/location_actionbar_name")).getText();
		System.out.println("Updated Location is :: " + updatedLocation);

	}

}
