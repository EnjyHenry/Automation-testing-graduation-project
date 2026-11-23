import Pages.DashBoardPage;
import Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DashBoardTest extends BaseTest{
    LoginPage loginpage;
    DashBoardPage dashBoardpage;

    @BeforeMethod
    public void setup()
    {
        loginpage=new LoginPage(driver);
        dashBoardpage=loginpage.validlogin();
    }

    @Test
    public void VerifysidebarmenuUI()
    {
        String[] items = {"Admin", "PIM", "Leave", "Time", "Recruitment",
                "My Info", "Performance", "Dashboard", "Directory"};
        for (String name:items)
        {
            assertTrue(dashBoardpage.GETMenuItemText(name).contains(name));
        }

    }
}
