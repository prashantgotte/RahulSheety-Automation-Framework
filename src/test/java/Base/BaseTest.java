
package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import UtilitiesPackage.ConfigReader;
import UtilitiesPackage.LogUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        LogUtil.info("Test setup started");

        driver = new ChromeDriver();
        LogUtil.info("Chrome browser launched");

        driver.manage().window().maximize();
        LogUtil.info("Browser window maximized");

        driver.get(ConfigReader.getProperty("url"));
        LogUtil.info("Application opened successfully");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
