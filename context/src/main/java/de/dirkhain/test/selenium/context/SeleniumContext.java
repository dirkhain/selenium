package de.dirkhain.test.selenium.context;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Context interface to separate test fixture from test logic.
 */
public interface SeleniumContext {

    public ChromeDriverService getChrome();
    public FirefoxDriver getFirefox();
    public WebDriver getChromeDriver();

}
