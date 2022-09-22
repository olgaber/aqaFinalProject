package com.provectus.pages.api.jobController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.provectus.pages.Config;
import com.provectus.pages.entities.Job;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ListAllJobsApi {

    public List<Job> listAllJobs(String token) throws IOException {

        Request request = new Request.Builder()
                .get()
                .url(Config.url + "api/job/all")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        assert response.code() == 200;

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Job>>(){}.getType(); //getting type of the collection
        List<Job> jobList = gson.fromJson(response.body().string(), listType);

        return jobList;
    }
}
