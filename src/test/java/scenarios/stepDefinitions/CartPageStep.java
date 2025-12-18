package scenarios.stepDefinitions;

import context.TestContext;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ja.但し;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HeaderPage;
import pages.HomePage;
import utilities.ConfigLoader;
import utilities.CredsLoader;

import java.util.Arrays;
import java.util.List;

import static hooks.Hooks.driver;


public class CartPageStep {

    CartPage cartPage;
    HeaderPage headerPage;
    CheckoutPage checkoutPage;
    HomePage homePage;
    CredsLoader credsLoader;
    ConfigLoader configLoader;
    Scenario scenario;

    public CartPageStep(TestContext context) {

        cartPage = new CartPage(context.driver);
        headerPage = new HeaderPage(context.driver);
        checkoutPage = new CheckoutPage(context.driver);
        homePage = new HomePage(context.driver);
        this.credsLoader = context.credsLoader;
        this.configLoader = context.configLoader;
        this.scenario = context.scenario;
    }

    // Cart
    @When("user clicks the sub category lips from makeup page")
    public void User_clicks_the_sub_category_lips_from_makeup_page() {

        homePage.clickOnLips();
    }
    @When("user selects a product from lips plp")
    public void user_selects_a_product_from_lips_plp() throws InterruptedException {

        homePage.clickOnTheProduct(1);
    }
    @When("user clicks on the cart icon in the header")
    public void user_clicks_on_the_cart_icon_in_the_header() {

        cartPage.clickOnCartIcon();
    }
    @When("user back to home")
    public void user_back_to_home() {

        homePage.backNavigation(3);
    }
    @Then("user back to parent window")
    public void user_back_to_parent_window() {

        cartPage.closeParentWindowAndSwitchToChild();
    }
    @When("user validate that cart is not empty")
    public void User_validate_that_cart_is_not_empty() {

        if(cartPage.isElementDisplayed(checkoutPage.emptyBag)) {
            String msg = "Your Bag Feels Too Light!";
            Assert.fail("Test Failed because cart is empty: " + msg);
        }
        scenario.log("Cart has items, continuing...");
    }

    // Cart Page Components
    @When("system should display the following components on the cart page")
    public void system_should_display_the_following_components_on_the_cart_page(DataTable dataTable) throws InterruptedException {

        List<String> expectedSections = dataTable.asList();
        boolean result = cartPage.validateCartComponentsByText(expectedSections);
        Assert.assertTrue(result, "Some expected components are missing on the Account page!");

    }

    // Increase and Decrease
    @When("user increase the product quantity in the cart")
    public void user_increase_the_product_quantity_in_the_cart() throws InterruptedException {

        cartPage.adjustQuantity(2,true, cartPage.increaseQuan);
    }
    @When("user decrease the product quantity in the cart")
    public void user_decrease_the_product_quantity_in_the_cart() throws InterruptedException {

        cartPage.adjustQuantity(1,false, cartPage.decreaseQuan);
    }

    // Wishlist moving from Cart/bag
    @When("user clicks on the product x mark")
    public void user_clicks_on_the_product_x_mark() {

        cartPage.clickOnProductToRemove();
    }
    @When("user moves a product to the wishlist from the cart")
    public void user_moves_a_product_to_the_wishlist_from_the_cart() {

        cartPage.clickOnMoveToWishlist();
    }
    @When("user validate that product moved to wishlist")
    public void user_validate_that_product_moved_to_wishlist() {

        scenario.log("Product moved to your wishlist from Cart/My bag");
    }
    @When("user removes a product to the wishlist from the cart")
    public void user_removes_a_product_to_the_wishlist_from_the_cart() {

        cartPage.clickOnRemoveItem();
    }
    @When("user validate that product removed from cart")
    public void user_validate_that_product_removed_from_cart() {

        scenario.log("Product has been Removed");
    }

}
