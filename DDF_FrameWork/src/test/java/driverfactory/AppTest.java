package driverfactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import config.AppUtils;
import utilities.ExcelFileUtils;

public class AppTest extends AppUtils {
	String inputpath=	"./FileInput/LoginData.xlsx";
	String outputpath= "./FileOutput/DataDrivenResults.xlsx";
	ExtentReports report;
	ExtentTest loger;
	@Test
	public void startTest() throws Throwable
	{
		report=new ExtentReports("./target/reports/DataDriven.html");
	
		
		ExcelFileUtils xl=new ExcelFileUtils(inputpath);
		int rc=xl.rowCount("data");
		Reporter.log("no of row:="+rc);
		for(int i=1;i<=rc;i++)
		{
			loger=report.startTest("Login validation");
			String user= xl.getCellData("data", i, 0);
			String password=xl.getCellData("data", i, 1);
			//call adminLogin in to function Library
			boolean res=FunctionLibrary.adminLogin(user, password);
			if(res)
			{
				xl.setCellData("data", i, 2, "Login succses", outputpath);
				xl.setCellData("data", i, 3, "pass", outputpath);
			loger.log(LogStatus.PASS,"username and password are valid");
			}

			else
			{
				TakesScreenshot ts=(TakesScreenshot)driver;
				File screen=ts.getScreenshotAs(OutputType.FILE);
				File targ=new File("./screenshot/itration/"+i+"login.png");
				FileUtils.copyFile(screen, targ);
				xl.setCellData("data", i , 2, "Login fail", outputpath);
				xl.setCellData("data", i, 3, "Fail",outputpath);
				loger.log(LogStatus.FAIL,"uname and password are not valid");
			}
		report.endTest(loger);
		report.flush();
		} 
	}}
