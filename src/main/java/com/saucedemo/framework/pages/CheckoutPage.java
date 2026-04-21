package com.saucedemo.framework.pages;

import com.saucedemo.framework.core.BasePage;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {
    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By PAGE_TITLE = By.cssSelector("span.title");

    public boolean isLoaded() {
        return actions.isDisplayed(PAGE_TITLE) && "Checkout: Your Information".equals(actions.getText(PAGE_TITLE));
    }

    public CheckoutPage enterFirstName(String firstName) {
        actions.type(FIRST_NAME, firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        actions.type(LAST_NAME, lastName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalCode) {
        actions.type(POSTAL_CODE, postalCode);
        return this;
    }

    public CheckoutOverviewPage continueCheckout() {
        actions.click(CONTINUE_BUTTON);
        return new CheckoutOverviewPage();
    }

    public CheckoutOverviewPage enterCustomerInformation(String firstName, String lastName, String postalCode) {
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode)
                .continueCheckout();
    }
}

