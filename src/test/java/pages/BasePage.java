package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.codehaus.groovy.classgen.asm.BinaryIntExpressionHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class BasePage {
    protected static AppiumDriver driver;
    protected static WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public static void waitForVisibility(WebElement element) {
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

    public void clickOnElement(By element, int... param) {
        isElementDisplayed(element);
        if (param.length == 1) {
            WebElement elementForClick = driver.findElements(element).get(param[0]);
            wait.until(ExpectedConditions.visibilityOf(elementForClick));
            int max=2;
            for (int i = 0; i < max; i++) {
                elementForClick.click();
                i++;
            }

        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            driver.findElement(element).click();
        }
    }

    public void click(By element) {
        waitForVisibility(element);
        driver.findElement(element).click();
    }

    public static void click(WebElement element) {
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

    public boolean isElementPresentWithText(String elementText) {
        return isElementDisplayed(By.xpath("//*[contains(@text,'" + elementText + "')]"));
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
    public void clickElementWithScroll(By element) {
        int maxScrollAttempts = 5;
        int attempt = 0;
        boolean clicked = false;

        while (attempt < maxScrollAttempts) {
            try {
                clickOnElement(element);
                clicked = true;
                break;
            } catch (Exception e) {
                scroll("DOWN");
                attempt++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        }
        if (!clicked) {}
    }

//    public void clickElementWithScroll(WebElement element) {
//        int maxScrollAttempts = 5;
//        int attempt = 0;
//        boolean clicked = false;
//
//        while (attempt < maxScrollAttempts) {
//            try {
//                element.click();
//                clicked = true;
//                break;
//            } catch (Exception e) {
//                scroll("DOWN");
//                attempt++;
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException ignored) {}
//            }
//        }
//        if (!clicked) {}
//    }


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

    public String getDisplayedText(WebElement element) {
        String text = element.getAttribute("content-desc");
        if (text == null || text.isEmpty()) {
            text = element.getText();
        }
        return text;
    }

    public void validateErrorMessageByPartialText(String partialText, String expectedText) {
        try {
            By errorLocator = By.xpath("//*[contains(text(),'" + partialText + "')]");
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
            String actualText = errorElement.getText().trim();

            Assert.assertEquals(actualText, expectedText, "Toast message mismatch!");
        } catch (Exception e) {
            Assert.fail("Failed to validate error message for partial text: '" + partialText + "' - " + e.getMessage());
        }
    }

    public void safeClick(By b) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            // Use BasePage click which already does waits
            clickOnElement(b);
        } catch (Exception e) {
            // fallback to JS click if normal click fails
            WebElement el = retryGetElement(b);
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            js.executeScript("arguments[0].click();", el);
        }
    }

    public void safeClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            js.executeScript("arguments[0].click();", element);
        }
    }

    public WebElement retryGetElement(By locators) {
        try {
            return driver.findElement(locators);
        } catch (StaleElementReferenceException se) {
            // single retry
            wait.withTimeout(Duration.ofSeconds(2));
            try {
                return driver.findElement(locators);
            } finally {
                wait.withTimeout(Duration.ofSeconds(30));
            }
        }
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }


    public String textOf(WebElement element) {
        try {
            return element.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void switchToNewWindow() {

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void closeParentWindowAndSwitchToChild() {

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        if (allWindows.size() <= 1) {
            throw new RuntimeException("Child window not opened");
        }

        String childWindow = allWindows.stream()
                .filter(handle -> !handle.equals(parentWindow))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Child window not found"));

        driver.switchTo().window(childWindow);

        driver.switchTo().window(parentWindow);
        driver.close();

        driver.switchTo().window(childWindow);
    }

}