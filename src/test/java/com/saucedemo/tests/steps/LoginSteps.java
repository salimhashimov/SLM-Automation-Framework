package com.saucedemo.tests.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.framework.pages.LoginPage;
import com.saucedemo.framework.pages.ProductsPage;
import com.saucedemo.framework.utils.JsonDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("the user opens the SauceDemo login page")
    public void theUserOpensTheSauceDemoLoginPage() {
        loginPage = new LoginPage();
        loginPage.open();
        Assert.assertTrue(loginPage.isLoaded(), "Login page should be visible.");
    }

    @When("the user logs in with {string} credentials")
    public void theUserLogsInWithCredentials(String userKey) {
        JsonNode user = JsonDataReader.read("testdata/users.json").get(userKey);
        productsPage = loginPage.loginAs(user.get("username").asText(), user.get("password").asText());
    }

    @When("the user attempts login with {string} credentials")
    public void theUserAttemptsLoginWithCredentials(String userKey) {
        JsonNode user = JsonDataReader.read("testdata/users.json").get(userKey);
        loginPage.enterUsername(user.get("username").asText())
                .enterPassword(user.get("password").asText())
                .clickLogin();
    }

    @Then("the products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        Assert.assertTrue(productsPage.waitUntilLoaded().isLoaded(), "Products page should be displayed after login.");
    }

    @Then("a login error message {string} should be displayed")
    public void aLoginErrorMessageShouldBeDisplayed(String expectedMessage) {
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Unexpected login error message.");
    }
}

