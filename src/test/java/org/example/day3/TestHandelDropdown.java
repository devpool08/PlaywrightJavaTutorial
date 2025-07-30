package org.example.day3;

import com.microsoft.playwright.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.util.Collections.singletonList;

@Log4j2
public class TestHandelDropdown {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeClass
    public void setup() {
        try{
            playwright= Playwright.create();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("chrome") // Use Chrome browser
                            .setArgs(singletonList("--start-maximized")) // Start browser maximized
                            .setTimeout(6000) // Set timeout to 60 seconds
                            .setHeadless(true) // Run in headful mode
                            .setSlowMo(60) // Slow down operations by 50ms
            );
            page = browser.newPage();
            page.navigate("https://lambdatest.com/selenium-playground/select-dropdown-demo");
            log.info("🎉 Page loaded like a boss! Ready to rock and roll! 🚀 URL: {}", page.url());
        } catch (Exception e) {
            log.error("💥 Oops! Something went kaboom during setup! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }
    @Test
    public void testHandleDropdown() {
        try {
            assertThat(page).hasTitle("Selenium Grid Online | Run Selenium Test On Cloud");
            log.info("✅ Page title verified like a champ! We're cooking with gas! 🔥");

            // Select an option from the dropdown
            page.locator("select#select-demo")
                    .selectOption("Monday");
            log.info("📅 Dropdown option selected Monday, here we come! 🚀");
            // Verify the selected option
            Locator selectedOption = page.locator("//p[@class='selected-value text-size-14']");
            assertThat(selectedOption).hasText("Day selected :- Monday");
            log.info("✅ Dropdown selection verified successfully! We're on fire! 🔥🎉");
        } catch (Exception e) {
            log.error("💀 Houston, we have a problem! Test went sideways! 🛸 Error: {}", e.getMessage());
        }
    }



    @AfterClass
    public void tearDown() {
        try{
            if (page != null) {
                page.close();
                log.info("📄 Page closed gracefully! Goodbye sweet prince! 👋😢");
            }
            if (browser != null) {
                browser.close();
                log.info("🌐 Browser closed successfully! Time to say bye bye Chrome! 👋🦄");
            }
            if (playwright != null) {
                playwright.close();
                log.info("🎭 Playwright instance closed like the final curtain! That's a wrap folks! 🎬✨");
            }
        } catch (Exception e) {
            log.error("🚨 Cleanup went haywire! Even saying goodbye is hard! 😭 Error: {}", e.getMessage());
        }
    }
}
