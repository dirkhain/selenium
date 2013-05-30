package de.dirkhain.test.selenium.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This test will run separate logic that is custom to firefox and leverage the context established in the base test.
 */
public class FirefoxSeleniumTest extends BaseSeleniumTest {

    @DataProvider
    public Object[][] searchData() {
        return new Object[][] {
            {"http://www.google.com", "q", "webdriver", "Google"}

        };
    }

    @Test(dataProvider = "searchData")
    public void testGoogleSearch(String url, String element, String term, String title)
            throws InterruptedException {
        context.getFirefox().get(url);
        WebElement searchBox = context.getFirefox().findElement(By.name(element));
        Thread.sleep(1000);
        searchBox.sendKeys(term);
        Thread.sleep(1000);
        Assert.assertEquals(context.getFirefox().getTitle(), title);
    }
}
