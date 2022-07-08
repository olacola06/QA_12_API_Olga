package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import okhttp3.*;
import org.testng.Assert;

import java.io.IOException;

public class HelpMethods {

    Gson gson = new Gson();
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";
    int j = (int) System.currentTimeMillis() / 1000 % 3600;

    public int createContact() throws IOException {
        ContactDto newContact = ContactDto.builder().id(0).email("gfr@gmail.com").name("John" + j).lastName("Dow" + (j - 10))
                .phone("035882" + j).address("Tel Aviv").description("Private").build();

        RequestBody body = RequestBody.create(gson.toJson(newContact), JSON);
        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/contact")
                .addHeader("Authorization", token).post(body).build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ContactDto contact = gson.fromJson(response.body().string(), ContactDto.class);
        int contactID = contact.getId();
        return contactID;
    }
}
