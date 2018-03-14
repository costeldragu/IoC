package com.mdc.di;


import com.mdc.annotation.Component;
import com.mdc.annotation.Dependecy;
import com.mdc.service.UserService;
import com.mdc.service.impl.UserServiceImpl;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApplicationContextFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextFactory.class);

    private static Map<Class, Class> dependencyInjectionMap = new HashMap<>();

    public ApplicationContextFactory(String mainPackage) throws IllegalAccessException, InstantiationException {
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

        Set<Field> fields = reflections.getFieldsAnnotatedWith(Dependecy.class);
        for (Field field:fields) {
            Class<?> type =  field.getType();
            LOGGER.info("field type: {}", field.getType());
            LOGGER.info("getDeclaringClass: {}", field.getDeclaringClass().newInstance());
            LOGGER.info("dependencyInjectionMap: {}", dependencyInjectionMap.get(type));
            field.set(field.getDeclaringClass().newInstance(),dependencyInjectionMap.get(type).newInstance());
        }
    }

}
