package org.example.day4;


import lombok.extern.log4j.Log4j2;
import org.example.base.BaseTest;
import org.testng.annotations.Test;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@SuppressWarnings("deprecation")
@Log4j2
public class TestTraceViewer extends BaseTest {
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
}

