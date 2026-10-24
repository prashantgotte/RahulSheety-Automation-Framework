
package PageObjectPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.cssSelector(".oxd-alert-content-text");
    private By loginHeading = By.cssSelector(".orangehrm-login-title");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Enter username
    public void enterUsername(String username) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
    }

    // Click Login
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // Complete login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Get error message for invalid login
    public String getErrorMessage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(errorMessage))
            .getText();
    }

    // Check whether the login page heading is displayed
    public boolean isLoginHeadingDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(loginHeading))
            .isDisplayed();
    }

    // Check whether the username field is displayed
    public boolean isUsernameDisplayed() {
        return driver.findElement(usernameField).isDisplayed();
    }

    // Check whether the password field is displayed
    public boolean isPasswordDisplayed() {
        return driver.findElement(passwordField).isDisplayed();
    }

    // Check whether the login button is displayed
    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    // Check whether the password is masked
    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }

    // Get username required-field validation
    public String getUsernameRequiredMessage() {
        return wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(
                    By.xpath("//input[@name='username']/ancestor::div[contains(@class,'oxd-input-group')]//span")
                )
        ).getText();
    }

    // Get password required-field validation
    public String getPasswordRequiredMessage() {
        return wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(
                    By.xpath("//input[@name='password']/ancestor::div[contains(@class,'oxd-input-group')]//span")
                )
        ).getText();
    }

    // Click Forgot Password
    public void clickForgotPassword() {
        wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(
                    By.cssSelector("p.orangehrm-login-forgot-header")
                )
        ).click();
    }

}

