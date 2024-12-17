package TestUtils;
import Utils.AppiumUtils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;


public class IOSBaseTest extends AppiumUtils {

    public static AppiumDriverLocalService appiumService;
    public  IOSDriver driver;


    @BeforeClass
    public void configureAppium() throws IOException {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/TestData/data.properties");
        properties.load(fileInputStream);
        String ipAddress =properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        appiumService =startAppiumServer(ipAddress,Integer.parseInt(port));
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(properties.getProperty("IOSDeviceName"));
        options.setApp("/Users/akashray/Library/Developer/Xcode/DerivedData/UIKitCatalog-blreehjpjzkipvbhgolygjsrifxq/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
        options.setPlatformVersion(properties.getProperty("PlatformVersion"));
        //For Real device
//        options.setCapability("xcodeSigningId","iPhone Developer");
//        //for this we need apple account and there with the name Team ID
//        options.setCapability("xcodeOrgId","");
//        //https://www.wikihow.com/Obtain-the-Identifier-Number-(UDID)-for-an-iPhone,-iPod-or-iPad
//        options.setCapability("udid","");
//        //account and new project on xcode
//        options.setCapability("updateWDABundleId","");
        //Appium -> Webdriver Agent ->IOS App
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        driver= new IOSDriver(appiumService.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        appiumService=stopAppiumServer();
    }

}
