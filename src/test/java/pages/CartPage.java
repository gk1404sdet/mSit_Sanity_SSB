package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CartPage extends BasePage{


    // ---------- Locators ----------
    private final By cartIcon = By.xpath("//span[@class='MuiBadge-root css-1g48zis']");
    private final By bag = By.xpath("(//p[contains(text(),'Bag')])[2]");
    private final By address = By.xpath("(//p[contains(text(),'Address')])[1]");
    private final By payment = By.xpath("//p[contains(text(),'Payment')]");
    private final By bestCoupon = By.xpath("//p[contains(text(),'Best coupons for you')]");
    public final By increaseQuan = By.xpath("(//button[@type='button'])[3]");
    public final By decreaseQuan = By.xpath("(//button[@type='button'])[2]");
    private final By xMark = By.xpath("(//div[@class='MuiBox-root css-nbn3bo'])[1]");
    private final By moveToWishlist = By.xpath("//button[contains(text(),'move to wishlist')]");
    private final By removeBag = By.xpath("//button[contains(text(),'Remove')]");
    private final By remove = By.xpath("(//p[contains(text(),'Remove')])[2]");


    public CartPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------- Common Actions ----------
    public void adjustQuantity(int times, boolean increaseChoice, By element) throws InterruptedException {

        for (int i = 0; i < times; i++) {
            Thread.sleep(2000);
            click(element);
            System.out.println(increaseChoice ? "Quantity Increased" : "Quantity Decreased");
        }
    }

    public void clickOnCartIcon() {
        waitForVisibility(cartIcon);
        click(cartIcon);
    }

    public boolean validateCartComponentsByText(List<String> expectedSections) {
        waitForVisibility(bag);
        List<By> expectedComponents = Arrays.asList(
                bag,
                address,
                payment,
                bestCoupon
        );
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean allFound = true;
        for (By locator : expectedComponents) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (TimeoutException e) {
                allFound = false;
            }
        }
        return allFound;
    }

    public void clickOnProductToRemove() {
        waitForVisibility(xMark);
        safeClick(xMark);
    }

    public void clickOnMoveToWishlist() {
        waitForVisibility(moveToWishlist);
        click(moveToWishlist);
    }

    public void clickOnRemoveItem() {
        waitForVisibility(removeBag);
        click(removeBag);
    }

    public void clickOnRemove() {
        waitForVisibility(remove);
        click(remove);
    }

}
