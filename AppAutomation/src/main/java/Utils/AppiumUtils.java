package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class AppiumUtils {


    public static AppiumDriverLocalService appiumService;

    public static AppiumDriverLocalService startAppiumServer(String iPAdress, int portNo) {
        AppiumDriverLocalService appiumService= new AppiumServiceBuilder()
                .withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress(iPAdress).usingPort(portNo).build();

        appiumService.start();
        System.out.println("Appium server started...");
        return appiumService;
    }

    public static AppiumDriverLocalService stopAppiumServer() {
        if (appiumService != null && appiumService.isRunning()) {
            appiumService.stop();
            System.out.println("Appium server stopped...");
            return appiumService;
        }
        return null;
    }

    public List<HashMap<String,String>> getJsonData(String jsonFilePath)throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath));
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
        return data;
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException
    {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        //1. capture and place in folder //2. extent report pick file and attach to report

    }
    public void waitForElementToBeVisible(WebElement element,AppiumDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element,AppiumDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
    public void getSwitchInfo(AppiumDriver driver) {
        Set<String> contexts = driver.getWindowHandles();
        for(String contextName : contexts){
            System.out.println(contextName);
        }
        // Example of switching to WebView
        // driver.context("WEBVIEW_chrome");
    }
}
