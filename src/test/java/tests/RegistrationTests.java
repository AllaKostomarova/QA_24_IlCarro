package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
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
    public void registrationSuccess(){
        // random number
        //#1
        Random random = new Random();
        int i = random.nextInt(1000);
//        System.out.println(i);
//        System.out.println("******************");
        //#2
//        System.out.println(System.currentTimeMillis());
        //#3
        int y = (int)(System.currentTimeMillis()/1000)%3600;
//        System.out.println("******************");
//        System.out.println(y);

        User user = new User()
                .withFirstName("Lucy")
                .withLastName("Lancer")
                .withEmail("lancer"+i+"@mail.com")
                .withPassword("Lancer123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxTermsAndPolicy();
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessageSuccessfulRegistration(), "You are logged in success");
        Assert.assertTrue(app.getHelperUser().isUserPageOpen());
    }

    @Test
    public void registrationSuccessFull(){
         int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withFirstName("Sem")
                .withLastName("Smit")
                .withEmail("smit"+i+"@mail.com")
                .withPassword("Smit123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxTermsAndPolicy();
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessageSuccessfulRegistration(), "You are logged in success");
        app.getHelperUser().clickOkBtn();
        Assert.assertTrue(app.getHelperUser().isUserPageOpen());


    }
}
