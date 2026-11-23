import Pages.DashBoardPage;
import Pages.LoginPage;
import Utils.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LoginTest extends BaseTest {
    DataProvider dataProvider = new DataProvider();
    LoginPage loginpage;

    @BeforeMethod
    public void loginpage()
    {
         loginpage= new LoginPage(driver);
    }

    @Test
    public void Loginwithvalidcredentials()
    {
        String username = dataProvider.jsonreader("validusername");
        String password = dataProvider.jsonreader("validpassword");

        loginpage.enterusername(username);
        loginpage.enterpassword(password);
        DashBoardPage dashBoardPage = loginpage.clicklogin();
        assertTrue(loginpage.isloaded_Dashboard(),"the DashboardPage is not loaded");
        assertTrue(dashBoardPage.isdisplayed(),"the DashboardPage is not loaded");
    }
    @Test
    public void Loginwithinvalidcredentials()
    {
        String username = dataProvider.jsonreader("invalidusername");
        String password = dataProvider.jsonreader("invalidpassword");
        loginpage.enterusername(username);
        loginpage.enterpassword(password);
        loginpage.clicklogin();
        assertTrue(loginpage.Invalidcredentials(),"the error message is not displayed");
    }
    @Test
    public void Loginwithemptyfields ()
    {
        loginpage.enterusername(" ");
        loginpage.enterpassword(" ");
        loginpage.clicklogin();
        assertEquals(loginpage.Requiredmessage(),2);
    }

}
