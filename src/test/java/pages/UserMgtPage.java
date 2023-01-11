package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserMgtPage extends BasePage {

    public UserMgtPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Firstname")
    public WebElement firstNameInput;

    @FindBy(id = "Lastname")
    public WebElement lastNameInput;

    @FindBy(id = "Phonenumber")
    public WebElement phoneInput;

    @FindBy(id = "Email")
    public WebElement emailInput;

    @FindBy(id = "Select-role")
    public WebElement roleInput;

    @FindBy(id = "submit-btn")
    public WebElement submitBtn;

    @FindBy(id = "access-db-btn")
    public WebElement accessDbBtn;

    public void addNewUser(String firstName, String lastName, String phone, String email, String role){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phone);
        emailInput.sendKeys(email);
        roleInput.sendKeys(role);
        submitBtn.click();
    }
}
