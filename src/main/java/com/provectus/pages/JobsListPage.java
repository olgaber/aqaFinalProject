package com.provectus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.lang.invoke.SwitchPoint;

import static com.codeborne.selenide.Selenide.*;

public class JobsListPage {

    //private int index;
    //public final SelenideElement jobCard = $x("//mat-card[" + index + "]");
    private final SelenideElement profileButton = $x("//button[@mattooltip='Profile']");
    private final SelenideElement profileMenu = $("#cdk-overlay-0");
    private final SelenideElement profileMenuLink = $x("//button[@routerlink='/profile']");
    private final ElementsCollection jobsList = $$x("//mat-card");
    private SelenideElement viewInfoLink = $x("//mat-card-actions/button");

    public void clickViewInfoLink(){
        viewInfoLink.click();
    }
    public void clickProfileButton(){
        profileButton.click();
    }
    public void clickProfileMenuLink(){
        profileMenuLink.click();
    }

    public SelenideElement getProfileMenu(){
        return profileMenu;
    }

    public Boolean checkListNotEmpty(){
        if (!jobsList.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

/*    public JobsListPage getJobCard(int index){
        return this;
    }*/
}
