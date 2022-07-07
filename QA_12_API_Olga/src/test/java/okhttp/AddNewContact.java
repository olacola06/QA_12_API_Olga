package okhttp;

import com.google.gson.Gson;
import dto.Auth;
import dto.ContactDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContact {
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    int j = (int) System.currentTimeMillis()/1000%3600;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";

    @Test
    public void addNewContactSuccess() throws IOException {

        ContactDto newContact = ContactDto.builder().id(0).email("gfr@gmail.com").name("John"+j).lastName("Dow"+(j-10))
                .phone("035882"+j).address("Tel Aviv").description("Private").build();

        RequestBody body = RequestBody.create(gson.toJson(newContact), JSON);
        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/contact")
                .get().addHeader("Authorization", token).post(body).build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),200);

        ContactDto contact = gson.fromJson(response.body().string(),ContactDto.class);
        int i = contact.getId();
        System.out.println("ID of new contact = "+i);

    }
}
