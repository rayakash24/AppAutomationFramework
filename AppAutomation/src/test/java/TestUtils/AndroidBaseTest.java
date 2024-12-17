package TestUtils;

import Utils.AppiumUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumUtils {

    public static AppiumDriverLocalService appiumService;
    public  AndroidDriver driver;


    @BeforeClass
    public void configureAppium() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/TestData/data.properties");
        properties.load(fileInputStream);
        String ipAddress =properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        appiumService =startAppiumServer(ipAddress,Integer.parseInt(port));
        UiAutomator2Options options= new UiAutomator2Options();
        options.setDeviceName(properties.getProperty("IOSDeviceName"));
        //For Real Device
//        options.setDeviceName("Android Device");
        options.setChromedriverExecutable("//users//akashray//documents//chromedriver");
        options.setApp("//Users//akashray//Desktop//AppAutomation//src//test//java//TestClass//Resources//app-telus.apk");
        driver= new AndroidDriver(appiumService.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        appiumService=stopAppiumServer();
    }

    // @BeforeClass- when brower Test
    public void configurebrowser() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"src//main//java//TestData//data.properties");
        properties.load(fileInputStream);
        String ipAddress =properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        appiumService =startAppiumServer(ipAddress,Integer.parseInt(port));
        //chromeBrowser
        //options.setChromedriverExecutable("//users//akashray//documents//chromedriver");
        //options.setCapability("browserName","Chrome");
        UiAutomator2Options options= new UiAutomator2Options();
        options.setDeviceName(properties.getProperty("AndroidDeviceName"));

        driver= new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    public void startScript(String key,String packageName_activity){
        // If we want directly to open any particular activity and from there we want to start the operation
        // adb shell dumpsys window | grep -E 'mCurrentFocus'- MAC , grep/find -window
        // Activity activity = new Activity("com.telus.ai.collection.debug","com.telus.ai.collection.MainActivity");
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity",ImmutableMap.of(key,packageName_activity));
    }

}