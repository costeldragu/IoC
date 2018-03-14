package com.mdc;

import com.mdc.annotation.Component;
import com.mdc.annotation.Dependecy;
import com.mdc.di.ApplicationContextFactory;
import com.mdc.service.UserService;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Set;


public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Dependecy
    private UserService userService;


    public static void main(String... arg) {

        new Main().start();

    }

    public void start() {

        LOGGER.debug("{}");

        new ApplicationContextFactory("com.mdc");

//        Reflections reflections = new Reflections("com.mdc",new FieldAnnotationsScanner(),new TypeAnnotationsScanner(),new SubTypesScanner());
//
//        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Component.class);
//        Set<Field> ids = reflections.getFieldsAnnotatedWith(Dependecy.class);
//
//        LOGGER.debug("{}", annotated);
//        LOGGER.debug("{}", ids);
//
//        LOGGER.debug("debug:Program started");
//        LOGGER.info("info:Program started");
    }


}
