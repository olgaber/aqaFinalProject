package com.provectus.tests.ui;

import com.codeborne.selenide.Condition;
import com.provectus.pages.*;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.entities.Job;
import com.provectus.pages.entities.User;
import com.provectus.tests.BaseTest;
import com.provectus.tests.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewJobTest extends BaseTest {

    @Test
    public void createNewJobTest() throws InterruptedException, IOException {

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

        //---------- Go To Profile -----------
        JobsListPage jobsListPage = new JobsListPage();
        jobsListPage.clickProfileButton();
        jobsListPage.getProfileMenu().shouldBe(Condition.visible);
        jobsListPage.clickProfileMenuLink();
        TimeUnit.SECONDS.sleep(4);

        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.getTitle().text(), "Profile");

        //---------- Create New Job Post ---------
        Job job = dataProvider.fillJobData();
        String jobTitle = job.getTitle();
        String jobDescription = job.getDescription();
        double jobPrice = job.getPrice();

        String notPublishedPrice = "Price " + (int)jobPrice;

        profilePage.clickAddJobBtn();
        profilePage.fillJobTitle(jobTitle);
        profilePage.fillJobDescription(jobDescription);
        profilePage.fillJobPrice(jobPrice);
        profilePage.clickCreateJobBtn();

        Assert.assertEquals(profilePage.getPublishedJobTitle().text(), jobTitle);
        Assert.assertEquals(profilePage.getPublishedJobDescription().text(), jobDescription);
        Assert.assertEquals(profilePage.getPublishedJobPrice().text(), notPublishedPrice);

        TimeUnit.SECONDS.sleep(4);
    }
}
