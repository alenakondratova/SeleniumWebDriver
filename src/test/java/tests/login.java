package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class login {
    public WebDriver driver = new ChromeDriver();
    public WebDriverWait wait;

        @Before
        public void start() {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); }

        @Test
        public void login() {
            driver.navigate().to("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
        }

        @After
        public void stop() {
            driver.close();
            driver.quit();
        }
}
