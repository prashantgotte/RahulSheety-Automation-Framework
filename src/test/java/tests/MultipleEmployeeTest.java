
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

public class MultipleEmployeeTest extends BaseTest {

    private PIMPage pimPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void loginAndOpenPIM() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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
    public void addMultipleEmployees() {

        String[][] employees = {
            {"Rahul", "Kumar", "Sharma"},
            {"Priya", "Test", "Reddy"},
            {"Amit", "Automation", "Patil"},
            {"Sneha", "QA", "Verma"},
            {"Vijay", "Demo", "Singh"}
        };

        for (String[] employee : employees) {

            String firstName = employee[0];
            String middleName = employee[1];
            String lastName = employee[2];

            // Open Add Employee
            pimPage.clickAdd();

            wait.until(
                ExpectedConditions.urlContains("/pim/addEmployee")
            );

            pimPage.enterFirstName(firstName);
            pimPage.enterMiddleName(middleName);
            pimPage.enterLastName(lastName);

            pimPage.clickSave();

            wait.until(
                ExpectedConditions.urlContains("/pim/viewPersonalDetails")
            );

            Assert.assertTrue(
                pimPage.isPersonalDetailsDisplayed(),
                "Employee creation failed: " + firstName
            );

            System.out.println(
                "Employee created: " + firstName + " " + lastName
            );

            // Return to Employee List
            pimPage.openPIM();

            wait.until(
                ExpectedConditions.urlContains("/pim/viewEmployeeList")
            );

            System.out.println(
                "Employee List opened after adding: " + firstName
            );
        }

        System.out.println(
            "All employees processed. Browser will remain open for inspection."
        );
    }
}
