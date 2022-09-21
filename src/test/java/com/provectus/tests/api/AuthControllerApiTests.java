package com.provectus.tests.api;

import com.provectus.pages.api.authController.SignInApi;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.entities.User;
import com.provectus.tests.BaseTest;
import com.provectus.tests.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class AuthControllerApiTests extends BaseTest {

    @Test
    public void authorizeUserTest() throws IOException {
        SignUpApi signUpApi = new SignUpApi();
        DataProvider dataProvider = new DataProvider();

        User user = dataProvider.fillUserData();
        signUpApi.createNewUser(user);

        Assert.assertNotNull(user);

        SignInApi signInApi = new SignInApi();
        String token = signInApi.signIn(user);

        Assert.assertTrue(token != null,
              "Newly created user is logged in successfully");
    }
}
