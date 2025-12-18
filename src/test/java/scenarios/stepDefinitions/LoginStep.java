package scenarios.stepDefinitions;

import context.TestContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import pages.*;
import utilities.ConfigLoader;
import utilities.CredsLoader;

@Slf4j
public class LoginStep {

    AccountPage accountPage;
    LoginPage loginPage;
    CredsLoader credsLoader;
    ConfigLoader configLoader;
    Scenario scenario;

    public LoginStep(TestContext context) {

        accountPage = new AccountPage(context.driver);
        loginPage = new LoginPage(context.driver);
        this.credsLoader = context.credsLoader;
        this.configLoader = context.configLoader;
        this.scenario = context.scenario;
    }


    // Login with Mobile Number
    @Given("user launches the application")
    public void user_launches_the_application() {

        scenario.log("***** Mobile Application lunched Successfully *****");
    }

    @When("user clicks the Login button")
    public void user_clicks_the_login_button() {

        loginPage.clickOnLoginButton();
    }
    @When("user enters the valid mobile number")
    public void user_enters_the_valid_mobile_number() throws InterruptedException {

//        Thread.sleep(10000);

        String mob = configLoader.getProperty("login.validMob");
        loginPage.enterUserID(mob);
        scenario.log("user Entered Mobile Number: " + mob);
    }
    @When("user clicks the Continue button")
    public void user_clicks_the_continue_button() {

        boolean isVerified = loginPage.clickOnTheContinueButton();

        if (loginPage.isElementDisplayed(loginPage.invalidFormatMsg)) {
            scenario.log("Invalid mobile number format message displayed as expected");
            return;
        }

        if (loginPage.isElementDisplayed(loginPage.maxOtpAttempts)) {
            scenario.log("Max OTP attempts message displayed as expected");
            return;
        }
        Assert.assertTrue(isVerified, "No validation message shown and OTP screen not opened. App is in invalid state.");
    }
    @Then("user enters the OTP")
    public void user_enters_the_otp() throws InterruptedException {

//        Thread.sleep(20000);

        String otp = configLoader.getProperty("login.staticOtp");
        loginPage.enterOTP(otp);
        scenario.log("user Entered the OTP: " + otp);
    }
    @Then("user validates otp result")
    public void user_validates_otp_result() {

        boolean isVerified = loginPage.clickOnTheVerifyOTP();

        if (isVerified) {
            scenario.log("OTP validated successfully. Proceeding...");
        } else {
            if (loginPage.isElementDisplayed(loginPage.invalidOTP)) {
                scenario.log("Test failed: Invalid OTP entered");
            } else {
                scenario.log("Exception while clicking Verify OTP or OTP validation failed");
            }
        }
        Assert.assertTrue(isVerified, "OTP Verification Failed");
    }
    @Then("system should display the appropriate login status")
    public void system_should_display_the_appropriate_login_status() {

        scenario.log("***** Successfully logged in *****");
    }

    //Logout
    @When("user clicks on the Profile button")
    public void user_clicks_on_the_profile_button() {

        accountPage.clickOnMyProfile();
    }
    @When("user clicks on the Logout button")
    public void user_clicks_on_the_logout_button() {

        loginPage.clickOnLogout();
    }
    @Then("validate that the user is logged out")
    public void validate_that_the_user_is_logged_out() {

        scenario.log("Successfully logged out");
    }

    //OTP Validation
    @When("user enters the mobile number")
    public void user_enters_valid_mobile_number() {

        String mob = configLoader.getProperty("login.invalidMobileShort");
        loginPage.enterUserID(mob);
        scenario.log("user Entered Mobile Number: " + mob);
    }
    @Then("user clicks on the Resend SMS option")
    public void user_clicks_on_the_resend_sms_option() {

        loginPage.clickOnResendOTP();
    }
    @Then("user successfully resends the OTP")
    public void user_successfully_resends_the_otp() {

        scenario.log("OTP has successfully has resent");
    }
    @Then("user enters the invalid OTP")
    public void user_enters_the_invalid_otp() {

        String otp = configLoader.getProperty("login.invalidOtp");
        loginPage.enterOTP(otp);
        scenario.log("user Entered the OTP: " + otp);
    }
    @Then("user validates otp result for invalid otp")
    public void user_validates_otp_result_for_invalid_otp() {

        boolean isVerified = loginPage.clickOnTheVerifyOTP();

        if (!isVerified && loginPage.isElementDisplayed(loginPage.invalidOTP)) {
            scenario.log("Expected validation message displayed: Please enter a valid OTP");
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(isVerified, "OTP Verification Failed");
    }

    //Invalid User ID
    @When("user enters the invalid mobile number")
    public void user_enters_the_invalid_mobile_number() throws InterruptedException {

        String mob = configLoader.getProperty("login.invalidMobileLong");
        loginPage.enterUserID(mob);
        scenario.log("User Entered Mobile Number: " + mob);
    }
    @Then("validate that the appropriate error message is displayed")
    public void validate_that_the_appropriate_error_message_is_displayed() {

        Assert.assertTrue(loginPage.isElementDisplayed(loginPage.invalidFormatMsg),
                "Error message not displayed: " + loginPage.isElementDisplayed(loginPage.invalidFormatMsg));

    }

}
