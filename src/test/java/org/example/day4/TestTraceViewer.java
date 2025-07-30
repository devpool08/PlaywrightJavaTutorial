package org.example.day4;

import com.microsoft.playwright.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.util.Collections.singletonList;

@SuppressWarnings("deprecation")
@Log4j2
public class TestTraceViewer {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private BrowserContext context;

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
            context = browser.newContext();
            // Start tracing before any page interactions
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            page = context.newPage();
            page.navigate("https://lambdatest.com/selenium-playground/simple-form-demo");
            log.info("🎉 Page loaded like a boss! Ready to rock and roll! 🚀 URL: {}", page.url());
        } catch (Exception e) {
            log.error("💥 Oops! Something went kaboom during setup! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }
    @Test
    public void testTraceViewer() {
        try{
            String message="Hare Krishna! 🙏✨ This is our super duper awesome Journey! 🚀💬";
            page.getByPlaceholder("Please enter your Message").type(message);//Type the message in the input box //Fill used to fill the input box in one go(it drop the message in a single go)
            log.info("📝 Message input filled with our super duper awesome text! 🌟");

            page.locator("//button[@id='showInput']").click();
            log.info("👆 Show Input button got clicked harder than a like button! 💥");

            //verify the message
            page.locator("//p[@id='message']")
                    .waitFor();// Wait for the message to appear
            assertThat(page.locator("//p[@id='message']"))
                    .hasText(message);
            log.info("🎯 Message verification passed with flying colors! We're on fire! 🔥🎉");
        }
        catch (Exception e) {
            log.error("💀 Houston, we have a problem! Test went sideways! 🛸 Error: {}", e.getMessage());
        }
    }
    @AfterClass
    public void tearDown() {
        try{
            // Stop tracing and save to a ZIP file
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/trace.zip")));
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

