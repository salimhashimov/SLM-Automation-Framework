package com.saucedemo.tests.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.framework.pages.CartPage;
import com.saucedemo.framework.pages.LoginPage;
import com.saucedemo.framework.pages.ProductsPage;
import com.saucedemo.framework.utils.JsonDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.testng.Assert;

public class InventorySteps {
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private List<String> currentProductNames;

    @Given("the user is logged in as a valid shopper")
    public void theUserIsLoggedInAsAValidShopper() {
        loginPage = new LoginPage();
        JsonNode user = JsonDataReader.read("testdata/users.json").get("standardUser");
        productsPage = loginPage.open().loginAs(user.get("username").asText(), user.get("password").asText()).waitUntilLoaded();
        Assert.assertTrue(productsPage.isLoaded(), "Products page should be loaded for the shopper.");
    }

    @When("the user sorts products by {string}")
    public void theUserSortsProductsBy(String sortType) {
        productsPage.sortProductsBy(sortType);
        currentProductNames = productsPage.getProductNames();
    }

    @Then("the inventory should be sorted in descending alphabetical order")
    public void theInventoryShouldBeSortedInDescendingAlphabeticalOrder() {
        List<String> expected = new ArrayList<>(currentProductNames);
        expected.sort(Comparator.reverseOrder());
        Assert.assertEquals(currentProductNames, expected, "Products are not sorted in descending alphabetical order.");
    }

    @When("the user adds {string} to the cart")
    public void theUserAddsToTheCart(String productName) {
        productsPage.addProductToCart(productName);
    }

    @Then("the cart badge should show {int}")
    public void theCartBadgeShouldShow(Integer expectedCount) {
        Assert.assertEquals(productsPage.getCartBadgeCount(), expectedCount.intValue(), "Unexpected cart badge count.");
    }

    @When("the user opens the cart")
    public void theUserOpensTheCart() {
        cartPage = productsPage.openCart();
    }

    @Then("the cart should contain {string}")
    public void theCartShouldContain(String productName) {
        Assert.assertTrue(cartPage.isLoaded(), "Cart page should be loaded.");
        Assert.assertTrue(cartPage.hasProduct(productName), "Expected product was not found in the cart.");
    }

    @When("the user removes {string} from the products page")
    public void theUserRemovesFromTheProductsPage(String productName) {
        productsPage.removeProductFromCart(productName);
    }
}

