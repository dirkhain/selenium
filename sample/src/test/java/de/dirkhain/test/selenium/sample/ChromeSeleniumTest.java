package de.dirkhain.test.selenium.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * This is a simple selenium example test. The test fixture is injected in {@link BaseSeleniumTest} and accessed via
 * the {@link de.dirkhain.test.selenium.context.SeleniumContext}.
 * Requires chromedriver and ffdriver to be installed under {@value /opt/tools/selenium/chromedriver/chromedriver}.
 * See the pom.xml for details.
 */
public class ChromeSeleniumTest extends BaseSeleniumTest {

    @DataProvider
    public Object[][] searchData() {
        return new Object[][] {
            {"http://www.google.com", "q", "webdriver", "Google"}

        };
    }

    @Test(dataProvider = "searchData")
    public void testGoogleSearch(String url, String element, String term, String title)
            throws InterruptedException {
        context.getChromeDriver().get(url);
        Thread.sleep(2000);
        WebElement searchBox = context.getChromeDriver().findElement(By.name(element));
        Thread.sleep(2000);
        searchBox.sendKeys(term);
        Thread.sleep(2000);
        Assert.assertEquals(context.getChromeDriver().getTitle(), title);
    }
}
