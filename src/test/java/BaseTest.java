import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties props = new Properties();

    @BeforeMethod
    public void openBrowser() throws IOException {
        props.load(new FileInputStream("src/main/resources/config.properties"));

        String browser = props.getProperty("browser").trim();
        if (browser.equalsIgnoreCase("chrome")) {
            DriverManager.setDriver(new ChromeDriver());
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(props.getProperty("baseurl").trim());
    }


    @AfterMethod
    public  void CloseBrowser() {
        DriverManager.quitDriver();
}
}

