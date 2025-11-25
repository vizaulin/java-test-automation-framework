package autotests.settings;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import static project.webdriver.WebDriverManager.getDriver;

public class AllureScreenshotListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && testResult.getStatus() == ITestResult.FAILURE) {
            saveScreenshot();
        }
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot() {
        try {
            return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}