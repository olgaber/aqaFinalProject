package com.provectus.pages;

import org.openqa.selenium.WebDriver;

public class DriverHolder {
    private static DriverHolder holder;
    private WebDriver driver;
    private DriverHolder(){};

    public static DriverHolder getHolder(){

        if (holder == null){
            holder = new DriverHolder();
        }
        return holder;
    };

    public static void setDriver(WebDriver driver){
        getHolder().driver = driver;
    }

    public static WebDriver getDriver(){
        return getHolder().driver;
    }
}
