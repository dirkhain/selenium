package de.dirkhain.test.selenium.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Test fixture implementation that sets up the desired browsers.
 */
public class SeleniumContextImpl implements SeleniumContext {

    private ChromeDriverService chromeDriverService;
    private FirefoxDriver firefoxDriver;
    private WebDriver chromeWebDriver;

    @Override
    public ChromeDriverService getChrome() {
        return chromeDriverService;
    }

    @Override
    public FirefoxDriver getFirefox() {
        return firefoxDriver;
    }

    @Override
    public WebDriver getChromeDriver() {
        return chromeWebDriver;
    }

    public SeleniumContextImpl() throws IOException {
        if(chromeDriverService == null) {
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(System.getProperty("webdriver.chrome.driver")))
                    .usingAnyFreePort()
                    .build();
            chromeDriverService.start();
        }
        if(chromeWebDriver == null) {
            chromeWebDriver = new RemoteWebDriver(chromeDriverService.getUrl(),
                    DesiredCapabilities.chrome());

        }
        if(firefoxDriver == null) {
            firefoxDriver = new FirefoxDriver();
        }
    }
}
