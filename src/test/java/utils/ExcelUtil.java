package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.groovy.vmplugin.v10.Java10;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import endpoints.RegistrationEndpoints;

public class ExcelUtil {

    	  
    	  private static final String FILE = "./src/test/resources/test.xlsx";
    	  private static final DataFormatter dataFormatter = new DataFormatter();
  

   

    	  public static Map<String,Object> mapTestRow(Object[] testRow)
    	  {
    		  return (Map<String,Object>)testRow[0];
    		  
    	  }
    	  public static Object[][] readSheet(String sheetName, String fileLocation) throws IOException, InvalidFormatException {
    	    Workbook workbook = WorkbookFactory.create(new File(fileLocation));
    	    Sheet sheet = workbook.getSheet(sheetName);
    	    Iterable<Row> rows = sheet::rowIterator;
    	    List<Map<String, String>> results = new ArrayList<>();
    	    boolean header = true;
    	    List<String> keys = null;
    	    for (Row row : rows) {
    	      List<String> values = getValuesInEachRow(row);
    	      if (header) {
    	        header = false;
    	        keys = values;
    	        continue;
    	      }
    	      results.add(transform(keys, values));
    	    }
    	    return asTwoDimensionalArray(results);
    	  }
    	  
    	  private static Object[][] asTwoDimensionalArray(List<Map<String, String>> interimResults) {
    	    Object[][] results = new Object[interimResults.size()][1];
    	    int index = 0;
    	    for (Map<String, String> interimResult : interimResults) {
    	      results[index++] = new Object[] {interimResult};
    	    }
    	    return results;
    	  }

    	  private static Map<String, String> transform(List<String> names, List<String> values) {
    	    Map<String, String> results = new HashMap<>();
    	    for (int i = 0; i < names.size(); i++) {
    	      String key = names.get(i);
    	      String value = values.get(i);
    	      results.put(key, value);
    	    }
    	    return results;
    	  }

    	  private static List<String> getValuesInEachRow(Row row) {
    	    List<String> data = new ArrayList<>();
    	    Iterable<Cell> columns = row::cellIterator;
    	    for (Cell column : columns) {
    	    	
    	      data.add(dataFormatter.formatCellValue(column));
    	    }
    	    return data;
    	  }
}


