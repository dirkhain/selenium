package de.dirkhain.test.selenium.sample;

import com.google.common.collect.Lists;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Example to show that tests can be added on a per browser basis. Each if condition in the data provider would add
 * tests per browser.
 */
public class BrowserGenericSeleniumTest extends BaseSeleniumTest {

    @DataProvider
    public Object[][] searchData() {
        List<Object[]> data = Lists.newArrayList();
        if(context.getChrome() != null) {
            data.add(new Object[]{context.getChrome(), "http://127.0.0.1", "http://127.0.0.1/ is not available"});
        }
        if (context.getFirefox() != null) {
            data.add(new Object[]{context.getFirefox(), "http://127.0.0.1", "Problem loading page"});
        }
        if(context.getOpera() != null) {
            data.add(new Object[]{context.getOpera(), "http://127.0.0.1", "Error"});
        }
        return data.toArray(new Object[data.size()][]);
    }

    @Test(dataProvider = "searchData")
    public void testGoogleSearch(WebDriver driver, String url, String title)
            throws InterruptedException
    {
        System.out.println("Testing on " + driver.toString());
        driver.get(url);
        Assert.assertEquals(driver.getTitle(), title, "Test failed for " + driver.toString());
    }
}
