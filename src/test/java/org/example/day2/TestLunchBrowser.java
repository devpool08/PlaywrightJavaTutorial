package org.example.day2;

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import jdk.jfr.Category;
import org.example.base.BaseTest;
import org.testng.annotations.Test;



import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("Lunch Browser")
@Feature("Lunching Browser and Navigation")
@Story("Tester can launch the browser and navigate to a page")
@Severity(SeverityLevel.BLOCKER)
@Category("Smoke")
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
    }
}