package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] getInvalidCredentials() {
        return new Object[][]{
                {"register11111","register123"},
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void UnsuccessfulLogin (String username, String password) {
        System.out.println("1.Go to Homepage");
        HomePage homePage = new HomePage(driver); // create the driver object once
        homePage.navigate(); // call the method from the homepage

        System.out.println("2.Click on the login button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin(); // click on the login button

        System.out.println("3.Verify URL and log in");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyURL(loginPage.LOGIN_URL); // verify URL
        loginPage.verifyLoginFormVisibility(); //Verify visibility of Login form
        loginPage.login(username, password); // enter invalid credentials and click signIn button

        System.out.println("4.Verify that error is displayed.");
        loginPage.verifyErrorText();

    }
}