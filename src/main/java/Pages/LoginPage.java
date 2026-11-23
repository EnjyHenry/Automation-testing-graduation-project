package Pages;

import Utils.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {
    DataProvider dataProvider =new DataProvider();

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    private By Username = By.name("username");
    private By Password = By.name("password");
    private By Login = By.xpath("//button[text()=' Login ']");
    private By errormessage = By.xpath("//p[text()='Invalid credentials']");
    private By Requiredmessage = By.xpath("//span[text()='Required']");

    public void enterusername(String username)
    {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Username));
        driver.findElement(Username).clear();
        driver.findElement(Username).sendKeys(username);

    }
    public void enterpassword(String password)
    {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Password));
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys(password);

    }

    public boolean isloaded_Dashboard()
    {
       return wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    public boolean Invalidcredentials()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errormessage)).isDisplayed();
    }
    public int Requiredmessage()
    {
        List<WebElement> msg = driver.findElements(Requiredmessage);
        List<String> text=new ArrayList<>();

        for (WebElement element:msg)
        {
           text.add(element.getText());
        }
        return text.size();
    }
    public DashBoardPage clicklogin()
    {
        driver.findElement(Login).click();
        return new DashBoardPage(driver);
    }

    public DashBoardPage validlogin()
    {
        enterusername(dataProvider.jsonreader("validusername"));
        enterpassword(dataProvider.jsonreader("validpassword"));
        driver.findElement(Login).click();
        return new DashBoardPage(driver);
    }





}
