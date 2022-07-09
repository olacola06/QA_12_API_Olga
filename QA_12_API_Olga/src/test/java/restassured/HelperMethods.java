package restassured;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class HelperMethods {

    String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";

    @BeforeSuite
    public void init(){
        RestAssured.baseURI = "http://contacts-telran.herokuapp.com/";
        RestAssured.basePath = "api/";
    }
}
