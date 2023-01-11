package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CalendarPage;
import utils.ExtentManager;

public class CalendarTest extends BaseTest {

    private CalendarPage page;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver.findElement(By.xpath("//nav/a[text()='Calendar']")).click();
        page = new CalendarPage(driver);
    }

    @Test(testName = "US1015: Choosing date from calendar")
    public void test01(){
        //clicking input field to see calendar pop-up
        //driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[2]")).click();
        //calendarPage.endDateInput.click();
        page.click(page.endDateInput);

        //selecting next day from the calendar
        //driver.findElement(By.xpath("//div[contains(@class, 'keyboard-selected')]/following-sibling::div")).click();
        //calendarPage.endDateCalendar.click();
        page.click(page.endDateCalendar);

        //click submit button
        //driver.findElement(By.xpath("//button[text()='Submit']")).click();
        //calendarPage.submitBtn.click();
        page.click(page.submitBtn);

        //String startDate = driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']/input")).getAttribute("value");
        String startDate = page.startDateTxt.getAttribute("value");

        //String endDate = driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[2]/input")).getAttribute("value");
        String endDate = page.endDateTxt.getAttribute("value");

        if(startDate.charAt(0) == '0')
            startDate = startDate.substring(1);

        if(endDate.charAt(0) == '0')
            endDate = endDate.substring(1);

        String expectedTxt = "There are 1 days between " + startDate + " and " + endDate + "";
        //String actualTxt = driver.findElement(By.id("num-days")).getText();
        //String actualTxt = calendarPage.numberOfDaysTxt.getText();
        String actualTxt = page.getText(page.numberOfDaysTxt);

        Assert.assertEquals(actualTxt, expectedTxt);

    }

    @Test(testName = "US1015: Choosing date from calendar (shorter version)")
    public void test02(){
        page.click(page.endDateInput);
        page.click(page.endDateCalendar);
        page.click(page.submitBtn);
        //some code

    }
//
//    //NOTE: Delete all tests below this line once demo is done
//    @Test(testName = "US1015: Choosing date from calendar (shorter version 2)")
//    public void test03(){
//        page.click(page.endDateInput);
//        page.click(page.endDateCalendar);
//        page.click(page.submitBtn);
//        Assert.assertTrue(page.submitBtn.isDisplayed());
//    }
//
//    @Test(testName = "US1015: Choosing date from calendar")
//    public void test04(){
//        driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[2]")).click();
//        driver.findElement(By.xpath("//div[contains(@class, 'keyboard-selected')]/following-sibling::div")).click();
//        driver.findElement(By.xpath("//button[text()='Submit']")).click();
//        String expectedTxt = "There are 1 days between  + startDate +  and  + endDate + ";
//        String actualTxt = driver.findElement(By.id("num-days")).getText();
//        Assert.assertEquals(actualTxt, expectedTxt);
//    }
//
//    @Test(testName = "US1015: Choosing date from calendar 2")
//    public void test05(){
//        driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[2]")).click();
//        driver.findElement(By.xpath("//div[contains(@class, 'keyboard-selected')]/following-sibling::div")).click();
//        Assert.assertTrue(driver.findElement(
//                By.xpath("//div[contains(@class, 'keyboard-selected')]/following-sibling::div")).isDisplayed());
//    }
//
//    @Test(testName = "US1015: Choosing date from calendar 2")
//    public void test06(){
//        driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[2]")).click();
//        driver.findElement(By.xpath("//div[contains(@class, 'keyboard-selected')]/following-sibling::div")).click();
//        driver.findElement(By.xpath("//button[text()='Submit']")).click();
//
//        Assert.assertTrue(driver.findElement(By.xpath("*//[text()='Calendar")).isDisplayed());
//    }
//



}
