package com.provectus.tests.ui;

import com.codeborne.selenide.Configuration;
import com.provectus.pages.AddCommentPage;
import com.provectus.pages.JobsListPage;
import com.provectus.pages.SignInPage;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.entities.User;
import com.provectus.tests.BaseTest;
import com.provectus.tests.api.AuthControllerApiTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.open;

public class AddCommentTest extends BaseTest {
    @Test
    public void addCommentTest() throws InterruptedException, IOException {

        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        User user = authControllerApiTests.fillUserData();
        signUpApi.createNewUser(user);

        //---------- Log In ----------
        Configuration.baseUrl = "https://freelance.lsrv.in.ua";
        open("/login");

        SignInPage signInPage = new SignInPage();

        String username = user.getUsername();
        String password = user.getPassword();

        signInPage.fillCredentialsDirectLogin(username, password);
        signInPage.clickLoginButton();

       TimeUnit.SECONDS.sleep(4);

        //--------- Select job ---------
        JobsListPage jobsListPage = new JobsListPage();
        Assert.assertTrue(jobsListPage.checkListNotEmpty(), "Jobs list is empty");

        //System.out.println(jobsListPage.jobsList.size());
        //Random random = new Random();
        //int index = random.nextInt(jobsListPage.jobsList.size());
        //int index = random.nextInt(5);
        //System.out.println(index);

       jobsListPage.clickViewInfoLink();
       TimeUnit.SECONDS.sleep(4);

       //---------- Add Comment ----------
        String comment = "Cool position!";
        AddCommentPage addCommentPage = new AddCommentPage();
        addCommentPage.addComment(comment);
        addCommentPage.clickLeaveCommentBtn();

        Assert.assertTrue(addCommentPage.getTitleCommentPage().isDisplayed());
        Assert.assertTrue(addCommentPage.getDescriptionCommentPage().isDisplayed());
        Assert.assertTrue(addCommentPage.getPriceCommentPage().isDisplayed());

        Assert.assertEquals(addCommentPage.getComment().text(), comment, "Text should be the same!");

        TimeUnit.SECONDS.sleep(4);
    }
}
