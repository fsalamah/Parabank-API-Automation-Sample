package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

import base.FrameworkBase;

public class PropertiesHelper extends FrameworkBase{

	public static String get(String property) {
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(FrameworkConstants.PROPS_FILE_PATH );

			// load a properties file
			
			prop.load(input);
		} catch (Exception e) {
			//on exception abort the test run and exit with an error code
			logger.error("ABORTING TEST! - FAILED TO READ PROPERTIES FROM FILE " +FrameworkConstants.PROPS_FILE_PATH ,e);
			System.exit(100);
		}

		return prop.getProperty(property);
	}
}
