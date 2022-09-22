package com.provectus.tests.api;

import com.provectus.pages.api.authController.SignInApi;
import com.provectus.pages.api.authController.SignUpApi;
import com.provectus.pages.api.jobController.CreateJobApi;
import com.provectus.pages.api.jobController.DeleteJobApi;
import com.provectus.pages.api.jobController.GetJobByIdApi;
import com.provectus.pages.api.jobController.ListAllJobsApi;
import com.provectus.pages.entities.Job;
import com.provectus.pages.entities.User;
import com.provectus.tests.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class JobControllerApiTests {

    @Test
    public void createNewJob() throws IOException {

        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        DataProvider dataProvider = new DataProvider();

        User user = dataProvider.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);

        CreateJobApi createJobApi = new CreateJobApi();

        Job job = dataProvider.fillJobData();
        Job newJob = createJobApi.createJob(token, job);

        Assert.assertEquals(newJob.getTitle(), job.getTitle(), "Job titles should be the same!");
    }

    @Test
    public void getJobById() throws IOException {

        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        DataProvider dataProvider = new DataProvider();

        User user = dataProvider.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);

        Job job = dataProvider.fillJobData();
        //---------- Create New Job ----------
        CreateJobApi createJobApi = new CreateJobApi();
        Job newJob = createJobApi.createJob(token, job);

        //---------- Get Created Job By Id ----------
        int id = newJob.getId();

        GetJobByIdApi getJobByIdApi = new GetJobByIdApi();
        Job jobById = getJobByIdApi.getJobById(token, id);

        Assert.assertEquals(jobById.getId(), id, "Job ids should be the same!");
    }

    @Test
    public void listAllJobs() throws IOException {

        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        ListAllJobsApi listAllJobsApi = new ListAllJobsApi();

        DataProvider dataProvider = new DataProvider();
        User user = dataProvider.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);
        List<Job> list = listAllJobsApi.listAllJobs(token);

        Assert.assertTrue(list.size() > 0, "Jobs list is empty");
    }

    @Test
    public void deleteJob() throws IOException {

        SignUpApi signUpApi = new SignUpApi();
        SignInApi signInApi= new SignInApi();
        DataProvider dataProvider = new DataProvider();

        User user = dataProvider.fillUserData();
        User newUser =  signUpApi.createNewUser(user);

        String token = signInApi.signIn(newUser);

        Job job = dataProvider.fillJobData();
        //---------- Create New Job ----------
        CreateJobApi createJobApi = new CreateJobApi();
        Job newJob = createJobApi.createJob(token, job);

        //---------- Get Created Job By Id ----------
        int id = newJob.getId();

        GetJobByIdApi getJobByIdApi = new GetJobByIdApi();
        Job jobById = getJobByIdApi.getJobById(token, id);

        //---------- Delete Created Job By Id -----------
        DeleteJobApi deleteJobApi = new DeleteJobApi();
        String message = deleteJobApi.deleteJob(token, jobById.getId());

        Assert.assertEquals(message, "Job is deleted");

    }

}
