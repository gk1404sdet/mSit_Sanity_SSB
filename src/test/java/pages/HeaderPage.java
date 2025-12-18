package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderPage extends BasePage {

    // ---------- Locators ----------
    private final By wishlist = By.xpath("(//div[@class='MuiBox-root css-1dy8gno'])[1]");
    private final By searchEditBox = By.xpath("//input[@placeholder='Search']");
    private final By clickSearch = By.xpath("//input[@placeholder='Search by products, brands & more']");

    public final By redLip = By.xpath("//p[contains(text(),'red Lipstick')]");
    public final By trendingSearch = By.xpath("(//img[@alt='brand image'])[2]");

    public HeaderPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------- Common Actions ----------
    public void clickOnWishlist() {
        waitForVisibility(wishlist);
        click(wishlist);
    }
    public void clicksOnTheSearEditBox() {
        wait.until(ExpectedConditions.elementToBeClickable(searchEditBox)).click();
    }
    public void enterKeyword(String text) {
        click(clickSearch);
        sendKeys(clickSearch, text);
    }

    public void clickOnTrendProduct() {
        click(trendingSearch);
    }


}
