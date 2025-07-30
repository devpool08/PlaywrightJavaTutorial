package org.example.day2;

import com.microsoft.playwright.*;
import org.example.base.BaseTest;
import org.testng.annotations.Test;


import java.util.Collections;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestLunchBrowser extends BaseTest {

    @Test
    public void testLunchBrowser(){
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        System.out.println(page.title());
        Locator myAccount = page.locator("//a[@role='button']//span[@class='title'][normalize-space()='My account']");
        myAccount.hover();
        Locator loginButton = page.locator("//span[normalize-space()='Login']");
        loginButton.click();
        assertThat(page).hasTitle("Account Login");
//        page.close();// Close the current page
//        browser.close();// Close the browser instance
//        playwright.close(); // Close Playwright instance
    }
}