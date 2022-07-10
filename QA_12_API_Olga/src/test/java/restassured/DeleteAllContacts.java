package restassured;

import dto.AllContactsDto;
import dto.ContactDto;
import dto.DeleteResponseDto;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteAllContacts extends HelperMethods {

    @Test
    public void deleteAllContacts() {
        given().header("Authorization", token)
                .when().delete("clear")
                .then().assertThat().statusCode(200)
                .assertThat().body("status", containsString("Deleted"));

    }

    @Test
    public void deleteAllContactsOneByOne() {
        AllContactsDto allContacts = given().header("Authorization", token)
                .when().get("contact")
                .then().assertThat().statusCode(200)
                .extract().response().as(AllContactsDto.class);

        List<ContactDto> list = allContacts.getContacts();
        System.out.println("Total contacts = " + list.size());
        int totalContacts = list.size();
        for (ContactDto con : list) {
            if(totalContacts>4){
                int idToDelete = con.getId();
                given().header("Authorization", token)
                        .when().delete("contact/" + idToDelete)
                        .then().assertThat().statusCode(200);
                totalContacts = totalContacts - 1;
            }
        }
    }
}
