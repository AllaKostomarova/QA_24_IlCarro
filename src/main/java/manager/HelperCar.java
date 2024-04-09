package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        clickElement(By.cssSelector("a[id='1']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());

        typePositiveText(By.id("make"), car.getManufacture());
        typePositiveText(By.id("model"), car.getModel());
        typePositiveText(By.id("year"), car.getYear());

        select(By.id("fuel"), car.getFuel());

        // int --> String
        // #1
        typePositiveText(By.id("seats"), String.valueOf(car.getSeats()));
        // #2
//        typePositiveText(By.id("seats"), car.getSeats()+"");

        typePositiveText(By.id("class"), car.getCarClass());
        typePositiveText(By.id("serialNumber"), car.getCarRegNumber());
        typePositiveText(By.id("price"), car.getPrice()+"");
        typePositiveText(By.id("about"), car.getAbout());

    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);

        // select Gas
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        typePositiveText(By.id("pickUpPlace"), location);
        clickElement(By.cssSelector("div.pac-item"));

    }

    public void submitCarForm() {
    }

    public void returnToHome() {
        clickElement(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clickElement(By.id("dates"));

        //"4/10/2024", "4/15/2024"
        String[] from = dateFrom.split("/");//--->["4"]["10"]["2024"]
        String locatorFrom = "//div[text()=' "+from[1]+" ']";
        clickElement(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        String locatorTo = "//div[text()=' "+to[1]+" ']";
        clickElement(By.xpath(locatorTo));
    }

    private void typeCity(String city) {
        typePositiveText(By.id("city"), city);
        clickElement(By.cssSelector("div.pac-item"));
    }
}
