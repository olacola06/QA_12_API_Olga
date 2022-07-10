package restassured;

import dto.Auth;
import dto.AuthResponse;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationTests extends HelperMethods {
    int i = (int) System.currentTimeMillis()/1000%3600;

    @Test
    public void registrationSuccess(){
        Auth auth = Auth.builder().email("Do"+i+"@gmail.com").password("Gg45678$").build();

        given().body(auth).contentType("application/json")
                .when().post("login")
                .then().assertThat().statusCode(200);

    }
    @Test
    public void registrationWrongEmail(){
        Auth auth = Auth.builder().email("Do@mailru").password("Gg45678$").build();

        given().body(auth).contentType("application/json")
                .when().post("login")
                .then().assertThat().statusCode(400)
                .assertThat().body("message",containsString("Wrong email format! Example: name@mail.com"));

    }
    @Test
    public void registrationWrongPassword(){
        Auth auth = Auth.builder().email("Do@mail.ru").password("Gg456$").build();

        given().body(auth).contentType("application/json")
                .when().post("login")
                .then().assertThat().statusCode(400)
                .assertThat().body("message",containsString("Password length need be 8 or more symbols"));

    }
    @Test
    public void registrationExistUser(){

        Auth auth = Auth.builder().email("Oollaa@gmail.com").password("Gg45689$").build();
        given().body(auth).contentType("application/json")
                .when().post("login")
                .then().assertThat().statusCode(401)
                .assertThat().body("message",containsString("Wrong email or password"));

    }

}
