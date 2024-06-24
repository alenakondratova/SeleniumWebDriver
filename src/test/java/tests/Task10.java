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


import static org.junit.Assert.assertEquals;


public class Task10 {
    public WebDriver driver = new ChromeDriver();
    public WebDriverWait wait;

    @Before
    public void start() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("http://localhost/litecart");

    }

    @Test // Task10 Goods properties
    public void ProductAttributes() {
        WebElement productItem = driver.findElement(By.xpath("//*[@id='box-campaigns']//ul/li"));
        String expectedName = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[@class='name']")).getAttribute("outerText");
        WebElement expectedPriceElement = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[@class='regular-price']"));


        //properties of regular price on the main page
        String regularPriceColor = expectedPriceElement.getCssValue("color");
        String[] rgbRegularPrice = regularPriceColor.replace("rgba(", "").replace(")", "").split(",");
        int rRegularPrice = Integer.parseInt(rgbRegularPrice[0].trim());
        int gRegularPrice = Integer.parseInt(rgbRegularPrice[1].trim());
        int bRegularPrice = Integer.parseInt(rgbRegularPrice[2].trim());
        String regularPriceStyle = expectedPriceElement.getCssValue("text-decoration");
        boolean regularPriceIsCrossedOut = regularPriceStyle.contains("line-through");
        String fontSizeRegular = expectedPriceElement.getCssValue("font-size");
        float sizeRegular = Float.parseFloat(fontSizeRegular.replaceAll("[^\\d.]", ""));


        //discount price on the main page
        String expectedPrice = expectedPriceElement.getAttribute("outerText");
        WebElement expectedPriceDiscountElement = driver.findElement(By.xpath("//*[@id='box-campaigns']//*[@class='campaign-price']"));
        String expectedPriceDiscount = expectedPriceDiscountElement.getAttribute("outerText");

        String discountPriceColor = expectedPriceDiscountElement.getCssValue("color");
        String[] rgbDiscountPrice = discountPriceColor.replace("rgba(", "").replace(")", "").split(",");
        int gDiscountPrice = Integer.parseInt(rgbDiscountPrice[1].trim());
        int bDiscountPrice = Integer.parseInt(rgbDiscountPrice[2].trim());
        String discountPriceWeight = expectedPriceDiscountElement.getCssValue("font-weight");
        boolean discountPriceIsBold = "bold".equals(discountPriceWeight) || "bolder".equals(discountPriceWeight) || Integer.parseInt(discountPriceWeight) >= 700;
        String fontSizeDiscount = expectedPriceDiscountElement.getCssValue("font-size");
        float sizeDiscount = Float.parseFloat(fontSizeDiscount.replaceAll("[^\\d.]", ""));


        productItem.click();
        //elements on card page
        String actualName = driver.findElement(By.xpath("//*[@id='box-product']//*[@itemprop='name']")).getAttribute("outerText");
        WebElement actualPriceElement = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='regular-price']"));
        String actualPrice = actualPriceElement.getAttribute("outerText");
        WebElement actualPriceDiscountElement = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='campaign-price']"));
        String actualPriceDiscount = actualPriceDiscountElement.getAttribute("outerText");

        //properties of regular price on the card
        String regularPriceColorCard = actualPriceElement.getCssValue("color");
        String[] rgbRegularPriceCard = regularPriceColorCard.replace("rgba(", "").replace(")", "").split(",");
        int rRegularPriceCard = Integer.parseInt(rgbRegularPriceCard[0].trim());
        int gRegularPriceCard = Integer.parseInt(rgbRegularPriceCard[1].trim());
        int bRegularPriceCard = Integer.parseInt(rgbRegularPriceCard[2].trim());
        String regularPriceStyleCard = actualPriceElement.getCssValue("text-decoration");
        boolean regularPriceCardIsCrossedOut = regularPriceStyleCard.contains("line-through");
        String fontSizeRegularCard = actualPriceElement.getCssValue("font-size");
        float sizeRegularCard = Float.parseFloat(fontSizeRegularCard.replaceAll("[^\\d.]", ""));

        //properties of discount price on the card page
        String discountPriceColorCard = actualPriceDiscountElement.getCssValue("color");
        String[] rgbDiscountPriceCard = discountPriceColorCard.replace("rgba(", "").replace(")", "").split(",");
        int gDiscountPriceCard = Integer.parseInt(rgbDiscountPriceCard[1].trim());
        int bDiscountPriceCard = Integer.parseInt(rgbDiscountPriceCard[2].trim());
        String discountPriceCardWeight = actualPriceDiscountElement.getCssValue("font-weight");
        boolean discountPriceCardIsBold = "bold".equals(discountPriceCardWeight) || "bolder".equals(discountPriceCardWeight) || Integer.parseInt(discountPriceCardWeight) >= 700;
        String fontSizeDiscountCard = actualPriceDiscountElement.getCssValue("font-size");
        float sizeDiscountCard = Float.parseFloat(fontSizeDiscountCard.replaceAll("[^\\d.]", ""));

        // 1. checking Name on the main page and card page
        assertEquals(expectedName, actualName);

        //2. Checking Prices on the main page and card page
        assertEquals(expectedPrice, actualPrice);
        assertEquals(actualPriceDiscount, expectedPriceDiscount);

        //3.1 Check color and style regular price on the main page
        assertEquals(rRegularPrice, gRegularPrice, bRegularPrice);
        assertEquals(regularPriceIsCrossedOut, true);

        //3.2  Check color and style regular price on the card page
        assertEquals(rRegularPriceCard, gRegularPriceCard, bRegularPriceCard);
        assertEquals(regularPriceCardIsCrossedOut, true);

        // 4.1 Check color and price discount price on the main page
        assertEquals(gDiscountPrice, bDiscountPrice, 0);
        assertEquals(discountPriceIsBold, true);

        //4.2  checking discount price on the card
        assertEquals(gDiscountPriceCard, bDiscountPriceCard, 0);
        assertEquals(discountPriceCardIsBold, true);

        //checking price font size on the main page
        assertEquals(sizeDiscount>sizeRegular, true);

        //checking price font size on card page
        assertEquals(sizeDiscountCard>sizeRegularCard, true);
    }


    @After
    public void stop() {
        driver.quit();
    }

}
