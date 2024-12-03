package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage extends BasePage {

    private By searchBoxLocator = By.name("q");

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    // Open Google
    public void open() {
        driver.get("https://www.google.com");
    }

    // Search
    public void search(String query) {
        WebElement searchBox = driver.findElement(searchBoxLocator);
        enterText(searchBox, query);
        searchBox.submit();
    }
}
