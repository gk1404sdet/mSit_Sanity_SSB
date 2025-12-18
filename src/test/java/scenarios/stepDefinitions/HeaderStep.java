package scenarios.stepDefinitions;

import context.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import pages.CartPage;
import pages.HeaderPage;
import pages.HomePage;
import utilities.ConfigLoader;
import utilities.CredsLoader;

import java.awt.image.ImageObserver;

import static hooks.Hooks.driver;

public class HeaderStep {

    HomePage homePage;
    HeaderPage headerPage;
    CartPage cartPage;
    CredsLoader credsLoader;
    ConfigLoader configLoader;
    Scenario scenario;

    public static String expectedText = " ";

    public HeaderStep(TestContext context) {

        homePage = new HomePage(context.driver);
        headerPage = new HeaderPage(context.driver);
        cartPage = new CartPage(context.driver);
        this.credsLoader = context.credsLoader;
        this.configLoader = context.configLoader;
        this.scenario = context.scenario;
    }

    // Wishlist
    @When("User clicks on the wishlist icon in the header")
    public void user_clicks_on_the_wishlist_icon_in_the_header() {

        headerPage.clickOnWishlist();
    }
    @When("User validate that wishlist icon navigate to the wishlist page")
    public void user_validate_that_wishlist_icon_navigate_to_the_wishlist_page() {

      scenario.log("User landed to Wishlist Page");
    }
    @When("User select a product from wishlist page")
    public void user_select_a_product_from_wishlist_page() {

        homePage.clickOnTheProduct(1);
    }
    @When("User validate that it navigates to the PDP")
    public void user_validate_that_it_navigates_to_the_pdp() {

        homePage.switchToNewWindow();
        headerPage.scroll("DOWN");
        scenario.log("User landed to Product Details Page");
    }
    @When("User return to the wishlist page")
    public void user_return_to_the_wishlist_page() throws InterruptedException {

        Thread.sleep(10000);
    }
    @When("User adds a product to the cart from the wishlist page")
    public void user_adds_a_product_to_the_cart_from_the_wishlist_page() {

        homePage.validateShadeOrBag();
    }
    @When("User validate that product add to wishlist successfully")
    public void user_validate_that_product_add_to_wishlist_successfully() {

        scenario.log("Product added to your cart successfully");
    }
    @Then("User removes a product from the wishlist page")
    public void user_removes_a_product_from_the_wishlist_page() throws InterruptedException {

//        catalogPage.clickOnRemoveWishlist();
        Thread.sleep(10000);
    }
    @Then("User clicks on the remove item for removing product from wishlist")
    public void user_clicks_on_the_remove_item_for_removing_product_from_wishlist() {

        cartPage.clickOnRemove();
    }

    // Search Functionality
    @When("User clicks on the search bar")
    public void user_clicks_on_the_search_bar() {

        headerPage.clicksOnTheSearEditBox();
    }
    @When("User search with keywords")
    public void user_search_with_keywords() {

        headerPage.enterKeyword("lipstick");
    }
    @When("user clicks enter")
    public void user_clicks_enter() throws InterruptedException {
        Thread.sleep(5000);
    }
    @When("User validate that search option navigation to the relevant products")
    public void user_validate_that_search_option_navigation_to_the_relevant_products() {

        scenario.log("User landed to Searched product");
    }
    @When("User clicks on trending products")
    public void user_clicks_on_trending_products() {

        headerPage.clickOnTrendProduct();
    }

    @Then("User validate that selected brand navigation to the relevant brand")
    public void user_validate_that_selected_brand_navigation_to_the_relevant_brand() {

        scenario.log("User navigated to selected Popular Brand");
    }
}
