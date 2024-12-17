package TestClass.android;

import PageClass.android.HomePage;
import PageClass.android.LoginPage;
import TestUtils.AndroidBaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginTest extends AndroidBaseTest {

    private LoginPage loginPage;
    private HomePage homePage;


    @BeforeClass
    public void setUpPages() {

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

//    @BeforeMethod
//    public void preSetup(){
//        //Screen to Home Page
//        startScript("intent","com.telus.ai.collection.debug/com.telus.ai.collection.MainActivity");
//    }

    @Test(priority = 1)
    public void signInAuthTest() {

        loginPage.secureLoginTextValidation();
        loginPage.signAuthBtnclicked();

    }

//    @Test(priority = 2)
//    public void oneLogin(){
////        getSwitchInfO();
////        driver.context("WEBVIEW_chrome");
////        driver.context("NATIVE_APP");
////        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        loginPage.loginTitleValidation();
//        loginPage.oneLoginBoxFlow("akashray@telusinternation.com");
//    }
//
//    @Test(priority = 3)
//    public void forgotPassword(){
//        signInAuthTest();
//        loginPage.forgotPasswordLink("akash.ray@telusinternational.com");
//    }


    @Test(priority = 2,dataProvider = "getData")
    public void loginPage(HashMap<String,String> input)  {

        loginPage.loginEmailAndPassword(input.get("email"),input.get("Password"));

    }

    @Test(priority = 3)
    public void homePageVerification(){

        homePage.jobValidationPage();
        homePage.profilePage();
        homePage.settingsPage();
        homePage.supportPage();

    }
    @Test(priority = 4)
    public void loginPageNameChangeDirectly(){
        //To start from any particular activity
        startScript("intent","com.telus.ai.collection.debug/com.telus.ai.collection.MainActivity");
        loginPage.clickFirstButton();


    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>>data =getJsonData(System.getProperty("user.dir") + "//src//test//java//TestClass//Resources//DCApp.json");
//        return new Object[][]{{"akash.ray@playment.in","playmentt@123"}};
        return new Object[][]{{data.get(0)}};
    }
}
