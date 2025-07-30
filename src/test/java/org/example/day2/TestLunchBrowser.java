package org.example.day2;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;


import java.util.Collections;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestLunchBrowser {

    @Test
    public void testLunchBrowser(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setChannel("chrome") // Use Chrome browser
                        .setArgs(Collections.singletonList("--start-maximized")) // Start browser maximized
                        .setTimeout(60000) // Set timeout to 60 seconds
                        //.setDevtools(true) // Open DevTools automatically
                        //.setExecutablePath(Paths.get("/path/to/chrome")) // Specify the path to Chrome executable
                        .setHeadless(true) // Run in headful mode
                        .setSlowMo(50) // Slow down operations by 50ms

        );
        Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        System.out.println(page.title());
        Locator myAccount = page.locator("//a[@role='button']//span[@class='title'][normalize-space()='My account']");
        myAccount.hover();
        Locator loginButton = page.locator("//span[normalize-space()='Login']");
        loginButton.click();
        assertThat(page).hasTitle("Account Login");
        page.close();
        browser.close();
        playwright.close(); // Close Playwright instance
    }
}