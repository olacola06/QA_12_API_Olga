package okhttp;

import com.google.gson.Gson;
import dto.AllContactsDto;
import dto.ContactDto;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllContacts {

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";

    @Test
    public void getAllContactsSuccess() throws IOException {

        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/contact")
                .get().addHeader("Authorization",token).build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        AllContactsDto allContacts = gson.fromJson(response.body().string(),AllContactsDto.class);
        List<ContactDto> contacts = allContacts.getContacts();
        for(ContactDto contact:contacts){
            System.out.println(contact.toString());
            System.out.println("*********************************");
        }
    }
}
