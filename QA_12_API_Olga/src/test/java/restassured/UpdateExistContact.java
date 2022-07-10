package restassured;

import dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateExistContact extends HelperMethods {

//    ContactDto contact = ContactDto.builder().name("Ivan").lastName("Ivanov")
//                .email("Dogdy@mail.ru").phone("03568945").address("Paris").description("Friends")
//                .build();


    @BeforeMethod
    public void preConditions(){
//        ContactDto response = given().body(contact).contentType("application/json").header("Authorization",token)
//                .when().post("contact")
//                .then().assertThat().statusCode(200)
//                .extract().response().body().as(ContactDto.class);
        createNewContact();
    }

    @Test
    public void updateExistContactSuccess(){

        contact.setId(idOfContact);
        contact.setLastName("Sokolov");
        ContactDto updatedContact = given().body(contact).contentType("application/json").header("Authorization",token)
                .when().post("contact")
                .then().assertThat().statusCode(200)
                .assertThat().body("lastName",containsString("Sokolov"))
                .extract().response().body().as(ContactDto.class);

    }
}
