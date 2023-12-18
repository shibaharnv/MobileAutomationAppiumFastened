package nl.fastned.utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public  class AppiumUtils {

	public  AndroidDriver driver;
	public AppiumDriverLocalService service;


	/**
	 * Starts an Appium server with specified IP address and port, setting up required environment variables.
	 * Returns the AppiumDriverLocalService instance.
	 *
	 * @param  ipAddress  the IP address for the Appium server
	 * @param  port       the port for the Appium server
	 * @return            the AppiumDriverLocalService instance
	 * @throws IOException if an I/O error occurs during server start
	 */
	public AppiumDriverLocalService startAppiumServer(String ipAddress,int port) throws IOException {


		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/data.properties");
		prop.load(fis);
		Map<String, String> environmentVariables = new HashMap<>();


		String androidHome = prop.getProperty("Android_Home");
		String javaHome = prop.getProperty("Java_Home");
		String Main_Js_Path = prop.getProperty("Main_Js_Path");

		environmentVariables.put("ANDROID_HOME", androidHome);
		environmentVariables.put("JAVA_HOME", javaHome);
		File myfile = new File(Main_Js_Path);

		 service = new AppiumServiceBuilder().withAppiumJS(myfile)
					.withIPAddress(ipAddress).usingPort(port).withEnvironment(environmentVariables).build();
				service.start();
				return service;
	}





	/**
	 * Waits for a given WebElement to be visible within a specified timeout using WebDriverWait.
	 *
	 * @param ele    the WebElement to wait for
	 * @param driver the AppiumDriver instance
	 */
	public void waitForElementToAppear(WebElement ele, AppiumDriver driver)
	{
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}


	/**
	 * Checks if a given WebElement is present and displayed on the screen.
	 *
	 * @param driver  the AndroidDriver instance
	 * @param element the WebElement to check
	 * @return        true if the element is present and displayed, false otherwise
	 */
	public boolean isElementPresentAndDisplayed(AndroidDriver driver, WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


	/**
	 * Captures a screenshot from the AndroidDriver, saves it to a file, and returns the file path for reporting.
	 *
	 * @param testCaseName the name of the test case
	 * @param driver       the AndroidDriver instance
	 * @return             the file path of the captured screenshot
	 * @throws IOException if an I/O error occurs during screenshot capture
	 */
	public String getScreenshotPath(String testCaseName, AndroidDriver driver) throws IOException
	{
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		//1. capture and place in folder //2. extent report pick file and attach to report


	}
	
}
