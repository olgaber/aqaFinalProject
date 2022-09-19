package com.provectus.tests.ui;

import com.provectus.tests.BaseTest;
import com.provectus.tests.api.AuthControllerApiTests;
import com.codeborne.selenide.Condition;
import com.provectus.pages.*;
import com.codeborne.selenide.Configuration;
import com.provectus.pages.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UpdateUserDataTest extends BaseTest {

    @Test
    public void UserSignUpTest() throws InterruptedException {

        //---------- Sign Up ----------
        WelcomePage welcomePage = new WelcomePage();
        SignInPage signInPage = new SignInPage();
        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();

        Configuration.baseUrl = "https://freelance.lsrv.in.ua";

        open("/home");

        SignUpPage signUpPage = welcomePage.clickRegisterLink();

        User user = authControllerApiTests.fillUserData();
        signUpPage.signUp(user);

        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getFirstName();
        String lastname = user.getLastName();
        System.out.println(username + " " + password);
        System.out.println(name + " " + lastname);

        TimeUnit.SECONDS.sleep(4);

        //---------- Log In ----------
        signInPage.getTitle().shouldBe(Condition.visible);
        Assert.assertEquals(signInPage.getTitle().text(), "Login");

        signInPage.fillCredentials(username, password);
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

        //---------- Edit Personal Data ---------
        profilePage.clickEditInfoBtn();
        profilePage.getModal().shouldBe(Condition.visible);
        switchTo().window(getWebDriver().getWindowHandle());

        profilePage.fillNameInput(name);
        profilePage.fillLastNameInput(lastname);
        profilePage.clickUpdateBtn();

        String userData = name + " " + lastname;

        Assert.assertEquals(profilePage.getUpdatedData().text(), userData);

        TimeUnit.SECONDS.sleep(4);

    }
}
