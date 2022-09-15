package com.provectus.pages.api.jobController;

import com.google.gson.Gson;
import com.provectus.pages.entities.Job;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class CreateJobApi {
    public Job createJob(String token, Job job) throws IOException {

        job.setId(0);
        job.setTitle(job.getTitle());
        job.setDescription(job.getDescription());
        job.setUser(job.getUser());
        job.setPrice(job.getPrice());
        job.setNoOfComments(job.getNoOfComments());

        Gson gson = new Gson();
        String json = gson.toJson(job);

        System.out.println("JOB >>> JSON: " + json.toString());

        RequestBody body = RequestBody.create(json.toString().getBytes());

        Request request = new Request.Builder()
                .post(body)
                .url("https://freelance.lsrv.in.ua/api/job/create")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        assert response.code() == 200;

        return job;
    }
}
