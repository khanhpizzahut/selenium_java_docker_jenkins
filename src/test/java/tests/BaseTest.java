package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    // Setup WebDriver và ExtentReports
    @BeforeClass
    public void setUp() {
        // Start WebDriver (ChromeDriver)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Start ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/test-report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        // SetUp for report
        extentReports.setSystemInfo("OS", "Mac OS");
        extentReports.setSystemInfo("Tester", "Khanh");

        // SetUup ExtentTest for test cases
        extentTest = extentReports.createTest("Test Execution");
        logger.info("WebDriver and report are setup.");
    }

    // Cleanup sau khi test chạy xong
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extentReports.flush();
        logger.info("WebDriver closed, please check report.");
    }
}
