package runner;

import hooks.JvmReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;


@CucumberOptions(plugin = {  "pretty", "json:target/Reports/cucumber-report.json"
        , "html:target/cucumber/cucumber.html"
        , "junit:target/Reports/cucumber_Report.xml"
        , "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
},
        glue = { "scenarios", "hooks" },
        features = "src/test/java/scenarios",
        dryRun = false
        )
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite
    public void generateReport() {
        JvmReport.report("target/Reports/cucumber-report.json");
    }
}
