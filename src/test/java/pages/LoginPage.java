package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public final String LOGIN_URL = "http://training.skillo-bg.com:4200/users/login";

    @FindBy(xpath = "//p[contains(text(), 'Sign in')]")
    private WebElement signInText;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;

    @FindBy(id = "sign-in-button")
    private WebElement signInBtn;

    @FindBy(css = ".remember-me-button")
    private WebElement rememberCheckbox;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(id = "toast-container") //new
    WebElement errorInvalidUser;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void populateUsername(String username) {
        enterText(usernameField, username);
    }

    public void populatePassword(String password) {
        enterText(passwordField, password);
    }

    public void clickSignInBtn() {
        clickElement(signInBtn);
    }

    public void clickRegisterBtn() {
        clickElement(registerLink);
    }

    public void verifyLoginFormVisibility() {
        wait.until(ExpectedConditions.visibilityOf(signInText));
    }

    public void verifyErrorText() { //new
        Assert.assertTrue(errorInvalidUser.isDisplayed());
    }

    public void login(String username, String password) { //additional
        populateUsername(username);
        populatePassword(password);
        clickSignInBtn();
    }
}


