package endpoints;

import base.BaseEndpoints;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CustomerAccount;
import utils.FrameworkConstants;

public class AccountsEndpoint extends BaseEndpoints {

	public static final String ENDPOINT_ACCOUNT_BY_ID ="/bank/accounts/{accountId}";
	public static final String ENDPOINT_CREATE_ACCOUNT ="/bank/createAccount?customerId={customerId}&newAccountType={accountType}&fromAccountId={accountId}";


	/**
	 * Gets the customer account by id
	 * @param accountId provide the customer account id
	 * @return
	 */
	@Step("Get Account by id {accountId}")
	public static Response getAccountById(int accountId, String sessionId) {

    return RestAssured.given()
                          .spec(getApiSpecs())
                          .cookie(FrameworkConstants.JSESSIONID, sessionId)
                          .accept(ContentType.JSON)
                          .pathParam("accountId", accountId)
                          .get(ENDPOINT_ACCOUNT_BY_ID)
                      .then()
                          .spec(GetApiResponseSpec())
                          .extract()
                          .response();
	}

	public static CustomerAccount getAccountObjById(int accountId, String sessionId)
	{
		return getAccountById(accountId, sessionId).as(CustomerAccount.class);


	}
	public static double getBalanceById(int accountId, String sessionId)
	{
		return getAccountById(accountId, sessionId).as(CustomerAccount.class).getBalance();
	}



	/**
	 * Creates a new customer account
	 *
	 * @param accounts
	 * @param accountType
	 * @param customerId
	 * @param sourceAccountId the source account from which the new account will be
	 *                        funded
	 * @return a CustomerAccount object with the new account
	 */
	@Step("Create Customer Account account type {accountType}")
	public static Response createAccount(int accountType, int customerId, int sourceAccountId, String sessionId) {
		logger.info("Create account endpoint call starting");
		return RestAssured.given()
							.spec(getApiSpecs())
							.cookie(FrameworkConstants.JSESSIONID, sessionId)
							.pathParam("customerId", customerId)
							.pathParam("accountType", accountType)
							.pathParam("accountId", sourceAccountId)
						.when()
											.post(ENDPOINT_CREATE_ACCOUNT)
						.then()
							.spec(GetApiResponseSpec())
							.extract()
							.response();

	}

}
