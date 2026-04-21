package com.saucedemo.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void click(By locator) {
        waitUtils.waitForClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitUtils.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return waitUtils.waitForVisible(locator).getText().trim();
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitUtils.waitForVisible(locator).isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    public void waitForUrlContains(String urlPart) {
        waitUtils.waitForUrlContains(urlPart);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

