package context;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Scenario;
import utilities.ConfigLoader;
import utilities.CredsLoader;

public class TestContext {
    public AppiumDriver driver;
    public CredsLoader credsLoader;
    public ConfigLoader configLoader;
    public Scenario scenario;

}
