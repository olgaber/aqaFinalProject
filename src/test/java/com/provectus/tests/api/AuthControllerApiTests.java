package com.provectus.tests.api;

import com.github.javafaker.Faker;
import com.provectus.pages.api.authController.SignInApi;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.entities.User;
import com.provectus.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class AuthControllerApiTests extends BaseTest {

    @Test
    public void authorizeUserTest() throws IOException {
        SignUpApi signUpApi = new SignUpApi();

        User user = fillUserData();
        signUpApi.createNewUser(user);

        //String username = user.getUsername();
        //System.out.println(username);
        Assert.assertNotNull(user);

        SignInApi signInApi = new SignInApi();
        String token = signInApi.signIn(user);

        //System.out.println(token);

        Assert.assertTrue(token != null,
              "Newly created user is logged in successfully");
    }

    public User fillUserData(){
        Faker faker = new Faker();

        User user = new User();
        user.setId(null);
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setPassword(faker.regexify("[a-z1-9]{8}"));
        String password = user.getPassword();
        user.setConfirmPassword(password);

        return user;
    }
}
