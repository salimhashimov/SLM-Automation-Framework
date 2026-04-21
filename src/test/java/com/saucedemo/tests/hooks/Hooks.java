package com.saucedemo.tests.hooks;

import com.saucedemo.framework.driver.BrowserFactory;
import com.saucedemo.framework.driver.DriverManager;
import com.saucedemo.framework.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        WebDriver driver = BrowserFactory.createDriver();
        DriverManager.setDriver(driver);
        LOGGER.info("Starting scenario: {} on browser={}", scenario.getName(), System.getProperty("browser", "chrome"));
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                ScreenshotUtils.attachScreenshot(DriverManager.getDriver(), scenario.getName());
            }
        } catch (Exception exception) {
            LOGGER.warn("Unable to capture screenshot for scenario: {}", scenario.getName(), exception);
        } finally {
            DriverManager.quitDriver();
            LOGGER.info("Finished scenario: {} with status={}", scenario.getName(), scenario.getStatus());
        }
    }
}

