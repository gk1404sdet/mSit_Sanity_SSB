package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DiscoverPage extends BasePage {

    // ---------- Locators ----------
    private final By discover = By.xpath("//p[contains(text(),'Discover')]");
    private final By skin = By.xpath("//span[contains(text(),'Skin')]");
    private final By cleansers = By.xpath("//span[contains(text(),'Cleansers & Exfoliators')]");
    private final By personalCare = By.xpath("//span[contains(text(),'Personal Care')]");
    private final By handsAndFeet = By.xpath("//span[contains(text(),'Hands & Feet')]");
    private final By brands = By.xpath("//button[contains(text(),'Brands')]");
    private final By blueBerry = By.xpath("(//img[@alt='Brand Logo'])[3]");
    private final By skinn = By.xpath("(//img[@alt='Brand Logo'])[8]");


    public DiscoverPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------- Common Actions ----------
    public void clickOnDiscover() {
        waitForVisibility(discover);
        click(discover);
    }

    public void clickOnSkin() {
        waitForVisibility(skin);
        click(skin);
    }

    public void clickOnCleansers() {
        waitForVisibility(cleansers);
        click(cleansers);
    }

    public void clickOnPersonalCare() {
        waitForVisibility(personalCare);
        click(personalCare);
    }

    public void clickOnHandsAndFeet() {
        waitForVisibility(handsAndFeet);
        click(handsAndFeet);
    }

    public void clickOnBrands() {
        waitForVisibility(brands);
        click(brands);
    }

    public void clickOnTrendingBrand() {
        waitForVisibility(blueBerry);
        click(blueBerry);
    }

    public void clickOnSkinn() {
        waitForVisibility(skinn);
        click(skinn);
    }
}