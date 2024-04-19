package endpoints;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.xmlbeans.impl.repackage.Repackage;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import commons.BaseEndpoints;
import commons.FrameworkConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RegistrationEndpoints extends BaseEndpoints{
	//
	
	public static String registerUser(String username, String password )
	{
		
		var cookie = getSessionCookie();
	
		//Execute the user registration request
		RestAssured.given()
						.baseUri(BASE_WEB_URL)
						.cookie(FrameworkConstants.JSESSIONID,cookie)
						.contentType("application/x-www-form-urlencoded")
						.body("customer.firstName=user001&customer.lastName=user001"
								+ "&customer.address.street=user001&customer.address.city=user001"
								+ "&customer.address.state=user001&customer.address.zipCode=user001"
								+ "&customer.phoneNumber=user001&customer.ssn=user001"
								+ "&customer.username="+username+"&customer.password="+password+"&repeatedPassword="+password)
						.post("/register.htm")
					.then()
						.assertThat().body(Matchers.stringContainsInOrder("Your account was created successfully. You are now logged in.")) ;
		return cookie;
		}
		
		
		
		public static String getSessionCookie()
		{
			return RestAssured.given().get(BASE_WEB_URL+ "/register.htm").then().extract().cookie(FrameworkConstants.JSESSIONID);
			
		}
	
}
