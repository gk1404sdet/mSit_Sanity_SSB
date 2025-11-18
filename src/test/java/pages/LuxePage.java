package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LuxePage extends BasePage {

    public LuxePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "LUXE, Tab 4 of 5")
    private WebElement luxe;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(4)")
    private WebElement heroBanner;

    @AndroidFindBy(accessibility = "Sunglasses")
    public WebElement sunGlass;

    @AndroidFindBy(xpath = "(//android.view.View[@index='1'])[5]")
    private WebElement productLux;


    public void clickOnLuxe() {
        click(luxe);
    }

    public void clickOnHeroBanner() {
        click(heroBanner);
    }

    public void clickOnProductLux() {
        click(productLux);
    }

}
