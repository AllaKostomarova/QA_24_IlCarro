package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

@BeforeTest
public void precondition(){
   app.getHelperUser().toHomePage();
}

@AfterTest
public void postcondition(){
    if(app.getHelperUser().isUserPageOpen()) {
        if (app.getHelperUser().isButtonOkPresent())
            app.getHelperUser().loggedInOk();
        app.getHelperUser().logout();
    }
}

@Test
public void loginSuccess(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm("xx@xx.ru", "Test123$");
    app.getHelperUser().submitLogin();
    app.getHelperUser().loggedInOk();
    Assert.assertTrue(app.getHelperUser().isLogged());
    Assert.assertTrue(app.getHelperUser().isUserPageOpen());
    }

@Test
public void loginSuccessModel(){
    app.getHelperUser().openLoginForm();
    Assert.assertTrue(app.getHelperUser().isLoginFormOpen());
    app.getHelperUser().fillLoginForm("aa@aa.ru", "Test123$");
    app.getHelperUser().submitLogin();
    Assert.assertTrue(app.getHelperUser().isLogged());
    Assert.assertTrue(app.getHelperUser().isUserPageOpen());
}



}
