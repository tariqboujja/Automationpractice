package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import data.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ParallelTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {


    }
    @Test(testName = "US1010: Staging table view", dataProvider = "roles", dataProviderClass = DataProviders.class)
    public void test01(String role){
        System.setProperty("webdriver.chrome.driver", "/Users/tariqboujja/Desktop/selenium/libs/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automation.techleadacademy.io/#/home");

        driver.findElement(By.xpath("//nav/a[text()='User-Mgt']")).click();

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys(role);
        driver.findElement(By.id("submit-btn")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//td[1]")).getText(), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[2]")).getText(), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[3]")).getText(), phone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[4]")).getText(), email);
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]")).getText(), role);

        driver.quit();
    }

    @Test(testName = "US1010: Staging table view - DB check", dataProvider = "roles", dataProviderClass = DataProviders.class)
    public void test02(String role){
        System.setProperty("webdriver.chrome.driver", "/Users/tariqboujja/Desktop/selenium/libs/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automation.techleadacademy.io/#/home");

        driver.findElement(By.xpath("//nav/a[text()='User-Mgt']")).click();

        //creating a test user
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();//test@gmail.com

        //adding user to the table
        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys(role);
        driver.findElement(By.id("submit-btn")).click();

        //accessing db page
        driver.findElement(By.id("access-db-btn")).click();

        //switch to db window
        Set<String> allWindows = driver.getWindowHandles();

        for(String each: allWindows){
            if (!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }

        //validate user email doesn't exist
        String xpath = "//td[text()='" + email + "']";

        //using list to avoid NoSuchElementException, which would stop the execution and not reach Assertion
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        Assert.assertEquals(elementList.size(), 0);

        driver.quit();
    }

    @Test(testName = "US1010: Staging table view - DB check Version 2", dataProvider = "roles", dataProviderClass = DataProviders.class, enabled = false)
    public void test03(String role){
        System.setProperty("webdriver.chrome.driver", "/Users/tariqboujja/Desktop/selenium/libs/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automation.techleadacademy.io/#/home");

        driver.findElement(By.xpath("//nav/a[text()='User-Mgt']")).click();

        //creating a test user
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();//test@gmail.com

        //adding user to the table
        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys(role);
        driver.findElement(By.id("submit-btn")).click();

        //accessing db page
        driver.findElement(By.id("access-db-btn")).click();

        //switch to db window
        Set<String> allWindows = driver.getWindowHandles();

        for(String each: allWindows){
            if (!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }

        //validate user email doesn't exist

        List<WebElement> emailList = driver.findElements(By.xpath("//td[5]"));
//        System.out.println("email: " + email);
//        emailList.forEach(a -> System.out.println(a.getText()));

        for(WebElement each: emailList){
            Assert.assertNotEquals(email, each.getText());
        }

        driver.quit();
    }

    @Test(testName = "US1011: Clear staging table option", dataProvider = "roles", dataProviderClass = DataProviders.class)
    public void test04(String role){
        System.setProperty("webdriver.chrome.driver", "/Users/tariqboujja/Desktop/selenium/libs/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://automation.techleadacademy.io/#/home");

        driver.findElement(By.xpath("//nav/a[text()='User-Mgt']")).click();

        //creating a test user
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        //adding the user to staging table
        driver.findElement(By.id("Firstname")).sendKeys(firstName);
        driver.findElement(By.id("Lastname")).sendKeys(lastName);
        driver.findElement(By.id("Phonenumber")).sendKeys(phone);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Select-role")).sendKeys(role);
        driver.findElement(By.id("submit-btn")).click();

        //clearing staging table
        driver.findElement(By.id("clear-btn")).click();

        //validating table is cleared
        List<WebElement> trs = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(trs.size(), 0);

        driver.quit();
    }

}