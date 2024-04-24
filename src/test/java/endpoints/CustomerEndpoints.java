package endpoints;

import base.BaseEndpoints;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.FrameworkConstants;

public class CustomerEndpoints extends BaseEndpoints {
    public static final String ENDPOINT_CUSTOMER_ACCOUNTS = "/bank/customers/{customerId}/accounts";
    public static final String ENDPOINT_CUSTOMER_DETAILS = "/customers/{customerId}";
    
    /**
     * Gets the all customer accounts
     *
     */
    @Step("Get customer accounts")
    public static Response getAccounts(int customerId, String sessionId) {
        
        logger.info("getting customer accounts for customer " + customerId + " with the session id "+ sessionId);

        return RestAssured.given()
                .cookie(FrameworkConstants.JSESSIONID, sessionId)
                .spec(getApiSpecs())
                .pathParam("customerId", customerId)
                .when()
                .get(ENDPOINT_CUSTOMER_ACCOUNTS)
                .then()
                .spec(GetApiResponseSpec())
                .extract()
                .response();

    }
    
      /**
     * Gets the all customer accounts
     *
     */
    @Step("Get customer details")
    public static Response getCustomerDetails(int customerId, String sessionId) {
        
        logger.info("getting customer details for customer " + customerId + " with the session id "+ sessionId);

        return RestAssured.given()
                .cookie(FrameworkConstants.JSESSIONID, sessionId)
                .spec(getApiSpecs())
                .pathParam("customerId", customerId)
                .when()
                .get(ENDPOINT_CUSTOMER_DETAILS)
                .then()
                .spec(GetApiResponseSpec())
                .extract()
                .response();

    }
    
    
}
