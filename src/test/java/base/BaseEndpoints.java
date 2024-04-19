package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.FrameworkConstants;
import utils.PropertiesHelper;

public class BaseEndpoints {
	protected static final Logger logger = LoggerFactory.getLogger(BaseEndpoints.class);
	public static final  String BASE_WEB_URL = PropertiesHelper.get( FrameworkConstants.BASE_WEB_URL);
	public static final String BASE_API_URL = PropertiesHelper.get( FrameworkConstants.BASE_API_URL);
	
	public static  RequestSpecification getApiSpecs()
	{ 
		RequestSpecBuilder sb = new RequestSpecBuilder();
		sb.setBaseUri(BASE_API_URL);		
		sb.addFilter(new AllureRestAssured());	
		sb.addFilter(new RequestLoggingFilter());	
		sb.addHeader("Cache-Control","no-cache");	
		sb.addHeader("Accept-Encoding", "gzip, deflate, br");
		sb.setAccept(ContentType.JSON);		
		return sb.build();
	} 
	
	public static  RequestSpecification getWebSpecs()
	{ 
		RequestSpecBuilder sb = new RequestSpecBuilder();
		sb.setBaseUri(BASE_WEB_URL);		
		sb.addFilter(new AllureRestAssured());	
		sb.addFilter(new RequestLoggingFilter());
		sb.addHeader("Cache-Control","no-cache");		
		sb.setAccept(ContentType.JSON);		
		return sb.build();
	}
	
	//TODO:response spec to be added here to include standard assertions such as time taken
}
