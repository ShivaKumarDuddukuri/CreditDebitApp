package com.shiva.context.utils;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

public class ContextUtil {

    private static WebApplicationContext applicationContext;

    public static void setContext(WebApplicationContext appContext) {
        applicationContext = appContext;
    }

    public static <T> T getSpringBean(String beanId) {
        return (T) applicationContext.getBean(beanId);
    }

    public static <T> T getSpringBean(String beanId, Object... args) {
        return (T) applicationContext.getBean(beanId, args);
    }

    public static <T> T getSpringBean(Class<T> className) {
        return (T) applicationContext.getBean(className);
    }

    public static <T> T getSpringBeanNullIfNotExists(Class<T> className) {
        try {
            if (applicationContext != null) {
                return (T) applicationContext.getBean(className);
            } else {
                return null;
            }
        } catch (BeansException e) {
            return null;
        }
    }
}