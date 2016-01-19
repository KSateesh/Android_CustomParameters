package com.twc.AppiumAutoStart;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.twc.driver.Driver;
import com.twc.driver.PropertyFile;

public class movingFiles extends Driver {

	@SuppressWarnings("unused")
	public static void movefiles() throws IOException {

		Driver.property();
		PropertyFile.property();

		String file1 = properties.getProperty("img1");

		String file2 = properties.getProperty("img2");

		String file3 = properties.getProperty("css1");

		String file4 = properties.getProperty("css2");

		File source = new File(file1);

		File dest = new File(file2);

		File css1 = new File(file3);

		File css2 = new File(file4);

		long start = System.nanoTime();

		long end = System.nanoTime();

		FileUtils.copyFile(source, dest);

		FileUtils.copyFile(css1, css2);

//		System.out.println("Required files moved to TestReports Folder - For Reporting Purpose");

		// System.out.println("Time taken to move a file : " + (end-start));

		// Date time = new Date(System.currentTimeMillis());

		// System.out.println("\n Program Ran on " + time );

	}

}