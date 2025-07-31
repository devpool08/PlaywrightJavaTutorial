package org.example.base;

import com.microsoft.playwright.*;
import lombok.extern.log4j.Log4j2;
import org.example.utils.ScreenshotAndTraceUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static java.util.Collections.singletonList;

@Log4j2
public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;
    protected Page.ScreenshotOptions screenshotOptions;
    @BeforeClass
    public void setup() {
        try {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("chrome") // Use Chrome browser
                            .setArgs(singletonList("--start-maximized")) // Start browser in maximized mode
                            .setTimeout(8000)// Set timeout for browser launch
                            .setHeadless(true) // Run in headful mode
                            .setSlowMo(60) // Slow down operations by 50ms
            );
            context = browser.newContext();
            // Start tracing before any page interactions
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            page = context.newPage();// Create a new page in the context
            screenshotOptions = new Page.ScreenshotOptions();
            log.info("🚀 Playwright setup complete! Ready to rock and roll with the browser! 🎉");
        } catch (Exception e) {
            log.error("💥 Oops! Something went kaboom during setup! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }
    @AfterMethod
    public void captureScreenshotAndTraceIfFailed(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                // Check if the test failed
                if (result.getStatus() == ITestResult.FAILURE) {
                    ScreenshotAndTraceUtil.captureScreenshotAndTraceIfFailed(page, context, screenshotOptions,this.getClass().getSimpleName(),result);
                    log.info("📸 Screenshot captured and trace saved for failed test! 📂 Check target/traces and target/screenshots for details! 🗂️");
                }
            } catch (Exception e) {
                log.error("💥 Oops! Something went wrong while taking the screenshot! 😱 Error: {}", e.getMessage());
            }
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (context != null) {
                context.close();
                log.info("🗂️ Context closed like a pro! No more shenanigans in this playground! 🎠");
            }
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
