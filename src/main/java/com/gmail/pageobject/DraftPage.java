package com.gmail.pageobject;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yahor local on 1/17/2016.
 */
public class DraftPage extends BasePage {

    @FindBy(css = "span.ts")
    private WebElement draftMailLink;

    public DraftPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getDraftTitle() {
        return draftMailLink.getText();
    }

    public DraftItemPage goToDraftItemPage() {
        draftMailLink.click();
        return PageFactory.initElements(driver, DraftItemPage.class);
    }

}
