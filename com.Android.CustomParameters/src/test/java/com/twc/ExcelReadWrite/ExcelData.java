package com.twc.ExcelReadWrite;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.twc.driver.Driver;


public class ExcelData extends Driver {

    public String[][] excelread() throws Exception {
	File f = new File(properties.getProperty("ExcelFilePath"));
	FileInputStream fis = new FileInputStream(f);
	HSSFWorkbook wb = new HSSFWorkbook(fis);
	HSSFSheet ws = wb.getSheet("SMOKE");

	int rownum = ws.getLastRowNum() + 1;
	int colnum = ws.getRow(0).getLastCellNum();
	String data[][] = new String[rownum][colnum];

	for (int i = 0; i < rownum; i++) {
	    HSSFRow row = ws.getRow(i);

	    for (int j = 0; j < colnum; j++) {
		HSSFCell cell = row.getCell(j);
		String value = CellToString.ctos(cell);
		data[i][j] = value;		
//----// System.out.println("Values are :" + value + " : data[" + i + "][" + j + "]");

	    }
	}
	// wb.close();
	return data;

    }

}
