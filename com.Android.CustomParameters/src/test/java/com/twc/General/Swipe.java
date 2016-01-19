package com.twc.General;

import org.openqa.selenium.Dimension;

import com.twc.driver.Driver;

public class Swipe extends Driver {

	public static void swipe(){
	
		Dimension dimensions = Ad.manage().window().getSize();
	 /*		
        System.out.println("dimensions :: "+dimensions);
		int scrollStart = 2300;
		int scrollEnd = 50;
	     //Ad.swipe(0, scrollStart, 0, scrollEnd, 2000);
		Ad.swipe(0, 2300, 0, 60, 2000);
     */
		  Double startY1 = dimensions.getHeight() * 0.9;  
		//  System.out.println("startY1 value :: "+ startY1);
		  
		  int startY = startY1.intValue();
		//  System.out.println("startY value = " + startY);
		  
		  Double endY1 = (double) (dimensions.getHeight()/40);  //  dimensions.getHeight()  0.2;  == 512.0
		//  System.out.println("endY1 value :: " + endY1);
		  
		  int endY = endY1.intValue();
		 // System.out.println("endY value = " + endY);
		  
		  Ad.swipe(0, startY, 0, endY, 2000);
	}
}
