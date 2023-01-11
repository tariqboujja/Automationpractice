package tests;

import base.BaseTest;
import data.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTest {
    HomePage page;

    @BeforeMethod
    public void setUp(){
        page = new HomePage(driver);
    }

    @Test(testName = "US1021 - Test title of the home page")
    public void test01(){
        String expected = "Automation with Selenium";
        String actual = driver.findElement(By.id("title")).getText();
        //Assert.assertEquals(expected, actual);
//        extentManager.logInfo("Automation with selenium");
        page.assertEquals(actual, expected);
    }

    @Test(testName = "US1023 - Test apple vs banana")
    public void test02(){
        Assert.assertEquals("apple", "banana");
    }

//    @Test
//    public void test02(){
//        String[] navBtnTitles = new String[] {"Home", "Curriculum", "Notes", "Inputs", "Selectors",
//                "Select-class", "Alert", "Pop-Up", "Multiple-window", "Tables", "Calendar", "iFrames",
//                "Action-class", "JS-Executor", "Synchronization", "Files", "User-Mgt", "Others"};
//
//        for(String eachBtn: navBtnTitles){
//            Assert.assertTrue(driver.findElement(
//                    By.xpath("//a[contains(@class,'navbar-brand ml-3') and text()='" + eachBtn + "']")).isDisplayed());
//        }
//    }
//
//    @Test(dataProvider = "nav buttons", dataProviderClass = DataProviders.class)
//    public void test03(String navBtn){
//        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@class,'navbar-brand ml-3') and text()='" + navBtn + "']")).isDisplayed());
//    }


}
