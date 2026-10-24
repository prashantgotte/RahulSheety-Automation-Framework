
package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import PageObjectPackage.LoginPage;
import PageObjectPackage.PIMPage;

public class SearchEmployeeTest extends BaseTest {

    private PIMPage pimPage;

    @BeforeMethod
    public void loginAndOpenPIM() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
            ExpectedConditions.urlContains("/dashboard/index")
        );

        pimPage = new PIMPage(driver);
        pimPage.openPIM();

        wait.until(
            ExpectedConditions.urlContains("/pim/viewEmployeeList")
        );
    }

    @Test
    public void searchEmployeeByExistingName() {

        String employeeName = "Rahul Kumar Sharma";

        pimPage.searchEmployeeByName(employeeName);

        Assert.assertTrue(
            pimPage.isEmployeeFoundByName(employeeName),
            "Employee was not found by name: " + employeeName
        );

        System.out.println("Employee found: " + employeeName);
    }

    @Test
    public void searchEmployeeByInvalidId() {

        pimPage.searchEmployeeById("999999");

        Assert.assertTrue(
            pimPage.isNoRecordsFound(),
            "Expected no records for invalid employee ID"
        );
    }

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
}
