package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.OthersPage;

public class OthersTest extends BaseTest {
    OthersPage page;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){

        driver.findElement(By.xpath("//nav/a[text()='Others']")).click();
        page = new OthersPage(driver);
    }

    @Test(testName = "US2001 - Disabled button is working as expected")
    public void test01(){
        Assert.assertFalse(page.statusBtn.isEnabled());
    }

    @Test(testName = "US2002 - Enable option to toggle disabled button")
    public void test02(){
        page.toggleBtn.click();
        Assert.assertTrue(page.statusBtn.isEnabled());
    }
}
