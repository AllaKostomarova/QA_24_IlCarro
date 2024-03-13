package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    WebDriver wd;

    public void init(){
        wd = new ChromeDriver();
        wd.navigate().to("https://ilcarro.web.app/");
    }

    public void stop(){
        wd.quit();
    }
}
