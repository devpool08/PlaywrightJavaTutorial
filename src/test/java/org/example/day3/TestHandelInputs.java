package org.example.day3;

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import jdk.jfr.Category;
import lombok.extern.log4j.Log4j2;
import org.example.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@SuppressWarnings({"deprecation"})
@Log4j2
@Epic("Handle Inputs")
@Feature("Handle Inputs")
@Story("Tester can handle simple input boxes, two-way data binding, and checkboxes/radio buttons")
@Severity(SeverityLevel.NORMAL)
@Category("Sanity")
public class TestHandelInputs extends BaseTest {
    @Test
    public void openPage() {
        try{
            page.navigate("https://lambdatest.com/selenium-playground/simple-form-demo");
            log.info("🌐 Navigated to the input form demo page! Let's get our hands dirty! 🚀 URL: {}", page.url());
        } catch (Exception e) {
            log.error("💥 Oops! Something went kaboom while loading the page! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testHandleSimpleInputBox() {
        try {
            assertThat(page).hasTitle("Selenium Grid Online | Run Selenium Test On Cloud");
            log.info("✅ Page title verified like a champ! We're cooking with gas! 🔥");

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
        } catch (Exception e) {
            log.error("💀 Houston, we have a problem! Test went sideways! 🛸 Error: {}", e.getMessage());
            throw e;
        }
    }
    @Test
    public void testHandleTwoWayDataBinding() {
        try {
            page.locator("//input[@id='sum1']").fill("4");
            log.info("🔢 First input filled with 4! Let's do some math magic! ✨");
            page.locator("//input[@id='sum2']").fill("4");
            log.info("🔢 Second input filled with 5! We're adding like pros! ✨");
            page.locator("//button[normalize-space()='Get Sum']").click();
            log.info("➕ Get Sum button clicked! Time to unleash the power of addition! 💥");
            page.locator("//p[@id='addmessage']")
                    .waitFor(); // Wait for the sum to appear
            assertThat(page.locator("//p[@id='addmessage']"))
                    .hasText("8");
            log.info("✅ Two-way data binding test passed! The sum is correct! 🎉✨");
        } catch (Exception e) {
            log.error("💥 Oopsie daisy! Something went wrong while handling two-way data binding! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }
    @Test
    public void testGetInputValueAndAttribute() {
        try {
            page.navigate("https://letcode.in/edit");
            log.info("🌐 Navigated to the input value page! Let's get that value! 🚀");
            Locator ipValue = page.locator("#getMe");
            String inputValue = ipValue.inputValue();// Get the input value
            log.info("🔍 Input value retrieved: {}", inputValue);
            ipValue.clear();// Clear the input value
            log.info("🧹 Input value cleared like a pro! Time to fill it with something new! ✨");
            Locator nameIp = page.locator("#fullName");
            assertThat(nameIp).hasAttribute("placeholder", "Enter first & last name");// Verify the placeholder attribute
            log.info("🔍 Placeholder retrieved: {}", nameIp.getAttribute("placeholder"));
            page.goBack();// Navigate back to the previous page
            log.info("🔙 Navigated back to the previous page! We're on a roll! 🚀");
        } catch (Exception e) {
            log.error("💥 Oops! Something went wrong while getting the input value! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }
    @Test
    public void testHandelCheckboxAndRadioButton() {
        try {
            page.navigate("https://lambdatest.com/selenium-playground/checkbox-demo");
            log.info("🌐 Navigated to the checkbox and radio button demo page! Let's get our hands dirty! 🚀");
            // Handle checkboxes And Click vs Check
            Locator checkBox = page.locator("//label[normalize-space()='Click on check box']");
            checkBox.check();
            log.info("☑️ Checkbox clicked! Let's see if it works! 💥");
            assertThat(checkBox).isChecked();
            log.info("✅ Checkbox is checked! We're on the right track! 🎉");
            checkBox.click();
            log.info("❌ Checkbox clicked again! Let's uncheck it! 💥");
            assertThat(checkBox).not().isChecked();
            log.info("✅ Checkbox is unchecked! We're on a roll! 🎉");
            page.goBack();
            log.info("🔙 Navigated back to the previous page! Let's handle some radio buttons now! 🚀");
        } catch (Exception e) {
            log.error("💥 Oopsie daisy! Something went wrong while handling checkboxes and radio buttons! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }

}
