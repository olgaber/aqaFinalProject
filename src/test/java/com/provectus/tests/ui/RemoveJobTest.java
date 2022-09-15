package com.provectus.tests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.provectus.pages.JobsListPage;
import com.provectus.pages.ProfilePage;
import com.provectus.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class RemoveJobTest {

    @Test
    public void removeJob() throws InterruptedException {

        //---------- Log In ----------
        SignInPage signInPage = new SignInPage();

        Configuration.baseUrl = "https://freelance.lsrv.in.ua";
        open("/login");

        //user with jobs
        String username = "teodora.kuhic";
        String password = "4j1s11xk";

        //user without jobs
//        String username = "alphonse.ryan";
//        String password = "ojvqbx8w";

        signInPage.fillCredentialsDirectLogin(username, password);
        signInPage.clickLoginButton();

        TimeUnit.SECONDS.sleep(4);

        //---------- Go To Profile -----------
        JobsListPage jobsListPage = new JobsListPage();
        jobsListPage.clickProfileButton();
        jobsListPage.getProfileMenu().shouldBe(Condition.visible);
        jobsListPage.clickProfileMenuLink();
        TimeUnit.SECONDS.sleep(4);

        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.getTitle().text(), "Profile");

        Assert.assertTrue(profilePage.getJobHeader().exists(), "Jobs list is empty for the user");

        //define initial jobs number
        String header = profilePage.getJobHeader().text();
        String[] numberArr = header.split("\\D+");
        int initialJobsNumber = Integer.parseInt(String.join("", numberArr));
        System.out.println(initialJobsNumber);

        //remove job
        profilePage.clickRemoveJobBtn();
        switchTo().alert().accept();
        TimeUnit.SECONDS.sleep(2);

        //define jobs number after removing job
        String header1 = profilePage.getJobHeader().text();
        String[] numberArr1 = header1.split("\\D+");
        int updatedJobsNumber = Integer.parseInt(String.join("", numberArr1));
        System.out.println(updatedJobsNumber);

       Assert.assertEquals(initialJobsNumber, updatedJobsNumber + 1);
    }

}
