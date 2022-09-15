package com.provectus.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class WelcomePage {

    private final SelenideElement loginLink = $x("//a[contains(text(), 'Log in')]");
    private final SelenideElement createAccountLink = $x("//a[contains(text(), 'Create account')]");

    public SignUpPage clickRegisterLink(){
        createAccountLink.click();
        return new SignUpPage();
    }

    public SignInPage clickLoginLink(){
        loginLink.click();
        return new SignInPage();
    }
}
