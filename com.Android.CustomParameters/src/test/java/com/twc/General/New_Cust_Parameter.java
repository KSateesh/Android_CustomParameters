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


public class New_Cust_Parameter extends Driver {

	public static String ParamValue =null;
	public static String ParamType = null;
	public static String Param_val = null;
	public void custparam() throws Exception{



		System.out.println("Case Started");

		//	//Ad.resetApp();
		WebDriverWait wait = new WebDriverWait(Ad, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/temperature")));
		
		//Temperature  Element
		MobileElement temp = (MobileElement) Ad.findElementById("com.weather.Weather:id/temperature");
		Thread.sleep(10000);
		System.out.println("Temperatuer is : "+temp.getText());
		
		String[] arrays=null;
		
		for(int feed =1;feed<=6;feed++)
		{
			Swipe.swipe();
			Swipe.swipe();
			
			System.out.println("Scroll done for feed_"+feed);
			
			String adbPath = properties.getProperty("adbPath");
			String[] str ={"/bin/bash", "-c", adbPath+" shell setprop log.tag.TwcAd DEBUG"};
			Runtime.getRuntime().exec(str);
		
			System.out.println("Debug command is done");
		
		
			String[] str1 ={"/bin/bash", "-c", adbPath+" logcat -d  >> "+properties.getProperty("LogFilePath")};
			Runtime.getRuntime().exec(str1);
			Thread.sleep(20000);
			System.out.println("Writing App logs to LogFile");

			String FilePath = properties.getProperty("LogFilePath");

			Map<String, String> mapkeys = new HashMap<String, String>();

			try {
				FileInputStream fstream = new FileInputStream(FilePath);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String strLine;

				// / read log line by line ------ strLine = br.readLines(6, 10); /
				StringBuffer sb = new StringBuffer("");
				while ((strLine = br.readLine()) != null) {

					// parse strLine to obtain what you want /
					//System.out.println (strLine);
					sb.append(strLine);

				}
				
				//String lastdata = sb.toString().lastIndexOf(", adUnitId=/7646/app_android_us/display/feed/feed_"+feed);
				  if(sb.toString().contains("}], adUnitId=/7646/app_android_us/display/feed/feed_"+feed)){
				   String req_param = sb.toString().substring(0,sb.toString().lastIndexOf("}], adUnitId=/7646/app_android_us/display/feed/feed_"+feed));
				   String req = req_param.substring(req_param.lastIndexOf("preload_partner"));
				   
				   String[][] data = new String[10][10];
					ExcelData er = new ExcelData();
					data = er.excelread();
					
				   for(int testcase=1;testcase<=43;testcase++){

						
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

									int Getresult =feed*2;
									
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

				   	
				  }
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("****************************");

		System.out.println("Custom Parameters verifcation case completed");
		
		System.out.println("****************************");
	}

}


