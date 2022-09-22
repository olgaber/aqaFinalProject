package com.provectus.pages.api.jobController;

import com.provectus.pages.Config;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class DeleteJobApi {

    public String deleteJob(String token, int id) throws IOException {

        JSONObject json = new JSONObject();
        json.put("id", id);

        System.out.println("Job Id to be removed: " + id);

        RequestBody body = RequestBody.create(json.toString().getBytes());

        Request request = new Request.Builder()
                .post(body)
                .url(Config.url + "api/job/delete/" + id)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        assert response.code() == 200;

        JSONObject obj = new JSONObject(response.body().string());
        String message =  obj.get("message").toString();

        return message;
    }
}
