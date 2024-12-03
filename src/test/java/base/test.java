package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class test {
    private WebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        // Selenium Hub URL (Docker)
        String seleniumHubUrl = "http://localhost:4444/wd/hub";

        // Chrome Options
        ChromeOptions options = new ChromeOptions();

        // Remote WebDriver setup
        driver = new RemoteWebDriver(new URL(seleniumHubUrl), options);
    }

    @Test
    public void testGoogleHomePage() {
        // Navigate to Google
        driver.get("https://www.google.com");

        // Verify the page title
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Google"), "Page title does not contain 'Google'.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
