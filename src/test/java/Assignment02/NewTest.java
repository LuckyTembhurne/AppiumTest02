package Assignment02;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class NewTest{
	  public AndroidDriver driver;
	   
@BeforeClass
  public void beforeClass() throws MalformedURLException, InterruptedException {
			DesiredCapabilities capability= new DesiredCapabilities();
		    capability.setCapability("deviceName", "Lucky");
		    //capability.setCapability(MobileCapabilityType.NO_RESET, true);
		    capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		    capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");
		    capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.khanacademy.android.ui.library.MainActivity");
		    driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
		    Thread.sleep(15000);
	         
	   }	
  @Test
  public void test() throws InterruptedException, IOException {
      driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Dismiss\")")).click();
      Thread.sleep(3000);
      driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
      Thread.sleep(3000);
      driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Continue with Google\")")).click();
      Thread.sleep(5000);
      driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Add another account\")")).click();
      Thread.sleep(20000);
      
      File sf = new File("C:\\Users\\LuckyTembhurne\\Desktop\\SDET\\Appium\\TestData.xlsx");
      FileInputStream fis = new FileInputStream(sf);
      XSSFWorkbook wb = new XSSFWorkbook(fis);
      XSSFSheet sh = wb.getSheet("Sheet1");
      int rowcount =sh.getLastRowNum();
      for(int i=1;i<=rowcount;i++)
      {
    	  String username = sh.getRow(i).getCell(0).getStringCellValue();
    	  String password = sh.getRow(i).getCell(1).getStringCellValue();
    	  driver.findElement(MobileBy.className("android.view.View")).sendKeys(username);
	      driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Next\")")).click();
	      Thread.sleep(3000);
	      driver.findElement(MobileBy.className("android.view.View")).sendKeys(password);
	      driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Next\")")).click();
	      Thread.sleep(5000);
	      String actualText02 =  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Verify it's you\")")).getText();
	      System.out.println(actualText02);
	      String expectedText="Verify it's you";
	      Assert.assertEquals(actualText02, expectedText);
	      driver.pressKey(new KeyEvent(AndroidKey.BACK));
	      
	      }
  }

  @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
