package utils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import base.FrameworkBase;

public class RetryListener  extends FrameworkBase implements IRetryAnalyzer  {
	private int count = 0;
	private int retries = Integer.parseInt( PropertiesHelper.get(FrameworkConstants.PROP_MAXIMUM_RETRIES));

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