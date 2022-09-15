package com.provectus.tests.ui;

import com.codeborne.selenide.Configuration;
import com.provectus.pages.AddCommentPage;
import com.provectus.pages.JobsListPage;
import com.provectus.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;

public class AddCommentTest {
    @Test
    public void addCommentTest() throws InterruptedException {
        //---------- Log In ----------
        SignInPage signInPage = new SignInPage();

        Configuration.baseUrl = "https://freelance.lsrv.in.ua";
        open("/login");

        String username = "teodora.kuhic";
        String password = "4j1s11xk";

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
        String comment = "I'm in!";
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
