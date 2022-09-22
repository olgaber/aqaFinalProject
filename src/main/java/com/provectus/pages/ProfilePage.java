package com.provectus.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private final SelenideElement title = $x("//div/h1");

    private final SelenideElement editInfoBtn = $x("//div/button/span[contains(text(), 'Edit Info')]");
    private final SelenideElement editUserModal = $x("//div[@id='cdk-overlay-1']");
    private final SelenideElement nameInputField = $x("//div/input[@formcontrolname='name']");
    private final SelenideElement lastNameInputField = $x("//div/input[@formcontrolname='lastname']");
    private final SelenideElement updateBtn = $x("//div[@id='cdk-overlay-1']//form//button/span[contains(text(), 'Update')]");
    private final SelenideElement updatedData = $x("//div[@class='col']/h3");

    private final SelenideElement addJobBtn = $x("//button[@routerlink='/profile/add-job']");
    private final SelenideElement jobTitle = $x("//input[@formcontrolname='title']");
    private final SelenideElement jobDescription = $x("//textarea[@formcontrolname='description']");
    private final SelenideElement jobPrice = $x("//input[@formcontrolname='price']");
    private final SelenideElement createJobBtn = $x("//button/span[contains(text(), 'Create job')]");
    private final SelenideElement publishedJobTitle = $x("//app-my-jobs/div[1]//mat-card-title");
    private final SelenideElement publishedJobDescription = $x("//app-my-jobs/div[1]//mat-card-content/p");
    private final SelenideElement publishedJobPrice = $x("//app-my-jobs/div[1]//mat-card-subtitle[2]");
    private final SelenideElement removeJobBtn = $x("//app-my-jobs//mat-card[1]//button");
    private final SelenideElement jobHeader = $x("//app-my-jobs//h2");


    public SelenideElement getPublishedJobTitle(){
        return publishedJobTitle;
    }
    public SelenideElement getPublishedJobDescription(){
        return publishedJobDescription;
    }
    public SelenideElement getPublishedJobPrice(){
        return publishedJobPrice;
    }
    public SelenideElement getTitle(){return title;}
    public SelenideElement getModal(){
        return editUserModal;
    }
    public SelenideElement getUpdatedData(){
        return updatedData;
    }

    public SelenideElement getJobHeader(){return jobHeader; }
    public void clickEditInfoBtn(){
        editInfoBtn.click();
    }
    public void clickUpdateBtn(){
        updateBtn.click();
    }
    public  void clickCreateJobBtn(){
        createJobBtn.click();
    }
    public void clickAddJobBtn(){
        addJobBtn.click();
    }

    public void clickRemoveJobBtn(){ removeJobBtn.click(); }
    public void fillNameInput(String name){
        nameInputField.clear();
        nameInputField.sendKeys(name);
    }
    public void fillLastNameInput(String lastname){
        lastNameInputField.clear();
        lastNameInputField.sendKeys(lastname);
    }

    public void fillJobTitle(String title){
        jobTitle.clear();
        jobTitle.sendKeys(title);
    }

    public void fillJobDescription(String description){
        jobDescription.clear();
        jobDescription.sendKeys(description);
    }

    public void fillJobPrice(double price){
        jobPrice.clear();
        jobPrice.sendKeys(String.valueOf(price));
    }
}
