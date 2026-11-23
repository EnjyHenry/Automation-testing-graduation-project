import Pages.DashBoardPage;
import Pages.LoginPage;
import Pages.PIMPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddEmployeeTest extends BaseTest{
    LoginPage loginpage;
    PIMPage pimPage;
    DashBoardPage dashBoardPage;

    @BeforeMethod
    public void setup()
    {
        loginpage=new LoginPage(driver);
        pimPage=new PIMPage(driver);
        dashBoardPage = loginpage.validlogin();

    }
    @Test
    public void OpenAddEmployeepage()
    {
        dashBoardPage.selectMenuItem("PIM");
        var addemployee = pimPage.clickaddemployee();
        assertTrue(addemployee.isloaded());
        assertTrue(addemployee.firstname());
        assertTrue(addemployee.lasttname());
    }
    @Test
    public void AddNewemployeeWithanEmptyRequiredField()
    {
        dashBoardPage.selectMenuItem("PIM");
        var addemployee = pimPage.clickaddemployee();
        addemployee.enterlastname("peter");
        addemployee.clicksave();
        assertTrue(addemployee.validationerror());
    }
    @Test
    public void AddNewEmployeeSuccessfully()
    {
        dashBoardPage.selectMenuItem("PIM");
        var addemployee = pimPage.clickaddemployee();
        String Name = "Yara" + System.currentTimeMillis();
        addemployee.enterfirstname(Name);
        addemployee.enterlastname("mark");
        var pimpage = addemployee.clicksave();
        assertTrue(pimpage.isloaded());
        pimpage.clickemployeelist();
        pimpage.enteremployeename(Name);
        pimpage.clicksearch();
        assertTrue(pimpage.getFirstResultName().contains(Name));
    }
}

