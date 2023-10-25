package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ChangePublicInfoTest extends BaseTest {


    @DataProvider(name = "validCredentials")
    public Object[][] getValidCredentials() {
        return new Object[][]{
                {"register", "register123", "Traveller"},
        };
    }


    @Test(dataProvider = "validCredentials")
    public void createPost(String username, String password, String text) {
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

        System.out.println("4 Verify that the URL is correct");
        homePage.verifyURL(homePage.HOME_URL);

        System.out.println("5.Go to profile");
        headerPage.goToProfile();

        System.out.println("6.Check if edit button is visible");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.visibilityOfEditButton();

        System.out.println("7.Click on the edit button");
        profilePage.clickEditButton();

        System.out.println("8.Clear old public info and fill with new one with at least 6 symbols");
        profilePage.clearAndFillPublicInfo(text);

        System.out.println("9. Check if the save button is enabled and fill public info");
        try {
            profilePage.isSaveButtonEnabled();
            profilePage.clearAndFillPublicInfo(text);
        } catch (RuntimeException e) {
            System.out.println("Save button is not enabled. " + e.getMessage());
        }

        System.out.println("10.Click save button");
        profilePage.clickSaveButton();

        System.out.println("11. Check if public info is updated");




    }
}
