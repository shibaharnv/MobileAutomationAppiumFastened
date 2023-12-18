package nl.fastned.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import nl.fastned.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
/**
 * TestNG listener class for logging test execution events and capturing screenshots on failure.
 */
public class Listeners extends AppiumUtils implements ITestListener {

    public static ExtentTest test;
    public  ExtentReports extent=ExtentReporterNG.getReporterObject();



    public void onTestStart(ITestResult result) {

        test= extent.createTest(result.getMethod().getMethodName());

        System.out.println(test);
        test.log(Status.INFO,"Starting the test case");
    }

     public void onTestSuccess(ITestResult result) {

         test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());


        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
           // test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
            test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName()).build());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

        test.log(Status.SKIP,"This test case is skipped ");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }


    public void onFinish(ITestContext context) {

        extent.flush();
    }


}
