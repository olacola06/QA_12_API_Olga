package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateExistContact extends HelpMethods {

    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";

    int idContact;

    ContactDto newContact = ContactDto.builder().email("allWo@gmail.com").name("Tom").lastName("Tomson")
            .phone("035882120").address("Ashdod").description("Business").build();

    @BeforeMethod
    public void createNewContact() throws IOException {
        RequestBody body = RequestBody.create(gson.toJson(newContact),JSON);
        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/contact")
                .header("Authorization",token).post(body).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ContactDto contact = gson.fromJson(response.body().string(),ContactDto.class);
        idContact = contact.getId();
        System.out.println("Contact with name:-->"+newContact.getName()+ " created");
    }

    @Test
    public void updateExistContact() throws IOException {
        newContact.setName("Tommy");
        newContact.setId(idContact);

        RequestBody body = RequestBody.create(gson.toJson(newContact),JSON);

        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/contact")
                .header("Authorization",token).put(body).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ContactDto contact = gson.fromJson(response.body().string(),ContactDto.class);
        Assert.assertEquals(contact.getName(),"Tommy");
        System.out.println("Contact name updated from->>Tom to->>" + contact.getName()+", Contact ID = "+contact.getId());

    }

    @AfterMethod
    public void postCondition() throws IOException {
        deleteByContactID(idContact);
    }
}
