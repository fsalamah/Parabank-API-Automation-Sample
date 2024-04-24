package utils;

import base.FrameworkBase;
import io.restassured.RestAssured;

public class SessionCookieHelper extends FrameworkBase {
    public static String getSessionCookie(String url)
    {   
            logger.info("Getting a session cookie from " + url);
            return RestAssured  .given()
                                    .get(url)
                                .then()
                                    .statusCode(200)
                                    .extract().cookie(FrameworkConstants.JSESSIONID);
    }
}
