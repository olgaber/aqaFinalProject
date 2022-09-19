package com.provectus.tests;

import com.provectus.tests.listeners.CustomExtentPageListener;
import org.testng.annotations.Listeners;

@Listeners(CustomExtentPageListener.class)
public class BaseTest {

    static{
        System.setProperty("extent.reporter.html.start", "true");
        System.setProperty("extent.reporter.html.out", "target/extentReport/ExtentHtml.html");
    }

}
