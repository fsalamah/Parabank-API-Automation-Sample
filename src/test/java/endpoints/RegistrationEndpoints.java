package endpoints;

import base.BaseEndpoints;
import io.restassured.RestAssured;
import utils.FrameworkConstants;
import utils.SessionCookieHelper;

public class RegistrationEndpoints extends BaseEndpoints{
	
	public static final String REGISTER_ENDPOINT = "/register.htm";
	/*
	* Registers a user with the provided username and password.
	*
	* @param username The username of the user to register.
	* @param password The password of the user to register.
	* @return The session cookie obtained after successful registration.
	*/
	public static String registerUser(String username, String password )
	{

		var cookie = SessionCookieHelper.getSessionCookie(BASE_WEB_URL+ REGISTER_ENDPOINT);
		
		//Execute the user registration request
		return RestAssured	.given()
								.spec(getWebSpecs())
								.baseUri(BASE_WEB_URL)
								.cookie(FrameworkConstants.JSESSIONID,cookie)
								.contentType("application/x-www-form-urlencoded")
								.body("customer.firstName=user001&customer.lastName=user001"
										+ "&customer.address.street=user001&customer.address.city=user001"
										+ "&customer.address.state=user001&customer.address.zipCode=user001"
										+ "&customer.phoneNumber=user001&customer.ssn=user001"
										+ "&customer.username="+username+"&customer.password="+password+"&repeatedPassword="+password)
							.when()
								.post("/register.htm")
							.then()
								.extract()
								.cookie(FrameworkConstants.JSESSIONID);
	}


}
