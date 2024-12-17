package TestUtils;

import Utils.AppiumUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {

    ExtentReports extentReports = ExtentReporterNG.getReportObject();
    ExtentTest extentTest;
    AppiumDriver driver;

     public void onTestStart(ITestResult result) {
         extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,"Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        extentTest.fail("Test Failed: " + result.getThrowable());
        try{
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (NoSuchFieldException e1) {
            throw new RuntimeException(e1);
        } catch (IllegalAccessException e1) {
            throw new RuntimeException(e1);
        }
        try{extentTest.addScreenCaptureFromBase64String(getScreenshotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        extentTest.skip("Test Skipped: " + result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
