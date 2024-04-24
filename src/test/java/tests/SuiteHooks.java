package tests;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;

import endpoints.AdminEndpoints;
import io.qameta.allure.Allure;
import utils.TestDataHelper;

public class SuiteHooks {
    @BeforeSuite
	public void Setup() throws IOException {
        
        // Clean up the database
        Allure.step("Clean up the system db",()->AdminEndpoints.cleanDatabase());
        
        // initialize account balances since the test relies on these
        Allure.step("initialize account balances since the test relies on these",()-> AdminEndpoints.initializeAccountBalanceSettings());

        //Setup the data for the tests (users and accounts)
        Allure.step("Setup the data for the tests (users and accounts)" ,()->TestDataHelper.setupInitialTestData());

	}
}
