package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static String readExcel(String filePath, String fileName, String sheetName,int c) throws IOException {

		

		File file = new File(filePath + "\\" + fileName);


		FileInputStream inputStream = new FileInputStream(file);

		Workbook excelbook = null;

		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		

		if (fileExtensionName.equals(".xlsx")) {


			excelbook = new XSSFWorkbook(inputStream);

		}

	


		Sheet excelsheet = excelbook.getSheet(sheetName);


		int rowCount = excelsheet.getLastRowNum() - excelsheet.getFirstRowNum();

		
		return String.valueOf(excelsheet.getRow(0).getCell(c));

	}

}
