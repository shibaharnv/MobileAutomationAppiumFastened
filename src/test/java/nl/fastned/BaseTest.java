package nl.fastned;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import nl.fastned.TestUtils.EmailTest;
import nl.fastned.pageObjects.android.HomePage;
import nl.fastned.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


/**
 * Base test class containing reusable utilities and pre-requisites required for tests.
 */
public class BaseTest extends AppiumUtils {

    // All our reusable utilities and pre-requisites required for tests are present in this class

    public AppiumDriverLocalService service;
    public AndroidDriver driver;

    public HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws IOException {


        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/data.properties");
        prop.load(fis);

        // Assign ipAddress based on the parameter passed from maven if set, else fallback to property from configuration

        String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");

        String port = prop.getProperty("port");
        System.out.println(ipAddress);

        service = startAppiumServer(ipAddress,Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();

        String deviceName = prop.getProperty("AndroidDeviceName");
        options.setDeviceName(deviceName);//emulator

        String currentDir = System.getProperty("user.dir");

        // Appending the relative path to the APK file
        String apkPath = currentDir + "/src/test/resources/Fastned_6.10.5_Apkpure.apk";

        // Setting the APK path in UiAutomator2Options
        options.setApp(apkPath);


        if (System.getProperty("user.home").equals("/root")) {

            driver = new AndroidDriver(service.getUrl(), options);
        } else {
            driver = new AndroidDriver(service.getUrl(), options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage= new HomePage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws IOException {
        driver.quit();
        service.stop();
        String recipient=System.getProperty("RecipientList");
        EmailTest.emailTrigger(recipient);

    }
}
