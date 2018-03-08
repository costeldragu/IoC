package com.mdc;

import com.mdc.annotation.Dependecy;
import com.mdc.service.UserService;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Dependecy
    private UserService userService;


    public static void main(String... arg) {

        new Main().start();

    }

    public void start() {

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forJavaClassPath())
        );

        Set<Class<? extends Object>> subTypes = reflections.getSubTypesOf(Object.class);

        LOGGER.debug("{}",subTypes);

        LOGGER.debug("debug:Program started");
        LOGGER.info("info:Program started");
    }


}
