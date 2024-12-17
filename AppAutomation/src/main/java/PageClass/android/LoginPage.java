package PageClass.android;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends AndroidActions {


    AndroidDriver driver;
    public LoginPage(AndroidDriver driver){


        super(driver);
        this.driver= driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    ////android.widget.TextView[@text="Continue to our secure sign-in page,
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Continue to our secure sign-in page')]")
    private WebElement secureLoginText;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign-in with Auth0\")")
    private WebElement signInAuth;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Log in to playment-auth-staging')]")
    private WebElement loginTitle;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Continue with one-login')]")
    private WebElement oneLoginBox;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
    private WebElement sigNInTelusUsername;

    @AndroidFindBy(xpath = "//android.view.View[contains(@text, 'Remember me')]")
    private WebElement rememberMeCheckBox;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
    private WebElement oneLoginContinue;

    @AndroidFindBy(accessibility = "Close tab")
    private WebElement closeTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Forgot password')]")
    private WebElement forgotPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    private WebElement emailIdBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private WebElement passwordBox;

    @AndroidFindBy(xpath = "//android.widget.Button")
    private WebElement btn;


    public void signAuthBtnclicked(){

        waitForElementToBeClickable(signInAuth,driver);
        signInAuth.click();

    }
    public void secureLoginTextValidation() {

        waitForElementToBeVisible(secureLoginText,driver);
        String actualText = secureLoginText.getText();
        String expectedText = "Continue to our secure sign-in page,\npowered by Auth0.";
        Assert.assertEquals(actualText, expectedText, "Secure login text validation failed!");
    }

    public void loginTitleValidation() {
        waitForElementToBeVisible(loginTitle,driver);
        String actualTitle = loginTitle.getText();
        String expectedTitle = "Log in to playment-auth-staging to continue to DC Android.";
        Assert.assertEquals(actualTitle, expectedTitle, "Login title validation failed!");
    }

    public void oneLoginBoxFlow(String emailId){

        waitForElementToBeClickable(oneLoginBox,driver);
        oneLoginBox.click();
        waitForElementToBeClickable(sigNInTelusUsername,driver);
        sigNInTelusUsername.sendKeys(emailId);
        rememberMeCheckBox.click();
        oneLoginContinue.click();
        closeTab.click();

    }
    public void forgotPasswordLink(String email){

        waitForElementToBeClickable(forgotPassword,driver);
        forgotPassword.click();
        waitForElementToBeClickable(sigNInTelusUsername,driver);
        sigNInTelusUsername.sendKeys(email);
        closeTab.click();

    }

    public void loginEmailAndPassword(String email, String password){

        loginTitleValidation();
        waitForElementToBeVisible(emailIdBox,driver);
        emailIdBox.sendKeys(email);
        passwordBox.sendKeys(password);
        oneLoginContinue.click();

    }
    public void clickFirstButton() {

        waitForElementToBeClickable(btn,driver);
        btn.click();
    }

}
