package com.gmail.pageobject;

import org.openqa.selenium.WebDriver;

/**
 * Created by Yahor local on 1/17/2016.
 */
public abstract class BasePage {

    protected String pageTitle;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

}
