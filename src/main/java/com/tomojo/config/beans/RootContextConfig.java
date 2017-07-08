package com.tomojo.config.beans;

import com.tomojo.config.jdbc.JDBCDaoSpringConfig;
import com.tomojo.dao.TransactionDao;
import com.tomojo.dao.TransactionDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Configuration
@Import(value = {JDBCDaoSpringConfig.class})
@ComponentScan(basePackages = {
        "com.tomojo"}, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
        "com.tomojo.controllers", "com.tomojo.config.web"}))

public class RootContextConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public TransactionDao getContactDAO() {
        return new TransactionDaoImpl(dataSource);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        List<Resource> resources = new LinkedList<Resource>();
        resources.add(new ClassPathResource("db.properties"));
        configurer.setLocations(resources.toArray(new Resource[0]));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        Environment environment = new StandardEnvironment();
        configurer.setEnvironment(environment);
        return configurer;
    }
}
