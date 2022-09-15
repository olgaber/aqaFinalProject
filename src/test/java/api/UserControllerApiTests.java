package api;

import com.provectus.pages.api.authController.SignInApi;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.api.userController.GetUserApi;
import com.provectus.pages.api.userController.GetUserByIdApi;
import com.provectus.pages.api.userController.UpdateUserApi;
import com.provectus.pages.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class UserControllerApiTests {

    @Test
    public void getUser() throws IOException {

        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        GetUserApi getUserApi = new GetUserApi();
        GetUserByIdApi getUserByIdApi = new GetUserByIdApi();

        User user = authControllerApiTests.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        //---------- Get Current User ---------

        String token = signInApi.signIn(newUser);

        User currentUser = getUserApi.getUser(token);
        //System.out.println(currentUser);
        Assert.assertEquals(user.getUsername(), currentUser.getUsername(),
               "Usernames should be the same");

        //---------- Get User By Id ---------

        String id = currentUser.getId();
        User userById = getUserByIdApi.getUserById(token, id);
        //System.out.println(userById);
        Assert.assertEquals(user.getUsername(), currentUser.getUsername(),
                "Usernames should be the same");
    }


    @Test
    public void updateUser() throws IOException {

        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        GetUserApi getUserApi = new GetUserApi();
        UpdateUserApi updateUserApi = new UpdateUserApi();

        User user = authControllerApiTests.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);
        User currentUser = getUserApi.getUser(token);

        String updatedUsername = "updated username";
        String updatedName = "updated name";
        String updatedLastName = "updated lastname";

        currentUser.setUsername(updatedUsername);
        currentUser.setFirstName(updatedName);
        currentUser.setLastName(updatedLastName);

        updateUserApi.updateUser(currentUser, token);

        Assert.assertEquals(currentUser.getUsername(), updatedUsername, "Username is not updated");
        Assert.assertEquals(currentUser.getFirstName(), updatedName, "First name us not updated");
        Assert.assertEquals(currentUser.getLastName(), updatedLastName, "Last name is not updated");
    }

}
