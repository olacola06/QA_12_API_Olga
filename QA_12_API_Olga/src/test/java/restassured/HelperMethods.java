package restassured;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.jayway.restassured.RestAssured;
import dto.Auth;
import dto.AuthResponse;
import dto.ContactDto;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class HelperMethods {

    String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";
    int idOfContact;
    int i = (int) System.currentTimeMillis()/1000%3600;
    ContactDto contact;
    ContactDto newContact = ContactDto.builder().name("Ivan"+i).lastName("Perov")
            .email("Dog@mail.ru").phone("035689"+i).address("Rome").description("Friend")
            .build();

    @BeforeSuite
    public void init(){
        RestAssured.baseURI = "http://contacts-telran.herokuapp.com/";
        RestAssured.basePath = "api/";
    }

    public void createNewContactGetID(){
           idOfContact = given().body(newContact).contentType("application/json")
                .header("Authorization",token)
                .when().post("contact")
                .then().assertThat().statusCode(200).extract()
                .path("id");
    }
    public void createNewContact(){
        contact = given().body(newContact).contentType("application/json")
                .header("Authorization",token)
                .when().post("contact")
                .then().assertThat().statusCode(200).extract()
                .as(ContactDto.class);
        idOfContact = contact.getId();
        System.out.println("New contact with created:->>"+contact.toString());
    }
}
