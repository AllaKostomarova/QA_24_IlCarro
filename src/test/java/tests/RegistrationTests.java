package tests;

import manager.DataProviderRegistration;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logout();
        app.getHelperUser().toHomePage();
    }

    @AfterMethod
    public void postcondition(){
        app.getHelperUser().pause(2);
        if (app.getHelperUser().isButtonOkPresent())
            app.getHelperUser().clickOkBtn();
    }

    //============POSITIVE TESTS====================

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
        Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "You are logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
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
        app.getHelperUser().clickCheckBoxPolicyXY();
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "You are logged in success");
        app.getHelperUser().clickOkBtn();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test(dataProvider = "registrationWithFileCSV", dataProviderClass = DataProviderRegistration.class)
    public void registrationSuccessFull_dataFromFileCSV(User user){
        // 6 new users from file CSV
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "You are logged in success");
        app.getHelperUser().clickOkBtn();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    //=========NEGATIVE TESTS===================
    @Test
    public void negativeTestRegFormIsEmpty(){
        // #1 Registration form is empty && check-box is off
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestRegFormIsEmptyCheckBoxIsOn(){
        //#2 Registration form is empty && check-box is on
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().clickCheckBoxPolicyXY();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestNameFieldIsEmpty(){
        // #3 Name field is empty
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .withFirstName(null)
                .withLastName("Li")
                .withEmail("li"+i+"@mail.com")
                .withPassword("Limit123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestLastNameFieldIsEmpty(){
        // #4 LastName field is empty
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .withFirstName("Lily")
                .withLastName(null)
                .withEmail("li"+i+"@mail.com")
                .withPassword("Limit123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestWrongEmailFormat(){
        // #5 Wrong Email format
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .withFirstName("Lily")
                .withLastName("Li")
                .withEmail("li"+i+"mail.com")
                .withPassword("Limit123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestEmailFieldIsEmpty(){
        // #6 Email field is empty
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .withFirstName("Lily")
                .withLastName("Li")
                .withEmail(null)
                .withPassword("Limit123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestWrongPasswordFormat(){
        // #7 Wrong password format
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .withFirstName("Lily")
                .withLastName("Li")
                .withEmail("li"+i+"@mail.com")
                .withPassword("Li123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().clickCheckBoxPolicyXY();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitYala();
        Assert.assertEquals(app.getHelperUser().getErrorText(),
                "Password must contain minimum 8 symbols\nPassword must contain 1 uppercase letter, " +
                        "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestPasswordFieldIsEmpty(){
        // #8 Password field is empty
        int i = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .withFirstName("Lily")
                .withLastName("Li")
                .withEmail("li"+i+"@mail.com")
                .withPassword(null);
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().clickCheckBoxPolicyXY();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitYala();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestCheckBoxIsOff(){
        // #9 Check-Box is off
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withFirstName("Lily")
                .withLastName("Li")
                .withEmail("li"+i+"@mail.com")
                .withPassword("Limit123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submitYala();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestAlreadyRegisteredUser(){
        // #10 Already registered user with registered data
        User user = new User()
                .withFirstName("al")
                .withLastName("al")
                .withEmail("aa@aa.ru")
                .withPassword("Test123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        app.getHelperUser().submitYala();
        app.waitElement();
        Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "\"User already exists\"");
        app.getHelperUser().clickOkBtn();
        Assert.assertFalse(app.getHelperUser().isRegistered());
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestRegisteredUserWithNewPassword(){
        // #11 Already registered user with new valid password
        User user = new User()
                .withFirstName("al")
                .withLastName("al")
                .withEmail("aa@aa.ru")
                .withPassword("New1$Pass");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        app.getHelperUser().submitYala();
        app.waitElement();
        app.getHelperUser().pause(5);
        Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "\"User already exists\"");
        app.getHelperUser().clickOkBtn();
        Assert.assertFalse(app.getHelperUser().isRegistered());
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }

    @Test
    public void negativeTestRegisteredUserWithNewData(){
        // #12 Already registered user with new valid data
        User user = new User()
                .withFirstName("new")
                .withLastName("new")
                .withEmail("aa@aa.ru")
                .withPassword("Test123$");
        app.getHelperUser().openRegistrationForm();
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().clickCheckBoxPolicyXY();
        app.getHelperUser().submitYala();
        app.waitElement();
        Assert.assertEquals(app.getHelperUser().getMessageFromPane(), "\"User already exists\"");
        app.getHelperUser().clickOkBtn();
        Assert.assertFalse(app.getHelperUser().isRegistered());
        Assert.assertTrue(app.getHelperUser().isRegistrationFormOpen());
    }




}
