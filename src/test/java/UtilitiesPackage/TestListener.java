
package UtilitiesPackage;

import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Base.BaseTest;

public class TestListener implements
        ITestListener,
        ISuiteListener,
        IExecutionListener,
        IInvokedMethodListener {

    // Execution starts
    @Override
    public void onExecutionStart() {
        System.out.println("========== TestNG Execution Started ==========");
    }

    // Execution finishes
    @Override
    public void onExecutionFinish() {
        System.out.println("========== TestNG Execution Finished ==========");
    }

    // Suite starts
    @Override
    public void onStart(ISuite suite) {
        System.out.println("Suite Started: " + suite.getName());
    }

    // Suite finishes
    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Suite Finished: " + suite.getName());
    }

    // Test context starts
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Context Started: " + context.getName());
    }

    // Test context finishes
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Context Finished: " + context.getName());

        System.out.println("Passed Tests: "
                + context.getPassedTests().size());

        System.out.println("Failed Tests: "
                + context.getFailedTests().size());

        System.out.println("Skipped Tests: "
                + context.getSkippedTests().size());
    }

    // Individual test starts
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    // Individual test passes
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    // Individual test fails
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());

        Object instance = result.getInstance();

        if (instance instanceof BaseTest) {
            BaseTest test = (BaseTest) instance;
            WebDriver driver = test.getDriver();

            if (driver != null) {
                try {
                    ScreenshotUtil.takeScreenshot(
                            driver,
                            result.getMethod().getMethodName());
                } catch (Exception e) {
                    System.out.println(
                            "Screenshot capture failed: " + e.getMessage());
                }
            } else {
                System.out.println(
                        "Screenshot not captured: WebDriver is null.");
            }
        }
    }

    // Individual test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    // Test fails but is within a success-percentage allowance
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(
                "Test Failed Within Success Percentage: "
                        + result.getName());
    }

    // Invoked before each test method or configuration method
    @Override
    public void beforeInvocation(
            IInvokedMethod method, ITestResult result) {

        if (method.isTestMethod()) {
            System.out.println(
                    "Before Test Method: " + method.getTestMethod().getMethodName());
        }
    }

    // Invoked after each test method or configuration method
    @Override
    public void afterInvocation(
            IInvokedMethod method, ITestResult result) {

        if (method.isTestMethod()) {
            System.out.println(
                    "After Test Method: " + method.getTestMethod().getMethodName());
        }
    }
}
