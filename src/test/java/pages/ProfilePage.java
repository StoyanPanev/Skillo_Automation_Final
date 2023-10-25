package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class ProfilePage extends BasePage {
    @FindBy(css = ".profile-user-settings > h2")
    WebElement usernameTitle;

    @FindBy(xpath = "//li[contains(text(), 'posts')]/strong")
    WebElement postsCount;

    @FindBy(css = "app-post")
    List<WebElement> existingPosts;

    @FindBy(css = ".fas.fa-user-edit.ng-star-inserted")
    WebElement editButton;

    @FindBy(css = "textarea.form-control")
    WebElement publicInfo;

    @FindBy(css = "button.btn.btn-primary")
    WebElement saveBtn;

    @FindBy(css = "input[type='file']")
    WebElement fileUploadInput;


    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUsernameTitle() {
        wait.until(ExpectedConditions.visibilityOf(usernameTitle));
        return usernameTitle.getText();
    }

    public int getALlPostsNumber() {
        wait.until(ExpectedConditions.visibilityOf(postsCount));
        return Integer.parseInt(postsCount.getText());
    }

    public int getExistingPostsNumber() {
        wait.until(ExpectedConditions.visibilityOf(existingPosts.get(0)));
        return existingPosts.size();
    }

    public void clickEditButton() {
        clickElement(editButton);
    }

    public void visibilityOfEditButton() {
        wait.until(ExpectedConditions.visibilityOf(editButton));
    }

    public void clearAndFillPublicInfo(String text) {
        publicInfo.clear();
        enterText(publicInfo, text);

    }

    public void clickSaveButton() {
        clickElement(saveBtn);
    }

    public boolean isSaveButtonEnabled() {
        wait.until(ExpectedConditions.presenceOfElementLocated((By) saveBtn));
        if (!saveBtn.isEnabled()) {
            throw new RuntimeException("Save button is not enabled. Enter at least 6 symbols.");
        }
        return true;

    }
    public void uploadProfilePicture(File file) {
        fileUploadInput.sendKeys(file.getAbsolutePath());
    }


}