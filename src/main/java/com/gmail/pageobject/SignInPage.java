package com.gmail.pageobject;

import java.lang.String;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yahor local on 1/17/2016.
 */
public class SignInPage extends BasePage {

    public static final String GMAIL_URL = "http://gmail.com";

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "Passwd")
    private WebElement passwdTextbox;

    @FindBy(id = "signIn")
    private WebElement signinButton;

    @FindBy(css = "h2.hidden-small")
    private WebElement headerTitle;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public SignInPage setEmail(String emailValue) {
        emailTextbox.clear();
        emailTextbox.sendKeys(emailValue);
        return this;
    }

    public SignInPage goToPasswPage() {
        nextButton.click();
        return this;
    }

    public SignInPage setPasswd(String passwdValue) {
        passwdTextbox.clear();
        passwdTextbox.sendKeys(passwdValue);
        return this;
    }

    public InboxPage goToInboxPage() {
        signinButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

}
