import Pages.DashBoardPage;
import Pages.LoginPage;
import Pages.PIMPage;
import Utils.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PIMTest extends BaseTest{
    DataProvider dataProvider = new DataProvider();
    LoginPage loginpage;
    PIMPage pimPage;
    DashBoardPage dashBoardPage;

    @BeforeMethod
    public void setup(){
     loginpage = new LoginPage(driver);
     pimPage = new PIMPage(driver);
     dashBoardPage = new DashBoardPage(driver);

    }

    @Test
    public void Searchforemployee()
    {
        String firstName = "maddy" + System.currentTimeMillis();
        loginpage.validlogin();
        dashBoardPage.selectMenuItem("PIM");
        var addemployee = pimPage.clickaddemployee();
        addemployee.enterfirstname(firstName);
        addemployee.enterlastname("Henry");
        addemployee.clicksave();

        dashBoardPage.selectMenuItem("PIM");
        pimPage.enteremployeename(firstName);
        pimPage.clicksearch();

        assertTrue(pimPage.getFirstResultName().contains(firstName));
    }
    @Test
    public void Searchfornonexistingemployee()
    {
        String username = dataProvider.jsonreader("validusername");
        String password = dataProvider.jsonreader("validpassword");
        String employee = dataProvider.jsonreader("nonexistingemployee");

        loginpage.enterusername(username);
        loginpage.enterpassword(password);
        var dashboardpage=loginpage.clicklogin();
        dashboardpage.selectMenuItem("PIM");
        pimPage.enteremployeename(employee);
        pimPage.clicksearch();
        assertTrue(pimPage.recordfoundmessage(),"No Records Found is displayed");
    }





}
