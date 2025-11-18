package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends BasePage {

    public CatalogPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(accessibility = "WISHLIST")
    private WebElement wishlist;

    @AndroidFindBy(accessibility = "ADD TO BAG")
    public WebElement addToBag;

    @AndroidFindBy(accessibility = "VIEW BAG")
    public WebElement viewBag;

    @AndroidFindBy(accessibility = "Dismiss")
    public WebElement dismiss;

    @AndroidFindBy(xpath = "(//android.view.View[@index='0'])[6]")
    public WebElement back;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(22)")
    private WebElement removeCart;

    @AndroidFindBy(accessibility = "MOVE TO WISHLIST")
    private WebElement moveToWishlist;

    @AndroidFindBy(accessibility = "REMOVE")
    private WebElement removeToBag;

    @AndroidFindBy(xpath = "(//android.view.View[@index='1'])[3]")
    private WebElement productWL;

    @AndroidFindBy(accessibility = "PRODUCT DETAILS")
    public WebElement proDetails;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[1]")
    private WebElement pdpBack;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@index='0'])[1]")
    private WebElement pdpBackPDP;

    @AndroidFindBy(xpath = "//android.widget.CheckBox")
    public WebElement giftWrap;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='0']")
    private WebElement giftName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='2']")
    private WebElement giftMessage;

    @AndroidFindBy(xpath = "//android.widget.EditText[@index='4']")
    private WebElement giftSenderName;

    @AndroidFindBy(accessibility = "SAVE GIFT DETAILS")
    private WebElement saveGift;

    @AndroidFindBy(accessibility = "SUGGESTED")
    public WebElement suggest;

    @AndroidFindBy(accessibility = "Allen Solly")
    public WebElement allen;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(3)")
    private WebElement pinCode;

    @AndroidFindBy(accessibility = "Check")
    private WebElement checkPin;

    @AndroidFindBy(accessibility = "Please enter a valid pincode")
    public WebElement invalidMessage;



    public void clickOnWishlist() {
        click(wishlist);
    }

    public void clickOnAddToBag() {
        click(addToBag);
    }

    public void clickOnViewBag() {
        click(viewBag);
    }

    public void clickOnDismiss() {
        click(dismiss);
    }

    public void clickOnProductToRemove() {
        click(removeCart);
    }

    public void clickOnMoveToWishlist() {
        click(moveToWishlist);
    }

    public void clickOnRemoveToBag() {
        click(removeToBag);
    }

    public void clickOnBackButton() {

        try {
            Thread.sleep(1000);
            boolean isElementPresent = isElementDisplayed(back);
            if (isElementPresent) {
                // If the element is present, click the back button again
                click(back);
            }
        } catch (Exception e) {}

    }

    public void clickAddToBagForNthProduct(int index) {

        WebElement addToBagButton = driver.findElement(By.xpath("(//android.widget.Button[@content-desc=\"Add To Bag\"])[" + index + "]"));
        click(addToBagButton);
    }

    public void selectProduct() {
        click(productWL);
    }

    public void clickOnPDPBackButton() throws InterruptedException {
        Thread.sleep(2000);
        click(pdpBack);
    }

    public void clickOnGiftWrap() {
        TestUtils.clickElementWithScroll(giftWrap);
    }

//    public void enterGiftReceiverName(String text) {
//        click(giftName);
//        sendKeys(giftName, text);
//    }
//
//    public void enterGiftMessage(String text) {
//        click(giftMessage);
//        sendKeys(giftMessage, text);
//    }
//
//    public void enterGiftSenderName(String text) {
//        click(giftSenderName);
//        sendKeys(giftSenderName, text);
//    }

    public void clickOnSaveGiftDetails() {
        click(saveGift);
    }

    public void clickOnBackPDP() {
        click(pdpBackPDP);
    }

//    public void enterPinCde(String pin) {
//        click(pinCode);
//        sendKeys(pinCode, pin);
//    }

    public void clickOnCheck() {
        click(checkPin);
    }

}