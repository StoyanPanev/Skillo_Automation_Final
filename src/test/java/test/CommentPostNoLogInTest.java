package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ModalPage;

public class CommentPostNoLogInTest extends BaseTest {
    @DataProvider(name = "comment")
    public Object[][] getComment() {
        return new Object[][] {
                {"I like this photo!"}
        };
    }

    @Test(dataProvider = "comment")
    public void commentTest(String comment ) {
        System.out.println("1.Go to Homepage");
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        System.out.println("2.Open latest post");
        WebElement firstPost = driver.findElement(By.cssSelector("div.row.post-list-container > div.col-12.offset-md-1.col-md-10.offset-lg-3.col-lg-6 > app-post-detail"));
        // Check if the 'firstPost' element is not null before clicking on it
        if (firstPost != null) {
            firstPost.click();
        } else {
            System.out.println("The 'firstPost' element is null. Unable to click on it.");
        }
        System.out.println("3.Check if like button is visible");
        ModalPage modalPage = new ModalPage(driver);
        modalPage.isLikeButtonVisible();
        Assert.assertTrue(modalPage.isLikeButtonVisible(), "Like button is not visible!");

        System.out.println("4.Write a comment");
        modalPage.writeComment(comment);

        System.out.println("5.Send a comment");
        modalPage.sendComment();

        System.out.println("6.Must log in message should appear");
        modalPage.errorMsg();






    }
}