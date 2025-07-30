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

import static java.nio.file.Files.createDirectories;

public class ScreenshotAndTraceUtil {
    @SneakyThrows
    public static void captureScreenshotAndTraceIfFailed(Page page, BrowserContext context, Page.ScreenshotOptions screenshotOptions,String className,ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Check if the test failed
            if (result.getStatus() == ITestResult.FAILURE) {
                //if traces folder does not exist, create it
                createDirectories(Paths.get("target/traces"));
                // Stop tracing and save to a ZIP file
                String path1 = "target/traces/" + className + ".zip";
                String path2 = "./target/screenshots/" + className + ".png";

                // Stop tracing and save the file
                Path path = Paths.get(path1);
                context.tracing().stop(new Tracing.StopOptions()
                        .setPath(path));

                // Take a screenshot and get the byte array
                byte[] screenshot = page.screenshot(
                        screenshotOptions
                                .setPath(Paths.get(path2)) // Optional: still saves a local copy
                                .setFullPage(true)
                );

                // Correctly attach the screenshot to the Allure report
                Allure.addAttachment("Screenshot of failed test", "image/png", new ByteArrayInputStream(screenshot), ".png");
                // attach the trace file to the Allure report
                Allure.addAttachment("Trace of failed test", "application/zip", path.toUri().toURL().openStream(), ".zip");
            }
        }
    }
}
