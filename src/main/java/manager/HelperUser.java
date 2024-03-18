package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

   //locators
    By titleLoginInHeader = By.xpath("//a[text()=' Log in ']");
    //CssSelector --> a[ng-reflect-router-link='login']
    By loginForm = By.className("login-registration-progress-container");
    By inputEmail = By.id("email");
    By inputPass = By.id("password");
    By btnYalla = By.cssSelector("button[type='submit']");
    By dialogContainer = By.cssSelector("mat-dialog-container[class='mat-dialog-container ng-tns-c31-0 ng-trigger ng-trigger-dialogContainer ng-star-inserted']");
    //xpath --> //h2[text()='Logged in success']
    //CSS --> mat-dialog-container[class='mat-dialog-container ng-tns-c31-0 ng-trigger ng-trigger-dialogContainer ng-star-inserted']
    //CSS --> div[class='cdk-overlay-pane']
    By textLoginSuccess = By.cssSelector(".dialog-container>h2");
    By btnOk = By.cssSelector("button[class='positive-button ng-star-inserted']");
    By titleDeletedAccountInHeader = By.xpath("//a[text()='Delete account']");
    By titleLogoutInHeader = By.xpath("//a[text()=' Logout ']");
    By logoInHeader = By.xpath("//div[@class='header']//a[@class='logo']/*[@alt='logo']");
    By errorEmail = By.xpath("//div[@class='error ng-star-inserted']");
    By textErrorEmail = By.xpath("//div[@class='ng-star-inserted']");
    // xpath --> //div[@class='ng-star-inserted']
    //CSS Selector --> div[class='ng-star-inserted']
    By textLoginFailed = By.cssSelector(".dialog-container>h2");

    //Methods
    public void openLoginForm(){
        clickElement(titleLoginInHeader);
    }

    public void logout(){
        clickElement(titleLogoutInHeader);
    }

    public void fillLoginForm(String email, String password){
        typePositiveText(inputEmail, email);
        typePositiveText(inputPass, password);
    }

    public void fillLoginForm(User user){
        typePositiveText(inputEmail, user.getEmail());
        typePositiveText(inputPass, user.getPassword());
    }

    public void submitYala(){
        clickElement(btnYalla);
    }

    public void toHomePage(){
        clickElement(logoInHeader);
    }

    public void clickOkBtn(){
        clickElement(btnOk);
    }

    public boolean isLoginFormOpen(){
        return isElementPresent(loginForm);
    }

    public boolean isLogged(){
        return isElementPresent(textLoginSuccess);
    }

    public boolean isUserPageOpen(){
        return isElementPresent(titleDeletedAccountInHeader);
    }

    public boolean isButtonOkPresent(){
        return isElementPresent(btnOk);
    }


    public String getMessage() {
        return wd.findElement((textLoginSuccess)).getText();
    }

    public boolean isBtnYllaDisabled() {
        String attribute = wd.findElement(btnYalla).getAttribute("disabled");
        return attribute !=null;
    }

    public boolean isErrorMessageWrongEmailPresent(){
        return isElementPresent(errorEmail);
    }
     public String getErrorMessageWrongEmail(){
        return wd.findElement(textErrorEmail).getText();
     }

     public String getMessageLoginFailed(){
        return wd.findElement(textLoginFailed).getText();
     }

}
