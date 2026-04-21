package com.saucedemo.tests.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.framework.pages.CartPage;
import com.saucedemo.framework.pages.CheckoutOverviewPage;
import com.saucedemo.framework.pages.CheckoutPage;
import com.saucedemo.framework.pages.LoginPage;
import com.saucedemo.framework.pages.ProductsPage;
import com.saucedemo.framework.utils.JsonDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {
    private static final String PRODUCT_NAME = "Sauce Labs Backpack";

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;

    @Given("the user is ready to checkout a product")
    public void theUserIsReadyToCheckoutAProduct() {
        loginPage = new LoginPage();
        JsonNode user = JsonDataReader.read("testdata/users.json").get("standardUser");
        productsPage = loginPage.open().loginAs(user.get("username").asText(), user.get("password").asText()).waitUntilLoaded();
        productsPage.addProductToCart(PRODUCT_NAME);
        cartPage = productsPage.openCart();
        Assert.assertTrue(cartPage.hasProduct(PRODUCT_NAME), "Checkout product should be present in the cart.");
    }

    @When("the user completes checkout with default customer data")
    public void theUserCompletesCheckoutWithDefaultCustomerData() {
        JsonNode customer = JsonDataReader.read("testdata/checkout-data.json").get("defaultCustomer");
        checkoutPage = cartPage.checkout();
        checkoutOverviewPage = checkoutPage.enterCustomerInformation(
                customer.get("firstName").asText(),
                customer.get("lastName").asText(),
                customer.get("postalCode").asText());
        checkoutOverviewPage.finishCheckout();
    }

    @Then("the checkout should finish successfully")
    public void theCheckoutShouldFinishSuccessfully() {
        Assert.assertEquals(checkoutOverviewPage.getCompleteHeader(), "Thank you for your order!",
                "Checkout confirmation message is incorrect.");
    }
}

