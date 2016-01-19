package com.twc.ExcelReadWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import com.twc.driver.Driver;


public class WriteResultintoExcel extends Driver {

    public void enterResult(String sheetName, String Result, String Val, int rowIndex, int resultColIndex, int valueColIndex) {

	try {

	    FileInputStream file = new FileInputStream(new File(properties.getProperty("ExcelFilePath")));
	    HSSFWorkbook workbook = new HSSFWorkbook(file);

	    HSSFSheet sheet = workbook.getSheet(sheetName);

	    Cell searchText3 = sheet.getRow(rowIndex).getCell(resultColIndex);
	    searchText3.setCellValue(Result);

	    Cell searchText4 = sheet.getRow(rowIndex).getCell(valueColIndex);
	    searchText4.setCellValue(Val);

	    file.close();

	    FileOutputStream outFile = new FileOutputStream(new File(properties.getProperty("ExcelFilePath")));
	    workbook.write(outFile);
	    outFile.close();

	} catch (FileNotFoundException fnfe) {
	    fnfe.printStackTrace();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }
}
