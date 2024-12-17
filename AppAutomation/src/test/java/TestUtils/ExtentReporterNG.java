package TestUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReporterNG {

    static ExtentReports extentReports;
    public static ExtentReports getReportObject(){

//        String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
        String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "index.html";
        File reportDir = new File(System.getProperty("user.dir") + "\\reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("App Automation Reports");
        reporter.config().setDocumentTitle("Test Results");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Automater", "Akash Ray");
        return extentReports;
    }
}



