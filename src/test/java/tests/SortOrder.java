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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SortOrder {
    public WebDriver driver = new ChromeDriver();
    public WebDriverWait wait;

    @Before
    public void start() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test // Task8.1 Checking Countries sort order
    public void SortOrder() {
        List<WebElement> countries = driver.findElements(By.xpath("//*[@class='dataTable']//td[5]"));
        List<String> expected = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        int countItems = countries.size();
        for (int i = 0; i < countItems; i++) {
            expected.add(countries.get(i).getAttribute("textContent"));
            actual.add(countries.get(i).getAttribute("textContent"));
        }
        Collections.sort(expected);
        assertEquals(expected, actual);


    }

    @Test //Test 8.2 Verify sorting of existing zones

    public void ZoneOrder() {

        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='row']"));
        int tableSize = rows.size();
        for (int i = 0; i < tableSize; i++) {
            List<WebElement> zone = driver.findElements(By.xpath("//tr[@class='row']//td[6]"));
            List<WebElement> country = driver.findElements(By.xpath("//tr[@class='row']//td[7]//*[@title='Edit']"));
            boolean zoneValue = zone.get(i).getAttribute("outerText").equals("0");
            if (!zoneValue) {
                country.get(i).click();
                List<WebElement> subCountries = driver.findElements(By.xpath("//*[@class='dataTable']//td[3]//input"));
                List<String> expected = new ArrayList<>();
                List<String> actualCountry = new ArrayList<>();
                int countItems = subCountries.size();
                for (int j = 0; j < countItems; j++) {
                    boolean zoneExists= subCountries.get(j).getAttribute("value").isEmpty();
                    if (!zoneExists) {
                    expected.add(subCountries.get(j).getAttribute("value"));
                    actualCountry.add(subCountries.get(j).getAttribute("value"));
                } }
                Collections.sort(expected);
                assertEquals(expected, actualCountry);
                driver.navigate().back();
            }

        }
    }


    @After
    public void stop() {
        driver.quit();
    }

}
