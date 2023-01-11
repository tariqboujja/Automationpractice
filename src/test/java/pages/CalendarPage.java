package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarPage extends BasePage{
    WebDriver driver;

    public CalendarPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='react-datepicker__input-container'])[2]")
    public WebElement endDateInput;

    @FindBy(xpath = "//div[contains(@class, 'keyboard-selected')]/following-sibling::div")
    public WebElement endDateCalendar;

    @FindBy(xpath = "//button[text()='Submit']")
    public WebElement submitBtn;

    @FindBy(xpath = "//div[@class='react-datepicker__input-container']/input")
    public WebElement startDateTxt;

    @FindBy(xpath = "(//div[@class='react-datepicker__input-container'])[2]/input")
    public WebElement endDateTxt;

    @FindBy(id = "num-days")
    public WebElement numberOfDaysTxt;

}