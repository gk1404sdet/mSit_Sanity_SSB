package scenarios.stepDefinitions;

import context.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AccountPage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.ConfigLoader;
import utilities.CredsLoader;

import static hooks.Hooks.driver;

public class HomeStep {

    AccountPage accountPage;
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    CredsLoader credsLoader;
    ConfigLoader configLoader;
    Scenario scenario;

    public HomeStep(TestContext context) {

        loginPage = new LoginPage(context.driver);
        accountPage = new AccountPage(context.driver);
        homePage = new HomePage(context.driver);
        cartPage = new CartPage(context.driver);
        this.credsLoader = context.credsLoader;
        this.configLoader = context.configLoader;
        this.scenario = context.scenario;
    }
    // Home Page
    @When("user clicks the sub category eyes from makeup page")
    public void User_clicks_the_sub_category_eyes_from_makeup_page() {

        homePage.clickOnEyes();
    }
    @When("user selects a product from eyes makeup plp")
    public void user_selects_a_product_from_eyes_makeup_plp() throws InterruptedException {

        homePage.clickOnTheProduct(1);
    }

    // PLP
    @Given("User adds a product to the wishlist from PLP")
    public void user_adds_a_product_to_the_wishlist_from_plp() {

        homePage.clickOnAddWishlist();
    }
    @Given("User validate that product added to wishlist")
    public void user_validate_that_product_added_to_wishlist() {

        scenario.log("Product is added to the wishlist");
    }
    @Given("User remove a product to the wishlist from PLP")
    public void user_remove_a_product_to_the_wishlist_from_plp() {

        homePage.clickOnAddWishlist();
    }
    @Then("user removes a product to the wishlist")
    public void user_removes_a_product_to_the_wishlist() {

        cartPage.clickOnRemoveItem();
    }
    @Then("User validate that product removed from wishlist")
    public void user_validate_that_product_removed_from_wishlist() {

        scenario.log("Removed from your wishlist");
    }

    // Sort
    @Then("User clicks on the sort option")
    public void user_clicks_on_the_sort_option() {

        homePage.clickOnSort();
    }
    @Then("User selects a sort option as price low to high")
    public void user_selects_a_sort_option_as_price_low_to_high() {

        homePage.clickOnPriceLowToHigh();
    }
    @Then("User selects a sort option as price high to low")
    public void user_selects_a_sort_option_as_price_high_to_low() {

        homePage.clickOnPriceHighToLow();
    }
    @Then("User validate that product sorted product is displayed")
    public void user_validate_that_product_sorted_product_is_displayed() {

        Assert.assertTrue(true, "User Sort successfully");
        scenario.log("Sort Applied Successfully");
    }

    // Filter
    @Then("User clicks on the filter option")
    public void user_clicks_on_the_filter_option() {

        homePage.clickOnFilter();
    }
    @Then("User select the category as makeup")
    public void user_select_the_category_as_makeup() {

        homePage.clickOnCategoryFilter();
    }
    @Then("User selects the brands as bobbi")
    public void user_selects_the_brands_as_bobbi() {

        homePage.clickOnBrandsFiler();
    }
    @Then("User clicks on the apply filter")
    public void user_clicks_on_the_apply_filter() {

        homePage.clickOnApplyFilter();
    }
    @Then("User validate that products are displayed as per filter")
    public void user_validate_that_products_are_displayed_as_per_filter() {

        Assert.assertTrue(true, "User Applied Filter successfully");
        scenario.log("User has Applied Filter successfully");
    }
    @Then("user validates that filter has removed")
    public void user_validates_that_filter_has_removed() {

        Assert.assertTrue(true, "User Removed Filter successfully");
        scenario.log("User has Removed Filter successfully");
    }

    // Invalid Postal code Check
    @When("user clicks on the change option")
    public void user_clicks_on_the_change_option() {

        homePage.switchToNewWindow();
    }
    @When("user enters an invalid postal code for delivery")
    public void user_enters_an_invalid_postal_code_for_delivery() {

        String pin = configLoader.getProperty("postal.invalidPin");
        homePage.enterPostalCode(pin);
        scenario.log("User Entered the Postal Code: " + pin);
    }
    @When("user clicks on the check option")
    public void user_clicks_on_the_check_option() {

        homePage.clickOnCheck();
    }
    @When("user sees a message indicating to enter a valid postal code")
    public void user_sees_a_message_indicating_to_enter_a_valid_postal_code() {

        homePage.validateErrorMessageByPartialText("Invalid Pincode, Please enter 6 Digits Pincode", "Invalid Pincode, Please enter 6 Digits Pincode");

    }

    //Valid Postal code
    @When("user enters an valid postal code for delivery")
    public void user_enters_an_valid_postal_code_for_delivery() {

        String pin = configLoader.getProperty("postal.validPin");
        homePage.enterPostalCode(pin);
        scenario.log("User Entered the Postal Code: " + pin);
    }
}