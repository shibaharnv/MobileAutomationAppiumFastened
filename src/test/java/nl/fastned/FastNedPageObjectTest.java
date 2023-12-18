package nl.fastned;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import nl.fastned.TestUtils.Listeners;
import nl.fastned.pageObjects.android.DashBoardPage;
import org.testng.annotations.Test;



public class FastNedPageObjectTest extends BaseTest {

    DashBoardPage dashBoardPage;
    ExtentTest exTest;


    /**
     * Smoke test for the FastNed mobile app. Performs a series of actions such as clicking on buttons,
     * entering text, selecting stations, and verifying station details.
     */
    @Test (groups = "smoke")
    public void fastNedAppTest()
    {

        exTest= Listeners.test;

        exTest.log(Status.INFO,"Clicking on the continue button");
        dashBoardPage=homePage.clickContinueButton();


        exTest.log(Status.INFO,"Wait for the search box");
        dashBoardPage.waitForSearchBox();

        exTest.log(Status.INFO,"Entering text in Search Box");
        dashBoardPage.enterTextInSearchBox("Fastned");

        exTest.log(Status.INFO,"Selecting station from the suggestion");
        dashBoardPage.selectStationFromSuggestion();

        exTest.log(Status.INFO,"Get station name from the pop up");
        dashBoardPage.getStationNameFromPopup();


        dashBoardPage.compareStationNames("Fastned - Kreuz Hilden");
        dashBoardPage.getStationAddressFromPopup();
        dashBoardPage.compareStationAddress("Zum Jägerhof, Hilden, DEU");

        exTest.log(Status.INFO,"Clicking on More button");
        dashBoardPage.clickMoreButton();


        dashBoardPage.comparePowerKw("400 kW");


        exTest.log(Status.INFO,"Clicking on Route Link");
        dashBoardPage.clickRouteLink();

        //dashBoardPage.clickThirdPartyNavigationApp();


    }

//
    @Test (groups = "regression")
    public void sampleFailedTestcase()
    {
        exTest= Listeners.test;
        exTest.log(Status.INFO,"This is a sample failure testcase");
        dashBoardPage=homePage.clickContinueButton();
        exTest.log(Status.INFO,"Selecting station from the suggestion");
        dashBoardPage.selectStationFromSuggestion();

}




}
