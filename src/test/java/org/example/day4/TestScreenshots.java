package org.example.day4;

import io.qameta.allure.*;
import jdk.jfr.Category;
import lombok.extern.log4j.Log4j2;
import org.example.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


@Log4j2
@SuppressWarnings({"all"})
@Epic("Screenshot Tests")
@Feature("Screenshot Functionality")
@Story("Tester can take screenshots of the page and failed tests")
@Severity(SeverityLevel.MINOR)
@Category("Regression")
public class TestScreenshots extends BaseTest {
    @Test
    public void openPage() {
        try {
            page.navigate("https://lambdatest.com/selenium-playground/select-dropdown-demo");
            log.info("🎉 Page loaded successfully! Ready to rock and roll! 🚀 URL: {}", page.url());
        } catch (Exception e) {
            log.error("💥 Oops! Something went kaboom while loading the page! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void takeScreenshot() {
        try {
            //Intentionally fail the test to take screenshot
            assertThat(page).hasTitle("Selenium Grid");
            log.info("✅ Page title verified like a champ! We're cooking with gas! 🔥");
        } catch (Exception e) {
            log.error("💥 Oops! Something went wrong while taking the screenshot! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }
}
