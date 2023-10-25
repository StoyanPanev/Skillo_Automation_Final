package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    WebDriver driver;

    public static final String SCREENSHOT_DIR = "src\\main\\screenshots\\";
    public static final String REPORTS_DIR = "src\\main\\reports\\";

    @BeforeMethod
    public void setup() throws IOException {
        cleanDirectory(SCREENSHOT_DIR);
        cleanDirectory(REPORTS_DIR);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5)); //additional
    }

    public void cleanDirectory(String filepath) throws IOException {
        File directory = new File(filepath);
        FileUtils.cleanDirectory(directory);
    }

    public void takeScreenshot(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            try {
                FileUtils.copyFile(screenshot, new File(SCREENSHOT_DIR.concat(testName).concat(".jpeg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @AfterMethod
    public void cleanup(ITestResult testResult) {
        takeScreenshot(testResult);
        driver.close();
    }
}
