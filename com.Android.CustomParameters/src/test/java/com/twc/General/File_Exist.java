package com.twc.General;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;


public class File_Exist extends Driver{
	 
	public boolean fileexist() throws InterruptedException, IOException
	{
		Driver.property();
		PropertyFile.property();
		System.out.println("Filepath verifying");
		System.out.println("Path is ::"+properties.getProperty("LogFilePath"));
		
		File f = new File(properties.getProperty("LogFilePath"));
		
				//Users/aparna/Documents/Naresh/com.weather.SmokeTest/Files/syslog.log");
		if(f.exists() && !f.isDirectory()) { 
		   System.out.println("File Exist to run");
		   return true;
		}else
		{
			System.out.println("File not existed yet");
			return false;
		}
	}
}
