package com.provectus.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class AddCommentPage {
    private final SelenideElement titleCommentPage = $x("//div[@class='mat-card-header-text']/mat-card-title");
    private final SelenideElement descriptionCommentPage = $x("//mat-card-content/p");
    private final SelenideElement priceCommentPage = $x("//div[@class='mat-card-header-text']/mat-card-subtitle[2]");
    private final SelenideElement commentField = $x("//textarea[@formcontrolname='message']");
    private final SelenideElement leaveCommentBtn = $x("//mat-card//button");
    private final SelenideElement comment = $x("//div[@class='comments']/div[1]//mat-card-content");

    public SelenideElement getTitleCommentPage() {
        return titleCommentPage;
    }
    public SelenideElement getDescriptionCommentPage() {
        return descriptionCommentPage;
    }
    public SelenideElement getPriceCommentPage() {
        return priceCommentPage;
    }
    public SelenideElement getComment(){
        return comment;
    }
    public void clickLeaveCommentBtn(){
        leaveCommentBtn.click();
    }
    public void addComment(String comment){
        commentField.clear();
        commentField.sendKeys(comment);
    }
}
