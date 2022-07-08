package okhttp;

import com.google.gson.Gson;
import dto.DeleteResponseDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContacts {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Ik9vbGxhYUBnbWFpbC5jb20ifQ.DRvj59OcD8Fm4JNHvdPlsAaX2pXmDVv-w6OtT3s5NTo";

    @Test
    public void deleteAllContacts() throws IOException {

        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/clear")
                .delete().addHeader("Authorization",token).build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        DeleteResponseDto deletedAll = gson.fromJson(response.body().string(),DeleteResponseDto.class);
        //Assert.assertEquals(deletedAll.getStatus(),"");
        System.out.println(deletedAll);
    }

}
