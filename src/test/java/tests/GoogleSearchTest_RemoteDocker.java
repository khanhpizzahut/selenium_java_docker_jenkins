package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GoogleHomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleSearchTest_RemoteDocker {

    private WebDriver driver;
    private GoogleHomePage googleHomePage;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        // Selenium Hub URL (Docker)
        String seleniumHubUrl = "http://localhost:4444/wd/hub";
        // Chrome Options
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(seleniumHubUrl), options);
        //driver.manage().window().maximize();

    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        // Open Google and perform search
        googleHomePage = new GoogleHomePage(driver);
        googleHomePage.open();
        googleHomePage.search("Welcome to vietnam");
        // Verify the title contains the search term
        String title = googleHomePage.getPageTitle();
        Assert.assertTrue(title.contains("Welcome to vietnam - Tìm trên Google"), "Title does not contain search term!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
