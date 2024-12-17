package PageClass.android;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends AndroidActions {


    AndroidDriver driver;
    public HomePage(AndroidDriver driver){

        super(driver);
        this.driver= driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Jobs']")
    private WebElement jobFotterTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Active jobs']")
    private WebElement activeJobsHeading;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My jobs']")
    private WebElement myJobTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Profile']")
    private WebElement profileFotterTitle;
    @AndroidFindBy(xpath ="(//android.widget.TextView[@text='Profile'])[1]")
    private WebElement profileTitle;
    @AndroidFindBy(xpath ="(//android.widget.TextView[@text='Settings'])")
    private WebElement settingsFotterTitle;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Settings'])[1]")
    private WebElement settingsTitle;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Support'])")
    private WebElement supportFotterTitle;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Support'])[1]")
    private WebElement supportTitle;

    public void jobValidationPage(){
        jobFotterTitle.click();
        String myJob = myJobTitle.getText();
        Assert.assertEquals(myJob,"My jobs");
        String activeJob = activeJobsHeading.getText();
        Assert.assertEquals(activeJob,"Active jobs");
    }

    public void profilePage(){
        profileFotterTitle.click();
        String profile =profileTitle.getText();
        Assert.assertEquals(profile,"Profile");
    }

    public void settingsPage(){
        settingsFotterTitle.click();
        String settings= settingsTitle.getText();
        Assert.assertEquals(settings,"Settings");
    }

    public void supportPage(){
        supportFotterTitle.click();
        String support = supportTitle.getText();
        Assert.assertEquals(support,"Support");
    }

}
