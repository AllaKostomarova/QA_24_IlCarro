package tests;

import manager.ApplicationManager;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends TestBase{

@BeforeMethod
public void precondition(){
    if(app.getHelperUser().isLogged())
        app.getHelperUser().logout();
    app.getHelperUser().toHomePage();
    app.getHelperUser().pause(3000);
}

@AfterMethod
public void postcondition(){
    app.waitElement();
    if (app.getHelperUser().isButtonOkPresent())
        app.getHelperUser().clickOkBtn();
    }

@Test
public void loginSuccess(){
    app.getHelperUser().openLoginForm();
    Assert.assertTrue(app.getHelperUser().isLoginFormOpen());
    app.getHelperUser().fillLoginForm("aa@aa.ru", "Test123$");
    app.getHelperUser().submitYala();
    app.waitElement();
    Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "Logged in success");
    Assert.assertTrue(app.getHelperUser().isLogged());
    }

@Test
public void loginSuccessFull(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm("x@x.ru", "Test123$");
    app.getHelperUser().submitYala();
    app.waitElement();
    Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "Logged in success");
    app.getHelperUser().clickOkBtn();
    Assert.assertTrue(app.getHelperUser().isLogged());
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
    app.waitElement();
    Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "Logged in success");
    Assert.assertTrue(app.getHelperUser().isLogged());
    }

@Test
public void loginWrongEmail(){
    User user = new User().withEmail("aaaa.ru").withPassword("Test123$");
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm(user);
    Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
    app.getHelperUser().submitYala();
    Assert.assertFalse(app.getHelperUser().isLogged());
}
@Test
public void loginWrongPassword(){
    User user = new User().withEmail("aa@aa.ru").withPassword("Test");
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm(user);
    app.getHelperUser().submitYala();
    Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "\"Login or Password incorrect\"");
    Assert.assertFalse(app.getHelperUser().isLogged());
}

@Test
public void loginUnregisteredUser(){
    User user = new User().withEmail("new@new.new").withPassword("NewTest12$");
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillLoginForm(user);
    app.getHelperUser().submitYala();
    app.waitElement();
    Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "\"Login or Password incorrect\"");
    Assert.assertFalse(app.getHelperUser().isLogged());
}

@Test
public void loginEmailIsNull(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().clickEmailField();
    app.getHelperUser().fillPassField("Test123$");
    Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
    Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    app.getHelperUser().submitYala();
    Assert.assertFalse(app.getHelperUser().isLogged());
}
@Test
public void loginPassIsNull(){
    app.getHelperUser().openLoginForm();
    app.getHelperUser().fillEmailField("aa@aa.ru");
    app.getHelperUser().clickPassField();
    Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    app.getHelperUser().submitYala();
    Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
    Assert.assertFalse(app.getHelperUser().isLogged());
}
}
