package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;
import pages.GoogleHomePage;

public class GoogleSearchTest_FullFlow_Parallel {

    private WebDriver driver;
    private GoogleHomePage googleHomePage;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

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
