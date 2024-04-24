package utils;

import java.util.HashMap;

public class DataStore {
// This class is a thread-safe singleton to share required test data across methods 
	    private static volatile DataStore instance;
	    
		
		private static HashMap<String, String> userSessions ;
		private static HashMap<String, Integer> customerAccounts ;	

		public synchronized HashMap<String, String> getUserSessions() {
			return userSessions;
	    }
		
		public synchronized HashMap<String, Integer> getCustomerAccounts() {
	        return customerAccounts;
	    }
	   
		
		   
	    private DataStore() {
	    	userSessions = new HashMap<String, String>();
            customerAccounts = new HashMap<String, Integer>();
	    }

	    public static DataStore getInstance() {
	        if (instance == null) {
	            synchronized (DataStore.class) {
	                if (instance == null) {
	                    instance = new DataStore();
	                    
	                    
	                }
	            }
	        }
	        return instance;
	    }

	   
	
}
