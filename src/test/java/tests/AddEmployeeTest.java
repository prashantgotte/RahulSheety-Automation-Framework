
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

public class AddEmployeeTest extends BaseTest {

    private PIMPage pimPage;

    @BeforeMethod
    public void loginAndOpenAddEmployee() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.urlContains("/dashboard/index"));

        pimPage = new PIMPage(driver);
        pimPage.openPIM();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));

        pimPage.clickAdd();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.urlContains("/pim/addEmployee"));
    }

    @Test
    public void verifyAddEmployeeWithValidData() {

        // Enter employee details
        pimPage.enterFirstName("Prashant");
        pimPage.enterMiddleName("Test");
        pimPage.enterLastName("Automation");

        // Capture the generated employee ID before saving
        String employeeId = pimPage.getEmployeeId();

        // Save employee
        pimPage.clickSave();

        // Verify employee profile opens
        Assert.assertTrue(
            pimPage.isPersonalDetailsDisplayed(),
            "Employee was not created successfully"
        );

        // Verify employee ID was generated
        Assert.assertNotNull(
            employeeId,
            "Employee ID was not generated"
        );

        Assert.assertFalse(
            employeeId.trim().isEmpty(),
            "Employee ID should not be empty"
        );

        System.out.println("Employee created successfully.");
        System.out.println("Employee ID: " + employeeId);
    }
}
