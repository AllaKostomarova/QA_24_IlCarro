package manager;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    @Getter
    HelperUser helperUser;

    public void init(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/");
        helperUser = new HelperUser(wd);
    }

    public void waitElement(){
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void stop(){
        wd.quit();
    }
}
