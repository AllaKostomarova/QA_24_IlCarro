package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

   //locators
    By titleLoginInHeader = By.cssSelector("a[ng-reflect-router-link='login']");
    By loginForm = By.className("login-registration-progress-container");
    By inputEmail = By.id("email");
    By inputPass = By.id("password");
    By btnYalla = By.cssSelector("button[type='submit']");
    By dialogContainer = By.xpath("//h2[text()='Logged in success']");
    //CssSelector --> div[class='cdk-overlay-pane']
    By btnOk = By.cssSelector("button[class='positive-button ng-star-inserted']");
    By titleDeletedAccountInHeader = By.xpath("//a[text()='Delete account']");
    By titleLogoutInHeader = By.xpath("//a[text()=' Logout ']");
    By logoInHeader = By.xpath("//div[@class='header']//a[@class='logo']/*[@alt='logo']");

    //Methods
    public void openLoginForm(){
        clickElement(titleLoginInHeader);
    }

    public void logout(){
        clickElement(titleLogoutInHeader);
    }

    public void fillLoginForm(String email, String password){
        typePositiveText(inputEmail, "aa@aa.ru");
        typePositiveText(inputPass, "Test123$");
    }

    public void submitLogin(){
        clickElement(btnYalla);
    }

    public void toHomePage(){
        clickElement(logoInHeader);
    }

    public void loggedInOk(){
        clickElement(btnOk);
    }

    public boolean isLoginFormOpen(){
        return isElementPresent(loginForm);
    }

    public boolean isLogged(){
        return isElementPresent(dialogContainer);
    }

    public boolean isUserPageOpen(){
        return isElementPresent(titleDeletedAccountInHeader);
    }

    public boolean isButtonOkPresent(){
        return isElementPresent(btnOk);
    }






}
