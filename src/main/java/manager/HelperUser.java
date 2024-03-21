package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.SplittableRandom;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    //**********COMMON LOCATORS******************
   By logoInHeader = By.xpath("//div[@class='header']//a[@class='logo']/*[@alt='logo']");
    By titleSignUpInHeader = By.xpath(" //a[text()=' Sign up '] ");
    By titleLoginInHeader = By.xpath("//a[text()=' Log in ']");
    //CssSelector --> a[ng-reflect-router-link='login']
    By titleDeletedAccountInHeader = By.xpath("//a[text()='Delete account']");
    By titleLogoutInHeader = By.xpath("//a[text()=' Logout ']");
    By inputEmail = By.id("email");
    By inputPass = By.id("password");
    By btnYalla = By.cssSelector("button[type='submit']");
    By btnOk = By.cssSelector("button[class='positive-button ng-star-inserted']");

    //**********COMMON METHODS******************
    public void submitYala(){
        clickElement(btnYalla);
    }
    public void toHomePage(){
        clickElement(logoInHeader);
    }
    public void clickOkBtn(){
        clickElement(btnOk);
    }
    public boolean isUserPageOpen(){
        return isElementPresent(titleDeletedAccountInHeader);
    }
    public boolean isButtonOkPresent(){
        return isElementPresent(btnOk);
    }


    //******************LOGIN****************
    //----------LOGIN LOCATORS---------------
    By loginForm = By.className("login-registration-progress-container");
    By dialogContainer = By.cssSelector("mat-dialog-container[class='mat-dialog-container ng-tns-c31-0 ng-trigger ng-trigger-dialogContainer ng-star-inserted']");
    //xpath --> //h2[text()='Logged in success']
    //CSS --> mat-dialog-container[class='mat-dialog-container ng-tns-c31-0 ng-trigger ng-trigger-dialogContainer ng-star-inserted']
    //CSS --> div[class='cdk-overlay-pane']
    By textLoginSuccess = By.cssSelector(".dialog-container>h2");
    By textErrorEmail = By.xpath("//div[@class='error']");
    By textLoginFailed = By.cssSelector(".dialog-container>h2");
    //By textMissingEmail = By.cssSelector();

    //-----------LOGIN METHODS----------------
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
    public void clickEmailField(){
        clickInField(inputEmail);
    }
    public void fillEmailField(String email){
        typePositiveText(inputEmail, email);
    }
    public  void clickPassField(){
        clickInField(inputPass);
    }
    public void fillPassField(String pass){
        typePositiveText(inputPass, pass);
    }
    public boolean isLoginFormOpen(){
        return isElementPresent(loginForm);
    }
    public boolean isLogged(){
        return isElementPresent(textLoginSuccess);
    }
    public boolean isErrorMessageWrongEmailPresent(){
        return isElementPresent(textErrorEmail);
    }
    public String getMessage() {
        return wd.findElement((textLoginSuccess)).getText();
    }

//    public boolean isBtnYllaDisabled() {
//        String attribute = wd.findElement(btnYalla).getAttribute("disabled");
//        return attribute !=null;
//    }

     public String getErrorMessageWrongEmail(){
        return wd.findElement(textErrorEmail).getText();
     }
     public String getMessageLoginFailed(){
        return wd.findElement(textLoginFailed).getText();
     }

     
    //*************REGISTRATION***********************
    //----------REGISTRATION LOCATORS-------------------
    By textInRegistrationForm = By.cssSelector("div[class='login-registration-container']>h2");
    By registrationForm = By.cssSelector("div[class='login-registration-container']");
    By inputName = By.id("name");
    By inputLastName = By.id("lastName");
    By checkBoxTermsAndPolicy = By.id("terms-of-use");
    By checkBoxLabel = By.cssSelector("label[for='terms-of-use']");
    By paneRegisteredSuccess = By.cssSelector("div[class='cdk-overlay-pane']");
    By messageRegisteredSuccess = By.cssSelector("h2[class='message']");


    //----------REGISTRATION METHODS-------------------
    public void openRegistrationForm() {
        clickElement(titleSignUpInHeader);
    }
    public void fillRegistrationForm(User user) {
        typePositiveText(inputName, user.getFirstName());
        typePositiveText(inputLastName, user.getLastName());
        typePositiveText(inputEmail, user.getEmail());
        typePositiveText(inputPass, user.getPassword());
    }
    public void clickCheckBoxTermsAndPolicy() {
//        clickElement(checkBoxTermsAndPolicy);
//        clickElement(checkBoxLabel);
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }
    public String getMessageSuccessfulRegistration(){
        return wd.findElement(messageRegisteredSuccess).getText();
    }

    public boolean isRegistrationFormOpen(){
        return isElementPresent(registrationForm);
    }
    public boolean isRegistered(){
        return isElementPresent(messageRegisteredSuccess);
    }

}
