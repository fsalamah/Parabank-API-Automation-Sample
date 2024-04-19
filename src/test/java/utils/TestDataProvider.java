package utils;

import java.io.IOException;

import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import commons.FrameworkConstants;
import io.restassured.RestAssured;

public class TestDataProvider {

	
	@DataProvider(name="test-data-provider")
	public Object[][] getData(ITestNGMethod methodContext) throws InvalidFormatException, IOException
	{				
		// implemented a direct mapping based on the method name for simplicity, for later it could use a mapping sheet 
		return ExcelUtil.readSheet( methodContext.getMethodName(), PropertiesHelper.get(FrameworkConstants.TEST_FILE_PATH));				
	}
	
	
	
	
}
