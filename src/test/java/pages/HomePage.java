package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "div.row.post-list-container > div.col-12.offset-md-1.col-md-10.offset-lg-3.col-lg-6 > app-post-detail")
    private WebElement firstPost;

    public final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";

    public HomePage(WebDriver driver) { // takes webdriver and call the constructor of the parent class to setup WebDriver
        super(driver);
    }

    public void navigate() { // to open the URL
        driver.get(HOME_URL);
    }

    public void openFirstPost() {
        WebElement firstPostContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.row.post-list-container > div.col-12.offset-md-1.col-md-10.offset-lg-3.col-lg-6 > app-post-detail")));

        // Once the container is visible, locate the first post within the container
        WebElement firstPost = firstPostContainer.findElement(By.cssSelector("app-post-detail"));

        // Wait for the first post to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(firstPost)).click();


    }
}
