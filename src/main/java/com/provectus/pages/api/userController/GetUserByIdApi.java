package com.provectus.pages.api.userController;
import com.google.gson.Gson;
import com.provectus.pages.Config;
import com.provectus.pages.entities.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class GetUserByIdApi {

    public User getUserById(String token, int id) throws IOException {

        Request request = new Request.Builder()
                .get()
                .url(Config.url + "api/user/" + id)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        assert response.code() == 200;

        Gson gson = new Gson();
        User userById = gson.fromJson(response.body().string(), User.class);
        //System.out.println("User Id: " + userById.getId());

        return userById;
    }
}
