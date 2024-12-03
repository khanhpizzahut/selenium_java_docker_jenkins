package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest{

    @Test
    public void testGoogleSearch() {
        // Open Google
        driver.get("https://www.google.com");
        extentTest.info("Open Google site.");

        // Search
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Welcome to Viet Nam");
        searchBox.submit();
        extentTest.info("Search keyword on gooogle: done");

        // Check page title
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Welcome to Viet Nam - Tìm trên Google"), "Title does not contain search term!");
        extentTest.pass("Page title is correctly");
    }
}
