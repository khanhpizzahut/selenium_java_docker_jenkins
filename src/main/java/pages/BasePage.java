package pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;
    private WebDriverWait wait;

    // Constructor: Start driver và PageFactory
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Thời gian chờ mặc định là 10 giây
        PageFactory.initElements(driver, this);
    }

    // Wait
    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    // log info
    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message) {
        logger.error(message);
    }

    // Click
    protected void click(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    // enter
    protected void enterText(WebElement element, String text) {
        waitForVisibility(element);
        element.clear(); // Xóa nội dung hiện tại (nếu có)
        element.sendKeys(text);
    }

    // GetPageTitle
    public String getPageTitle() throws InterruptedException {
        Thread.sleep(1000);
        return driver.getTitle();
    }

    //
    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
