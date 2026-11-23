package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashBoardPage extends BasePage{

    public DashBoardPage(WebDriver driver)
    {
        super(driver);
    }
    private By header=By.xpath("//h6[text()='Dashboard']");

    private By MenuItemLocator(String locator)
    {
        return By.xpath("//span[contains(@class, 'oxd-main-menu-item--name')" +
                " and normalize-space()='"+locator+"']");
    }

    public void selectMenuItem(String menuitem)
    {
        By item =MenuItemLocator(menuitem);
        wait.until(ExpectedConditions.visibilityOfElementLocated(item));
        driver.findElement(item).click();
    }

    public boolean isdisplayed()
    {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(header));
        return driver.findElement(header).isDisplayed();

    }

    public String GETMenuItemText(String menuitem)
    {
        By item =MenuItemLocator(menuitem);
        wait.until(ExpectedConditions.visibilityOfElementLocated(item));
        return driver.findElement(item).getText();
    }

}
