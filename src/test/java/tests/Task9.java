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


public class Task9 {
    public WebDriver driver = new ChromeDriver();
    public WebDriverWait wait;

    @Before
    public void start() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test // Task9 Checking Zones sort order
    public void ZoneOrders() {
        List<WebElement> geoZones = driver.findElements(By.xpath("//*[@class='dataTable']//td[3]"));
        int countItems = geoZones.size();
        for (int i = 0; i < countItems; i++) {
            List<WebElement> zones = driver.findElements(By.xpath("//*[@class='dataTable']//td[3]/a"));
            zones.get(i).click();
            List <WebElement> zoneList = driver.findElements(By.xpath("//*[@class='dataTable']//td[3]/select//*[@selected='selected']"));
            List<String> expected = new ArrayList<>();
            List<String> actual= new ArrayList<>();
            int zoneItems = zoneList.size();
            for (int j = 0; j< zoneItems; j++) {
                expected.add(zoneList.get(j).getText());
                actual.add(zoneList.get(j).getText());
            }
            Collections.sort(expected);
            assertEquals(expected, actual);
            driver.navigate().back();
        }

    }

    @After
    public void stop() {
        driver.quit();
    }

}
