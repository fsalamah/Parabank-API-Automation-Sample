package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.AccountsEndpoint;
import endpoints.AdminEndpoints;
import endpoints.TransferEndpoints;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import utils.DataStore;
import utils.FrameworkConstants;
import utils.RetryListener;
import utils.TestDataHelper;
import utils.TestDataProvider;


public class TestTransfer extends BaseTest {

	private static HashMap<String,String> userSessions = DataStore.getInstance().getUserSessions();
	private static HashMap<String,Integer> customerAccounts = DataStore.getInstance().getCustomerAccounts();

	@BeforeClass
	public void Setup() throws IOException {

		// Clean up the database
		AdminEndpoints.cleanDatabase();

		// initialize account balances since the test relies on these
		AdminEndpoints.initializeAccountBalanceSettings();

		//Setup the data for the tests (users and accounts)
		TestDataHelper.setupInitialTestData();

	}





	@Test( testName = "Verify Transfer Positive Cases",
			dataProvider 	= "test-data-provider",
			dataProviderClass = TestDataProvider.class,
			priority = 0, groups = {"regression", "smoke"},
			retryAnalyzer = RetryListener.class)
	public void verifyTransferPositiveCases(Map<String, String> testData) {

		Allure.description(testData.get("description"));

	    AllureLifecycle lifecycle = Allure.getLifecycle();
	    lifecycle.updateTestCase(testResult -> testResult.setName(testData.get("description")));

		//get the user token
		var sessionId = userSessions.get(testData.get("username"));

		//get the sending account id
		int sendAccountId = customerAccounts.get( testData.get("to-account"));
		//get the source account id
		int receiveAccountId = customerAccounts.get(testData.get("from-account"));


		//get the send account balance to check the funds before/after transfer
		double sendAccountBalanceBefore 	= AccountsEndpoint.getBalanceById(sendAccountId, sessionId);
		//get the source account balance to check the funds before/after transfer
		double receiveAccountBalanceBefore 	= AccountsEndpoint.getBalanceById(receiveAccountId, sessionId);



		double transferAmount 			= Double.parseDouble(testData.get("amount"));
		double expectedTransferAmount 	= Double.parseDouble(testData.get("expected-transfered-amount"));


		// perform the transfer and assert response code, perform response regex assertion,
		TransferEndpoints.transfereBetweenAccounts(sendAccountId, receiveAccountId, transferAmount, sessionId)
			.then()
				.body(Matchers.matchesRegex("Successfully transferred \\$.* from account #" + sendAccountId + " to account #" + receiveAccountId)) // assert regex response body
				.assertThat().statusLine(FrameworkConstants.HTTP_STATUS_OK) // assert status line
				.assertThat().statusCode(200)// assert status code
				.assertThat().time(Matchers.lessThan( REQUEST_ALLOWED_TIME_MS)); //assert time taken



		// Get the updated accounts after the transfer
		double receiveAccountBalanceAfter = AccountsEndpoint.getBalanceById(receiveAccountId, sessionId);
		double SendAccountBalanceAfter = AccountsEndpoint.getBalanceById(sendAccountId, sessionId);


		// Verify the amount was deducted from the original account
		Assert.assertEquals(SendAccountBalanceAfter,(sendAccountBalanceBefore - expectedTransferAmount),
								"Verify that the amount was deducted from the sending account, Original balance "
								+ sendAccountBalanceBefore + ", new balance "
								+ SendAccountBalanceAfter);

		// The balance of the receiving account before the transfer + expected transfer amount must equal the updated balance in the receiving account
		Assert.assertEquals((receiveAccountBalanceBefore + expectedTransferAmount),receiveAccountBalanceAfter,
						"Verify that the correct amount was credited to the receiving account, old balance "
						+ receiveAccountBalanceBefore + " - new Balance "
						+ receiveAccountBalanceAfter);



	}










}
