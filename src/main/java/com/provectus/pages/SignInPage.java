package com.provectus.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignInPage {
    //---------- For Sign Up and Login scenario ----------
    private final SelenideElement loginInput = $("input#mat-input-5");
    private final SelenideElement passwordInput = $("input#mat-input-6");
    //-------- For direct login scenario ---------
    private final SelenideElement loginInput1 = $("input#mat-input-0");
    private final SelenideElement passwordInput1 = $("input#mat-input-1");
    private final SelenideElement loginButton = $x("//div[@id='controls']/button");
    private final SelenideElement registerLink = $x("//a[contains(text(), 'Register')]");
    private final SelenideElement title = $x("//div/h2");

    public SelenideElement getTitle() {
        return title;
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public SignInPage fillCredentials(String username, String password){
        loginInput.clear();
        loginInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public SignInPage fillCredentialsDirectLogin(String username, String password){
        loginInput1.clear();
        loginInput1.sendKeys(username);
        passwordInput1.clear();
        passwordInput1.sendKeys(password);

        return this;
    }





}
