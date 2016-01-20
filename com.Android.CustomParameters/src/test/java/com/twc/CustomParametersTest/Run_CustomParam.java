package com.twc.CustomParametersTest;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.twc.AppiumAutoStart.Capabilities_android;
import com.twc.General.Cust_param;
import com.twc.General.DeleteFile;
import com.twc.General.File_Exist;
import com.twc.General.toKnowBuildVersion;
import com.twc.driver.Driver;

public class Run_CustomParam extends Driver{
	

	// Verify Custom parameters
	@Test(priority = 1)
	public void Run_CustomParam_Verification() throws Exception {

		Cust_param cp = new Cust_param();
		cp.custparam();
	}
	
   // To set the Capabilities and to Launch the APP
	@BeforeTest
	public void CapabilitesLaunch() throws Exception {

		//Stop and Start Appium Server and launch the APP
		Capabilities_android cap = new Capabilities_android();
		cap.dcap();
		
		Thread.sleep(1000);
		// Check and Delete the log file before the test start execute
		DeleteFile DF = new DeleteFile();
		File_Exist FE = new File_Exist();
		if (FE.fileexist()) {
			DF.deleteFile();

		} else {
			System.out.println("File not exist");
		}
		
		Thread.sleep(1000);
		
		// To know APP Build Version
		toKnowBuildVersion getBuildVersion = new toKnowBuildVersion();
		getBuildVersion.moreOptionsClick();
		
	}

	@AfterTest
	public void afterTest() {
		
	}

}
