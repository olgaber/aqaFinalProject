package com.provectus.tests;

import com.github.javafaker.Faker;
import com.provectus.pages.entities.Job;
import com.provectus.pages.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataProvider {

    public User fillUserData(){
        Faker faker = new Faker();

        User user = new User();
        user.setId(0);
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setPassword(faker.regexify("[a-z1-9]{8}"));
        String password = user.getPassword();
        user.setConfirmPassword(password);

        return user;
    }
    public Job fillJobData(){

        Job job1 = new Job();
        job1.setId(0);
        job1.setTitle("Data Engineer");
        job1.setDescription("At Lyft, our mission is to improve people’s lives with the world’s best transportation. " +
                "To do this, we start with our own community by creating an open, inclusive, and diverse organization.");
        job1.setPrice(1500);
        job1.setUser("test pass12345");
        job1.setNoOfComments(0);

        Job job2 = new Job();
        job2.setId(0);
        job2.setTitle("Python Developer");
        job2.setDescription("For this project, we are looking for a Python Developer to join our team.");
        job2.setPrice(3000);
        job2.setUser("test pass12345");
        job2.setNoOfComments(0);

        Job job3 = new Job();
        job3.setId(0);
        job3.setTitle("Full-stack engineer (PHP/Vue.js)");
        job3.setDescription("We’re looking for a skilled Full-Stack Developer who will share his/her expertise to " +
                "create the best game experience for our players around the world. As a software engineer in Sciplay " +
                "you will take a role in developing complex software systems that have successfully been delivered, " +
                "working along with business teams and management to collect requirements.");
        job3.setPrice(3000);
        job3.setUser("test pass12345");
        job3.setNoOfComments(0);

        List<Job> jobsList = new ArrayList<>();
        jobsList.add(job1);
        jobsList.add(job2);
        jobsList.add(job3);

        Random random = new Random();
        int index = random.nextInt(3);

        return jobsList.get(index);
    }

    public String getComment(){
        return "Will do this ASAP!";
    }

}
