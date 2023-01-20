package DataDriven_Framework.Datadriven;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class Excel_dataDriven {

	public ArrayList<String> getData(String testCaseName) throws Throwable {
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream file = new FileInputStream("C:\\Automation-Selenium\\Datadriven\\DemoData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		int sheets = workBook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("testData")) {
				XSSFSheet sheet = workBook.getSheetAt(i);

				Iterator<Row> rows = sheet.rowIterator(); // Sheeet is a collection of Rows
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator(); // row is a collection of cells
				int j = 0;
				int column = 0;
				while (cells.hasNext()) {
					Cell values = cells.next();
					if (values.getStringCellValue().equalsIgnoreCase("testcases")) {
						column = j;
					}
					j++;
				}

				int k = 0;
				int row = 0;
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> pCell = r.cellIterator();
						int l = 0;
						while (pCell.hasNext()) {
							Cell p = pCell.next();
//							String name = p.getStringCellValue();
//							System.out.println(name);
							// a.add(pCell.next().getStringCellValue());

							// Suppose if data has both numeric and String then use If-else condition
							DataFormatter df = new DataFormatter();
							if (p.getCellType() == org.apache.poi.ss.usermodel.CellType.STRING) {
								a.add(p.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText( p.getNumericCellValue()));
							}

						}
						l++;

					}
					k++;
				}
			}
		}
		return a;

	}

	public static void main(String[] args) throws Throwable {

	}

}
