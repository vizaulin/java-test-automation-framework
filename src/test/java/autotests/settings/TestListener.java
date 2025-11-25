package autotests.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test " + result.getName() + " started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test " + result.getName() + " passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test " + result.getName() + " failed. Reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test " + result.getName() + " skipped. Reason: " + result.getThrowable().getMessage());
    }
}
