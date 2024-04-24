package tests;

import manager.DataProviderAddNewCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().logIn(new User()
                    .withEmail("aa@aa.ru")
                    .withPassword("Test123$"));

        }
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperCar().returnToHome();
    }
    @Test
    public void addNewCarSuccessAllFields(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("567-600-"+i)
                .price(50)
                .about("Nice Car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("C:\\Users\\allak\\OneDrive\\Documents\\TelRanProjects\\QA_24_IlCarro\\lamb.jpeg");
        app.getHelperCar().submitYala();
        app.getHelperCar().pause(5);
        Assert.assertTrue(app.getHelperCar().getMessageFromPane().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessageFromPane(),
                car.getManufacture() + " " + car.getModel() + " " + "added successful");
    }

    @Test
    public void addNewCarSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("567-600-"+i)
                .price(50)
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submitYala();
        app.getHelperCar().pause(5);
        Assert.assertTrue(app.getHelperCar().getMessageFromPane().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessageFromPane(),
                car.getManufacture() + " " + car.getModel() + " " + "added successful");
    }

    @Test(dataProvider = "addNewCarFromFileCSV", dataProviderClass = DataProviderAddNewCar.class)
    public void addNewCarSuccess_dataFromFileCSV(Car car){

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submitYala();
        app.getHelperCar().pause(5);
        Assert.assertTrue(app.getHelperCar().getMessageFromPane().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessageFromPane(),
                car.getManufacture() + " " + car.getModel() + " " + "added successful");
    }
}
