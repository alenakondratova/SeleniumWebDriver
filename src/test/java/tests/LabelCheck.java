package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LabelCheck {
    public WebDriver driver = new ChromeDriver();
    public WebDriverWait wait;

    @Before
    public void start() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void LabelCheck() {
        driver.navigate().to("http://localhost/litecart");

        List<WebElement> productItems = driver.findElements(By.xpath(".//li[contains(@class, 'product')]"));
        int numberOfListElements = productItems.size();
        for (int i = 0; i < numberOfListElements; i++) {
            WebElement label = driver.findElement(By.xpath(".//div[contains(@class, 'sticker')]"));
            int amount = driver.findElement(By.xpath(".//li[contains(@class, 'product')]")).findElements(By.xpath(".//div[contains(@class, 'sticker ')]")).size();
            label.isDisplayed();
            assertEquals (1, amount);
        }


    }
        @After
        public void stop() {
            driver.close();
            driver.quit();
       }

}
