package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddEmployeePage extends BasePage {
    public AddEmployeePage(WebDriver driver)
    {
        super(driver);
    }

    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By saveButton=By.xpath("//button[normalize-space()='Save']");
    private By requiredmsg = By.xpath("//span[text()='Required']");
    private By loader = By.className("oxd-form-loader");

    public boolean isloaded()
    {
        return wait.until(ExpectedConditions.urlContains("/pim/addEmployee"));
    }

    public boolean firstname()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).isDisplayed();
    }
    public boolean lasttname()
    {
        return driver.findElement(lastName).isDisplayed();
    }
    public void enterlastname(String name)
    {

        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(name);
    }
    public void enterfirstname(String name)
    {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(name);
    }

    public PIMPage clicksave()
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return new PIMPage(driver);
    }


    public boolean validationerror()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredmsg)).isDisplayed();
    }
}
