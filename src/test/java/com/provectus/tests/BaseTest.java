package com.provectus.tests;

import com.codeborne.selenide.Configuration;
import com.provectus.tests.listeners.CustomExtentPageListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners(CustomExtentPageListener.class)
public class BaseTest {

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browserSize = "1800x1050";
        Configuration.baseUrl = "https://freelance.lsrv.in.ua";
    }

    static{
        System.setProperty("extent.reporter.html.start", "true");
        System.setProperty("extent.reporter.html.out", "target/extentReport/ExtentHtml.html");
    }

}
