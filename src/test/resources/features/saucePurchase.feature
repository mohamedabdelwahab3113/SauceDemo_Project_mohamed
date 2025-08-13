Feature: Purchase flow on SauceDemo
  // Tests different scenarios in the purchase flow

  Scenario: Login successfully
    Given user is on the login page
    When user logs in with username "standard_user" and password "secret_sauce"
    Then user should be on products page

  Scenario: Add first product to cart
    Given user is logged in as "standard_user" with password "secret_sauce"
    When user adds product "Sauce Labs Backpack" to cart
    Then product "Sauce Labs Backpack" should be in the cart

  Scenario: Add second product to cart
    Given user is logged in as "standard_user" with password "secret_sauce"
    When user adds product "Sauce Labs Bike Light" to cart
    Then product "Sauce Labs Bike Light" should be in the cart

  Scenario: Checkout with two products
    Given user has two products "Sauce Labs Backpack" and "Sauce Labs Bike Light" in the cart
    When user proceeds to checkout with first name "John" last name "Doe" postal code "12345"
    Then the purchase should be successful

  Scenario: Login with invalid credentials
    Given user is on the login page
    When user logs in with username "wrong_user" and password "wrong_pass"
    Then an error message "Epic sadface: Username and password do not match any user in this service" should be displayed

  Scenario: Add product without logging in
    Given user is on the login page
    When user tries to access the products page without logging in
    Then the login page should still be displayed

  Scenario: Checkout without adding any products
    Given user is logged in as "standard_user" with password "secret_sauce"
    When user proceeds to checkout with first name "John" last name "Doe" postal code "12345"
    Then the purchase should fail with message "Error: Cart is empty"
