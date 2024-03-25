package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

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

    public boolean isYallaButtonNotActive() {
        // type 1
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
        //type 2
        WebElement el = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = el.isEnabled(); // Active --> return True, Not Active --> return False
        return res && !result;
    }

    public void submitYala(){
        clickElement(By.cssSelector("button[type='submit']"));
    }


}
