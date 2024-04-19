package endpoints;

import java.util.Map;

import base.BaseEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import utils.FrameworkConstants;

public class AdminEndpoints extends BaseEndpoints{

	public static void initializeAccountBalanceSettings()
	{
		logger.info("INITIALIZING PARABANK SETTINGS");
		
		Map<String,String> cookies = RestAssured.given()
				.spec(getWebSpecs())
				.get("/admin.htm")
			.then().extract().cookies();
		
		
		RestAssured.given()
			.spec(getWebSpecs())
			.formParam("accessMode","restjson")
			.formParam("soapEndpoint",  "")
			.formParam("restEndpoint", "")
			.formParam("endpoint", "")
			.formParam("initialBalance","100000")
			.formParam("minimumBalance","1000")
			.formParam("loanProvider", "ws")
			.formParam("loanProcessorThreshold", "10")
			.cookies(cookies)
			.post("/admin.htm")
		.then()
			.statusCode(200);
		
	}
	
	public static void cleanDatabase()
	{
		logger.info("CLEANING UP THE SYSTEM DB");
		
		//get a session token
		var sessionId = RestAssured.given()
										.baseUri("https://parabank.parasoft.com")
										.get()
									.then()
										.extract()
										.cookie(FrameworkConstants.JSESSIONID);

		//Clean up the database 		
		RestAssured.given()
						.spec(getWebSpecs())
						.formParam("action", "CLEAN")
						.cookie("JSESSIONID",sessionId)
						.post("/db.htm")
					.then()
						.statusCode(200);
	}
}
