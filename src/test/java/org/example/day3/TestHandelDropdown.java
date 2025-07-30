package org.example.day3;

import com.microsoft.playwright.*;
import lombok.extern.log4j.Log4j2;
import org.example.base.BaseTest;
import org.testng.annotations.Test;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Log4j2
public class TestHandelDropdown extends BaseTest {

    @Test
    public void openPage(){
        try{
            page.navigate("https://lambdatest.com/selenium-playground/select-dropdown-demo");
            log.info("🎉 Page loaded successfully! Ready to rock and roll! 🚀 URL: {}", page.url());
        } catch (Exception e) {
            log.error("💥 Oops! Something went kaboom while loading the page! 😱 Error: {}", e.getMessage());
            throw e;
        }
    }
    @Test
    public void testHandleSingleSelect() {
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
            log.error("💀 Mayday! Single-select test went off the rails! 🚨 Error: {}", e.getMessage());
            throw e;
        }
    }
    @Test
    public void testHandleMultiSelect() {
        try{
            page.locator("//select[@id='multi-select']").selectOption(new String[]{"Florida", "New York", "Ohio"});
            log.info("🌎 Multi-select options selected: Florida, New York, Ohio! We're globetrotting! 🌍✈️");
            Locator button=page.locator("//button[@id='printAll']");
            button.click();
        } catch (Exception e) {
            log.error("💀 Mayday! Multi-select test went off the rails! 🚨 Error: {}", e.getMessage());
            throw e;
        }
    }
}
