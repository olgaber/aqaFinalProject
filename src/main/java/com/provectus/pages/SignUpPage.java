package com.provectus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.provectus.pages.entities.User;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignUpPage {
    private final SelenideElement usernameInput = $("input#mat-input-0");
    private final SelenideElement firstNameInput = $("input#mat-input-1");
    private final SelenideElement lastNameInput = $("input#mat-input-2");

    private final SelenideElement passwordInput = $("input#mat-input-3");
    private final SelenideElement confirmPasswordInput = $("input#mat-input-4");

    private final SelenideElement registerButton = $x("//div[@id='controls']/button");
    private final SelenideElement signupLink = $x("//a[contains(text(), 'Sign in')]");

    private final SelenideElement alert = $x("//div[@id='cdk-overlay-62']//simple-snack-bar");

    public SignUpPage setUsername(String username){
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public SignUpPage setFirstName(String firstName){
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public SignUpPage setLastName(String lastName){
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public SignUpPage setPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage confirmPassword(String passwordConfirmation){
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(passwordConfirmation);
        return this;
    }

    public void clickRegisterButton(){
        registerButton.click();
    }

    public SignInPage clickSignupLink(){
        signupLink.click();
        return new SignInPage();
    }

    public void signUp(User user) throws InterruptedException {
        setUsername(user.getUsername());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setPassword(user.getPassword());
        String pw = user.getPassword();
        confirmPassword(pw);
        clickRegisterButton();
    }
}
