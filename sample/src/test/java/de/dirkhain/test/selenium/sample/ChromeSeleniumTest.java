package de.dirkhain.test.selenium.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test(dataProvider = "searchData", enabled = false)
    public void testGoogleSearch(String url, String element, String term, String title)
            throws InterruptedException {
        context.getChrome().get(url);
        WebElement searchBox = context.getChrome().findElement(By.name(element));
        searchBox.sendKeys(term);
        Assert.assertEquals(context.getChrome().getTitle(), title);
    }
}
