package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    // ---------- Locators ----------
    private final By home = By.xpath("//p[contains(text(),'Home')]");
    private final By makeUp = By.xpath("(//p[contains(text(),'Makeup')])[1]");
    private final By fragrance = By.xpath("(//p[contains(text(),'Fragrance')])[1]");
    private final By face = By.xpath("//p[contains(text(),'Face')]");
    private final By eyes = By.xpath("//p[contains(text(),'Eyes')]");
    private final By women = By.xpath("//p[contains(text(),'Women')]");
    private final By lips = By.xpath("//p[contains(text(),'Lips')]");
    public final By productList = By.xpath("//div[@class='MuiBox-root css-1mxrzyz']");
    private final By addToBag = By.xpath("//p[contains(text(),'Add To Bag')]");
    private final By shade = By.xpath("//button[contains(text(),'Select Shade')]");
    private final By shadePopup = By.xpath("(//div[@class='MuiBox-root css-130eto8'])[2]");
    private final By addWishlist = By.xpath("(//div[@class='MuiGrid-root css-136489g'])[2]");
    private final By sort = By.xpath("//p[contains(text(),'Sort By')]");
    private final By lowToHigh = By.xpath("//p[contains(text(),'Price: Low to High')]");
    private final By highToLow = By.xpath("//p[contains(text(),'Price: High to Low')]");
    private final By filter = By.xpath("//p[contains(text(),'Filter')]");
    private final By categoryFilter = By.xpath("(//button[contains(text(),'Category')])[1]");
    private final By makeUpFilter = By.xpath("(//h6[contains(text(),'Makeup')])[1]");
    private final By brandsFilter = By.xpath("(//button[contains(text(),'Brand')])[1]");
    private final By bobbi = By.xpath("(//h6[contains(text(),'Bobbi Brown')])[1]");
    private final By apply = By.xpath("//button[contains(text(),'Apply')]");
    private final By pin = By.xpath("//input[@placeholder='Enter PIN code']");
    private final By change = By.xpath("//button[contains(text(),'CHANGE')]");


    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------- Common Actions ----------
    public void backNavigation(int maxAttampt) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (int i = 0; i < maxAttampt; i++) {
            driver.navigate().back();
        }
    }

    public void clickOnHomeButton() {
        waitForVisibility(home);
        click(home);
    }

    public void clickOnMakeUp() {
        waitForVisibility(makeUp);
        click(makeUp);
    }
    public void clickOnFragrance() {
        waitForVisibility(fragrance);
        click(fragrance);
    }
    public void clickOnLips() {
        waitForVisibility(lips);
        click(lips);
    }
    public void clickOnFace() {
        waitForVisibility(face);
        click(face);
    }
    public void clickOnWomen() {
        waitForVisibility(women);
        click(women);
    }
    public void clickOnEyes() {
        waitForVisibility(eyes);
        click(eyes);
    }
    public void clickOnTheProduct(Integer index) {
        waitForVisibility(productList);
        List<WebElement> products = driver.findElements(productList);

        if (products.isEmpty()) {
            throw new NoSuchElementException("No ProductSCCard elements found");
        }
        if (index < 0 || index >= products.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid index: " + index + ". Total products: " + products.size()
            );
        }
        products.get(index).click();
    }

    public void clickOnAddToBag() {
        waitForVisibility(addToBag);
        click(addToBag);
    }

    public void clickOnAddWishlist() {
        waitForVisibility(addWishlist);
        click(addWishlist);
    }

    public void validateShadeOrBag() {
        try {

            if (isElementDisplayed(shade)) {
                safeClick(shade);
                isElementDisplayed(shadePopup);
                safeClick(shadePopup);
            }
            if (isElementDisplayed(addToBag)) {
                safeClick(addToBag);
            }
        } catch (Exception e) {}
    }

    public void clickOnSort() {
        waitForVisibility(sort);
        click(sort);
    }

    public void clickOnFilter() {
        waitForVisibility(filter);
        click(filter);
    }

    public void clickOnPriceLowToHigh() {
        waitForVisibility(lowToHigh);
        click(lowToHigh);
    }

    public void clickOnPriceHighToLow() {
        waitForVisibility(highToLow);
        click(highToLow);
    }

    public void clickOnCategoryFilter() {
        waitForVisibility(categoryFilter);
        click(categoryFilter);
        waitForVisibility(makeUpFilter);
        click(makeUpFilter);
    }

    public void clickOnBrandsFiler() {
        waitForVisibility(brandsFilter);
        click(brandsFilter);
        waitForVisibility(bobbi);
        click(bobbi);
    }

    public void clickOnApplyFilter() {
        waitForVisibility(apply);
        click(apply);
    }

    public void enterPostalCode(String str) {
        scroll("DOWN");
        waitForVisibility(pin);
        sendKeys(pin, str);
    }
    public void clickOnCheck() {
        waitForVisibility(change);
        safeClick(change);
    }

}