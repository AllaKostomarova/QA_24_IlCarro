package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    //=======SERVICE METHODS=================

    public void clickElement(By locator){
        //The method finds an element by locator and clicks on the element
//        WebElement element = wd.findElement(locator);
//        element.click();
        wd.findElement(locator).click();
    }

    public void typePositiveText(By locator, String text){
        //The method finds input field by locator, clicks on the field, clears the field
        // and types a text into the field, if the string isn't empty and not null
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        if(text != null && !text.isEmpty())
            element.sendKeys(text);
    }

    public void clickInField(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
    }

    public boolean isElementPresent(By locator){
        //The method finds elements by locator and writes them to the List
        //The method returns True if the list contains some elements
        // The method returns False if the list is empty
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public void pause(int time){
        //The method is for debugging of project
        // time is in miles seconds
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //======APPLIED METHODS============

    //=======LOCATORS================
    By yallaButtonDisabled = By.cssSelector("button[disabled]");
    By yallaButton = By.cssSelector("button[type='submit']");
    By textOfDialogContainer = By.cssSelector(".dialog-container>h2");


    //=========COMMON METHODS================
//    public boolean isBtnYllaDisabled() {
//        String attribute = wd.findElement(btnYalla).getAttribute("disabled");
//        return attribute !=null;
//    }
    public boolean isYallaButtonNotActive() {
        // type 1
        boolean res = isElementPresent(yallaButtonDisabled);
        //type 2
        WebElement el = wd.findElement(yallaButton);
        boolean result = el.isEnabled(); // Active --> return True, Not Active --> return False
        return res && !result;
    }

    public void submitYala(){
        clickElement(yallaButton);
    }


    public String getMessageFromPane() {
        return wd.findElement((textOfDialogContainer)).getText();
    }
    public void getScreen(String link){
        TakesScreenshot scr = (TakesScreenshot) wd;
        File file = scr.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(file, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
