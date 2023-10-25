package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ModalPage extends BasePage {
    @FindBy(css = "i.like")
    WebElement likeButton;

    @FindBy(css = "input[formcontrolname='content']")
    WebElement commentField;

    @FindBy(id = "toast-container") //new
    WebElement mustLogInMsg;

    public ModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLikeButtonVisible() {
        return likeButton.isDisplayed();

    }

    public void writeComment(String comment) {
        enterText(commentField, comment);
    }

    public void sendComment() {
        commentField.sendKeys(Keys.ENTER);

    }
    public void errorMsg () {
        Assert.assertTrue(mustLogInMsg.isDisplayed());

    }
}




