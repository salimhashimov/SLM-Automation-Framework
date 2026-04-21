package com.saucedemo.framework.pages;

import com.saucedemo.framework.core.BasePage;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {
    private static final By PAGE_TITLE = By.cssSelector("span.title");
    private static final By PRODUCT_SORT = By.cssSelector("select[data-test='product-sort-container']");
    private static final By INVENTORY_ITEMS = By.cssSelector("div.inventory_item_name");
    private static final By CART_BADGE = By.cssSelector("span.shopping_cart_badge");
    private static final By CART_LINK = By.cssSelector("a.shopping_cart_link");

    public boolean isLoaded() {
        return actions.isDisplayed(PAGE_TITLE) && "Products".equals(actions.getText(PAGE_TITLE));
    }

    public ProductsPage waitUntilLoaded() {
        actions.waitForUrlContains("inventory.html");
        waitUtils.waitForVisible(PAGE_TITLE);
        return this;
    }

    public ProductsPage sortProductsBy(String visibleText) {
        WebElement dropdown = waitUtils.waitForVisible(PRODUCT_SORT);
        new Select(dropdown).selectByVisibleText(visibleText);
        return this;
    }

    public List<String> getProductNames() {
        return driver.findElements(INVENTORY_ITEMS)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public ProductsPage addProductToCart(String productName) {
        actions.click(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"));
        return this;
    }

    public ProductsPage removeProductFromCart(String productName) {
        actions.click(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(.,'Remove')]"));
        return this;
    }

    public int getCartBadgeCount() {
        if (!actions.isDisplayed(CART_BADGE)) {
            return 0;
        }
        return Integer.parseInt(actions.getText(CART_BADGE));
    }

    public CartPage openCart() {
        actions.click(CART_LINK);
        return new CartPage();
    }
}

