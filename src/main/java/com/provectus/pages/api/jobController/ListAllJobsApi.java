package com.provectus.pages.api.jobController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ListAllJobsApi {

    public void listAllJobs(String token) throws IOException {

        Request request = new Request.Builder()
                .get()
                .url("https://freelance.lsrv.in.ua/api/job/all")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        assert response.code() == 200;
        System.out.println(response.code());
        //System.out.println(response.body().string());
    }
}
