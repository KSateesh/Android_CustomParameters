package com.twc.General;
import java.io.File;  
import java.io.IOException;
import java.util.Properties;

import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;


  
public  class DeleteFile extends Driver {  
  
 public void deleteFile()  throws InterruptedException, IOException{  
	 //read from property File Path
	 
	 Driver.property();
		PropertyFile.property();

  try {  
   File fileToDelete = new File(properties.getProperty("LogFilePath")); 

  
   if (fileToDelete.delete()) {  
    System.out.println("File deleted successfully !");  
   } else {  
    System.out.println("File delete operation failed !");  
   }  
  
  } catch (Exception e) {  
   e.printStackTrace();  
  }  
 }  
}  