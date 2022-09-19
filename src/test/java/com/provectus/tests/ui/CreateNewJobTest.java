package com.provectus.tests.ui;

import com.codeborne.selenide.Condition;
import com.provectus.pages.*;
import com.codeborne.selenide.Configuration;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.entities.User;
import com.provectus.tests.BaseTest;
import com.provectus.tests.api.AuthControllerApiTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.open;

public class CreateNewJobTest extends BaseTest {

    @Test
    public void createNewJobTest() throws InterruptedException, IOException {

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

        //---------- Go To Profile -----------
        JobsListPage jobsListPage = new JobsListPage();
        jobsListPage.clickProfileButton();
        jobsListPage.getProfileMenu().shouldBe(Condition.visible);
        jobsListPage.clickProfileMenuLink();
        TimeUnit.SECONDS.sleep(4);

        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.getTitle().text(), "Profile");

        //---------- Create New Job Post ---------
//        String jobTitle = "Middle+/Senior Java Developer";
//        String jobDescription = "SPD-Ukraine is looking for Java Developer to join the team who works with Fintech-product " +
//                "for the US market. You will work on one of the most interesting parts of the product — search core functionality.";
//        double jobPrice = 3500;

        String jobTitle = "Python Developer";
        String jobDescription = "For this project, we are looking for a Python Developer to join our team.";
        double jobPrice = 3000;

//        String jobTitle = "Full-stack engineer (PHP/Vue.js)";
//        String jobDescription = "We’re looking for a skilled Full-Stack Developer who will share his/her expertise to " +
//                "create the best game experience for our players around the world. As a software engineer in Sciplay " +
//                "you will take a role in developing complex software systems that have successfully been delivered, " +
//                "working along with business teams and management to collect requirements.";
//        double jobPrice = 4000;

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
