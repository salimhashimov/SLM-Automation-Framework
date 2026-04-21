package com.saucedemo.framework.pages;

import com.saucedemo.framework.config.ConfigManager;
import com.saucedemo.framework.core.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
    private static final By LOGIN_LOGO = By.className("login_logo");

    public LoginPage open() {
        driver.get(ConfigManager.getBaseUrl());
        waitUtils.waitForVisible(LOGIN_LOGO);
        return this;
    }

    public LoginPage enterUsername(String username) {
        actions.type(USERNAME_INPUT, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        actions.type(PASSWORD_INPUT, password);
        return this;
    }

    public ProductsPage clickLogin() {
        actions.click(LOGIN_BUTTON);
        return new ProductsPage();
    }

    public ProductsPage loginAs(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    public String getErrorMessage() {
        return actions.getText(ERROR_MESSAGE);
    }

    public boolean isLoaded() {
        return actions.isDisplayed(LOGIN_LOGO);
    }
}

