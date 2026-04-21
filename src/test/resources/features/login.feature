@smoke @login
Feature: Login
  As a SauceDemo shopper
  I want to authenticate with valid and invalid credentials
  So that access is validated correctly

  Scenario: Successful login with a standard user
    Given the user opens the SauceDemo login page
    When the user logs in with "standardUser" credentials
    Then the products page should be displayed

  Scenario: Blocked login for a locked out user
    Given the user opens the SauceDemo login page
    When the user attempts login with "lockedOutUser" credentials
    Then a login error message "Epic sadface: Sorry, this user has been locked out." should be displayed

