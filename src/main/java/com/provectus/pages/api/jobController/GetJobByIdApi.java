package com.provectus.pages.api.jobController;

import com.google.gson.Gson;
import com.provectus.pages.entities.Job;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class GetJobByIdApi {

    public Job getJobById(String token, int id) throws IOException {

        Request request = new Request.Builder()
                .get()
                .url("https://freelance.lsrv.in.ua/api/job/" + id)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        assert response.code() == 200;
        System.out.println("GetUserById response code " + response.code());

        Gson gson = new Gson();
        Job jobById = gson.fromJson(response.body().string(), Job.class);

        return jobById;
    }
}
