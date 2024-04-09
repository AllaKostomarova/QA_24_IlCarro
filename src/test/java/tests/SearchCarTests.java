package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Tel Aviv Israel", "4/10/2025", "4/15/2024");
        app.getHelperCar().submitYala();
      //  Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }
}
