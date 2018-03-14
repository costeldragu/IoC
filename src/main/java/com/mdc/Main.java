package com.mdc;

import com.mdc.annotation.Dependecy;
import com.mdc.di.ApplicationContextFactory;
import com.mdc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Dependecy
    private UserService userService;


    public static void main(String... arg) {
        new Main().start();
    }

    public void start() {
        new ApplicationContextFactory("com.mdc");
    }


}
