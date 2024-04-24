package base;


import java.io.StringWriter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.DataStore;
import utils.PropertiesHelper;

public class BaseTest extends FrameworkBase {

	protected static final DataStore store = DataStore.getInstance();
	
	
	protected static final long REQUEST_ALLOWED_TIME_MS = Integer.parseInt( PropertiesHelper.get("REQUEST_ALLOWED_TIME_MS"));
	
	@BeforeMethod
	public void beforeMethod(ITestResult result)
	{
		logger.info( "------- TEST METHOD: "+ result.getMethod().getMethodName() +" SRATED ---------");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		
		// in case of an exception log the method name followed by the exception
		if(result.getStatus() == ITestResult.FAILURE)
		{
			
			StringWriter error = new StringWriter();  		
			logger.error(result.getMethod().getMethodName());
			logger.error(result.getThrowable().getStackTrace().toString());
			
		}
		logger.info( "------- TEST METHOD: "+ result.getMethod().getMethodName() +" FINISHED ---------");
	}
	

	
}
