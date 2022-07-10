package restassured;

import org.testng.annotations.Test;

public class CreateNewContact extends HelperMethods {

    @Test(invocationCount = 5)
    public void createANewContact(){
        createNewContact();
    }
}
