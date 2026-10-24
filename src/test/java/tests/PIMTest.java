
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
import PageObjectPackage.PIMPage;

public class PIMTest extends BaseTest {

    private PIMPage pimPage;

    @BeforeMethod
    public void loginAndOpenPIM() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h6[normalize-space()='Dashboard']")
        ));

        pimPage = new PIMPage(driver);
        pimPage.openPIM();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h5[normalize-space()='Employee Information']")
        ));
    

        // Open PIM page
        pimPage = new PIMPage(driver);
        pimPage.openPIM();

        // Wait for Employee Information heading
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h5[normalize-space()='Employee Information']")
                )
        );
    }

    // Test 1: Verify Employee Information heading
    @Test
    public void verifyEmployeeListHeading() {

        Assert.assertTrue(
                pimPage.isEmployeeListHeadingDisplayed(),
                "Employee Information heading is not displayed"
        );
    }

    // Test 2: Verify Search button
    @Test
    public void verifySearchButton() {

        Assert.assertTrue(
                pimPage.isSearchButtonDisplayed(),
                "Search button is not displayed"
        );
    }

    // Test 3: Verify Reset button
    @Test
    public void verifyResetButton() {

        Assert.assertTrue(
                pimPage.isResetButtonDisplayed(),
                "Reset button is not displayed"
        );
    }

    // Test 4: Verify employee table
    @Test
    public void verifyEmployeeTable() {

        Assert.assertTrue(
                pimPage.isEmployeeTableDisplayed(),
                "Employee table is not displayed"
        );
    }

    // Test 5: Search using a non-existing Employee ID
    @Test
    public void verifySearchByEmployeeId() {

        pimPage.enterEmployeeId("999999");
        pimPage.clickSearch();

        Assert.assertTrue(
                pimPage.isNoRecordsMessageDisplayed(),
                "No records message was not displayed"
        );
    }

    // Test 6: Verify Reset clears the Employee ID field
    @Test
    public void verifyResetSearchFields() {

        pimPage.enterEmployeeId("999999");
        pimPage.clickReset();

        Assert.assertEquals(
                pimPage.getEmployeeIdValue(),
                "",
                "Employee ID field was not cleared"
        );
    }

    // Test 7: Verify Add Employee navigation
    @Test
    public void verifyAddButtonNavigation() {

        pimPage.clickAdd();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.urlContains("/pim/addEmployee")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/pim/addEmployee"),
                "Add Employee page did not open"
        );
    }
}
