package restassured;

import dto.AllContactsDto;
import dto.ContactDto;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsRA extends HelperMethods{

    @Test
    public void getAllContacts(){
        AllContactsDto allContacts = given().header("Authorization",token)
                .when().get("contact")
                .then().assertThat().statusCode(200)
                .extract().response().as(AllContactsDto.class);

        List<ContactDto> list = allContacts.getContacts();
        for(ContactDto con:list){
            System.out.println(con.toString());
            System.out.println("***************");
        }
    }
}
