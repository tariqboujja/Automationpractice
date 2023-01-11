package data;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import data.pojos.Employee;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.Screenshot;

import java.lang.reflect.Method;


public class Temp {
    ExtentReports extentReports;

    @BeforeSuite
    public void setUp(){
        extentReports = new ExtentReports();

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter("target/report.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("AutomationPractice");
        sparkReporter.config().setReportName("Report for US");

        extentReports.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void tearDown(){
        extentReports.flush();
    }

    @Test(testName = "US001 - User story...")
    public void test01(Method method){
        ExtentTest extentTest = extentReports.createTest("Test 1");
        Test testClass = method.getAnnotation(Test.class);

        String methodName = method.getName();
        extentTest.info("message");

    }

    @Test
    public void test02(){

        ExtentTest extentTest = extentReports.createTest("Test 2");

        extentTest.log(Status.INFO,"Expected test: 'Automation Practice'");

        //add a list of data
        extentTest.info(MarkupHelper.createOrderedList(new String[]{"blue", "red ", "white" }));

        extentTest.info(MarkupHelper.createUnorderedList(new String[]{"blue", "red ", "white"}));

        String[][] table = {{"blue", "red ", "white"},{"car", "dog", "fruit"}};

        extentTest.pass(MarkupHelper.createTable(table));



        extentTest.fail("FAIL test");
    }

    @Test
    public void test03(){
        ExtentTest extentTest = extentReports.createTest("Test 3");

        System.setProperty("webdriver.chrome.driver", "/Users/tariqboujja/Desktop/selenium/libs/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");

        extentTest.info("Screenshot of google page",
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                        Screenshot.takeScreenshot(driver)
                ).build());

        driver.close();

    }
    }

