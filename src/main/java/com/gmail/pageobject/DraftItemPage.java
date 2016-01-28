package com.gmail.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yahor local on 1/17/2016.
 */
public class DraftItemPage extends BasePage {

    @FindBy(id = "to")
    private WebElement toField;

    @FindBy(name = "subject")
    private WebElement subjectField;

    @FindBy(name = "body")
    private WebElement bodyField;

    @FindBy(name = "nvp_bu_send")
    private WebElement sendButton;

    public DraftItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getTo() {
        return toField.getText();
    }

    public String getSubject() {
        return subjectField.getAttribute("value");
    }

    public String getBody() {
        return bodyField.getText();
    }

    public InboxPage clickSendButton() {
        sendButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }

    public InboxPage clickSendButtonJs() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("document.getElementsByName('nvp_bu_send')[0].click();");
        return PageFactory.initElements(driver, InboxPage.class);
    }

}
