package com.twc.General;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.twc.driver.Driver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

public class toKnowBuildVersion extends Driver {

	WebElement aboutThisAPP = null;
	WebElement backButton = null;
	
	public void moreOptionsClick() throws InterruptedException {

		Thread.sleep(2000);
		
		System.out.println("Get the \"Build Version\" of APP");

		//Wait for 10 sec for element presence
		WebDriverWait wait = new WebDriverWait(Ad, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.ImageButton[1]")));
		
		// Clicking on Menu Options
		WebElement menu = Ad.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.ImageButton[1]"));

		menu.click();

		// Clicking on Setting link

		Ad.findElementByName("Settings").click();

		// System.out.println("Searching for About this app element");

		System.out.println("About this app element is found, get build version");
		
		aboutThisAPP = Ad.findElementByName("About this app");
		
		aboutThisAPP.click();
		
		String BuildVersion = Ad.findElementById("com.weather.Weather:id/about_version").getText();
		
		System.out.println("Build Version is : " + BuildVersion);
		
		backButton = Ad.findElementByAccessibilityId("Navigate up");

		backButton.click();

		backButton.click();

//		try {
//
//			aboutThisAPP = Ad.findElementByName("About this app");
//
//		} catch (Exception e) {
//			// System.out.println("About this app element not found,so scroll down now");
//		}
//
//		if (aboutThisAPP != null && aboutThisAPP.isDisplayed()) {
//			System.out.println("About this app element is found, get build version");
//
//			// clicking on About this App
//			aboutThisAPP.click();
//
//			// Getting the text of build version
//			String BuildVersion = Ad.findElementByName("English (°F/MPH/IN)").getText();
//		
//			// String BuildVersion = Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[2]").getText();
//
//			System.out.println("Build Version is : " + BuildVersion);
//
//			// Clicking back button
//			backButton = Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.ImageButton[1]");
//
//			backButton.click();
//
//			backButton.click();
//
//		}

		System.out.println("Build Version taken from the \"About this app\" section under Settings");

	}

}
