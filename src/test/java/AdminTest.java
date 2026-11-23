import Pages.AdminPage;
import Pages.DashBoardPage;
import Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class AdminTest extends BaseTest{
    LoginPage loginpage;
    DashBoardPage dashboardpage;
    AdminPage adminpage;

    @BeforeMethod
    public void setup()
    {
        loginpage=new LoginPage(driver);
        dashboardpage = loginpage.validlogin();
        adminpage = new AdminPage(driver);
    }
    @Test
    public void VerifyAdmin_AddUserpage()
    {
        dashboardpage.selectMenuItem("Admin");
        adminpage.clickAdd();
        assertTrue(adminpage.isFieldDisplayed("User Role"));
        assertTrue(adminpage.isFieldDisplayed("Employee Name"));
        assertTrue(adminpage.isFieldDisplayed("Username"));
        assertTrue(adminpage.isFieldDisplayed("Password"));
    }

    @Test
    public void VerifyOrangeHRMbrandinglink()
    {
        dashboardpage.selectMenuItem("Admin");
        assertTrue(adminpage.GetOrangeHRMLinkText().contains("OrangeHRM, Inc"));
        assertTrue(adminpage.clickOrangeHRMlink().contains("orangehrm.com"));

    }
}
