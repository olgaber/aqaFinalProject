package com.provectus.tests.ui;

import com.codeborne.selenide.AuthenticationType;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.provectus.pages.DriverHolder;
import com.provectus.pages.JobsListPage;
import com.provectus.pages.ProfilePage;
import com.provectus.pages.SignInPage;
import com.provectus.pages.api.authController.SignInApi;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.api.jobController.CreateJobApi;
import com.provectus.pages.entities.Job;
import com.provectus.pages.entities.User;
import com.provectus.tests.BaseTest;
import com.provectus.tests.api.AuthControllerApiTests;
import com.provectus.tests.api.JobControllerApiTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class RemoveJobTest extends BaseTest {

    @Test
    public void removeJob() throws InterruptedException, IOException {

        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        User user = authControllerApiTests.fillUserData();
        signUpApi.createNewUser(user);
        SignInApi signInApi = new SignInApi();
        String token = signInApi.signIn(user);

        Configuration.baseUrl = "https://freelance.lsrv.in.ua";
        open("/login");

        SignInPage signInPage = new SignInPage();

        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println(username + " " + password);

       TimeUnit.SECONDS.sleep(2);

        //---------- Create Job ----------
        JobControllerApiTests jobControllerApiTests = new JobControllerApiTests();
        Job job = jobControllerApiTests.fillJobData();
        CreateJobApi createJobApi = new CreateJobApi();
        Job createdJob = createJobApi.createJob(token, job);

        //---------- Log In ----------
        signInPage.fillCredentialsDirectLogin(username, password);
        signInPage.clickLoginButton();

        TimeUnit.SECONDS.sleep(2);

        //---------- Go To Profile -----------
        JobsListPage jobsListPage = new JobsListPage();
        jobsListPage.clickProfileButton();
        jobsListPage.getProfileMenu().shouldBe(Condition.visible);
        jobsListPage.clickProfileMenuLink();
        TimeUnit.SECONDS.sleep(2);

        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.getTitle().text(), "Profile");

        //define initial jobs number
        String header = profilePage.getJobHeader().text();
        String[] numberArr = header.split("\\D+");
        int initialJobsNumber = Integer.parseInt(String.join("", numberArr));
        Assert.assertTrue(initialJobsNumber >=1);

        //remove job
        profilePage.clickRemoveJobBtn();
        switchTo().alert().accept();
        TimeUnit.SECONDS.sleep(4);

        //define jobs number after removing job
        String header1 = profilePage.getJobHeader().text();

        if(initialJobsNumber == 1){
            Assert.assertEquals(profilePage.getJobHeader().text(), "You don't have jobs. Wanna create new?");
        }
        else{
            String[] numberArr1 = header1.split("\\D+");
            int updatedJobsNumber = Integer.parseInt(String.join("", numberArr1));

            Assert.assertEquals(profilePage.getJobHeader().text(), "You have " + updatedJobsNumber + "jobs");
        }
    }
}
