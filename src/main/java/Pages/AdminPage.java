package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage{
    public AdminPage(WebDriver driver)
    {
        super(driver);
    }

    private By addbutton =By.xpath("//button[normalize-space()='Add']");
    private By orangelink=By.linkText("OrangeHRM, Inc");

    private By labellocator(String name)
    {
        return By.xpath("//label[normalize-space()='"+name+"']");
    }

    public void clickAdd()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addbutton)).click();
    }


    public boolean isFieldDisplayed(String label) {
     return wait.until(ExpectedConditions.visibilityOfElementLocated(labellocator(label))).isDisplayed();
    }
    public String GetOrangeHRMLinkText()
    {
        String text= wait.until(ExpectedConditions.visibilityOfElementLocated(orangelink)).getText();
        return text;
    }
    public String clickOrangeHRMlink()
    {
        String parent = driver.getWindowHandle();
        driver.findElement(orangelink).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
            }
        }

        wait.until(ExpectedConditions.urlContains("orangehrm.com"));
        return driver.getCurrentUrl();
    }







}
