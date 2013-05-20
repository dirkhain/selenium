package de.dirkhain.test.selenium.context;

import com.google.inject.Binder;
import com.google.inject.Module;

import java.io.IOException;

/**
 * Guice module to inject the test fixture into the tests.
 */
public class SeleniumContextGuiceModule implements Module {

    @Override
    public void configure(Binder binder) {
        SeleniumContext context = null;
        try {
            context = new SeleniumContextImpl();
        } catch (IOException e) {
            System.out.println("Unable to initialize selenium context. " + e.getMessage());
            System.exit(-1);
        }
        binder.bind(SeleniumContext.class).toInstance(context);
    }
}
