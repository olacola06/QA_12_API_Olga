package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.DeleteResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByID {

    Gson gson = new Gson();

    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";
    int j = (int) System.currentTimeMillis() / 1000 % 3600;
    int contactID;

    @BeforeMethod
    public void createNewContact() throws IOException {
        ContactDto newContact = ContactDto.builder().id(0).email("gfr@gmail.com").name("John" + j).lastName("Dow" + (j - 10))
                .phone("035882" + j).address("Tel Aviv").description("Private").build();

        RequestBody body = RequestBody.create(gson.toJson(newContact), JSON);
        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/contact")
                .get().addHeader("Authorization", token).post(body).build();

        Response response = client.newCall(request).execute();
        ContactDto contact = gson.fromJson(response.body().string(), ContactDto.class);
        contactID = contact.getId();
    }

    @Test
    public void deleteContactByIDSuccess() throws IOException {

        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/contact/"+contactID)
                .delete().addHeader("Authorization", token).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        DeleteResponseDto contactDeleted = gson.fromJson(response.body().string(), DeleteResponseDto.class);
        Assert.assertEquals(contactDeleted.getStatus(), "Contact was deleted!");
        System.out.println("Contact with ID ="+contactID+" was deleted");

    }

}
