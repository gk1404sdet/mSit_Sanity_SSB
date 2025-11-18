package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPage extends BasePage{

    public CategoriesPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "CATEGORIES, Tab 3 of 5")
    private WebElement categories;

    @AndroidFindBy(accessibility = "Men")
    private WebElement men;

    @AndroidFindBy(accessibility = "Gifts")
    public WebElement gifts;

    @AndroidFindBy(accessibility = "Fragrance for Men")
    private WebElement fragranceForMen;

    @AndroidFindBy(accessibility = "Perfumes")
    private WebElement perfumes;

    @AndroidFindBy(accessibility = "House Warming Gift")
    private WebElement houseWarmingGift;

    @AndroidFindBy(accessibility = "Home Decor")
    private WebElement homeDecor;


    public void clickOnCategories() {
        click(categories);
    }

    public void clickOnMen() {
        click(men);
    }

    public void clickOnFragranceForMen() {
        click(fragranceForMen);
    }

    public void clickOnGifts() {
        click(gifts);
    }

    public void clickOnHouseWarmingGift() {
        click(houseWarmingGift);
    }

    public void clickOnHomeDecor() {
        click(homeDecor);
    }

    public void clickOnPerfumes() {
        click(perfumes);
    }

}
