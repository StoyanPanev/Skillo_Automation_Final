package test;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.io.File;

public class ProfilePhotoTest extends BaseTest {
    @DataProvider(name = "validCredentials")
    public Object[][] getValidCredentials() {
        return new Object[][]{
                {"register", "register123", "src/main/resources/ProfilePhoto/ProfilePicture.jpg"},
        };
    }


    @Test(dataProvider = "validCredentials")
    public void photoChange(String username, String password, String filePath) {
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
        loginPage.login(username, password); // enter valid credentials and click signIn button

        System.out.println("4.Verify that the URL is correct");
        homePage.verifyURL(homePage.HOME_URL);

        System.out.println("5.Go to profile");
        headerPage.goToProfile();

        System.out.println("6.Upload new profile photo");
        ProfilePage profilePage = new ProfilePage(driver);
        File uploadPicture = new File(filePath); // Use the provided file path
        profilePage.uploadProfilePicture(uploadPicture);

        System.out.println("7.Check in green pop-up");
        WebElement greenPopUp = driver.findElement(By.id("toast-container"));
        if (greenPopUp.isDisplayed()) {
            System.out.println("Green pop-up message appeared as expected.");
        } else {
            System.out.println("Green pop-up message didn't appear.");
            Assert.fail("Pop-up message doesn't appear!");
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}