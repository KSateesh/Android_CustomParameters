package com.twc.General;


import io.appium.java_client.MobileElement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import atu.testng.reports.ATUReports;
import com.twc.ExcelReadWrite.ExcelData;
import com.twc.ExcelReadWrite.WriteResultintoExcel;
import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;


public class Cust_param extends Driver {

	public void custparam() throws Exception{

		System.out.println("Case Started");
		
		String adbPath = properties.getProperty("adbPath");
		String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
		Process p = Runtime.getRuntime().exec(str);
		
		System.out.println("Debug command is done");
	
//		String[] str2 ={"/bin/bash", "-c", adbPath+" logcat -c >> "+properties.getProperty("LogFilePath")};
//		Process p2 = Runtime.getRuntime().exec(str2);
//		System.out.println("Delete existing App logs to LogFile");
	
		String[] str1 ={"/bin/bash", "-c", adbPath+" -d logcat -v time >> "+properties.getProperty("LogFilePath")};
		Process p1 = Runtime.getRuntime().exec(str1);
		System.out.println("Writing App logs to LogFile");

	//Wait for 10 sec for element presence
	WebDriverWait wait = new WebDriverWait(Ad, 20);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
	
	//Temperature  Element
	MobileElement temp = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
	Thread.sleep(10000);
	System.out.println("Temperatuer is : "+temp.getText());
	
	//Thread.sleep(5000);
	Swipe.swipe();
	//Thread.sleep(5000);
	Swipe.swipe();
		
		BufferedReader r = new BufferedReader(new FileReader(properties.getProperty("LogFilePath")));

		String line = "";
		String allLine = "";

//		while((line=r.readLine()) != null)
//		{
//			System.out.println("Sys data is :: "+line);
//		}

		String FilePath = properties.getProperty("LogFilePath");

		Map<String, String> mapkeys = new HashMap<String, String>();
		StringBuffer res_sb=null;
		try {
			FileInputStream fstream = new FileInputStream(FilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;

			// / read log line by line ------ strLine = br.readLines(6, 10); /
			res_sb = new StringBuffer("");
			while ((strLine = br.readLine()) != null) {
				
				//System.out.println (strLine);
				res_sb.append(strLine);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
			String	req1=null;
			String	req=null;
			String[] arrays=null;
			
			for(int FeedValue =1;FeedValue<=6;FeedValue++)
			{	
				System.out.println("Feed Value ======> "+FeedValue);
				Thread.sleep(5000);
				String sb = res_sb.toString();
				if (sb.toString().contains("slotName=weather.feed"+FeedValue)) {
					//String sb = 
					//System.out.println(sb.toString());
					System.out.println("Feed URL Contains :::::::: "+FeedValue);
				
//					if (sb.toString().equals("slotName=weather.feed"+FeedValue)) {

					System.out.println("---------------------");    
					System.out.println("Get the Feed_"+FeedValue+" Call Data");
					System.out.println("---------------------");
					
					//Taking the FeedIndex and then get the flmtpc and Locale key value pair 
					int feed_Index = sb.toString().lastIndexOf("slotName=weather.feed"+FeedValue);
			//---//	System.out.println("Position : " + feed_Index);
					String beforeStr = sb.toString().substring(feed_Index - 45,feed_Index);
	    	//---//	System.out.println("Before String: " + beforeStr);
					req1 = sb.toString().substring(feed_Index);

					//Adding the beforeStr and req1 string to finalStr,which is a having feed call content
					String finalStr = beforeStr + req1;
					// System.out.println("finalStr : "+finalStr);
					//req = finalStr.substring(0, finalStr.indexOf("}"));
					req = finalStr.substring(0, finalStr.indexOf("feed_"+FeedValue)+6);
					//System.out.println("Verifing the :: " + req);
//					}
					//Read Excel
					String[][] data = new String[10][10];
					ExcelData er = new ExcelData();
					data = er.excelread();

					//First Test Cases
					
					Thread.sleep(10000);
					
					for(int testcase=1;testcase<=43;testcase++)
					{
						Thread.sleep(500);
						String param = data[testcase][4].toString();
				        System.out.println("============================================="); //---//
						System.out.println("Verifying Cust_Param is : "+ param);
						// Loop to read all lines one by one from file and print It.

						// return mbrLastName;// Getting ord value from Cust_param
    	            	System.out.println("============================================="); //---//		
						System.out.println("Verifing the Feed_Call_Request : "+req);
					    arrays = req.split(", ");
						for(String keys : arrays){
				//---//  System.out.println(keys);
							if(keys.contains("=")){
								String[] key = keys.split("=");
								// System.out.println(key[0] + "---"+key[1]);
								mapkeys.put(key[0], key[1]);

								if (param.trim().equals(key[0].trim())) {
									String ExactValue = key[key.length - 1];
									System.out.println("Cust_Param '"+param +"' value is : " + ExactValue);

									WriteResultintoExcel wResult = new WriteResultintoExcel();

									int Getresult =FeedValue*2;
									
									int ResultColumn_1=7+Getresult;
									int ResultColumn_2=8+Getresult;

									//If Value Size is NA
									if(data[testcase][6].contains("NA"))
									{
										if (data[testcase][8].contains(ExactValue.toString())&&ExactValue!="") {
											System.out.println("NO Values found for "+param);
											wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
										}else if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Fixed")){
											System.out.println(param+" value(s) : " + keys.contains(data[testcase][4].toString()));
											System.out.println(keys);
											wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
										}

										break;
									}

									//Verify Value Size is not NA data
									if(!data[testcase][6].contains("NA")){
								         //System.out.println("Exact Value is "+ ExactValue + " Data in Excel " + data[testcase][8]+" Size is : "+ExactValue.length());
										if (data[testcase][8].contains(ExactValue)&&(ExactValue.length() !=data[testcase][6].length())&&ExactValue!="") {
											System.out.println("NO Values found for "+param);
											wResult.enterResult("SMOKE", "Fail", ExactValue, testcase, ResultColumn_1, ResultColumn_2);

										} else

											if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Not Fixed")){
												System.out.println(data[testcase][4]+" value(s) :" + keys.contains(param));
												System.out.println(keys);
												wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
											}else if(data[testcase][7].contains(ExactValue)||data[testcase][5].contains("Fixed")){
												System.out.println(data[testcase][4]+" value(s) :" + keys.contains(param));
												System.out.println(keys);
												wResult.enterResult("SMOKE", "Pass", ExactValue, testcase, ResultColumn_1, ResultColumn_2);
											}
									}

								}

							}
					}
					}
					
					//Thread.sleep(10000);
//					if(FeedValue == 1) { 
//						Swipe.swipe();
//						Thread.sleep(10000); 
//					}
//					else {
						Swipe.swipe();
						Thread.sleep(5000);
						Swipe.swipe();
						Thread.sleep(10000);
					//}
				}	
			}

		System.out.println("****************************");

		System.out.println("Custom Parameters verifcation case completed");
		
		System.out.println("****************************");
				
	}

}

