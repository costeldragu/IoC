package com.mdc.di;

import com.mdc.Main;
import com.mdc.annotation.Component;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApplicationContextFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextFactory.class);

    private static Map<Class, Class> dependencyInjectionMap = new HashMap<>();

    public ApplicationContextFactory(String mainPackage) {
        Reflections reflections = new Reflections(mainPackage, new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());

        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> implementationClass : types) {
            if (implementationClass.getInterfaces().length > 0) {
                for (Class iface : implementationClass.getInterfaces()) {
                    dependencyInjectionMap.put(iface, implementationClass);
                }
            } else {
                dependencyInjectionMap.put(implementationClass, implementationClass);
            }
        }

        dependencyInjectionMap.forEach((key, value) -> {
            LOGGER.info("Key : {} , Value: {}", key, value);
        });
    }

}
