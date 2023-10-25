package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;

public class SuccessfulRegister extends BaseTest {

    @DataProvider(name = "validCredentialsRegister")
    public Object[][] getValidCredentials() {
        return new Object[][]{
                {"register11562", "ivan123s1@abv.bg", "register1231", "register1231"},
        };
    }
    @Test(dataProvider = "validCredentialsRegister")
    public void SuccessfulRegister(String username, String email, String password, String verifyPassword) {
        System.out.println("1.Go to Homepage");
        HomePage homePage = new HomePage(driver); // create the driver object once
        homePage.navigate(); // call the method from the homepage

        System.out.println("2.Click on the login button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin(); // click on the login button

        System.out.println("3.Verify that URL is correct");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyURL(loginPage.LOGIN_URL);

        System.out.println("4.Click 'Register' button");
        loginPage.clickRegisterBtn();

        System.out.println("5.Verify that URL is correct");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.verifyURL(registerPage.REGISTER_URL);

        System.out.println("6.Fill username field with valid not used username");
        registerPage.fillUsername(username);

        System.out.println("7.Fill email field with valid and not used email");
        registerPage.fillEmail(email);

        System.out.println("8.Fill password field with valid password");
        registerPage.fillPassword(password);

        System.out.println("9.Fill confirm password field with same password");
        registerPage.fillVerifyPassword(verifyPassword);

        System.out.println("10.Click on the 'Sign In' button");
        registerPage.clickSignInBtn();

        System.out.println("11.Verify that the URL is correct");
        homePage.verifyURL(homePage.HOME_URL);

        System.out.println("12.Verify that 'Profile' button is visible");
        Boolean isProfileLinkVisible = headerPage.isProfileLinkVisible();
        Assert.assertTrue(isProfileLinkVisible, "Profile link button is not visible!");








    }
}


