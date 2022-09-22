package com.provectus.tests.ui;

import com.codeborne.selenide.Selenide;
import com.provectus.pages.AddCommentPage;
import com.provectus.pages.SignInPage;
import com.provectus.pages.api.authController.SignInApi;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.api.jobController.CreateJobApi;
import com.provectus.pages.entities.Job;
import com.provectus.pages.entities.User;
import com.provectus.tests.BaseTest;
import com.provectus.tests.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.open;

public class AddCommentTest extends BaseTest {
    @Test
    public void addCommentTest() throws InterruptedException, IOException {

        SignUpApi signUpApi = new SignUpApi();
        DataProvider dataProvider = new DataProvider();

        User user = dataProvider.fillUserData();
        signUpApi.createNewUser(user);

        //---------- Log In ----------
        open("/login");

        SignInPage signInPage = new SignInPage();

        String username = user.getUsername();
        String password = user.getPassword();

        signInPage.fillCredentialsDirectLogin(username, password);
        signInPage.clickLoginButton();

        TimeUnit.SECONDS.sleep(4);

        SignInApi signInApi = new SignInApi();
        String token = signInApi.signIn(user);

        //---------- Create New Job ----------
        Job job = dataProvider.fillJobData();
        CreateJobApi createJobApi = new CreateJobApi();
        Job newJob = createJobApi.createJob(token, job);

        //---------- Reload Jobs List ----------
        Selenide.refresh();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(newJob);

        newJob.clickViewInfoLink();
        TimeUnit.SECONDS.sleep(2);

       //---------- Add Comment ----------
        AddCommentPage addCommentPage = new AddCommentPage();
        addCommentPage.addComment(dataProvider.getComment());
        addCommentPage.clickLeaveCommentBtn();

        Assert.assertTrue(addCommentPage.getTitleCommentPage().isDisplayed());
        Assert.assertTrue(addCommentPage.getDescriptionCommentPage().isDisplayed());
        Assert.assertTrue(addCommentPage.getPriceCommentPage().isDisplayed());

        Assert.assertEquals(addCommentPage.getComment().text(), dataProvider.getComment(), "Text should be the same!");
        //Assert.assertEquals("sadasdasd", dataProvider.getComment()); ---> make the test fail
        TimeUnit.SECONDS.sleep(4);
    }
}
