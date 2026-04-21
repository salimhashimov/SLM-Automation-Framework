package com.saucedemo.framework.pages;

import com.saucedemo.framework.core.BasePage;
import org.openqa.selenium.By;

public class CheckoutOverviewPage extends BasePage {
    private static final By PAGE_TITLE = By.cssSelector("span.title");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By COMPLETE_HEADER = By.cssSelector("h2.complete-header");

    public boolean isLoaded() {
        return actions.isDisplayed(PAGE_TITLE) && "Checkout: Overview".equals(actions.getText(PAGE_TITLE));
    }

    public CheckoutOverviewPage finishCheckout() {
        actions.click(FINISH_BUTTON);
        return this;
    }

    public String getCompleteHeader() {
        return actions.getText(COMPLETE_HEADER);
    }
}

