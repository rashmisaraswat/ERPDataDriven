package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public  class ExcelFileUtils {
	Workbook wb;
	//constructor for reading excel File
	public ExcelFileUtils(String Excelpath) throws Throwable
	{
		FileInputStream fi=new FileInputStream(Excelpath);
		wb=WorkbookFactory.create(fi);

	}
	public int rowCount(String Sheetname)
	{
		return wb.getSheet(Sheetname).getLastRowNum();

	}
	public String getCellData(String Sheetname,int row,int colom) 
	{
		String data="";
		if(wb.getSheet(Sheetname).getRow(row).getCell(colom).getCellType()==CellType.NUMERIC)
		{
			int celldata=(int) wb.getSheet(Sheetname).getRow(row).getCell(colom).getNumericCellValue();
			data=String.valueOf(celldata);
		}else 
		{
			data=wb.getSheet(Sheetname).getRow(row).getCell(colom).getStringCellValue();
		}
		return data;
	}

	public void setCellData(String Sheetname,int row,int colom,String status, String WriteExcel) throws Throwable {
		{

			Sheet ws=wb.getSheet(Sheetname);

			Row rowNum=ws.getRow(row);
			Cell cell=rowNum.createCell(colom);
			cell.setCellValue(status);
			if(status.equalsIgnoreCase("pass"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.GREEN.getIndex());
				font.setBold(true);
				style.setFont(font);
				ws.getRow(row).getCell(colom).setCellStyle(style);;
			}

			else if(status.equalsIgnoreCase("fail"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				style.setFont(font);
				ws.getRow(row).getCell(colom).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("blocked"));
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.BLUE.getIndex());
				font.setBold(true);
				style.setFont(font);
				ws.getRow(row).getCell(colom).setCellStyle(style);
			}
			FileOutputStream fo=new FileOutputStream("WriteExcel");
			wb.write(fo);
		}
	}
	public static void main(String[] args) throws Throwable {
		ExcelFileUtils xl=new ExcelFileUtils("C:\\Users\\Lenovo\\OneDrive\\Documents\\Book1.xlsx");
		int rc=xl.rowCount("emp");
		System.out.println(rc);
		for(int i=1;i<=rc;i++)
		{
			String fname=xl.getCellData("emp", i, 0);
			String mname=xl.getCellData("emp",i, 1);
			String lname=xl.getCellData("emp", i, 2);
			String empid=xl.getCellData("emp", i, 3);
			System.out.println(fname+"  "+mname+"  "+lname+" "+empid);
			xl.setCellData("emp", i, 4, "pass", "D:\results2.xlsx");
		}


	}
}
