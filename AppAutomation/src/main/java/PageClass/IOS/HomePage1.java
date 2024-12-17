package PageClass.IOS;

import Utils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage1 extends IOSActions {


    IOSDriver driver;
    public HomePage1(IOSDriver driver){

        super(driver);
        this.driver= driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertViews;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label =='Text Entry'`]")
    private WebElement textEntryMenu;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
    private WebElement confirmPopup;

    @iOSXCUITFindBy(accessibility = "A message should be a short, complete sentence.")
    private WebElement completeSentence;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Confirm']")
    private WebElement confirm;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='UIKitCatalog']")
    private WebElement uiCatalogue;

    public void selectAlertViews(){
        alertViews.click();
    }

    public void selectConfirm(){
        confirmPopup.click();
    }

    public void verifyCompleteSentence(){
        String actualText = completeSentence.getText();
        String expectedText = "A message should be a short, complete sentence.";
        Assert.assertEquals(actualText, expectedText, "Message validation failed!");
    }


    public void selectUICatalogue(){
        uiCatalogue.click();
    }
    public void confirmBtn(){
        confirm.click();
    }




}
