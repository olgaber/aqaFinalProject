package com.provectus.tests.api;

import com.provectus.pages.api.authController.SignInApi;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.api.jobController.CreateJobApi;
import com.provectus.pages.api.jobController.GetJobByIdApi;
import com.provectus.pages.api.jobController.ListAllJobsApi;
import com.provectus.pages.entities.Job;
import com.provectus.pages.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class JobControllerApiTests {

    @Test
    public Job createNewJob() throws IOException {

        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();

        User user = authControllerApiTests.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);

        CreateJobApi createJobApi = new CreateJobApi();

        Job job = fillJobData();
        Job newJob = createJobApi.createJob(token, job);

        //Assert.assertEquals(newJob.getTitle(), job.getTitle(), "Job titles should be the same!");
       return newJob;
    }

    @Test
    public void getJobById() throws IOException {

        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        GetJobByIdApi getJobByIdApi = new GetJobByIdApi();
        int id = 74;

        User user = authControllerApiTests.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);
        Job jobById = getJobByIdApi.getJobById(token, id);

        Assert.assertEquals(jobById.getId(), id, "Job ids should be the same!");
    }

    @Test
    public void listAllJobs() throws IOException {
        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        ListAllJobsApi listAllJobsApi = new ListAllJobsApi();

        User user = authControllerApiTests.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);

        listAllJobsApi.listAllJobs(token);
    }
/*
    @Test
    public void deleteJob() throws IOException {

        //---------- Create New Job ----------
        AuthControllerApiTests authControllerApiTests = new AuthControllerApiTests();
        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();

        User user = authControllerApiTests.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);

        CreateJobApi createJobApi = new CreateJobApi();

        Job job = fillJobData();
        createJobApi.createJob(token, job);

        // get job id?????

        //---------- Delete Created Job By Id -----------

        DeleteJobApi deleteJobApi = new DeleteJobApi();
        deleteJobApi.deleteJob(token, job.getId());

    }*/

    public Job fillJobData(){

        Job job = new Job();
        job.setId(0);
        job.setTitle("Data Engineer");
        job.setDescription("At Lyft, our mission is to improve people’s lives with the world’s best transportation. " +
                "To do this, we start with our own community by creating an open, inclusive, and diverse organization.");
        job.setPrice(1500);
        job.setUser("test pass12345");
        job.setNoOfComments(0);

        return job;
    }
}
