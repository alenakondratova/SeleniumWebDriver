package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class myFirstTest {
    public WebDriver driver = new ChromeDriver();
    private WebDriverWait wait;

    @Before
    public void start() {

       wait = new WebDriverWait(driver, Duration.ofSeconds(10)); }

    @Test
    public void myFirstTest() {
        driver.get("http://www.google.com");
    }

    @After
    public void stop() {
        driver.close();
        driver.quit();
    }
}


