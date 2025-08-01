package org.example.utils;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ScreenshotAndTraceUtil {
    @SneakyThrows
    public static void captureScreenshotAndTraceIfFailed(Page page, BrowserContext context, Page.ScreenshotOptions screenshotOptions, String className, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Check if the test failed
            if (result.getStatus() == ITestResult.FAILURE) {
                // Stop tracing and save to a ZIP file
                String tracePath = "target/traces/" + className + ".zip";
                String fullPageScreenshotPath = "./target/screenshots/" + className + "(full page).png";
                // Stop tracing and save the file
                Path path = Paths.get(tracePath);
                //Video recording is not supported in headless mode, so we skip it
                context.tracing().stop(new Tracing.StopOptions()
                        .setPath(path));
                // Take a full screenshot and get the byte array
                byte[] fullScreenshot = page.screenshot(
                        screenshotOptions
                                .setPath(Paths.get(fullPageScreenshotPath)) // Optional: still saves a local copy
                                .setFullPage(true)
                );
                // Correctly attach the screenshot to the Allure report
                Allure.addAttachment("Screenshot of failed test", "image/png", new ByteArrayInputStream(fullScreenshot), ".png");
                // attach the trace file to the Allure report
                Allure.addAttachment("Trace of failed test", "application/zip", path.toUri().toURL().openStream(), ".zip");
            }
        }
    }
}
