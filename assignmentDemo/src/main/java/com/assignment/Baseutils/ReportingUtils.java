package com.assignment.Baseutils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * |
 * @author kkumars
 *
 */
public class ReportingUtils implements ITestListener {
	private static ExtentReports extent1 = ExtentManager.createInstance("CustomisedReport.html");
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    public static ExtentTest parent;
    @Override
   	public synchronized void onStart(ITestContext context) {
    	parent = extent1.createTest(getClass().getName());
           parentTest.set(parent);
   	}

   	@Override
   	public synchronized void onFinish(ITestContext context) {
   		extent1.flush();
   	}
   	
   	@Override
   	public synchronized void onTestStart(ITestResult result) {
   		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName());
           test.set(child);
   	}

   	@Override
   	public synchronized void onTestSuccess(ITestResult result) {
   		((ExtentTest) test.get()).pass("Test passed");
   	}

   	@Override
   	public synchronized void onTestFailure(ITestResult result) {
   		((ExtentTest) test.get()).fail(result.getThrowable());
   	}

   	@Override
   	public synchronized void onTestSkipped(ITestResult result) {
   		((ExtentTest) test.get()).skip(result.getThrowable());
   	}

   	@Override
   	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
   		
   	}


	public static class ExtentManager {
	    
	    private static ExtentReports extent;
	    
	    public ExtentReports getInstance() {
	    	if (extent == null)
	    		createInstance("test-output/extent.html");
	    	
	        return extent;
	    }
	    
	    public static ExtentReports createInstance(String fileName) {
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        extent=new ExtentReports();
	        extent.attachReporter(htmlReporter);
	         return extent;
	    }
	
}
}
	
