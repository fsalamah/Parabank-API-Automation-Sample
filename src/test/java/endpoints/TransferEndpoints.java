package endpoints;

import base.BaseEndpoints;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TransferEndpoints extends BaseEndpoints{
	private static final String ENDPOINT = "/bank/transfer?fromAccountId={accountId}&toAccountId={toAccountId}&amount={amount}";
	
	/**
	 * Transfer money between the customer accounts
	 * @param sourceAccount
	 * @param receivingAccountDetails
	 * @return 
	 */
	@Step("Transfere money between accounts")
	public static Response transfereBetweenAccounts(int sourceAccountId, int receivingAccountid, Double amount, String token) {
		
		return 				RestAssured.given()
									.spec(getApiSpecs())
									.cookie("JSESSIONID",token)
									.header("Accept-encoding", "gzip, deflate, br")
									.pathParam("accountId", sourceAccountId)
									.pathParam("toAccountId", receivingAccountid)
									.pathParam("amount", amount)
							.when()
									.post(ENDPOINT);
	}

	/**
	 * Transfer money between the customer accounts
	 * @param sourceAccount
	 * @param receivingAccountDetails
	 * @return 
	 */
	@Step("Transfere money between accounts")
	public static Response transfereBetweenAccountsWithoutToken(int sourceAccountId, int receivingAccountid, Double amount) {
		
		return 				RestAssured.given()
									.spec(getApiSpecs())
									.header("Accept-encoding", "gzip, deflate, br")
									.pathParam("accountId", sourceAccountId)
									.pathParam("toAccountId", receivingAccountid)
									.pathParam("amount", amount)
							.when()
									.post(ENDPOINT);
	}
}
