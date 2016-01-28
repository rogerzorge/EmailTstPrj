package com.gmail.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Math.abs;
import static java.lang.Math.random;

/**
 * Created by Yahor local on 1/17/2016.
 */
public class ComposePage extends BasePage {

//    public static int randomNumber;

    @FindBy(id = "to")
    private WebElement toTextbox;

    @FindBy(name = "subject")
    private WebElement subjectTextbox;

    @FindBy(name = "body")
    private WebElement bodyTextbox;

    @FindBy(xpath = "//input[@type='submit' and @value='Save Draft']")
    private WebElement saveDraftButton;

    @FindBy(xpath = "//a[@href='?&s=d']")
    private WebElement draftLink;

    @FindBy(xpath = "//td[@bgcolor='#FAD163' and @role='alert']/b")
    private WebElement draftSavedLabel;

    public ComposePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ComposePage setTo(String to) {
        toTextbox.clear();
        toTextbox.sendKeys(to);
        return this;
    }

    public ComposePage setSubject(String subject) {
        subjectTextbox.clear();
        subjectTextbox.sendKeys(subject);
        return this;
    }

    public ComposePage setBody(String body) {
        bodyTextbox.clear();
        bodyTextbox.sendKeys(body);
        return this;
    }

    public ComposePage saveDraft() {
        saveDraftButton.click();
        return this;
    }

    public DraftPage goToDraftPage() {
        draftLink.click();
        return PageFactory.initElements(driver, DraftPage.class);
    }

    public String getDraftSavedLabel() {
        return draftSavedLabel.getText();
    }

    public ComposePage saveDraftActions() {
        Actions builder = new Actions(driver);
        Action saveDraft = builder.click(saveDraftButton).build();
        saveDraft.perform();
        return this;
    }

    public ComposePage setToActions(String to) {
        Actions builder = new Actions(driver);
        Action setAndSave = builder.sendKeys(toTextbox, to).build();
        setAndSave.perform();
        return this;
    }

}
