package Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
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

public class MobileAction {

    AppiumDriver driver;
    String platform;

    public MobileAction(AppiumDriver driver) {
        this.driver = driver;
        this.platform = driver.getCapabilities().getCapability("platformName").toString().toLowerCase();
    }

    // Wait for an element to be visible
    public void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for an element to be clickable
    public void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    // Single Click on Any element (generic for both Android and iOS)
    public void singleClick(WebElement element) {
        if (platform.equals("android")) {
            driver.executeScript("mobile: tap", ImmutableMap.of(
                    "element", ((RemoteWebElement) element).getId()
            ));
        } else if (platform.equals("ios")) {
            driver.executeScript("mobile: tap", ImmutableMap.of(
                    "element", ((RemoteWebElement) element).getId()
            ));
        }
    }

    // Double Click on Any element
    public void doubleClick(WebElement element) {
        if (platform.equals("android")) {
            ((JavascriptExecutor) driver).executeScript("mobile: doubleTap", ImmutableMap.of(
                    "element", ((RemoteWebElement) element).getId()
            ));
        } else if (platform.equals("ios")) {
            ((JavascriptExecutor) driver).executeScript("mobile: doubleTap", ImmutableMap.of(
                    "element", ((RemoteWebElement) element).getId()
            ));
        }
    }

    // Long Press on Any Element
    public void longPressAction(WebElement element) {
        if (platform.equals("android")) {
            ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                    "elementID", ((RemoteWebElement) element).getId(),
                    "duration", 2000));
        } else if (platform.equals("ios")) {
            Map<String,Object> params = new HashMap<>();
            params.put("element",((RemoteWebElement)element).getId());
            params.put("duration",5);
            driver.executeScript("mobile:touchAndHold",params);
        }
    }

    // Scroll down to the end
    public void scrollToEndAction() {
        boolean canScrollMore = false;
        do {
            if (platform.equals("android")) {
                canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 100, "top", 100, "width", 200, "height", 200,
                        "direction", "down",
                        "percent", 1.0
                ));
                canScrollMore=true;
            } else if (platform.equals("ios")) {
                Map<String,Object> params = new HashMap<>();
                params.put("direction","down");
//        params.put("element", ((RemoteWebElement)ele).getId());
                driver.executeScript("mobile:swipe",params);
                canScrollMore=true;
            }
        } while (canScrollMore);
    }

    // Scroll until a specific element is visible
    public void scrollUntilElement(WebElement element) {
        if (platform.equals("android")) {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + element + "\"));"));
        } else if (platform.equals("ios")) {
            ((JavascriptExecutor) driver).executeScript("mobile: scroll", ImmutableMap.of(
                    "element", ((RemoteWebElement) element).getId(),
                    "direction", "down"
            ));
        }
    }

    // Swipe left or right
    public void swipeLeftRight(WebElement element, String direction) {
        if (platform.equals("android")) {
            ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) element).getId(),
                    "direction", direction,
                    "percent", 0.75
            ));
        } else if (platform.equals("ios")) {
            Map<String,Object> params = new HashMap<>();
            params.put("direction",direction);
//        params.put("element", ((RemoteWebElement)ele).getId());
            driver.executeScript("mobile:swipe",params);
        }
    }

    // Drag and Drop action
    public void dragAction(WebElement source, Integer x, Integer y) {
        if (platform.equals("android")) {
            ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) source).getId(),
                    "endX", x,
                    "endY", y
            ));
        } else if (platform.equals("ios")) {
            ((JavascriptExecutor) driver).executeScript("mobile: dragFromToForDuration", ImmutableMap.of(
                    "element", ((RemoteWebElement) source).getId(),
                    "endX", x,
                    "endY", y,
                    "duration", 1.0 // Duration in seconds
            ));
        }
    }

    // Device Rotation
    //Rotation of Device
//    public void deviceRotation(Integer x, Integer y, Integer Angle) {
//
//        DeviceRotation landscape = new DeviceRotation(x,y,Angle);
//        driver.rotate(landscape);
//    }

    // Validate Toast Message
    public void validateToastMessage(String xpath, String expectedMessage) {
        String toastMessage = driver.findElement(By.xpath(xpath)).getAttribute("value"); // For iOS, use "value"
        Assert.assertEquals(toastMessage, expectedMessage);
    }

    // Wait until visible element
    public void waitUntilVisibleElement(String locator, String key, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(locator)), key, value));
    }

    // Switch Context Information (WebView/NATIVE_APP)
//    public void getSwitchInfo() {
//        Set<String> contexts = driver.getContextHandles();
//        for(String contextName : contexts){
//            System.out.println(contextName);
//        }
//        // Example of switching to WebView
//        // driver.context("WEBVIEW_chrome");
//    }
}
