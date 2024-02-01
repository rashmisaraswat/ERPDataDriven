package commonFunction;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtils;

public class FunctionLibrary extends AppUtils {


public  static boolean adminLogin( String uname,String password)
{
	driver.get(conpro.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
	driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(uname);
	driver.findElement(By.xpath(conpro.getProperty("ObjPassw"))).sendKeys(password);
     driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
     String Expected="dashboard";
     String Actual=driver.getCurrentUrl();
     if(Actual.contains(Expected))
     {
    	 Reporter.log("username and password are valid=="+Expected+"-----"+Actual,true);
    	//click logout Link
    	 driver.findElement(By.xpath(conpro.getProperty("ObjLogOut"))).click();
    	 
    	 return true;
     }
     else
     {
    	String  errormsg= driver.findElement(By.xpath(conpro.getProperty("ObjEorrr_msg"))).getText();
    	driver.findElement(By.xpath(conpro.getProperty("ObjOkButton"))).click();
    	Reporter.log(errormsg+"-----"+Expected+" ----"+Actual,true);
     return false;
     }
}
public void add()
{
int a=32,b=43,c;
c=a+b;
System.out.println(c);
}}
