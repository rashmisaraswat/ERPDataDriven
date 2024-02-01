package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtils {
 public static Properties conpro;
public static WebDriver driver;
@BeforeTest
public static  void setUp() throws Throwable
{
	conpro= new Properties();
	conpro.load(new FileInputStream("./PropertyFiles/Envinment.properties"));

	if(conpro.getProperty("browser").equalsIgnoreCase("chrome"))
	{
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	}
	else if(conpro.getProperty("browser").equalsIgnoreCase("Firefox"))
	{
	driver=new FirefoxDriver();
}
	else
	{
		Reporter.log("Browser valUe is not matching_",true);
	}
}

@AfterTest
public static void tearDown()
{
	driver.quit();
}
}
