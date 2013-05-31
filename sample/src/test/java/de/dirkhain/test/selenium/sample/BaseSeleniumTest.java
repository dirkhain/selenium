package de.dirkhain.test.selenium.sample;

import com.google.inject.Inject;
import de.dirkhain.test.selenium.context.SeleniumContext;
import de.dirkhain.test.selenium.context.SeleniumContextGuiceModule;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Guice;

/**
 * Base test that handles the context injection via testng-guice integration and shuts down selenium afterwards.
 */
@Guice(modules = SeleniumContextGuiceModule.class)
public class BaseSeleniumTest {

    @Inject
    SeleniumContext context;

    @AfterSuite(alwaysRun = true)
    public void createAndStopService() {
        for(WebDriver driver : context.getWebdrivers()) {
            driver.quit();
        }
        if(context.getChromeService() != null) {
            context.getChromeService().stop();
        }
    }

}
