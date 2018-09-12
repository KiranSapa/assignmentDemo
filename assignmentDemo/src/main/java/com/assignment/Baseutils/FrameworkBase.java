package com.assignment.Baseutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@Listeners(ReportingUtils.class)
public class FrameworkBase {
	static ExtentTest test;
	public static Properties config = new Properties();

	/**
	 * Method is to generate the logs in the report as well as in the console.
	 * 
	 * @param info
	 */
	public static void log(String info) {
		test = ReportingUtils.parent;
		test.log(Status.INFO, info);
		Reporter.log(info, true);
	}

	/**
	 * Static block used to Load the config.properties file.
	 */
	static {
		String filename = System.getProperty("user.dir") + "\\config\\config.properties";

		try {
			final FileInputStream fileInput = new FileInputStream(filename);
			config.load(fileInput);
			fileInput.close();
		} catch (IOException e) {
			log(e.getLocalizedMessage());
		}

	}
	
	public BaseTest getBaseTest(){
		return new BaseTest();
	}
	
}
