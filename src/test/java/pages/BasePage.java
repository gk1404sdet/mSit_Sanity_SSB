package pages;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
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
import java.util.NoSuchElementException;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForStaleness(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public  WebElement waitForVisibility(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return null;
    }

    public boolean elementExistOrNot(By element) {
        try {
            if (driver.findElements(element).size() > 0)
                return true;
        } catch (Exception err) {
            return false;
        }
        return false;
    }

    public void clear(By element) {
        WebElement ele = (WebElement) driver.findElement(element);
        waitForVisibility(ele);
        ele.clear();
    }

    public void clear(WebElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public void sendKeys(By element, String txt) {
        waitForVisibility(element);
        WebElement ele = driver.findElement(element);
        clear(element);
        ele.sendKeys(txt);
    }

    public void sendKeys(WebElement element, String txt) {
        waitForVisibility(element);
        clear(element);
        element.sendKeys(txt);
    }

    public void click(By element) {
        waitForVisibility(element);
        driver.findElement(element).click();
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public String getAttribute(By element, String attribute) {
        WebElement ele = (WebElement) driver.findElement(element);
        waitForVisibility(ele);
        return ele.getAttribute(attribute);
    }

    public String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public void swipeScreenUp() {
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, (dims.height / 2) - 100);
        try {
            new TouchAction((PerformsTouchActions) driver).press(pointOptionStart).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                    .moveTo(pointOptionEnd).release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
    }

    public void clickOnTextButton(String button) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + button + "')]")));
        click(By.xpath("//*[contains(text(),'" + button + "')]"));
    }

    public void swipeScreen(Direction dir) {
        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;
        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN:
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP:
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        try {
            new TouchAction((PerformsTouchActions) driver).press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
                    .perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public void clickOnDeviceBackButton() {
        driver.navigate().back();
    }

    public void verifyElementWithText(By element) {
        Assert.assertTrue(driver.findElement(element).isDisplayed());
    }

    public boolean isElementDisplayed(By element) {
        try {
            waitForVisibility(element);
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
        return true;
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException exception) {
            return false;
        }
//        return true;
    }

    public void switchToContext(String contextName) {
//        driver.context(contextName);
    }

    public static <T> T getNestedValue(Map map, String... keys) {
        Object value = map;
        for (String key : keys) {
            value = ((Map) value).get(key);
        }
        return (T) value;
    }

    public String generateDate(String dateFormat, int dateDiff) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, dateDiff);
        dt = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String date = simpleDateFormat.format(dt);
        return date;
    }

    public String generateTimeInUtc(String timeFormat, int timeDiff) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, timeDiff);
        String date = simpleDateFormat.format(cal.getTime());
        return date;
    }

    public String getInputFieldText(WebElement inputField) {
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

    public void scroll(String direction) {
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

    public void swipe(String direction) {
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
    private void performSwipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public void captureScreenshot(String screenshotName) throws IOException {
        if (driver instanceof TakesScreenshot) {

            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";

            File destinationPath = new File("src/test/resources/ScreenShot/" + fileName);
            FileUtils.copyFile(sourcePath, destinationPath);

            System.out.println("Screenshot saved at: " + destinationPath.getAbsolutePath());
        }
    }

    public void adjustQuantity(int times, boolean increaseChoice) throws InterruptedException {
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

    public void clickElementWithScroll(By element) {
        boolean found = false;
        while (!found) {
            try {
                click(element);// Attempt to click the element
                found = true;    // If successful, set found to true
            } catch (Exception e) {
                // Scroll down and continue searching if element is not found
                scroll("DOWN");
            }
        }
    }

    public void tapElement(WebElement element) {

        int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
        int centerY = element.getRect().getY() + (element.getRect().getHeight() / 2);

        performTap(centerX, centerY);
    }

    private void performTap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 0);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    public boolean isElementPresent(By locatorKey) {
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
    public void waitForOverlayToDisappear() {
        try {
            List<By> overlays = Arrays.asList(
                    By.cssSelector("div.bg-transparent"),
                    By.cssSelector("div.flex.gap-3.bg-neutral-100.p-4"),
                    By.cssSelector("div[role='dialog']"),
                    By.cssSelector(".loader, .loading, .spinner")
            );

            for (By overlay : overlays) {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
            }
        } catch (Exception ignored) {
        }
    }


    public void validateErrorMessageByPartialText(String partialText, String expectedText) {
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

    public void clickJsUsingBy(By loc) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", driver.findElement(loc));
        driver.findElement(loc).click();
    }


}