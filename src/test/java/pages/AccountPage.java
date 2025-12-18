package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.swing.*;
import java.time.Duration;
import java.util.*;

public class AccountPage extends BasePage {


    // ---------- Locators ----------
    private final By account = By.xpath("//p[contains(text(), 'Account')]");
    private final By profile = By.xpath("//p[contains(text(),'Profile')]");
    private final By manageAddress = By.xpath("//p[contains(text(),'Manage Address')]");
    private final By savedPayments = By.xpath("//p[contains(text(),'Saved Payments')]");
    private final By helpAndSupport = By.xpath("//p[contains(text(),'Help & Support')]");
    private final By aboutUs = By.xpath("//div[contains(text(),'About Us')]");
    private final By termsOfUse = By.xpath("//div[contains(text(),'Terms Of Use')]");
    private final By privacyPolicy = By.xpath("//div[contains(text(),'Privacy Policy')]");
    private final By wishlist = By.xpath("//p[contains(text(),'Wishlist')]");
    private final By wallet = By.xpath("//p[contains(text(),'Wallet')]");
    private final By myOrders = By.xpath("//p[contains(text(),'My Orders')]");
    private final By firstConnect = By.xpath("//span[contains(text(),'First')]");
    private final By silver = By.xpath("//span[contains(text(),'Silver')]");
    private final By golden = By.xpath("//span[contains(text(),'Golden')]");
    private final By platinum = By.xpath("//span[contains(text(),'Platinum')]");
    private final By black = By.xpath("//span[contains(text(),'Black')]");
    private final By firstName = By.name("firstName");
    private final By lastName = By.name("lastName");
    private final By gender = By.xpath("//input[@type='radio']");
    private final By fName = By.xpath("//input[@placeholder='Enter First Name']");
    private final By lName = By.xpath("//input[@placeholder='Enter Last Name']");
    private final By updateChangesBtn = By.xpath("//p[contains(text(),'Update Changes')]");
    private final By newAddAddress = By.xpath("//p[contains(text(),'Add Address')]");
    private final By mobNo = By.xpath("//input[@placeholder='Enter Phone Number']");
    private final By pinCode = By.xpath("//input[@placeholder='Enter Pin Code']");
    private final By address = By.xpath("//textarea[@placeholder='Enter Address']");
    private final By addressType = By.xpath("//span[contains(text(),'Work')]");
    private final By addAdd = By.xpath("//p[contains(text(),'add address')]");
    private final By editOption = By.xpath("(//button[contains(text(),'Edit')])[2]");
    private final By updateAddress = By.xpath("//p[contains(text(),'update address')]");
    private final By deleteAddress = By.xpath("(//button[contains(text(),'Remove')])[2]");
    private final By confirmDelete = By.xpath("(//p[contains(text(),'Remove Address')])[3]");


    public AccountPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ---------- Common Actions ----------
    // ---------- Common Actions ----------

    public void clickOnAccountOption() {
        waitForVisibility(account);
        click(account);
    }

    public void clickOnMyProfile() {
        waitForVisibility(profile);
        click(profile);
    }

    public boolean validateAccountComponentsByText(List<String> expectedSections) {
        List<By> expectedComponents = Arrays.asList(
                profile,
                manageAddress,
                savedPayments,
                helpAndSupport,
                aboutUs,
                termsOfUse,
                privacyPolicy
        );
        if (!isElementDisplayed(privacyPolicy)) {
            scroll("DOWN");
        }
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

    public boolean validateQuickComponentsByText(List<String> expectedSections) {
        List<By> expectedComponents = Arrays.asList(
                wishlist,
                myOrders,
                wallet
        );
        if (!isElementDisplayed(wallet)) {
            scroll("UP");
        }
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

    public boolean validateFCCComponentsByText(List<String> expectedSections) {
        List<By> expectedComponents = Arrays.asList(
                firstConnect,
                silver,
                golden,
                platinum,
                black
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

    public String getEnteredName() {
        try {
            if (isElementDisplayed(firstName)) {
                return find(firstName).getAttribute("value");
            } else {
                return find(lastName).getAttribute("value");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void enterFirstName(String name) {
        waitForVisibility(firstName);
        click(firstName);
        sendKeys(firstName, name);
    }

    public void enterLastName(String name) {
        waitForVisibility(lastName);
        click(lastName);
        sendKeys(lastName, name);
    }

    public void clickOnGender() {
        driver.navigate().back();
        click(gender);
    }

    public void clickOnUpdateChanges() {
//        waitForVisibility(updateChangesBtn);
//        click(updateChangesBtn);
        driver.navigate().refresh();
    }

    public void clickOnManageAddresses() {
        driver.navigate().back();
        waitForVisibility(manageAddress);
        click(manageAddress);
    }

    public void clickOnAddAddress() {
        waitForVisibility(newAddAddress);
        click(newAddAddress);
    }

    public void enterNewFirstName(String name) {
        waitForVisibility(fName);
        click(fName);
        sendKeys(fName, name);
    }

    public void enterNewLastName(String name) {
        waitForVisibility(lName);
        click(lName);
        sendKeys(lName, name);
    }

    public void enterNewMobileNumber(String mob) {
        waitForVisibility(mobNo);
        click(mobNo);
        clear(mobNo);
        sendKeys(mobNo, mob);
    }

    public void enterPinCode(String pin) {
        waitForVisibility(pinCode);
        click(pinCode);
        sendKeys(pinCode, pin);
    }

    public void enterNewAddress(String add) {
        waitForVisibility(address);
        click(address);
        sendKeys(address, add);
    }

    public void clickOnAddressType() {
        driver.navigate().back();
        scroll("DOWN");
        waitForVisibility(addressType);
        click(addressType);
    }

    public void clickOnAddNewAddress() {
        waitForVisibility(addAdd);
        click(addAdd);
    }
    public void clickOnEdit() {
        waitForVisibility(editOption);
        click(editOption);
    }

    public void updateExistingFirstName(String name) {
        waitForVisibility(fName);
        click(fName);
        sendKeys(fName, name);
    }

    public void updateExistingLastName(String name) {
        waitForVisibility(lName);
        click(lName);
        sendKeys(lName, name);
    }

    public void updateExistingMobile(String name) {
        waitForVisibility(mobNo);
        click(mobNo);
        clear(mobNo);
        sendKeys(mobNo, name);
    }

    public void updateExistingPinCode(String pin) {
        waitForVisibility(pinCode);
        click(pinCode);
        sendKeys(pinCode, pin);
    }

    public void updateExistingAddress(String add) {
        waitForVisibility(address);
        click(address);
        sendKeys(address, add);
    }

    public void clickOnSaveChanges() {
        waitForVisibility(updateAddress);
        click(updateAddress);
    }

    public void clickOnRemove() {
        waitForVisibility(deleteAddress);
        click(deleteAddress);
    }

    public void clickOnConfirmRemoveButton() {
        waitForVisibility(confirmDelete);
        click(confirmDelete);
    }

}