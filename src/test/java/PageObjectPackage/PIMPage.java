
package PageObjectPackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pimMenu = By.xpath(
        "//span[normalize-space()='PIM']"
    );

    private By employeeListHeading = By.xpath(
        "//h5[normalize-space()='Employee Information']"
    );

    private By employeeIdInput1 = By.xpath(
        "//label[normalize-space()='Employee Id']" +
        "/ancestor::div[contains(@class,'oxd-input-group')]//input"
    );

    private By searchButton = By.xpath(
        "//button[normalize-space()='Search']"
    );

    private By resetButton = By.xpath(
        "//button[normalize-space()='Reset']"
    );

    private By employeeTable = By.cssSelector(".oxd-table");

    private By noRecordsMessage = By.xpath(
        "//*[contains(normalize-space(),'No Records Found')]"
    );

    private By addButton = By.xpath(
        "//button[normalize-space()='Add']"
    );

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(15)
        );
    }

    public void openPIM() {
        wait.until(
            ExpectedConditions.elementToBeClickable(pimMenu)
        ).click();
    }

    public boolean isEmployeeListHeadingDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                employeeListHeading
            )
        ).isDisplayed();
    }

    public boolean isSearchButtonDisplayed() {
        return driver.findElement(searchButton).isDisplayed();
    }

    public boolean isResetButtonDisplayed() {
        return driver.findElement(resetButton).isDisplayed();
    }

    public boolean isEmployeeTableDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                employeeTable
            )
        ).isDisplayed();
    }

    public void enterEmployeeId(String employeeId) {
        WebElement input = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                employeeIdInput1
            )
        );
        input.clear();
        input.sendKeys(employeeId);
    }

    public void clickSearch() {
        wait.until(
            ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }

    public boolean isNoRecordsMessageDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                noRecordsMessage
            )
        ).isDisplayed();
    }

    public void clickReset() {
        wait.until(
            ExpectedConditions.elementToBeClickable(resetButton)
        ).click();
    }

    public String getEmployeeIdValue() {
        return driver.findElement(employeeIdInput1)
                     .getAttribute("value");
    }

    public void clickAdd() {
        wait.until(
            ExpectedConditions.elementToBeClickable(addButton)
        ).click();
    }
    


private By firstNameInput = By.name("firstName");
private By middleNameInput = By.name("middleName");
private By lastNameInput = By.name("lastName");

private By employeeIdInput = By.xpath(
    "//label[normalize-space()='Employee Id']" +
    "/ancestor::div[contains(@class,'oxd-input-group')]//input"
);

private By saveButton = By.xpath(
    "//button[@type='submit' and normalize-space()='Save']"
);

private By personalDetailsHeading = By.xpath(
    "//h6[normalize-space()='Personal Details']"
);

public void enterFirstName(String firstName) {
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(firstNameInput)
    ).sendKeys(firstName);
}

public void enterMiddleName(String middleName) {
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(middleNameInput)
    ).sendKeys(middleName);
}

public void enterLastName(String lastName) {
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(lastNameInput)
    ).sendKeys(lastName);
}

public String getEmployeeId() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(employeeIdInput1)
    ).getAttribute("value");
}

public void clickSave() {
    wait.until(
        ExpectedConditions.elementToBeClickable(saveButton)
    ).click();
}

public boolean isPersonalDetailsDisplayed() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            personalDetailsHeading
        )
    ).isDisplayed();
}


// Employee name field
private By employeeNameInput = By.xpath(
    "//label[normalize-space()='Employee Name']" +
    "/ancestor::div[contains(@class,'oxd-input-group')]//input"
);

// Employee ID field
private By employeeIdInput11 = By.xpath(
    "//label[normalize-space()='Employee Id']" +
    "/ancestor::div[contains(@class,'oxd-input-group')]//input"
);

// Search employee by name
public void searchEmployeeByName(String name) {
    WebElement input = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            employeeNameInput
        )
    );

    input.clear();
    input.sendKeys(name);

    By suggestion = By.xpath(
        "//div[@role='option']//span[normalize-space()='" + name + "']"
    );

    try {
        wait.until(
            ExpectedConditions.elementToBeClickable(suggestion)
        ).click();
    } catch (org.openqa.selenium.TimeoutException e) {
        // Continue if no autocomplete option appears.
    }

    clickSearch();
}

// Search employee by ID
public void searchEmployeeById(String id) {
    WebElement input = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            employeeIdInput
        )
    );

    input.clear();
    input.sendKeys(id);

    clickSearch();
}

// Read employee table content
public String getEmployeeTableText() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".oxd-table")
        )
    ).getText();
}

// Check if the table displays no records
public boolean isNoRecordsFound() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(normalize-space(),'No Records Found')]")
        )
    ).isDisplayed();
}
//Employee Name field on the Employee List page
private By employeeNameInput1 = By.xpath(
 "//label[normalize-space()='Employee Name']" +
 "/ancestor::div[contains(@class,'oxd-input-group')]" +
 "//input"
);

private By searchButton1 = By.xpath(
 "//button[@type='submit' and normalize-space()='Search']"
);

private By employeeRows = By.xpath(
 "//div[@class='oxd-table-body']//div[@role='row']"
);

public void searchEmployeeByName1(String employeeName) {
 WebElement nameField = wait.until(
     ExpectedConditions.elementToBeClickable(employeeNameInput)
 );

 nameField.clear();
 nameField.sendKeys(employeeName);

 wait.until(
     ExpectedConditions.elementToBeClickable(searchButton)
 ).click();

 wait.until(
     ExpectedConditions.visibilityOfElementLocated(
         By.cssSelector(".oxd-table-body")
     )
 );
}


public boolean isEmployeeFoundByName(String employeeName) {

    List<WebElement> rows = driver.findElements(
        By.xpath("//div[@class='oxd-table-body']//div[@role='row']")
    );

    for (WebElement row : rows) {
        if (row.getText().contains(employeeName)) {
            return true;
        }
    }

    return false;
}

}