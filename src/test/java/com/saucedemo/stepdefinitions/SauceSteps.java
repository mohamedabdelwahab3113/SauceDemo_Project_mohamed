package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.*;
import com.saucedemo.utils.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class SauceSteps {

    private WebDriver driver = Hooks.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = new ProductsPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    private void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        loginPage.openLoginPage();
        waitForElement(By.id("user-name"));
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("user should be on products page")
    public void user_should_be_on_products_page() {
        waitForElement(By.className("title"));
        Assert.assertTrue(productsPage.isProductsTitleDisplayed(), "Products page is not displayed!");
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {
        waitForElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }

    @Given("user is logged in as {string} with password {string}")
    public void user_is_logged_in_as_with_password(String username, String password) {
        user_is_on_the_login_page();
        user_logs_in_with_username_and_password(username, password);
        user_should_be_on_products_page();
    }

    @When("user adds product {string} to cart")
    public void user_adds_product_to_cart(String productName) {
        productsPage.addProductToCart(productName);
        waitForElement(By.className("shopping_cart_badge"));
    }

    @Then("product {string} should be in the cart")
    public void product_should_be_in_the_cart(String productName) {
        productsPage.goToCart();
        waitForElement(By.className("cart_item"));
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found in cart!");
    }

    @Given("user has two products {string} and {string} in the cart")
    public void user_has_two_products_in_the_cart(String product1, String product2) {
        user_is_logged_in_as_with_password("standard_user", "secret_sauce");
        user_adds_product_to_cart(product1);
        user_adds_product_to_cart(product2);
        productsPage.goToCart();
    }

    @When("user proceeds to checkout with first name {string} last name {string} postal code {string}")
    public void user_proceeds_to_checkout(String firstName, String lastName, String postalCode) {
        cartPage.clickCheckoutButton();
        waitForElement(By.id("first-name"));
        checkoutPage.fillCheckoutInformation(firstName, lastName, postalCode);
        checkoutPage.finishOrder();
    }

    @Then("the purchase should be successful")
    public void the_purchase_should_be_successful() {
        waitForElement(By.className("complete-header"));
        Assert.assertTrue(checkoutPage.getOrderConfirmationText().contains("THANK YOU"), "Purchase failed!");
    }

    @Then("the purchase should fail with message {string}")
    public void the_purchase_should_fail_with_message(String expectedMessage) {
        waitForElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertEquals(checkoutPage.getErrorMessage(), expectedMessage);
    }

    @When("user tries to access the products page without logging in")
    public void user_tries_to_access_the_products_page_without_logging_in() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @Then("the login page should still be displayed")
    public void login_page_should_still_be_displayed() {
        waitForElement(By.id("user-name"));
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "User bypassed login!");
    }
}
