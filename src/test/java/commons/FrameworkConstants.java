package commons;

public class FrameworkConstants {
	
	//implemented TEST_FILE_PATH here for simplicity, should ideally be in a config file
	public static final String TEST_FILE_PATH =  "TEST_FILE_PATH";
	
	public static final String HTTP_STATUS_UNAUTHORIZED = "HTTP/1.1 401 UNAUTHORIZED";
	public static final String HTTP_STATUS_FORBIDDEN = "HTTP/1.1 403 FORBIDDEN";
	public static final String HTTP_STATUS_OK = "HTTP/1.1 200 OK";
	public static final String HTTP_STATUS_BAD_REQUEST = "HTTP/1.1 400 BAD REQUEST";
	public static final String JSESSIONID = "JSESSIONID";

	public static final String PROPS_PATH = "./src/test/resources/test.properties";

	public static final String BASE_API_URL = "BASE_API_URL";

	public static final String BASE_WEB_URL = "BASE_WEB_URL";

	public static final String MAXIMUM_RETRIES = "MAXIMUM_RETRIES";
	
}
