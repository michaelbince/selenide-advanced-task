package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtil.generateScreenshotFilename(result.getName(), "FAILED");
        ScreenshotUtil.takeScreenshot(screenshotPath);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String screenshotPath = ScreenshotUtil.generateScreenshotFilename(result.getName(), "PASSED");
        ScreenshotUtil.takeScreenshot(screenshotPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
    }
}
