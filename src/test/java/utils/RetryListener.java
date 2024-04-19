package utils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import commons.FrameworkBase;
import commons.FrameworkConstants;

public class RetryListener  extends FrameworkBase implements IRetryAnalyzer  {
	private int count = 0;
	private int retries = Integer.parseInt( PropertiesHelper.get(FrameworkConstants.MAXIMUM_RETRIES));

	@Override
	public boolean retry(ITestResult result) {

		boolean value = false;

			if (count < retries) {
				
				count++;
				
				logger.info("RETRYING TEST CASE: " + result.getMethod().getMethodName() );
				logger.info("RETRY #"+count );
				
				return true;
			}

		return value;
	}
}