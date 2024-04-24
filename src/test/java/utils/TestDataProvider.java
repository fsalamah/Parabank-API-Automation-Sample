package utils;

import java.io.IOException;

import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import base.FrameworkBase;

public class TestDataProvider extends FrameworkBase {

	
	@DataProvider(name="test-data-provider")
	public Object[][] getData(ITestNGMethod methodContext) throws InvalidFormatException, IOException
	{				
		logger.info("Getting data from Data provider: test-data-provider started");

		// implemented a direct mapping based on the method name for simplicity, for later it could use a mapping sheet 
		return ExcelUtil.readSheet( methodContext.getMethodName(), PropertiesHelper.get(FrameworkConstants.PROP_TEST_FILE_PATH));				
	}
	
	
	
	
}
