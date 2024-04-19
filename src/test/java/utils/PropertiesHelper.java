package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.mongodb.diagnostics.logging.Logger;

import base.FrameworkBase;

public class PropertiesHelper extends FrameworkBase{

	public static String get(String property) {
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(FrameworkConstants.PROPS_PATH);

			// load a properties file
			
			prop.load(input);
		} catch (Exception e) {
			
			logger.error("FAILED TO READ PROPERTIES FROM FILE " +FrameworkConstants.PROPS_PATH ,e);
			
		}

		return prop.getProperty(property);
	}
}
