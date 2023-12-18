package nl.fastned.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
/**
 * Utility class for configuring and obtaining an instance of ExtentReports for HTML reporting.
 */
public class ExtentReporterNG {
	static ExtentReports extent;


	/**
	 * Returns an instance of ExtentReports configured with ExtentSparkReporter.
	 *
	 * @return the configured ExtentReports instance
	 */
	public static ExtentReports getReporterObject()
	{
		
	//	ExtentReports , ExtentSparkReporter
		String path =System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Mobile Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shibahar");
		return extent;
		
	}

	
}
