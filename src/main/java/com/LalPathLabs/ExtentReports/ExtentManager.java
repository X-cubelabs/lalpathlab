package com.LalPathLabs.ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager 
{
	public static ExtentReports extent;
	 
    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            extent = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html", true);
        }
        return extent;
    }
}
