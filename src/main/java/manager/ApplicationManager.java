package manager;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    EventFiringWebDriver wd;
    //WebDriver wd;
    @Getter
    HelperUser helperUser;
    @Getter
    HelperCar helperCar;

    public void init(){
        wd = new EventFiringWebDriver(new ChromeDriver());
        logger.info("All tests run in Google Browser");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/");
        logger.info("The Link --> "+wd.getCurrentUrl());
        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
        wd.register(new ListenerWD(logger));
    }

    public void waitElement(){
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void stop(){
        wd.quit();
    }
}
