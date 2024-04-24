package endpoints;

import base.BaseEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.FrameworkConstants;
import utils.SessionCookieHelper;

public class LoginEndpoint extends BaseEndpoints{

	public static final String LOGIN_ENDPOINT="/bank/login/{username}/{password}";
	public static Response login(String username, String password) {
		return login(username,password,SessionCookieHelper.getSessionCookie(BASE_WEB_URL+ LOGIN_ENDPOINT));
	}


	public static Response login(String username, String password, String sessionId) {
    logger.info("Login endpoint call started");

		return RestAssured.given()
                        .spec(getApiSpecs())
                        .cookie(FrameworkConstants.JSESSIONID, sessionId)
                        .pathParam("username", username)
                        .pathParam("password", password)
                      .when()
                        .get(LOGIN_ENDPOINT)
                      .then()
                        .extract()
                        .response();
	}

	

}
