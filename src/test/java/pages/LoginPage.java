package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {

    // ---------- Locators ----------
    private final By login = By.xpath("//button[contains(text(), 'Login')]");
    private final By userID = By.xpath("//input[@placeholder='Enter Your Mobile Number']");
    private final By continueButton = By.xpath("//button[contains(text(),'Continue')]");
    public final By invalidFormatMsg = By.xpath("//div[contains(text(), 'Please enter a valid number')]");
    private final By otp = By.xpath("//input[@type='tel']");
    public final By invalidOTP = By.xpath("//p[contains(text(), 'Please enter a valid OTP')]");
    public final By logout = By.xpath("//button[contains(text(), 'LOG OUT')]");
    private final By resendOTP = By.xpath("//p[contains(text(), 'Resend SMS')]");


    public final By maxOtpAttempts = By.xpath("//p[contains(text(),'Since you have exceeded the maximum OTP attempts')]");


    public LoginPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    // ---------- Common Actions ----------
    public void clickOnLoginButton() {
        waitForVisibility(login);
        clickOnElement(login);
    }


    public void clickOnLogout() {
        try {
            if (!isElementDisplayed(logout)) {
                clickElementWithScroll(logout);
            }
        } catch (Exception e) {}

    }

    public void enterUserID(String str) {
        isElementDisplayed(userID);
        sendKeys(userID, str);
    }

    public boolean clickOnTheContinueButton() {
        clickOnElement(continueButton);
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(invalidFormatMsg),
                    ExpectedConditions.visibilityOfElementLocated(maxOtpAttempts)
            ));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    public void enterOTP(String str) {
        clickOnElement(otp);
        sendKeys(otp, str);
    }

    public boolean clickOnTheVerifyOTP() {
        try {
            clickOnElement(continueButton);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(invalidOTP));
                return false;
            } catch (TimeoutException te) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clickOnResendOTP() {
        isElementDisplayed(resendOTP);
        clickOnElement(resendOTP);
    }

}