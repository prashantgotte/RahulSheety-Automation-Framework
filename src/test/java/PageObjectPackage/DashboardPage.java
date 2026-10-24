
package PageObjectPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Dashboard locators
    private By dashboardHeading =
            By.xpath("//h6[text()='Dashboard']");

    private By sideMenu =
            By.cssSelector("nav[aria-label='Sidepanel']");

    private By userDropdown =
            By.cssSelector(".oxd-userdropdown-tab");

    private By logoutLink =
            By.xpath("//a[text()='Logout']");

    private By menuSearch =
            By.cssSelector(".oxd-main-menu-search input");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verify dashboard heading
    public boolean isDashboardHeadingDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dashboardHeading))
                .isDisplayed();
    }

    // Verify left navigation menu
    public boolean isSideMenuDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(sideMenu))
                .isDisplayed();
    }

    public void clickMenu(String menuName) {
        By menuItem = By.xpath(
            "//a[contains(@class,'oxd-main-menu-item')]" +
            "[.//span[normalize-space()='" + menuName + "']]"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(menuItem)
        ).click();
    }
    
    // Open user dropdown
    public void openUserDropdown() {
        wait.until(
                ExpectedConditions.elementToBeClickable(userDropdown)).click();
    }

    // Click Logout
    public void clickLogout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    // Search the side menu
    public void searchMenu(String menuName) {
        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(menuSearch));

        search.clear();
        search.sendKeys(menuName);
    }

    // Get current page URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    
}
