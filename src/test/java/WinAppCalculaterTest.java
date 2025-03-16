import org.loollab.WinDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.windows.WindowsDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WinAppCalculaterTest
{
    WindowsDriver driver = null;
    public static String status = "passed";
    /* Application Path */
    public String appPath="Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";

    @BeforeTest
    public void testSetUp() throws Exception
    {
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app",appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "Windows11Machine");

        /* Start WinAppDriver.exe so that it can start listening to incoming requests */
        WinDriver.start();

        Thread.sleep(3000);

        driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
    }

    @Test(description="Demonstration of Mouse Actions using ActionChains")
    public void test_mouse_interactions() throws InterruptedException
    {
        /* create an object for the Actions class and pass the driver argument */
        Actions action = new Actions(driver);

        /* Click on the Toggle Menu button */
        WebElement menuItem = driver.findElementByAccessibilityId("TogglePaneButton");
        action.click(menuItem);
        action.perform();

        /* Blocking Sleep  */
        Thread.sleep(3000);

        /* Click on the Scientific item in the Menu */
        WebElement scientific = driver.findElementByAccessibilityId("Standard");
        action.click(scientific);
        action.perform();

    }

    @Test(description="Demonstration of Button click", priority = 1)
    public void button_click_interactions() throws InterruptedException
    {

        /* Option 2: Click on Button - 1 by identifying it using AccessibilityID property */
        driver.findElementByAccessibilityId("num1Button").click();

        /* Use the Name property for locating the buttons and performing click operation */
        driver.findElement(By.name("Plus")).click();
        driver.findElement(By.name("Nine")).click();
        driver.findElement(By.name("Equals")).click();

        /* Blocking Sleep  */
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        /* The result should be 10, assert if the result pane does not show 10 */
        WebElement resultsElement = driver.findElementByAccessibilityId("CalculatorResults");

        String resultantText = "10";
        String resultsElementText = resultsElement.getText().replace("Display is","").trim();

        /* Assert if the result is not 10 */
        Assert.assertEquals(resultantText, resultsElementText);
    }

    @AfterTest
    public void tearDown()
    {
        if (driver != null)
        {
            /* WinDriver.stop(); */
            driver.quit();
        }
    }
}
