package functionLibrary;

import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonFunctions {

    public static WebDriver driver;

    public void openBrowser()
    {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    public void closeBrowser()
    {
        driver.quit();
    }

    public void assertNoSuchElement(WebDriver driver, By by)
    {
        try
        {
           WebElement element = driver.findElement(by);
            Assert.fail();
        }catch (NoSuchElementException e)
        {
            Assert.assertTrue(true);
        }
    }


    public void waitForTime(WebDriver driver, Duration duration) {
        driver.manage().timeouts().implicitlyWait(duration);
    }

    public WebElement findElement(WebDriver driver, By locator)
    {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(WebDriver driver, By locator)
    {
        return driver.findElements(locator);
    }

    public void clickElement(WebDriver driver, By locator)
    {
        findElement(driver, locator).click();
    }

    public void type(WebDriver driver, By locator, String text)
    {
        findElement(driver, locator).sendKeys(text);
    }

    public void goToUrl(WebDriver driver, String url)
    {
        driver.get(url);
    }

    public void assertTrue(WebDriver driver, By locator)
    {
        Assert.assertTrue(findElement(driver, locator).isDisplayed());
    }

    public void assertEquals(WebDriver driver, By locator, String expectedText)
    {
        Assert.assertEquals(expectedText, findElement(driver, locator).getText());
    }

    public void selectDrpDownOptn(WebDriver driver, By locator, String option)
    {
        new Select(findElement(driver, locator)).selectByVisibleText(option);
    }

}
