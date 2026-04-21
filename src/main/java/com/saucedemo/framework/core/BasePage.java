package com.saucedemo.framework.core;

import com.saucedemo.framework.driver.DriverManager;
import com.saucedemo.framework.utils.ElementActions;
import com.saucedemo.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils waitUtils;
    protected final ElementActions actions;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.waitUtils = new WaitUtils(driver);
        this.actions = new ElementActions(driver);
    }
}

