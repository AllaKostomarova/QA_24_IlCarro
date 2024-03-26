package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
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
    By btnOk = By.cssSelector("button[class='positive-button ng-star-inserted']");

    //**********COMMON METHODS******************

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
    By errorTextForField = By.cssSelector("div.error");
    By titleRegistration = By.xpath("//h1");


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
        // #1 --> check-box locator (size of check-box is 0x0 -->   you can't click on it)
        //  By.id("terms-of-use");
//        clickElement(checkBoxTermsAndPolicy);

        // #2 --> locator of check-box parents (it haves links, because you can't click on it)
        // By.cssSelector("label[for='terms-of-use']");
//        clickElement(checkBoxLabel);

        // #3 --> click through java-script
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }

    public void clickCheckBoxPolicyXY(){
        // #4 --> moving check-box coordinates
//        Dimension size = wd.manage().window().getSize();
//        System.out.println("Wight screen --> " + size.getWidth());
        WebElement label = wd.findElement(checkBoxLabel);
        Rectangle rect = label.getRect();
        int w = rect.getWidth();
        Actions actions = new Actions(wd);
        int xOffSet = -w/2;

        // .release --> collects all actions methods into single process (moveToElement + click)
        // .perform --> starts release/process of methods
        actions.moveToElement(label, xOffSet, 0).click().release().perform();
    }

    public String getRegistrationMessage(){
        return wd.findElement(messageRegisteredSuccess).getText();
    }

    public boolean isRegistrationFormOpen(){
        return isElementPresent(registrationForm);
    }
    public boolean isRegistered(){
        return getRegistrationMessage().equals("You are logged in success");
    }

    public void logIn(User user) {
        openLoginForm();
        fillLoginForm(user);
        submitYala();
        clickOkBtn();
    }

    public String getErrorTextForField(){
        return wd.findElement(errorTextForField).getText();
    }
}
