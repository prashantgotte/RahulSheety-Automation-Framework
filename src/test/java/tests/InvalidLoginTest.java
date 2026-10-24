
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import PageObjectPackage.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void invalidUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("WrongUser", "WrongPassword");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Invalid credentials"),
            "Expected invalid credentials error"
        );
    }

    @Test
    public void validUsernameInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("Admin", "WrongPassword");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Invalid credentials"),
            "Expected invalid credentials error"
        );
    }

    @Test
    public void invalidUsernameValidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("WrongUser", "admin123");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Invalid credentials"),
            "Expected invalid credentials error"
        );
    }

    @Test
    public void emptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLogin();

        Assert.assertEquals(
            loginPage.getUsernameRequiredMessage(),
            "Required"
        );

        Assert.assertEquals(
            loginPage.getPasswordRequiredMessage(),
            "Required"
        );
    }

    @Test
    public void emptyUsernameOnly() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        Assert.assertEquals(
            loginPage.getUsernameRequiredMessage(),
            "Required"
        );
    }

    @Test
    public void emptyPasswordOnly() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.clickLogin();

        Assert.assertEquals(
            loginPage.getPasswordRequiredMessage(),
            "Required"
        );
    }
}
