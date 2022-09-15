package com.provectus.pages.api.authController;

import com.provectus.pages.entities.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;

public class SignInApi {

    public String signIn(User user) throws IOException {

        JSONObject json = new JSONObject();
        json.put("username", user.getUsername());
        json.put("password", user.getPassword());

        RequestBody body = RequestBody.create(json.toString().getBytes());

        Request request = new Request.Builder()
                .post(body)
                .url("https://freelance.lsrv.in.ua/api/auth/signin")
                .addHeader("Content-Type", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        JSONObject responseJson = new JSONObject(responseBody);
        //System.out.println(responseJson.get("token").toString());

        assert response.code() == 200;
        assert responseJson.get("token").toString() != null;

        return responseJson.get("token").toString();
    }
}
