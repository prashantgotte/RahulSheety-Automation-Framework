
package UtilitiesPackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {

        if (driver == null) {
            System.out.println("Screenshot failed: WebDriver is null.");
            return;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        File folder = new File("screenshots");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destination = new File(
                folder, testName + "_" + timestamp + ".png");

        try {
            java.nio.file.Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: "
                    + destination.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Unable to save screenshot.");
            e.printStackTrace();
        }
    }
}
