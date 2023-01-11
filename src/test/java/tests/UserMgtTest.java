package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import data.DataProviders;
import data.pojos.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.UserMgtPage;
import utils.BrowserUtils;

import java.util.List;

public class UserMgtTest extends BaseTest {
    private UserMgtPage page;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){

        driver.findElement(By.xpath("//nav/a[text()='User-Mgt']")).click();
        page = new UserMgtPage(driver);
    }

    @Test(testName = "US1010: Staging table view", dataProvider = "roles", dataProviderClass = DataProviders.class)
    public void test01(String role){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        //version 1
//        driver.findElement(By.id("Firstname")).sendKeys(firstName);
//        driver.findElement(By.id("Lastname")).sendKeys(lastName);
//        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
//        driver.findElement(By.id("Email")).sendKeys(email);
//        driver.findElement(By.id("Select-role")).sendKeys(role);
//        driver.findElement(By.id("submit-btn")).click();

        //version 2
//        page.firstNameInput.sendKeys(firstName);
//        page.lastNameInput.sendKeys(lastName);
//        page.phoneInput.sendKeys(phone);
//        page.emailInput.sendKeys(email);
//        page.roleInput.sendKeys(role);
//        page.submitBtn.click();

        //version 3
        page.addNewUser(firstName, lastName, phone, email, role);

        Assert.assertEquals(driver.findElement(By.xpath("//td[1]")).getText(), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[2]")).getText(), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[3]")).getText(), phone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[4]")).getText(), email);
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]")).getText(), role);
    }

    @Test(testName = "US1010: Staging table view - DB check", dataProvider = "roles", dataProviderClass = DataProviders.class)
    public void test02(String role){
        //creating a test user
        Faker faker = new Faker();
        User user = new User(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().cellPhone(), faker.internet().emailAddress(), role);

        //adding user to the table
        page.addNewUser(user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), user.getRole());

        //accessing db page
        driver.findElement(By.id("access-db-btn")).click();
        //switch to db window
        BrowserUtils.switchToNewWindow(driver);

        //validate user email doesn't exist
        String xpath = "//td[text()='" + user.getEmail() + "']";

        //using list to avoid NoSuchElementException, which would stop the execution and not reach Assertion
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        Assert.assertEquals(elementList.size(), 0);
    }

    @Test(testName = "US1010: Staging table view - DB check Version 2", dataProvider = "roles", dataProviderClass = DataProviders.class, enabled = false)
    public void test03(String role){
        //creating a test user
        Faker faker = new Faker();
        User user = new User(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().cellPhone(), faker.internet().emailAddress(), role);

        //adding user to the table
        page.addNewUser(user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), user.getRole());

        //accessing db page
        driver.findElement(By.id("access-db-btn")).click();
        //switch to db window
        BrowserUtils.switchToNewWindow(driver);

        //validate user email doesn't exist
        List<WebElement> emailList = driver.findElements(By.xpath("//td[5]"));

        for(WebElement each: emailList){
            Assert.assertNotEquals(user.getEmail(), each.getText());
        }
    }

    @Test(testName = "US1011: Clear staging table option", dataProvider = "roles", dataProviderClass = DataProviders.class)
    public void test04(String role){
        //creating a test user
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        //adding the user to staging table
        page.addNewUser(firstName, lastName, phone, email, role);

        //clearing staging table
        driver.findElement(By.id("clear-btn")).click();

        //validating table is cleared
        List<WebElement> trs = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(trs.size(), 0);
    }

    @Test(testName = "US1012: Adding user to DB", dataProvider = "roles", dataProviderClass = DataProviders.class)
    public void test05(String role){
        //creating a test user
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        //adding the user to staging table
        page.addNewUser(firstName, lastName, phone, email, role);

        //submitting table to db
        driver.findElement(By.id("submit-table-btn")).click();

        //validating table is cleared
        List<WebElement> trs = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(trs.size(), 0);
    }


}

