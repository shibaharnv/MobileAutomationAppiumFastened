package nl.fastned.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import nl.fastned.utils.AppiumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;



/**
 * Page Object class representing the Home page of the FastNed mobile app.
 * Contains methods for interacting with elements on the Home page.
 */
public class HomePage extends AppiumUtils {


    // Declare AndroidDriver instance for managing Android application interactions
    AndroidDriver driver;

    // Constructor for HomePage class that takes an AndroidDriver as a parameter
    public HomePage(AndroidDriver driver)
    {
        // Assign the provided AndroidDriver to the class variable
        this.driver = driver;

        // Initialize page elements using PageFactory and AppiumFieldDecorator
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Define a WebElement for the continueButton using AndroidFindBy annotation with XPath
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"button.text\"]")
    private WebElement continueButton;

    // Method to perform a click action on the continueButton WebElement
    public DashBoardPage clickContinueButton()
    {
        waitForElementToAppear(continueButton,driver);
        continueButton.click();
        return	new DashBoardPage(driver);
    }

}
