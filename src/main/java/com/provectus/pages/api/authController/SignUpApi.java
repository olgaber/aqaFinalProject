package com.provectus.pages.api.authController;
import com.provectus.pages.entities.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;

import java.io.IOException;

public class SignUpApi {

    public User createNewUser(User user) throws IOException {

        user.setId(null);
        user.setUsername(user.getUsername());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setPassword(user.getPassword());
        String password = user.getPassword();
        user.setConfirmPassword(password);

        Gson gson = new Gson();
        String json = gson.toJson(user);

        //System.out.println("JSON" + json.toString());

        RequestBody body = RequestBody.create(json.getBytes());
        Request request = new Request.Builder()
                .post(body)
                .url("https://freelance.lsrv.in.ua/api/auth/signup")
                .addHeader("Content-Type", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

       assert response.code() == 200;
       return user;
    }
}
