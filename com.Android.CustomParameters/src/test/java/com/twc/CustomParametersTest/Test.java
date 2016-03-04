package com.twc.CustomParametersTest;

import com.twc.AppiumAutoStart.Capabilities_android;
import com.twc.General.Cust_param;
import com.twc.General.DeleteFile;
import com.twc.General.File_Exist;
import com.twc.General.New_Cust_Parameter;
import com.twc.General.toKnowBuildVersion;
import com.twc.driver.Driver;


public class Test extends Driver{

	public static void main(String[] args) throws Exception {

		Capabilities_android cap = new Capabilities_android();
		cap.dcap();
			
		//Check and Delete the log file if exists before start the custom parameters verification 
		DeleteFile DF = new DeleteFile();
		File_Exist FE = new File_Exist();
		if(FE.fileexist()) {
			DF.deleteFile();
		} else {
			System.out.println("File not exist");
		}
	
		//Thread.sleep(1500);
		
		// Calling the method to know APP Build Version
		//toKnowBuildVersion getBuildVersion = new toKnowBuildVersion();
		//getBuildVersion.moreOptionsClick();
				
		//Thread.sleep(1500);
		
		//Verify Custom parameters
		//Cust_param cp= new Cust_param();
		//cp.custparam();
		New_Cust_Parameter cp=new New_Cust_Parameter();
		cp.custparam();
//		Thread.sleep(1000);
		//Close all resources
//		Ad.quit();

	}

}
