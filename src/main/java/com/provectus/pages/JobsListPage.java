package com.provectus.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class JobsListPage {

    private final SelenideElement profileButton = $x("//button[@mattooltip='Profile']");
    private final SelenideElement profileMenu = $("#cdk-overlay-0");
    private final SelenideElement profileMenuLink = $x("//button[@routerlink='/profile']");
    private final ElementsCollection jobsList = $$x("//mat-card");

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

}
