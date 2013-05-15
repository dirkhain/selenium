package de.dirkhain.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

/**
 * This is a simple selenium example test.
 */
public class SimpleSeleniumTest {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeSuite
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(System.getProperty("webdriver.chrome.driver")))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterSuite
    public static void createAndStopService() {
        service.stop();
    }

    @BeforeTest
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
//            DesiredCapabilities.chrome());
            DesiredCapabilities.firefox());
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        driver.get("http://www.google.com");
        Thread.sleep(5000);
        WebElement searchBox = driver.findElement(By.name("q"));
        Thread.sleep(5000);
        searchBox.sendKeys("webdriver");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "Google");
    }

}
