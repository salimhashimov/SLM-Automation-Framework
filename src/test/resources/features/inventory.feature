@smoke @inventory
Feature: Inventory management
  As a logged-in shopper
  I want to manage products from the inventory page
  So that I can add and remove items from my cart

  Scenario: Sort products from Z to A
    Given the user is logged in as a valid shopper
    When the user sorts products by "Name (Z to A)"
    Then the inventory should be sorted in descending alphabetical order

  Scenario: Add a product to the cart from the products page
    Given the user is logged in as a valid shopper
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1
    When the user opens the cart
    Then the cart should contain "Sauce Labs Backpack"

  Scenario: Remove a product from the products page
    Given the user is logged in as a valid shopper
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1
    When the user removes "Sauce Labs Backpack" from the products page
    Then the cart badge should show 0

