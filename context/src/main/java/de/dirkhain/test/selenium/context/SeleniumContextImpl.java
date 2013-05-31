package de.dirkhain.test.selenium.context;

import com.google.common.collect.Lists;
import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Test fixture implementation that sets up the desired browsers.
 */
public class SeleniumContextImpl implements SeleniumContext {

    public boolean testChrome = false;
    public boolean testFirefox = false;
    public boolean testOpera = false;
    public boolean testIE = false;

    final static String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    final static String TEST_BROWSERS = "test.browsers";

    private ChromeDriverService chromeDriverService;
    private FirefoxDriver firefoxDriver;
    private WebDriver chromeWebDriver;
    private WebDriver operaDriver;
    private WebDriver ieDriver;

    public ChromeDriverService getChromeService() {
        return chromeDriverService;
    }

    public FirefoxDriver getFirefox() {
        return firefoxDriver;
    }

    public WebDriver getChrome() {
        return chromeWebDriver;
    }

    public WebDriver getOpera() {
        return operaDriver;
    }

    public WebDriver[] getWebdrivers() {
        List<WebDriver> driverList = Lists.newArrayList();
        if(testChrome) driverList.add(chromeWebDriver);
        if(testFirefox) driverList.add(firefoxDriver);
        if(testOpera) driverList.add(operaDriver);
        if(testIE) driverList.add(ieDriver);
        return driverList.toArray(new WebDriver[driverList.size()]);
    }

    public SeleniumContextImpl() throws IOException {
        String browsers = System.getProperty(TEST_BROWSERS);
        if(browsers != null) {
            StringTokenizer tokenizer = new StringTokenizer(browsers, ",", false);
            while (tokenizer.hasMoreTokens()) {
                String param = tokenizer.nextToken();
                if(param.equalsIgnoreCase("chrome")) {
                    testChrome = true;
                } else if(param.equalsIgnoreCase("firefox")) {
                    testFirefox = true;
                } else if (param.equalsIgnoreCase("opera")) {
                    testOpera = true;
                } else if (param.equalsIgnoreCase("ie")) {
                    testIE = true;
                } else {
                    System.err.println("Unrecognized browser " + param + ". Skipping it.");
                }
            }
        }


        if(testChrome && chromeDriverService == null) {
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(System.getProperty(WEBDRIVER_CHROME_DRIVER)))
                    .usingAnyFreePort()
                    .build();
            chromeDriverService.start();
        }
        if(testChrome && chromeWebDriver == null) {
            chromeWebDriver = new RemoteWebDriver(chromeDriverService.getUrl(),
                    DesiredCapabilities.chrome());
        }
        if(testFirefox && firefoxDriver == null) {
            firefoxDriver = new FirefoxDriver();
        }
        if(testOpera && operaDriver == null) {
            operaDriver = new OperaDriver();
        }
        if(testIE && ieDriver == null) {
            //todo
            ieDriver = new InternetExplorerDriver();
        }
    }
}
