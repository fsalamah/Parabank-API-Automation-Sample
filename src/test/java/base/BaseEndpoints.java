package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.FrameworkConstants;
import utils.PropertiesHelper;

public class BaseEndpoints extends FrameworkBase {
	
	public static final  String BASE_WEB_URL = PropertiesHelper.get( FrameworkConstants.PROP_BASE_WEB_URL);
	public static final String BASE_API_URL = PropertiesHelper.get( FrameworkConstants.PROP_BASE_API_URL);

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
		sb.addHeader("origin", "https://parabank.parasoft.com");
		sb.addHeader("Cache-Control","no-cache");
		
		return sb.build();
	}


  public static ResponseSpecification GetApiResponseSpec()
  {
    ResponseSpecBuilder rb = new ResponseSpecBuilder();
    return rb.expectStatusCode(200).build();
  }

}
