package manager;

import models.Car;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    // ======LOCATORS=====
    By fieldSelectCity = By.id("city");
    By listOfCities = By.cssSelector("div.pac-item");
    By listOfCars = By.cssSelector("a.car-container");
    By fieldDate = By.id("dates");
    By btnNextMinth = By.cssSelector("button.mat-calendar-next-button");
    By btnChooseYear = By.cssSelector("button[aria-label='Choose month and year']");
    By logo = By.cssSelector("a.logo");
    By divSearchCar = By.cssSelector("div.search-card");
    By textOfDateError = By.cssSelector("div.ng-star-inserted");
    By satCalendar = By.cssSelector("div[class='cdk-overlay-pane mat-datepicker-popup']");

    //==========METHODS=======

    public void openCarForm() {
        pause(5);
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
        clickElement(fieldDate);

        //"4/10/2024", "4/15/2024"
        String[] from = dateFrom.split("/");//--->["4"]["10"]["2024"]
        String locatorFrom = "//div[text()=' "+from[1]+" ']";
        clickElement(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        String locatorTo = "//div[text()=' "+to[1]+" ']";
        clickElement(By.xpath(locatorTo));
    }

    private void typeCity(String city) {
        typePositiveText(fieldSelectCity, city);
        clickElement(listOfCities);
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(listOfCars);
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clickElement(fieldDate);

        LocalDate now = LocalDate.now();
        LocalDate from = dateFormatting(dateFrom);
        LocalDate to = dateFormatting(dateTo);

        int diffMonth = from.getMonthValue()-now.getMonthValue();
        int diffYear = from.getYear()-now.getYear();

        if(diffYear !=0)
            selectInAnotherYear(from);
        else if(diffMonth != 0)
            clickNextMonthBtn(diffMonth);

        selectDay(from);

        diffMonth = to.getMonthValue() - from.getMonthValue();
        diffYear = to.getYear()-from.getYear();

        if (diffYear != 0)
            selectInAnotherYear(to);
        else if(diffMonth != 0)
            clickNextMonthBtn(diffMonth);

        selectDay(to);
    }

    public void searchAnyPeriod1(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clickElement(fieldDate);

        LocalDate now = LocalDate.now();
        LocalDate from = dateFormatting(dateFrom);
        LocalDate to = dateFormatting(dateTo);

        int diffMonth;
        int diffYear;

        //***from - 10/15/2024
        diffYear = from.getYear()-now.getYear();
        if(diffYear == 0) { // 2024
            diffMonth = from.getMonthValue() - now.getMonthValue();//10-4=6
        }else {//2024!=2025
            diffMonth = 12-now.getMonthValue()+from.getMonthValue();// 12-4+10=18
        }
        clickNextMonthBtn(diffMonth);

        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        clickElement(By.xpath(locator));

        //***to - 2/15/2025
        diffYear = to.getYear()-from.getYear();
        if (diffYear == 0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        }else{
            diffMonth = 12-from.getMonthValue()+to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);

        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        clickElement(By.xpath(locator));
    }

    private LocalDate dateFormatting(String date) {
//        LocalDate now = LocalDate.now();// 2024-04-13
//        int year = now.getYear();
//        int month = now.getMonthValue();
//        int day = now.getDayOfMonth();
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy"));// 4/15/2024--->2024-04-15
    }

    private void clickNextMonthBtn(int diffMonth) {
        while (diffMonth > 0) {
            clickElement(btnNextMinth);
            diffMonth--;
        }
    }

    private void selectInAnotherYear(LocalDate date) {
        selectYear(date);
        selectMonthByName(date);
    }

    private void selectMonthByName(LocalDate date) {
        String month = date.getMonth().toString().substring(0,3);
        clickElement(By.xpath("//div[text()=' "+month+" ']"));
    }

    private void selectDay(LocalDate date){
        clickElement(By.xpath("//div[text()=' "+date.getDayOfMonth()+" ']"));
    }

    private void selectYear(LocalDate date){
        clickElement(btnChooseYear);
        clickElement(By.xpath("//div[text()=' "+date.getYear()+" ']"));
    }

    public void clearCityField (){
       clearTextBox(fieldSelectCity);
    }

    public void clearDateField (){
        clearTextBox(fieldDate);
    }

    public void navigateByLogo() {
        clickElement(logo);
    }

    public void hideCalendar(){
     //   JavascriptExecutor js = (JavascriptExecutor) wd;
    //    js.executeScript("document.querySelector('div[class=\"cdk-overlay-pane mat-datepicker-popup\"]').style.display='none'");
        clickElement(By.cssSelector("div.cdk-overlay-backdrop"));
    }

    public String getTextOfDateError(){
        return getMessage(textOfDateError);
    }

    public boolean isTextOfDateErrorPresent(){
        return isElementPresent(textOfDateError);
    }

    private void typeDate(String date){
        clickInField(fieldDate);
        hideCalendar();
        pause(1);
        WebElement dateField = wd.findElement(fieldDate);
        dateField.sendKeys(date);
    }
    public void fillCarSearchForm(String city, String date){
        typeCity(city);
        typeDate(date);
    }
}
