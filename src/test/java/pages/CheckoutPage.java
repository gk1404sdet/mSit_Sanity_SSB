package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage{

    // ---------- Locators ----------
    public final By emptyBag = By.xpath("//p[contains(text(),'Your Bag Feels Too Light!')]");
    private final By continueButton = By.xpath("//p[contains(text(), 'continue')]");
    private final By cod = By.xpath("//div[contains(text(),'Cash On Delivery')]");
    public final By orderSuccess = By.xpath("//p[contains(text(),'Order Placed Successfully')]");
    private final By continueShopping = By.xpath("(//p[contains(text(),'Continue Shopping')])[1]");
    private final By upi = By.xpath("//div[contains(text(),'Pay by any UPI app')]");
    private final By editUpi = By.xpath("//input[@placeholder='Enter UPI ID Here']");
    private final By verify = By.xpath("//button[contains(text(),'VERIFY')]");

    private final By proceed = By.xpath("//button[contains(text(),'Proceed to Checkout')]");
    private final By placeOrder = By.xpath("//p[contains(text(),'Place Order')]");

    public CheckoutPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------- Common Actions ----------
    public void clickOnProceedToPay() {
        waitForVisibility(proceed);
        click(proceed);
    }
    public void clickOnContinueButton() {
        waitForVisibility(continueButton);
        safeClick(continueButton);
    }
    public void selectingTheCODOption() {
        scroll("DOWN");
        waitForVisibility(cod);
        safeClick(cod);
    }
    public void clickOnPlaceOrder() {
        waitForVisibility(placeOrder);
        safeClick(placeOrder);
    }
    public void clickOnContinueShopping() {
        waitForVisibility(continueShopping);
        safeClick(continueShopping);
    }
    public void selectingTheUPIOption() {
        waitForVisibility(upi);
        safeClick(upi);
    }
    public void enterTheUPIId(String upi) {
        waitForVisibility(editUpi);
        sendKeys(editUpi, upi);
    }
    public void clickOnVerify() {
        waitForVisibility(verify);
        safeClick(verify);
    }

}
