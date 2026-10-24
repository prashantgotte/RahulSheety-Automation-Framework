
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import PageObjectPackage.LoginPage;

public class ForgotPasswordTest extends BaseTest {

    @Test
    public void verifyForgotPasswordNavigation() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickForgotPassword();

        Assert.assertTrue(
            driver.getCurrentUrl()
                .contains("/auth/requestPasswordResetCode"),
            "Forgot Password page did not open"
        );

        System.out.println(
            "Forgot Password navigation test passed."
        );
    }
}
