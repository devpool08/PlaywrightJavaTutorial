package org.example.day4;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.*;
import jdk.jfr.Category;
import lombok.extern.log4j.Log4j2;
import org.example.base.BaseTest;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Arrays;

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
        String elementScreenshotPath = "./target/screenshots/" + this.getClass().getSimpleName() + "(element).png";
        String fullPageScreenshotPath = "./target/screenshots/" + this.getClass().getSimpleName() + "(MaskElement).png";
        Locator selectedOption = page.locator("select#select-demo");
        try {
            // Wait for the element to be visible
            selectedOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            // Take a screenshot of the element
            byte[] screenshot = selectedOption.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(elementScreenshotPath)));
            // Attach the screenshot to Allure report
            Allure.addAttachment("Element Screenshot", "image/png" ,new ByteArrayInputStream(screenshot), "png");
            //Take a screenshot of the full page with masking sensitive data
            byte[] fullPageMaskedScreenshot = page.screenshot(
                    new Page.ScreenshotOptions()
                    .setFullPage(false)
                    .setPath(Paths.get(fullPageScreenshotPath))
                    .setMask(Arrays.asList(selectedOption)));
            // Attach the full page screenshot to Allure report
            Allure.addAttachment("Full Page Screenshot with Masked Element (Select Option)", "image/png", new ByteArrayInputStream(fullPageMaskedScreenshot), "png");
            //Intentionally fail the test to take screenshot
            assertThat(page).hasTitle("Selenium Grid");
            log.info("✅ Page title verified like a champ! We're cooking with gas! 🔥");
        } catch (Exception e) {
            log.error("💥 Oops! Something went wrong while taking the screenshot! 😱 Error: {}", e.getMessage());
            throw new RuntimeException("Intentionally failing the test to take a screenshot", e);
        }
    }
}
