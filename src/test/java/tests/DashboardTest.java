
package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import PageObjectPackage.LoginPage;
import PageObjectPackage.DashboardPage;

public class DashboardTest extends BaseTest {

    private DashboardPage dashboardPage;

    @BeforeMethod
    public void loginToApplication() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait until the Dashboard heading is displayed
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[normalize-space()='Dashboard']")
                )
        );

        dashboardPage = new DashboardPage(driver);
    }

    // Test 1: Verify Dashboard heading
    @Test
    public void verifyDashboardHeading() {

        Assert.assertTrue(
                dashboardPage.isDashboardHeadingDisplayed(),
                "Dashboard heading is not displayed"
        );
    }

    // Test 2: Verify left navigation menu
    @Test
    public void verifyLeftNavigationMenu() {

        Assert.assertTrue(
                dashboardPage.isSideMenuDisplayed(),
                "Left navigation menu is not displayed"
        );
    }

    // Test 3: Verify Admin navigation
    @Test
    public void verifyAdminNavigation() {

        dashboardPage.clickMenu("Admin");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.urlContains(
                        "/admin/viewSystemUsers"
                )
        );

        Assert.assertTrue(
                dashboardPage.getCurrentUrl()
                        .contains("/admin/viewSystemUsers"),
                "Admin page did not open"
        );
    }

    // Test 3: PIM navigation
@Test
public void verifyPIMNavigation() {

    dashboardPage.clickMenu("PIM");

    WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

    wait.until(
            ExpectedConditions.urlContains("/pim/viewEmployeeList")
    );

    Assert.assertTrue(
            dashboardPage.getCurrentUrl().contains("/pim/viewEmployeeList"),
            "PIM page did not open"
    );
}


    // Test 5: Verify Leave navigation
    @Test
    public void verifyLeaveNavigation() {

        dashboardPage.clickMenu("Leave");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.urlContains(
                        "/leave/viewLeaveList"
                )
        );

        Assert.assertTrue(
                dashboardPage.getCurrentUrl()
                        .contains("/leave/viewLeaveList"),
                "Leave page did not open"
        );
    }

    // Test 6: Verify Dashboard after browser refresh
    @Test
    public void verifyDashboardAfterRefresh() {

        driver.navigate().refresh();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[normalize-space()='Dashboard']")
                )
        );

        Assert.assertTrue(
                dashboardPage.isDashboardHeadingDisplayed(),
                "Dashboard did not load after refresh"
        );
    }

    // Test 7: Verify Logout
    @Test
    public void verifyLogout() {

        dashboardPage.openUserDropdown();
        dashboardPage.clickLogout();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.urlContains("/auth/login")
        );

        Assert.assertTrue(
                dashboardPage.getCurrentUrl().contains("/auth/login"),
                "User was not redirected to the login page"
        );
    }
}
