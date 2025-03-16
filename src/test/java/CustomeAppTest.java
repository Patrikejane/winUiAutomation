import io.appium.java_client.windows.WindowsDriver;
import org.loollab.WinDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author sskma
 * @Created 17/03/2025 - 4:05 AM
 * @project uiautomation
 */
public class CustomeAppTest {

    WindowsDriver driver = null;
    public static String status = "passed";
    /* Application Path */
    public String appPath="D:\\CSharpDotNet\\publish\\HellowWorld.exe";

    @BeforeTest
    public void testSetUp() throws Exception
    {
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app",appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "Windows10Machine");

        /* Start WinAppDriver.exe so that it can start listening to incoming requests */
        WinDriver.start();

        Thread.sleep(5000);

        driver = new WindowsDriver(new URL ("http://127.0.0.1:4723/"), capability);
    }


    @Test (description="Demonstration of Button click", priority = 1)
    public void button_click_interactions() throws InterruptedException
    {
        driver.findElementByAccessibilityId("btnClickThis").click();

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        WebElement resultsElement = driver.findElementByAccessibilityId("lblHellowWorld");

        String resultantText = "Hellow World!";
        String resultsElementText = resultsElement.getText();

        /* Assert if the result  */
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
