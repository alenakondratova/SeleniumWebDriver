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

public class LeftSidePanelClick
    {
        public WebDriver driver = new ChromeDriver();
        public WebDriverWait wait;

        @Before
        public void start() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

        @Test
        public void LeftPanelClick() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

            List<WebElement> menuItems  =driver.findElements(By.xpath("//ul[@id='box-apps-menu']//li[contains(@id,'app')]"));
            int numberOfListElements = menuItems.size();

            for (int i = 0; i < numberOfListElements ; i++){
                menuItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[contains(@id,'app')]"));
                menuItems.get(i).click();
                WebElement title = driver.findElement(By.xpath("//h1"));
                title.isDisplayed();
                List<WebElement> subItem = driver.findElements(By.xpath("//ul[contains(@class, 'doc')]/li[contains(@id,'doc')]"));
                int numberOfSubItems = subItem.size();
                for (int j=0; j<numberOfSubItems; j++) {
                    subItem = driver.findElements(By.xpath("//ul[contains(@class, 'doc')]/li[contains(@id,'doc')]"));
                    subItem.get(j).click();
                    WebElement titleSubItem = driver.findElement(By.xpath("//h1"));
                    titleSubItem.isDisplayed();
                }
            }

        }
        @After
        public void stop() {
        driver.close();
        driver.quit();
    }
    }

