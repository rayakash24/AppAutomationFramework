package TestClass.IOS;

import PageClass.IOS.HomePage1;
import TestUtils.IOSBaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class DemoTestIOS extends IOSBaseTest {

        private HomePage1 homePage1;

        @BeforeClass
        public void setUpPages() {
            homePage1 = new HomePage1(driver);
        }

        @Test
        public void uiCatalogueDemoActions(){

            homePage1.selectAlertViews();
            homePage1.selectConfirm();
            homePage1.verifyCompleteSentence();
            homePage1.confirmBtn();
            homePage1.selectUICatalogue();
        }

}
