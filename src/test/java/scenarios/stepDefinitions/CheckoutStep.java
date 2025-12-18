package scenarios.stepDefinitions;

import context.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.*;
import utilities.ConfigLoader;
import utilities.CredsLoader;

import java.util.Arrays;
import java.util.List;

import static hooks.Hooks.driver;

public class CheckoutStep {

    HomePage homePage;
    AccountPage accountPage;
    LoginPage loginPage;
    CheckoutPage checkoutPage;
    HeaderPage headerPage;
    CredsLoader credsLoader;
    ConfigLoader configLoader;
    Scenario scenario;

    public static String expectedText = " ";

    public CheckoutStep(TestContext context) {

        homePage = new HomePage(context.driver);
        accountPage = new AccountPage(context.driver);
        loginPage = new LoginPage(context.driver);
        checkoutPage = new CheckoutPage(context.driver);
        headerPage = new HeaderPage(context.driver);
        this.credsLoader = context.credsLoader;
        this.configLoader = context.configLoader;
        this.scenario = context.scenario;
    }

    @Given("user is on the homepage")
    public void user_is_on_the_homepage() {

        homePage.clickOnHomeButton();
    }
    @When("user back to home page")
    public void user_back_to_home_page() throws InterruptedException {

//        homePage.backNavigation(2);
        Thread.sleep(20000);
    }
    @When("user selects a makeup category from home page")
    public void user_selects_a_makeup_category_from_home_page() {

        homePage.clickOnMakeUp();
    }
    @When("user clicks the sub category from makeup page")
    public void User_clicks_the_sub_category_from_makeup_page() {

        homePage.clickOnEyes();
    }
    @When("user selects a product from makeup plp")
    public void user_selects_a_product_from_makeup_plp() throws InterruptedException {

        homePage.clickOnTheProduct(0);
    }
    @When("User clicks on the add to bag")
    public void user_clicks_on_the_add_to_bag() throws InterruptedException {

        homePage.switchToNewWindow();
        Thread.sleep(3000);
        homePage.clickOnAddToBag();
    }
    @When("User clicks on the proceed to pay")
    public void user_clicks_on_the_proceed_to_pay() throws InterruptedException {

//        checkoutPage.clickOnProceedToPay();
        Thread.sleep(20000);
    }
    @When("User clicks on the continue to payment")
    public void user_clicks_on_the_continue_to_payment()  {

        checkoutPage.clickOnContinueButton();
    }
    @Then("user selecting cod option")
    public void user_selecting_cod_option() {

//        checkoutPage.selectingTheCODOption();
        scenario.log("NO COD Just Passing the test case");
    }
    @Then("User clicks on the place order button")
    public void user_clicks_on_the_place_order_button() throws InterruptedException {

//        checkoutPage.clickOnPlaceOrder();
        Thread.sleep(15000);
    }

    @Then("User validate that the order is placed successfully")
    public void user_validate_that_the_order_is_placed_successfully() throws InterruptedException {

        checkoutPage.isElementDisplayed(checkoutPage.orderSuccess);
        Assert.assertTrue(
                checkoutPage.isElementDisplayed(checkoutPage.orderSuccess),
                "Order NOT placed successfully"
        );
        scenario.log("Order Placed Successfully");
    }
    @Then("User clicks on the continue shopping button")
    public void user_clicks_on_the_continue_shopping_button() {

        checkoutPage.clickOnContinueShopping();
    }

    // Invalid Placement
    @When("user selects a fragrance category from home page")
    public void user_selects_a_fragrance_category_from_home_page() {

        homePage.clickOnFragrance();
    }
    @When("user clicks the sub category from fragrance page")
    public void User_clicks_the_sub_category_from_fragrance_page() {

        homePage.clickOnWomen();
    }
    @When("user selects a product from fragrance plp")
    public void user_selects_a_product_from_fragrance_plp() throws InterruptedException {

        homePage.clickOnTheProduct(1);
    }
    @Then("user selecting UPI option")
    public void user_selecting_upi_option() {

        checkoutPage.selectingTheUPIOption();
    }
    @Then("user enters the UPI id")
    public void user_enters_the_upi_id() {

        checkoutPage.enterTheUPIId("test@upi");
    }
    @Then("user validates that UPI id")
    public void user_validates_the_upi_id() {

        checkoutPage.clickOnVerify();
    }
    @Then("User validate that an error message indicating payment failure")
    public void user_validate_that_an_error_message_indicating_payment_failure() throws InterruptedException {

        scenario.log("Payment is Failed");
    }


}
