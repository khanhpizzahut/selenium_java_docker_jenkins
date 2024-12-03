package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.GoogleHomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleSearchTest_RemoteDocker_Parallel {

    private WebDriver driver;
    private GoogleHomePage googleHomePage;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws MalformedURLException {
        String seleniumHubUrl = "http://localhost:4444/wd/hub";
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL(seleniumHubUrl), options);
        }else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(seleniumHubUrl), options);
        }else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver = new RemoteWebDriver(new URL(seleniumHubUrl), options);
        }


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
