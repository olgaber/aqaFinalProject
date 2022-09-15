package com.provectus.pages.api.userController;

import com.google.gson.Gson;
import com.provectus.pages.entities.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class GetUserApi {
    public User getUser(String token) throws IOException {

        Request request = new Request.Builder()
                .get()
                .url("https://freelance.lsrv.in.ua/api/user/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        //System.out.println("GetUser response code" + response.code());
        assert response.code() == 200;

        Gson gson = new Gson();
        User newUser = gson.fromJson(response.body().string(), User.class);

        return newUser;
    }
}
