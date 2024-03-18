package tests;

import manager.ApplicationManager;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends TestBase{

@BeforeMethod
public void precondition(){
    if(app.getHelperUser().isUserPageOpen())
        app.getHelperUser().logout();
    app.getHelperUser().toHomePage();
}

@AfterMethod
public void postcondition(){
    if (app.getHelperUser().isButtonOkPresent())
        app.getHelperUser().clickOkBtn();
    }

@Test
public void loginSuccess(){
    app.getHelperUser().openLoginForm();
    Assert.assertTrue(app.getHelperUser().isLoginFormOpen());
    app.getHelperUser().fillLoginForm("aa@aa.ru", "Test123$");
    app.getHelperUser().submitYala();
    Assert.assertTrue(app.getHelperUser().isLogged());
    Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    Assert.assertTrue(app.getHelperUser().isUserPageOpen());
    }

@Test
public void loginSuccessFull(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm("x@x.ru", "Test123$");
    app.getHelperUser().submitYala();
    Assert.assertTrue(app.getHelperUser().isLogged());
    app.getHelperUser().clickOkBtn();
    Assert.assertTrue(app.getHelperUser().isUserPageOpen());
    app.getHelperUser().logout();
}

@Test
public void loginSuccessWithObjectUser(){
    User user = new User().withEmail("aa@aa.ru").withPassword("Test123$");

//        user.setEmail("aa@aa.ru");
//        user.setPassword("Test123$");


    app.getHelperUser().openLoginForm();
    Assert.assertTrue(app.getHelperUser().isLoginFormOpen());
    app.getHelperUser().fillLoginForm(user);
    app.getHelperUser().submitYala();
    Assert.assertTrue(app.getHelperUser().isLogged());
    Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    Assert.assertTrue(app.getHelperUser().isUserPageOpen());
    }

@Test
public void loginWrongEmail(){
    User user = new User().withEmail("aaaa.ru").withPassword("Test123$");
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm(user);
    //Assert.assertTrue(app.getHelperUser().isErrorMessageWrongEmailPresent());
    Assert.assertTrue(app.getHelperUser().isBtnYllaDisabled());
    //Assert.assertEquals(app.getHelperUser().getMessageWrongEmail(), "It'snot look like email");
    app.getHelperUser().submitYala();
    Assert.assertFalse(app.getHelperUser().isUserPageOpen());
}
@Test
public void loginWrongPassword(){
    User user = new User().withEmail("aa@aa.ru").withPassword("Test");
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm(user);
    app.getHelperUser().submitYala();
    Assert.assertEquals(app.getHelperUser().getMessageLoginFailed(), "\"Login or Password incorrect\"");
    Assert.assertFalse(app.getHelperUser().isUserPageOpen());
}

@Test
public void loginUnregisteredUser(){
    User user = new User().withEmail("new@new.new").withPassword("NewTest12$");
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm(user);
    app.getHelperUser().submitYala();
    Assert.assertEquals(app.getHelperUser().getMessageLoginFailed(), "\"Login or Password incorrect\"");
    Assert.assertFalse(app.getHelperUser().isUserPageOpen());
}
}
