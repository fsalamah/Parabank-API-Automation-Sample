package endpoints;
import base.BaseEndpoints;
import io.restassured.RestAssured;
import utils.FrameworkConstants;
import utils.SessionCookieHelper;

public class AdminEndpoints extends BaseEndpoints{

	public static final String DB_ENDPOINT ="/db.htm";
	public static final String ADMIN_PAGE_ENDPOINT = "/admin.htm";

/**
 * Initializes the account balance settings for Parabank.
 * 
 * This method performs a POST request to the Parabank admin page endpoint 
 * to set the initial account balance and other settings
 */
	public static void initializeAccountBalanceSettings()
	{
		logger.info("INITIALIZING PARABANK SETTINGS");
		
		String sessionId = SessionCookieHelper.getSessionCookie(BASE_WEB_URL+ADMIN_PAGE_ENDPOINT);
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
						.cookie(FrameworkConstants.JSESSIONID,sessionId)
						.post(ADMIN_PAGE_ENDPOINT)
					.then()
						.statusCode(200);

	}

	public static void cleanDatabase()
	{
		logger.info("CLEANING UP THE SYSTEM DB");

		String sessionId = SessionCookieHelper.getSessionCookie(BASE_WEB_URL+ADMIN_PAGE_ENDPOINT);

		//Clean up the database
		RestAssured.given()
						.spec(getWebSpecs())
						.formParam("action", "CLEAN")
						.cookie(FrameworkConstants.JSESSIONID,sessionId)
						.post(DB_ENDPOINT)
					.then()
            			.statusCode(200);
	}
}
