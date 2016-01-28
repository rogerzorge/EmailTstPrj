package com.epam.emailtest;

import com.gmail.pageobject.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;
import static java.lang.Math.random;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Created by Yahor_Faliazhynski on 1/28/2016.
 */
public class EmailTestPoGrid2 {

    protected WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    protected int randomNum;
    private final String HUB_URL = "http://localhost:4444/wd/hub";

    SignInPage signInPage;
    InboxPage inboxPage;
    ComposePage composePage;
    DraftPage draftPage;
    DraftItemPage draftItemPage;
    SentPage sentPage;

    @Test(testName = "Email test", description = "Gmail email sent test") //, threadPoolSize = 2, invocationCount = 4
    public void accountLoginTest() throws MalformedURLException {

        System.out.println("Class start!");
        randomNum = (int)abs(random() * 1000000);
        System.out.println("Current random number is: " + randomNum);

        DesiredCapabilities dCap = new DesiredCapabilities();
        dCap.setBrowserName("firefox");
        dCap.setPlatform(Platform.WINDOWS);

        driver = new RemoteWebDriver(new URL(HUB_URL), dCap);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        signInPage = new SignInPage(driver);
        driver.get(SignInPage.GMAIL_URL);

        inboxPage = signInPage.setEmail("ivan.mailfortest")
                .goToPasswPage()
                .setPasswd("Zaq1!Xsw2@")
                .goToInboxPage();
        inboxPage.goToHTMLGmailPage();
        assertEquals(inboxPage.getComposeLabel(), "Compose Mail", "Wasn't login (asserted elements are not equal)");

        composePage = inboxPage.goToComposePage()
//                                .setTo("ivan.mailtest@mail.ru")
                .setToActions("ivan.mailtest@mail.ru")  //new PO method that emulates keyboards key send through Actions class API
                .setSubject("Test subject " + randomNum + " gmail")
                .setBody("Test body " + randomNum + " test")
//                                .saveDraft();
                .saveDraftActions();    //new PO method that emulates LMC through Actions class API
        assertEquals(composePage.getDraftSavedLabel(), "Your message has been saved.", "Draft not saved (asserted elements are not equal)");

        draftPage = composePage.goToDraftPage();
        assertEquals(draftPage.getDraftTitle(), "Test subject " + randomNum + " gmail - Test body " + randomNum + " test", "No saved draft mail in Draft (asserted elements are not equal)");

        draftItemPage = draftPage.goToDraftItemPage();
        assertEquals(draftItemPage.getTo(), "ivan.mailtest@mail.ru", "To is incorrect (asserted elements are not equal)");
        assertEquals(draftItemPage.getSubject(), "Test subject " + randomNum + " gmail", "Subject is incorrect (asserted elements are not equal)");
        assertEquals(draftItemPage.getBody(), "Test body " + randomNum + " test", "Body is incorrect (asserted elements are not equal)");

        //        draftItemPage.clickSendButton();
        draftItemPage.clickSendButtonJs();  //new PO method that realizes JS Executor based clicker
        sentPage = inboxPage.goToSentPage();
        assertEquals(sentPage.getSentTitle(), "Test subject " + randomNum + " gmail - Test body " + randomNum + " test", "Mail wasn't sent (asserted elements are not equal)");

        sentPage.clickLogoutLink();
        assertEquals(signInPage.getHeaderTitle(), "Sign in to continue to Gmail", "Wasn't logout (asserted elements are not equal)");

        System.out.println("Class finish!");

        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }

    }

}
