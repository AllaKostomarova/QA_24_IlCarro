package tests;

import manager.DataProviderSearchCar;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class SearchCarTests extends TestBase{
    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod
    public void postCondition(){
        app.getHelperCar().navigateByLogo();
        app.getHelperCar().clearCityField();
        app.getHelperCar().clearDateField();
    }


    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Tel Aviv", "4/15/2024", "4/20/2024");
        app.getHelperCar().getScreen("src/test/resources/screenshots/currentMonth.png");
        app.getHelperCar().submitYala();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchAnyPeriod1("Tel Aviv", "4/15/2024", "6/20/2024");
        app.getHelperCar().getScreen("src/test/resources/screenshots/currentYear.png");
        app.getHelperCar().submitYala();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess(){
        app.getHelperCar().searchAnyPeriod("Tel Aviv", "10/15/2024", "2/20/2025");
        app.getHelperCar().getScreen("src/test/resources/screenshots/any.png");
        app.getHelperCar().submitYala();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchNegativeTest_WrongPeriod(){
        app.getHelperCar().fillCarSearchForm("Tel Aviv", "5/15/2024-5/15/2024");
        app.getHelperCar().getScreen("src/test/resources/screenshots/wrongPeriod.png");
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
        app.getHelperCar().submitYala();
        Assert.assertTrue(app.getHelperCar().isTextOfDateErrorPresent());
        List<String> errorTexts = new ArrayList<>();
        errorTexts.add("You can't pick date before today");
        errorTexts.add("Second date must be after first date");
        errorTexts.add("You can't book car for less than a day");
        errorTexts.add("Dates are required");
        Assert.assertTrue(errorTexts.contains(app.getHelperCar().getTextOfDateError()));
    }

    @Test(dataProvider = "selectDates", dataProviderClass = DataProviderSearchCar.class)
    public void searchNegativeTest_WrongPeriodDPFile(String date){
        app.getHelperCar().fillCarSearchForm("Tel Aviv", date);
        app.getHelperCar().pause(3);
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
        app.getHelperCar().submitYala();
        Assert.assertTrue(app.getHelperCar().isTextOfDateErrorPresent());
    }
}
