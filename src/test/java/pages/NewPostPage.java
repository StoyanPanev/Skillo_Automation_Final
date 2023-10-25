package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class NewPostPage extends BasePage {
    @FindBy(css = "input[type='file']")
    WebElement fileInput;

    @FindBy(css = ".image-preview")
    WebElement imagePreview;

    @FindBy(css = ".input-group input")
    WebElement fileNameInput;

    @FindBy(name = "caption")
    WebElement captionInput;

    @FindBy(css = ".post-status-label.custom-control-label.active") // new
    WebElement publicSwitch;

    @FindBy(id = "create-post")
    WebElement submitBtn;


    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void uploadFile(File file) { //java library when we work with file
        fileInput.sendKeys(file.getAbsolutePath());
    }

    public void verifyImagePreview() {
        wait.until(ExpectedConditions.visibilityOf(imagePreview));
    }

    public String getFileName() {
        return fileNameInput.getAttribute("placeholder");
    }

    public void enterCaption(String captionText) {
        wait.until(ExpectedConditions.visibilityOf(captionInput));
        captionInput.sendKeys(captionText);
    }
    public boolean isPublicSwitchVisible() {
        return publicSwitch.isDisplayed();
    }

    public boolean isPublicSwitchSetToPublic() {
        String labelText = publicSwitch.getText().trim();
        String classAttributeValue = publicSwitch.getAttribute("class").trim();

        return classAttributeValue.contains("active") && labelText.equals("Public");
    }

    public void submitPost() {
        clickElement(submitBtn);
    }
}

