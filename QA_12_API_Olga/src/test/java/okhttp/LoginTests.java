package okhttp;

import com.google.gson.Gson;
import dto.Auth;
import dto.AuthResponse;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {

    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginSuccess() throws IOException {
        Auth auth = Auth.builder().email("Oollaa@gmail.com").password("Bo12345$").build();
        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);
        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/login")
                .post(body).build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        AuthResponse authRes = gson.fromJson(response.body().string(),AuthResponse.class);
        System.out.println(authRes.getToken().toString());
    }

    @Test
    public void loginWrongEmail() throws IOException {
        Auth auth = Auth.builder().email("Oollaa@gmailcom").password("Bo12345$").build();
        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);
        Request request = new Request.Builder().url("http://contacts-telran.herokuapp.com/api/login")
                .post(body).build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);

        ErrorDto error = gson.fromJson(response.body().string(),ErrorDto.class);
        Assert.assertEquals(error.getMessage(),"Wrong email format! Example: name@mail.com");
        System.out.println(error.getDetails().toString());

    }

}
