package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegisterPage extends BasePage {
    public final String REGISTER_URL = "http://training.skillo-bg.com:4200/users/register";

    @FindBy(css = "input[name=username]")
    WebElement usernameInput;

    @FindBy(css = "input[type=email]")
    WebElement emailInput;

    @FindBy(css = "input[name=password]")
    WebElement passwordInput;

    @FindBy(css = "input[name=verify-password]")
    WebElement verifyPasswordInput;

    @FindBy(id = "sign-in-button")
    WebElement signInBtn;

    @FindBy(id = "toast-container")
    WebElement registrationDone;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);


    }

    public void fillUsername(String username) {
        enterText(usernameInput, username);
    }

    public void fillEmail(String email) {
        enterText(emailInput, email);
    }

    public void fillPassword(String password) {
        enterText(passwordInput, password);
    }

    public void fillVerifyPassword(String verifyPassword) {
        enterText(verifyPasswordInput, verifyPassword);
    }

    public void clickSignInBtn() {
        clickElement(signInBtn);
    }

    public void invalidRegistration() { //new
        Assert.assertTrue(registrationDone.isDisplayed());
    }
}
