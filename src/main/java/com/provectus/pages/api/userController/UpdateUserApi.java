package com.provectus.pages.api.userController;

import com.provectus.pages.Config;
import com.provectus.pages.entities.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;

public class UpdateUserApi {

    public void updateUser(User user, String token) throws IOException {

        JSONObject json = new JSONObject();
        json.put("id", user.getId());
        json.put("username", user.getUsername());
        json.put("name", user.getFirstName());
        json.put("lastname", user.getLastName());

        RequestBody body = RequestBody.create(json.toString().getBytes());

        Request request = new Request.Builder()
                .post(body)
                .url(Config.url + "api/user/update")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        System.out.println("Response body: " + response.body().string());
        System.out.println("Update user response code: " + response.code());

    }
}
