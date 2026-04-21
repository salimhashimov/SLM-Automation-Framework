package com.saucedemo.framework.pages;

import com.saucedemo.framework.core.BasePage;
import org.openqa.selenium.By;

public class CartPage extends BasePage {
    private static final By PAGE_TITLE = By.cssSelector("span.title");
    private static final By CHECKOUT_BUTTON = By.id("checkout");

    public boolean isLoaded() {
        return actions.isDisplayed(PAGE_TITLE) && "Your Cart".equals(actions.getText(PAGE_TITLE));
    }

    public boolean hasProduct(String productName) {
        By cartItem = By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");
        return actions.isDisplayed(cartItem);
    }

    public CheckoutPage checkout() {
        actions.click(CHECKOUT_BUTTON);
        return new CheckoutPage();
    }
}

