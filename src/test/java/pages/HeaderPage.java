package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderPage extends BasePage {

    @FindBy(id = "homeIcon")
    WebElement logoIcon;
    @FindBy(id = "nav-link-home")
    WebElement homeBtn;
    @FindBy(id = "nav-link-login")
    WebElement loginBtn;
    @FindBy(id = "nav-link-profile")
    WebElement profileBtn;
    @FindBy(id = "nav-link-new-post")
    WebElement newPostBtn;
    @FindBy(id = "search-bar")
    WebElement searchBar;
    @FindBy(css = "i.fas.fa-sign-out-alt.fa-lg") // another way (css = ".fa-sign-out-alt")
    WebElement signOutBtn;

    public HeaderPage(WebDriver driver) {
        super(driver); // invoke constructor
        PageFactory.initElements(driver, this);
    }

    public void clickLogo() {
        clickElement(logoIcon);
    }
    public void goToLogin() {
        clickElement(loginBtn);
    }
    public void goToHome(){
        clickElement(homeBtn);
    }
    public void goToProfile() {
        clickElement(profileBtn);
    }
    public void goToNewPost() {
        clickElement(newPostBtn);
    }

    public void goToSignOutBtn() {
        clickElement(signOutBtn);
    }


    public Boolean isProfileLinkVisible() {
        return profileBtn.isDisplayed();
    }
    public Boolean isLoginBtnVisible()  {
        return loginBtn.isDisplayed();
    }
    public Boolean isSignOutBtnVisible() {
        return signOutBtn.isDisplayed();
    }


}
