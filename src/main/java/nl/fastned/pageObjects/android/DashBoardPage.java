package nl.fastned.pageObjects.android;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import nl.fastned.utils.AppiumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import org.testng.Assert;



/**
 * Page Object class representing the Dashboard page of the FastNed mobile app.
 * Contains methods for interacting with elements on the Dashboard page.
 */



public class DashBoardPage extends AppiumUtils {

    AndroidDriver driver;

    String actualStationName;

    String actualstationAddress;
    public DashBoardPage(AndroidDriver driver)
    {
        this.driver = driver;

        // Initialize page elements using PageFactory and AppiumFieldDecorator
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"locations_search_input\"]")
    private WebElement searchButton;


    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"\uE90F\"]")
    private WebElement shareLocationIcon;
    @AndroidFindBy(xpath="//android.view.View[@content-desc=\"Google Map\"]/android.view.View[60]")
    private WebElement sharLocationButton;

    @AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
    private WebElement whileUsingApp;

    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"Recent Searches\"]")
    private WebElement recentSearches;

    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"locations_search_input\"]")
    private WebElement searchBox;


    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"Fastned Kreuz Hilden - Zum Jägerhof, Hilden DEU\"]")
    private WebElement stationName;


    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"Fastned - Kreuz Hilden\"]")
    private WebElement stationNameInPopUp;

    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"Zum Jägerhof, Hilden, DEU\"]")
    private WebElement stationAddressInPopUp;

    @AndroidFindBy(xpath="(//android.widget.TextView[@text=\"More\"])[1]")
    private WebElement moreButton;

    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"Route\"]")
    private WebElement routeButton;

    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"400 kW\"]")
    private WebElement powerKw;



    @AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'You can now find')]")
    private WebElement googleMapLink;

    public void waitForSearchBox()
    {
        while (!isElementPresentAndDisplayed(driver, recentSearches)) {

            searchBox.click();

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void enterTextInSearchBox(String inputString)
    {
        searchBox.sendKeys(inputString);

    }

    public void selectStationFromSuggestion()
    {

        waitForElementToAppear(stationName,driver);
        stationName.click();

    }

    public void getStationNameFromPopup()
    {

        waitForElementToAppear(stationNameInPopUp,driver);
        actualStationName=stationNameInPopUp.getText();

    }

    public void compareStationNames(String expectedStationName)
    {

        Assert.assertEquals(actualStationName,expectedStationName);
    }


    public void getStationAddressFromPopup()
    {

        waitForElementToAppear(stationAddressInPopUp,driver);
        actualstationAddress=stationAddressInPopUp.getText();

    }

    public void compareStationAddress(String expectedStationAddress)
    {

        Assert.assertEquals(actualstationAddress,expectedStationAddress);
    }


    public void clickMoreButton()
    {
        moreButton.click();
    }

    public void comparePowerKw(String expectedPowerKw)
    {
        waitForElementToAppear(powerKw,driver);
        String actualPowerKw=powerKw.getText();
        Assert.assertEquals(actualPowerKw,expectedPowerKw);
    }

    public void clickRouteLink()
    {
        waitForElementToAppear(routeButton,driver);
        routeButton.click();
    }


//    public void clickThirdPartyNavigationApp()
//    {
//        waitForElementToAppear(googleMapLink,driver);
//        googleMapLink.click();
//    }
//




}
