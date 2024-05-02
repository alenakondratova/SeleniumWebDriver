import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
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


