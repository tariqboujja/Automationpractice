package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SynchronizationPage;

public class SynchronizationTest extends BaseTest {
    private SynchronizationPage page;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        driver.findElement(By.xpath("//nav/a[text()='Synchronization']")).click();
        page = new SynchronizationPage(driver);
    }

    @Test(testName = "US1013: Display alert within 10 seconds")
    public void test01() {
        //click on alert btn
        driver.findElement(By.xpath("//button[text()='Display alert']")).click();

        //waiting for alert to be present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());

        //marking test as "PASS" if line 34 passes successfully
        Assert.assertTrue(true);
    }

    @Test(testName = "US1014 - Text display")
    public void test02() {
        //input text
        driver.findElement(By.id("input-text")).sendKeys("Automation testing");

        //click on Display btn
        driver.findElement(By.xpath("//button[text()='Display']")).click();

        //waiting for text to be present
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("text-display")), "Automation testing"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("text-display"), "Automation testing"));

        //marking test as "PASS" if above line passes successfully
        Assert.assertTrue(true);

    }
}