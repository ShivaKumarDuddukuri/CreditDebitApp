
package com.shiva.context.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContextEvent.getServletContext());
        ContextUtil.setContext(applicationContext);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
