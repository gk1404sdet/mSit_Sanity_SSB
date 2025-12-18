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
import pages.DiscoverPage;
import pages.LoginPage;
import utilities.ConfigLoader;
import utilities.CredsLoader;

import static hooks.Hooks.driver;

public class DiscoverStep {

    AccountPage accountPage;
    LoginPage loginPage;
    DiscoverPage discoverPage;
    CredsLoader credsLoader;
    ConfigLoader configLoader;
    Scenario scenario;

    public static String expectedText = " ";

    public DiscoverStep(TestContext context) {

        discoverPage = new DiscoverPage(context.driver);
        accountPage = new AccountPage(context.driver);
        loginPage = new LoginPage(context.driver);
        this.credsLoader = context.credsLoader;
        this.configLoader = context.configLoader;
        this.scenario = context.scenario;
    }

    @Given("User clicks on Discover in the footer")
    public void user_clicks_on_discover_in_the_footer() {

        discoverPage.clickOnDiscover();
    }
    @When("user selects a skin type")
    public void user_selects_a_skin_type() {

        discoverPage.clickOnSkin();
    }
    @When("user selects a cleansers")
    public void user_selects_a_cleansers() {

        discoverPage.clickOnCleansers();
    }

    @When("user validates that selected category page navigation")
    public void user_validates_that_selected_category_page_navigation() {

        scenario.log("User landed to Selected Category Page");
    }
    @Then("user selects a personal care")
    public void user_selects_a_personal_care() {

        discoverPage.clickOnPersonalCare();
    }
    @Then("user selects a hands and feet")
    public void user_selects_a_hands_and_feet() {

        discoverPage.clickOnHandsAndFeet();
    }

    // Brands
    @When("user clicks on the brands")
    public void user_clicks_on_the_brands() {

        discoverPage.clickOnBrands();
    }
    @When("user selects a brand from customer favorites")
    public void user_selects_a_brand_from_customer_favorites() {

        discoverPage.clickOnTrendingBrand();
    }
    @When("user validates that selected brands navigation")
    public void user_validates_that_selected_brands_navigation() {

        scenario.log("User landed to Selected Brand Category");
    }
    @Then("user selects a brands from the trending")
    public void user_selects_a_brands_from_the_trending() {

        discoverPage.clickOnSkinn();
    }
}
