package org.example.day5;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.*;
import jdk.jfr.Category;
import lombok.extern.log4j.Log4j2;
import org.example.base.BaseTest;
import org.testng.annotations.Test;

@Log4j2
@SuppressWarnings({"all"})
@Epic("Frame Handling Tests")
@Feature("Frame Handling")
@Story("Tester can handle frames on the page")
@Severity(SeverityLevel.NORMAL)
@Category("Sanity")
public class TestFrameHandeling extends BaseTest {

    @Test
    public void openPage() {
        try {
            page.navigate("https://demo.automationtesting.in/Frames.html");
            log.info("🎉 Page loaded successfully! Ready to rock and roll! 🚀 URL: {}", page.url());
        } catch (Exception e) {
            log.error("💥 Oops! Something went kaboom while loading the page! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testHandleFrames() {
        try {
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).click();
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).fill("H");
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).fill("Hare ");
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).fill("Hare K");
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("iframe[name=\"SingleFrame\"]").contentFrame().getByRole(AriaRole.TEXTBOX).fill("Hare Krishna");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Iframe with in an Iframe")).click();
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).click();
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).fill("H");
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).fill("Hare ");
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).fill("Hare K");
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).press("CapsLock");
            page.locator("#Multiple").getByText("<p>Your browser does not").contentFrame().getByText("<p>Your browser does not").contentFrame().getByRole(AriaRole.TEXTBOX).fill("Hare Krishna");

        } catch (Exception e) {
            log.error("💀 Mayday! Frame handling test went off the rails! 🚨 Error: {}", e.getMessage());
            throw e;
        }
    }
}



