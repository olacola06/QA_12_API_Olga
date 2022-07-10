package restassured;

import okhttp.HelpMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactByID extends HelperMethods {

    @BeforeMethod
    public void preConditions() throws IOException {
        createNewContactGetID();
    }

    @Test
    public void deleteContactByIDSuccess(){
        given().header("Authorization",token)
                .when().delete("contact/"+idOfContact)
                .then().assertThat().statusCode(200)
                .assertThat().body("status",containsString("Contact was deleted!"));
    }
}
