package utils;

import base.BaseTest;
import org.openqa.selenium.WebDriver;

public class BrowserUtils {

    public static void switchToNewWindow(WebDriver driver){
        for(String each: driver.getWindowHandles()){
            if (!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }
    }


}

