package hooks;

import context.TestContext;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.ConfigLoader;
import utilities.CredsLoader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Hooks {
    public static AppiumDriver driver;
    private static String executionMode;
    private static String buildName;
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @BeforeAll
    public static void initilize() throws MalformedURLException {
        initializeDriver();
    }

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        context.credsLoader = new CredsLoader();
        context.configLoader = new ConfigLoader();
        context.scenario = scenario;
//        initializeDriver();
        context.driver = driver;
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }
        String scenarioName = scenario.getName();
        if (scenarioName.contains("Verify the OTP functionality with an invalid OTP") ||
                scenarioName.contains("Login as a user with an invalid mobile number")) {

            restartApplication();
        }

//        driver.quit();
    }

    public static void initializeDriver() throws MalformedURLException {
        System.out.println("Driver initialization started ******************");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        executionMode = System.getProperty("executionMode");

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
//        capabilities.setCapability("appium:deviceName", System.getProperty("deviceName", "14081JEC202036"));
        capabilities.setCapability("appium:noReset", true);
        capabilities.setCapability("appium:forceAppLaunch", true);
        capabilities.setCapability("appium:browserName", "Chrome");
        capabilities.setCapability("appium:chromedriverExecutable", "/Users/apple/Downloads/chromedriver-mac-x64/chromedriver");
        // Key auto-handling capabilities
        capabilities.setCapability("appium:chromedriverAutodownload", true);
        capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        // Helps match minor version mismatches automatically
        capabilities.setCapability("appium:chromedriverUseSystemExecutable", false);

        driver = new AppiumDriver(new URL("http://localhost:4723"), capabilities);
        System.out.println("Driver initialization completed ******************");

        //Prod
        String url = System.getProperty("msiteUrl", "https://www.ssbeauty.in/");
        //UAT
//        String url = System.getProperty("msiteUrl", "https://maya-uat.ssecom.tech/");

        driver.get(url);
    }



    public void restartApplication() {
        try {
            if (driver != null) {
                driver.quit();
                initializeDriver();
            }
        } catch (Exception e) {
            System.out.println("Error restarting application: " + e.getMessage());
        }
    }

}
