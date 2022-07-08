package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.DeleteResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByID extends HelpMethods {

    int j = (int) System.currentTimeMillis() / 1000 % 3600;
    int contactID;

    @BeforeMethod
    public void createNewContact() throws IOException {
        contactID = createContact();
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
