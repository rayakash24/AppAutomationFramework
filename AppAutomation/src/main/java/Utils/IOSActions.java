package Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IOSActions extends AppiumUtils{


    IOSDriver driver;

    public IOSActions(IOSDriver driver){
        this.driver=driver;
    }


    //doubleClick on Any element
    public void doubleClick(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    //LongPress On Any Element  -dONE
    public void longPressAction(WebElement ele){
        Map<String,Object> params = new HashMap<>();
        params.put("element",((RemoteWebElement)ele).getId());
        params.put("duration",2);
        driver.executeScript("mobile:touchAndHold",params);
    }

    //Scroll down upto end,can change direction up, down, left and right
    public void scrollToEndAction(){
        boolean canScrollMore;
        do{
            // here we are scrolling down
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 1.0
            ));

        }while(canScrollMore);

    }

    //Scroll until Any element -Done
    public void scrollToWebElement(WebElement ele){
        Map<String,Object> params = new HashMap<>();
        params.put("direction","down");
        params.put("element", ((RemoteWebElement)ele).getId());
        driver.executeScript("mobile:scroll",params);
    }

    //swipe left or Right  -done
    public void swipeAction(WebElement ele,String direction){
        Map<String,Object> params = new HashMap<>();
        params.put("direction",direction);
//        params.put("element", ((RemoteWebElement)ele).getId());
        driver.executeScript("mobile:swipe",params);
    }

    //Drag and drop action
    public void dragAction(WebElement source,Integer x, Integer y){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", x,
                "endY",y
        ));
    }

    //Rotation of Device
    public void deviceRotation(Integer x, Integer y, Integer Angle) {

        DeviceRotation landscape = new DeviceRotation(x,y,Angle);
        driver.rotate(landscape);
    }

    public void startScript(String key,String packageName_activity){
        // If we want directly to open any particular activity and from there we want to start the operation
        // adb shell dumpsys window | grep -E 'mCurrentFocus'- MAC , grep/find -window
        // Activity activity = new Activity("com.telus.ai.collection.debug","com.telus.ai.collection.MainActivity");
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity",ImmutableMap.of(key,packageName_activity));
    }

    public void validateToastMessage(String xpath,String errorMessage){
        String toastMessage = driver.findElement(By.xpath(xpath)).getAttribute("name");
        Assert.assertEquals(toastMessage,errorMessage);
    }

    public void waitUntinlVisibleElement(String locator,String key,String value){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(locator)),key,value));

    }

    public void getSwitchInfO(){

        Set<String> contexts = driver.getContextHandles();
        for(String contextName : contexts){
            System.out.println(contextName);
        }
        //        getSwitchInfO();
        //        driver.context("WEBVIEW_chrome");//"NATIVE_APP"
    }

}
