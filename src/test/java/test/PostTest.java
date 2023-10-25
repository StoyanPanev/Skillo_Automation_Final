package test;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.io.File;

public class PostTest extends BaseTest {


    @DataProvider(name = "validCredentials")
    public Object[][] getValidCredentials() {
        return new Object[][]{
                {"register","register123", "src/main/resources/PostPhoto/hawaii.jpg", "Photo from Hawaii"},
        };
    }


    @Test(dataProvider = "validCredentials")
    public void createPost(String username, String password, String filePath, String caption) {
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

        System.out.println("6.Get current and all posts number");
        ProfilePage profilePage = new ProfilePage(driver);
        int existingPostsNumber = profilePage.getExistingPostsNumber();
        int allPostsNumber = profilePage.getALlPostsNumber();

        System.out.println("7.Click on 'New Post'");
        headerPage.goToNewPost();

        System.out.println("8.Upload picture");
        NewPostPage newPostPage = new NewPostPage(driver);
        File uploadPicture = new File(filePath);
        newPostPage.uploadFile(uploadPicture);

        System.out.println("9.Verify that image is visible and the name is correct");
        newPostPage.verifyImagePreview();
        String expectedImageName = uploadPicture.getName(); // taking the name of the picture that we upload
        Assert.assertEquals(expectedImageName, newPostPage.getFileName(),"File name is incorrect.");

        System.out.println("10.Populate post caption");
        newPostPage.enterCaption(caption);

        System.out.println("11.Verify that switch is visible and set to public");
        boolean isPublicSwitchVisible = newPostPage.isPublicSwitchVisible();
        Assert.assertTrue(isPublicSwitchVisible, "The public switch is not visible.");
        boolean isPublicSwitchSetToPublic = newPostPage.isPublicSwitchSetToPublic();
        Assert.assertTrue(isPublicSwitchSetToPublic, "The public switch is not set to 'Public'.");

        System.out.println("12.Click create post");
        newPostPage.submitPost();

        System.out.println("13.Verify the post number has increased");
        int existingPostNumAfter = profilePage.getExistingPostsNumber();
        int allPostCountAfter = profilePage.getALlPostsNumber();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(existingPostNumAfter, existingPostsNumber +1, "Incorrect existing posts count");
        softAssert.assertEquals(allPostCountAfter, allPostsNumber +1, "Incorrect all post number");







    }
}

