@smoke @checkout
Feature: Checkout flow
  As a shopper
  I want to complete a purchase
  So that I can buy products successfully

  Scenario: Complete checkout for a single product
    Given the user is ready to checkout a product
    When the user completes checkout with default customer data
    Then the checkout should finish successfully

