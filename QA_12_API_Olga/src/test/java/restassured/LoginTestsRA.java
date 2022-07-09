package restassured;

import com.jayway.restassured.RestAssured;
import dto.Auth;
import dto.AuthResponse;
import dto.ErrorDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

public class LoginTestsRA extends HelperMethods {

//    @BeforeMethod //created in HelperMethods
//    public void init() {
//        RestAssured.baseURI = "http://contacts-telran.herokuapp.com/";
//        RestAssured.basePath = "api/";
//    }
    Auth auth = Auth.builder().email("Oollaa@gmail.com").password("Bo12345$").build();

    @Test
    public void loginTestSuccess() {
        AuthResponse response = given().body(auth)
                .contentType("application/json").when().post("login").then().assertThat()
                .statusCode(200).extract().response().as(AuthResponse.class);
        System.out.println(response.getToken().toString());

    }
    @Test
    public void loginWrongEmailTestNG(){
        auth.setEmail("Oollaa@gmailcom");
        ErrorDto response = given().body(auth).contentType("application/json")
                .when().post("login")
                .then()
                .assertThat().statusCode(400).extract().response().as(ErrorDto.class);
        Assert.assertEquals(response.getMessage(),"Wrong email format! Example: name@mail.com");

    }
    @Test
    public void loginWrongPasswordRA(){
        auth.setEmail("Ol@gmail.com");
        auth.setPassword("Bo12345%");
        given().body(auth).contentType("application/json")
                .when().post("login")
                .then().assertThat().statusCode(400)
                .assertThat().body("message",containsString("Password must contain at least one special symbol from ['$','~','-','_']!"));
    }
}
