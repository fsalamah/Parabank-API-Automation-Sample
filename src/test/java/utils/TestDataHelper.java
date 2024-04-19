package utils;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import commons.FrameworkConstants;
import endpoints.AccountsEndpoint;
import endpoints.LoginEndpoint;
import endpoints.RegistrationEndpoints;
import models.Customer;
import models.CustomerAccount;

public class TestDataHelper {

	private static HashMap<String,String> userSessions = DataStore.getInstance().getUserSessions();
	private static HashMap<String,Integer> customerAccounts = DataStore.getInstance().getCustomerAccounts();
	
	/**
	 * Create test users and test customer accounts 
	 * @param customerAccountsData
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public static void setupInitialTestData() throws InvalidFormatException, IOException {
		
		// Get the data for Users and their customer accounts
		var usersTestData = ExcelUtil.readSheet("users", PropertiesHelper.get( FrameworkConstants.TEST_FILE_PATH));
		var customerAccountsTestData = ExcelUtil.readSheet("customer-accounts", PropertiesHelper.get( FrameworkConstants.TEST_FILE_PATH));		
		
		// loop throw all the rows from the user data sheet to create the users and their bank accounts
		for (var userDataObject : usersTestData) {								
					
					// get user registration details
					var userDetails = ExcelUtil.mapTestRow(userDataObject);
					var username = userDetails.get("username").toString();
					var password = userDetails.get("password").toString();
					
					//hit the registration end-point to register the user
				 	var sessionId = RegistrationEndpoints.registerUser(username,password);				

					//Get the user object through the login			
					var customer = LoginEndpoint.login(username,password,sessionId).getBody().as(Customer.class);
					
																					
					
					userSessions.put(username, sessionId);
					
					//Get the default customer default account
					CustomerAccount cusomerDefaultAccount = AccountsEndpoint.getCustomerAccounts(customer.getId(), sessionId).as(CustomerAccount[].class)[0];
					customerAccounts.put(username+".default",cusomerDefaultAccount.getId());		
				
					
					// Loop throw all the accounts to identify and create the customer accounts 
					for (var customerAccount : customerAccountsTestData) {
							
							//Get the customerAccount row as a Map object
							var customerAccountDetails = ExcelUtil.mapTestRow(customerAccount);	

							// Look for the accounts from the test data that belongs to the current customer and create them if any 										
							if( customerAccountDetails.get("username").toString().equals(userDetails.get("username").toString()))
							{								
									CustomerAccount newAccountDetails = 
									AccountsEndpoint.createCustomerAccount(	Integer.parseInt( customerAccountDetails.get("account-type").toString()), 
																			customer.getId(), 
																			cusomerDefaultAccount.getId(),sessionId)
																			.getBody().as(CustomerAccount.class);
									
										customerAccounts.put(username+"."+customerAccountDetails.get("account-alias").toString() ,newAccountDetails.getId());					
							}
					}
		}
	}
}
