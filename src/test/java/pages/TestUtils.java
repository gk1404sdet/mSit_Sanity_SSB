package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


public class TestUtils {
    protected static AppiumDriver driver;
    protected static WebDriverWait wait;

    public TestUtils(AppiumDriver driver) {
        TestUtils.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public static String isElementPresentWithText(String elementText) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.view.View[@content-desc='/" + elementText + "/']")

            ));
            return element.getAttribute("content-desc");
        } catch (Exception e) {
            System.out.println("Element with content-desc " + elementText + " not found.");
            return "";
        }
    }

    public static String getInputFieldText(WebElement inputField) {
        try {
            if (inputField != null) {
                String text = inputField.getAttribute("text");
                return text != null ? text.trim() : "";
            } else {
                System.out.println("Input field element is null.");
                return "";
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching the input field text: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    public static void scroll(String direction) {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = screenWidth / 2;
        int startY = 0;
        int endX = 0, endY = 0;

        switch (direction) {
            case "DOWN":
                startY = (int) (screenHeight * 0.8); // Start at 80% of the screen height
                endY = (int) (screenHeight * 0.2);   // End at 20% of the screen height
                break;
            case "UP":
                startY = (int) (screenHeight * 0.2); // Start at 20% of the screen height
                endY = (int) (screenHeight * 0.8);   // End at 80% of the screen height
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        // Perform the scroll action using the helper method
        performSwipe(startX, startY, startX, endY);
    }

    public static void swipe(String direction) {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = 0, startY = 0, endX = 0, endY = 0;

        switch (direction) {
            case "UP":
                startX = screenWidth / 2;
                startY = (int) (screenHeight * 0.8); // Start at 80% of the screen height
                endY = (int) (screenHeight * 0.2);   // End at 20% of the screen height
                break;
            case "DOWN":
                startX = screenWidth / 2;
                startY = (int) (screenHeight * 0.2); // Start at 20% of the screen height
                endY = (int) (screenHeight * 0.8);   // End at 80% of the screen height
                break;
            case "LEFT":
                startX = (int) (screenWidth * 0.8); // Start at 80% of the screen width
                startY = screenHeight / 2;          // Start in the middle of the screen height
                endX = (int) (screenWidth * 0.2);   // End at 20% of the screen width
                break;
            case "RIGHT":
                startX = (int) (screenWidth * 0.2); // Start at 20% of the screen width
                startY = screenHeight / 2;          // Start in the middle of the screen height
                endX = (int) (screenWidth * 0.8);   // End at 80% of the screen width
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        // Perform the swipe action using the helper method
        performSwipe(startX, startY, endX, endY);
    }

    // Helper Method to Perform Swipe Gesture
    private static void performSwipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public static void captureScreenshot(String screenshotName) throws IOException {
        if (driver instanceof TakesScreenshot) {

            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";

            File destinationPath = new File("src/test/resources/ScreenShot/" + fileName);
            FileUtils.copyFile(sourcePath, destinationPath);

            System.out.println("Screenshot saved at: " + destinationPath.getAbsolutePath());
        }
    }

    public static void adjustQuantity(int times, boolean increaseChoice) throws InterruptedException {
        String increaseQuantity = "new UiSelector().className(\"android.widget.ImageView\").instance(5)";
        String decreaseQuantity = "new UiSelector().className(\"android.widget.ImageView\").instance(4)";

        WebElement increase = driver.findElement(AppiumBy.androidUIAutomator(increaseQuantity));
        WebElement decrease = driver.findElement(AppiumBy.androidUIAutomator(decreaseQuantity));

        for (int i = 0; i < times; i++) {
            if (increaseChoice) {
                // If the user chooses to increase
                Thread.sleep(1000);
                increase.click();
                System.out.println("Quantity Increased");
            } else {
                // If the user chooses to decrease
                Thread.sleep(1000);
                decrease.click();
                System.out.println("Quantity Decreased");
            }
        }
    }

    public static void clickElementWithScroll(WebElement element) {
        boolean found = false;
        while (!found) {
            try {
                element.click();// Attempt to click the element
                found = true;    // If successful, set found to true
            } catch (Exception e) {
                // Scroll down and continue searching if element is not found
                scroll("DOWN");
            }
        }
    }

    public static void tapElement(WebElement element) {

        int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
        int centerY = element.getRect().getY() + (element.getRect().getHeight() / 2);

        performTap(centerX, centerY);
    }

    private static void performTap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 0);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    public static boolean isElementPresent(By locatorKey) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        try {
            driver.findElement(locatorKey);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            return false;
        }
    }


    public static void waitForOverlayToDisappear() {
        try {
            By overlayLocator = By.cssSelector("div[class*='inset-0'][class*='bg-neutral']");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
        } catch (Exception e) {}
    }

    public static void validateErrorMessageByPartialText(String partialText, String expectedText) {
        try {
            By errorLocator = By.xpath("//*[contains(text(),'" + partialText + "')]");
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
            String actualText = errorElement.getText().trim();

            System.out.println("Actual: " + actualText);
            Assert.assertEquals(actualText, expectedText, "Toast message mismatch!");
            System.out.println("Toasted Message is Matched: " + expectedText);
        } catch (Exception e) {
            Assert.fail("Failed to validate error message for partial text: '" + partialText + "' - " + e.getMessage());
        }
    }
}