
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import PageObjectPackage.LoginPage;

public class LoginUITest extends BaseTest {

    @Test
    public void verifyValidLogin() {

        // Create LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Enter valid credentials and login
        loginPage.login("Admin", "admin123");

        // Verify successful login using the dashboard URL
        new org.openqa.selenium.support.ui.WebDriverWait(
                driver, java.time.Duration.ofSeconds(10))
            .until(org.openqa.selenium.support.ui.ExpectedConditions
                .urlContains("/dashboard/index"));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/dashboard/index"),
            "Login failed: Dashboard page was not displayed"
        );

        System.out.println("Valid login test passed successfully.");
    }
}

