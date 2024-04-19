package endpoints;

import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import commons.BaseEndpoints;
import commons.FrameworkConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class LoginEndpoint extends BaseEndpoints{
	
	public static final String ENDPOINT="/bank/login/{username}/{password}"; 
	public static Response login(String username, String password) {
		return login(username,password, getSessionCookie());
	}
	
	
	public static Response login(String username, String password, String sessionId) {
		return RestAssured.given()
								.spec(getApiSpecs())
								.cookie(FrameworkConstants.JSESSIONID, sessionId)
								.pathParam("username", username)
								.pathParam("password", password).when()
							.get(ENDPOINT);
	}
	
	private static String getSessionCookie()
	{
		return RestAssured.given().baseUri(BASE_WEB_URL).get( "/index.htm").then().extract().cookie("JSESSIONID");
		
	}
	
}
