package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class LoginPage extends BasePage {

    public final JavascriptExecutor js = (JavascriptExecutor) driver;

    @AndroidFindBy(accessibility = "LOGIN")
    public WebElement loginButton;

    private final By login = By.xpath("//p[contains(text(),'LOGIN')]");
    public final By userID = By.id("Enter your phone number");
    private final By proceedBtn = By.xpath("//p[contains(text(), 'PROCEED')]");
    private final By verifyOTPBtn = By.xpath("//p[contains(text(), 'VERIFY OTP')]");
    private final By hello = By.xpath("//div[contains(text(), 'Hello, ')]");
    private final By logout = By.xpath("//p[contains(text(), 'LOGOUT')]");
    private final By yesLogout = By.xpath("//p[contains(text(), 'YES,LOG OUT')]");
    private final By resendOTP = By.xpath("//div[contains(text(), 'Resend OTP')]");
    public final By invalidFormatMsg = By.xpath("//div[contains(text(),'Invalid email or phone number format')]");
    public final By uidNotFound = By.xpath("//div[contains(text(),'Cannot find user with uid')]");
    public final By maxOtpAttempts = By.xpath("//div[contains(text(),'Since you have exceeded the maximum OTP attempts')]");
    public final By otpField = By.xpath("//div[contains(text(),'Please enter a valid OTP')]");
    private final By xmark = By.xpath("//img[@alt=\"sheet_component_img\"]");
    private final By iFrame = By.name("HyperServices");


    public LoginPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void clickOnXmark() {
        click(xmark);
    }

    public void clickOnLogin() {
        waitForOverlayToDisappear();
        click(login);
//        js.executeScript("arguments[0].click();", login);
    }

    public void enterUserID(String user) {
        waitForVisibility(userID);
        click(userID);
        clear(userID);
        sendKeys(userID, user);
    }

    public boolean clickOnTheProceedButton() {
        if (isElementPresent(invalidFormatMsg)) {
            return false;
        }
        try {
            click(proceedBtn);
        } catch (Exception e) {
            return false;
        }
        if (isElementPresent(uidNotFound)) {
            return false;
        }
        if (isElementPresent(maxOtpAttempts)) {
            return false;
        }
        return true;
    }

    public void enterOTP(String otp) {

        for (int i = 0; i < otp.length(); i++) {
            char digit = otp.charAt(i);
            String xpath = "//input[@aria-label='Please enter OTP character " + (i + 1) + "']";
            WebElement otpInput = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            otpInput.click();
            otpInput.sendKeys(String.valueOf(digit));
        }
    }

    public boolean clickOnTheVerifyOTPButton() {
        try {
            click(verifyOTPBtn);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(otpField));
                return false;
            } catch (TimeoutException te) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        try{
            waitForOverlayToDisappear();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement welCome = wait.until(ExpectedConditions.visibilityOfElementLocated(hello));
            return welCome.isDisplayed();
        } catch (TimeoutException te) {
            return false;
        }
    }

    public void clickOnLogout() {
        try {
            waitForOverlayToDisappear();
            clickElementWithScroll(logout);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stableClick(By locator) {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                el.click();
                return;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Could not click element: " + locator);
    }
    public void clickOnYesButton() {
     stableClick(yesLogout);
    }




    public void clickOnResendOTP() {
        click(resendOTP);
    }
}