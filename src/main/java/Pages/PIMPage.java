package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage extends BasePage {
    public PIMPage(WebDriver driver)
    {
        super(driver);
    }
    private By employeename=By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private By searchbutton=By.xpath("//button[@type='submit']");
    private By firstname=By.xpath("//div[@class='oxd-table-card']//div[@role='cell'][3]");
    private By norecordfound =By.xpath("//span[contains(normalize-space(),'No Records Found')]");
    private By addemployee= By.xpath("//a[text()='Add Employee']");
    private By employeelist =By.xpath("//a[text()='Employee List']");




    public void enteremployeename(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeename));
        driver.findElement(employeename).sendKeys(name);
    }

    public void clicksearch()
    {
        driver.findElement(searchbutton).click();
    }

    public String getFirstResultName() {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(firstname)).getText();
    }

    public boolean recordfoundmessage()
    {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(norecordfound)).isDisplayed();
    }
    public AddEmployeePage clickaddemployee()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addemployee)).click();
        return new AddEmployeePage(driver);
    }

    public boolean isloaded()
    {
        return wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
    }
    public void clickemployeelist()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeelist)).click();
    }


}
