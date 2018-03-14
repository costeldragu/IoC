package com.mdc;

import com.mdc.annotation.Dependecy;
import com.mdc.di.ApplicationContextFactory;
import com.mdc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Dependecy
    public UserService userService;

    public static void main(String... arg) throws IllegalAccessException, InstantiationException {
        new Main().start();
    }

    public void start() throws IllegalAccessException, InstantiationException {
        new ApplicationContextFactory("com.mdc");
    }


}
