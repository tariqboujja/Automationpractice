package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SynchronizationPage extends BasePage {



    public SynchronizationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//button[text()='Display alert']")
    public WebElement displayAlertBtn;

    @FindBy(id = "input-text")
    public WebElement textInput;

    @FindBy(xpath = "//button[text()='Display alert']")
    public WebElement displayTxtBtn;

}