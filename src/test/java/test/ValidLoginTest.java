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

import java.time.Duration;

public class ValidLoginTest extends BaseTest {

    @DataProvider(name = "validCredentials")
    public Object[][] getValidCredentials() {
        return new Object[][]{
                {"register","register123"},
        };
    }

    @Test(dataProvider = "validCredentials")
    public void SuccessfulLogin (String username, String password) {
        System.out.println("1.Go to Homepage");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        System.out.println("2.Click on the login button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin();

        System.out.println("3.Verify URL and log in");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyURL(loginPage.LOGIN_URL); // verify URL
        loginPage.verifyLoginFormVisibility(); //Verify visibility of Login form
        loginPage.login(username, password); // enter valid credentials and click signIn button

        System.out.println("4.Verify that the URL is correct");
        homePage.verifyURL(homePage.HOME_URL);

        System.out.println("5.Verify that 'Profile' button is visible");
        Boolean isProfileLinkVisible = headerPage.isProfileLinkVisible();
        Assert.assertTrue(isProfileLinkVisible, "Profile link button is not visible!");

        System.out.println("6.Click on the 'Profile button'");
        headerPage.goToProfile();

        System.out.println("7.Verify that the 'Sign out' button is displayed");
        Assert.assertTrue(headerPage.isSignOutBtnVisible(), "Sign out button is not visible!");
    }
}